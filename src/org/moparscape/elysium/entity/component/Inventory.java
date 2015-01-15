package org.moparscape.elysium.entity.component;

import io.netty.channel.ChannelFuture;
import org.moparscape.elysium.entity.Entity;
import org.moparscape.elysium.entity.InvItem;
import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.PacketBuilder;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.util.ItemByValueComparator;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;
import org.moparscape.elysium.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Inventory extends AbstractComponent {

    private static final World world = World.getInstance();

    private final int MAX_INVENTORY_ITEMS = 30;
    private final List<InvItem> items = new ArrayList<InvItem>();
    private final int maxItems;
    private final Player owner;
    private final AtomicInteger reserved = new AtomicInteger(0);

    public Inventory(Player owner) {
        this.owner = owner;
        this.maxItems = MAX_INVENTORY_ITEMS;
    }

    public Inventory(Player owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
    }

    public int add(InvItem item) {
        if (item.getAmount() < 1) {
            return -1;
        }

        if (item.getDef().isStackable()) {
            synchronized (items) {
                int index = items.indexOf(item);
                if (index >= 0) {
                    InvItem existing = items.get(index);

                    int newAmount = item.getAmount() + existing.getAmount();
                    existing.setAmount(newAmount);
                    updateInventoryItem(index, existing);
                    return index;
                }
            }
        } else {
            // If it isn't a stackable item, but it has an amount that is
            // greater than one, change the amount to one
            if (item.getAmount() > 1) {
                item.setAmount(1);
            }
        }

        if (isFull()) {
            Player p = owner;
            Point loc = p.getLocation();
            Packets.sendMessage(p, "Your inventory is full, the " +
                    item.getDef().getName() + " drops to the ground!");

            Region itemRegion = Region.getRegion(loc);
            itemRegion.addItem(new Item(item.getItemId(), item.getAmount(), loc, p));
            return -1;
        }

        // At this stage we know that the inventory can hold the new item,
        // so let's add it
        synchronized (items) {
            items.add(item);
            sendInventory();
            return items.size() - 1;
        }
    }

    public final boolean canHold(InvItem item) {
        synchronized (items) {
            return (maxItems - items.size()) >= getRequiredSlots(item);
        }

    }

    public final boolean contains(InvItem item) {
        synchronized (items) {
            return items.contains(item);
        }
    }

    public final int countById(int id) {
        synchronized (items) {
            int count = 0;
            for (InvItem item : items) {
                if (item.getItemId() == id) {
                    count += item.getAmount();
                }
            }
            return count;
        }
    }

    public void dropOnDeath(Entity killer, boolean skulled, boolean protectItem) {
        int keep = skulled ? 0 : 3;

        // If they have protect item enabled, increase the number of items
        // to be protected by one
        if (protectItem) {
            keep++;
        }

        Player pkiller = null;
        if (killer instanceof Player) {
            pkiller = (Player) killer;
        }

        Point victimLoc = owner.getLocation();
        Region region = Region.getRegion(victimLoc);

        synchronized (items) {
            InvItem item = null;

            if (keep == 0) {
                // We aren't keeping any, so don't worry about sorting them
                Iterator<InvItem> it = items.iterator();
                while (it.hasNext()) {
                    item = it.next();

                    region.addItem(new Item(item.getItemId(), item.getAmount(), victimLoc, pkiller));
                    it.remove();
                }
            } else {
                // Sort the list so that the most expensive are first
                Collections.sort(items, new ItemByValueComparator());

                int saved = 0;
                Iterator<InvItem> it = items.iterator();
                while (it.hasNext()) {
                    item = it.next();
                    if (saved++ < keep) {
                        continue;
                    }

                    region.addItem(new Item(item.getItemId(), item.getAmount(), victimLoc, pkiller));
                    it.remove();
                }
            }
        }
    }

    public final InvItem get(int index) {
        synchronized (items) {
            return items.get(index);
        }
    }

    public final InvItem getById(int itemId) {
        synchronized (items) {
            for (InvItem item : items) {
                if (item.getItemId() == itemId) {
                    return item;
                }
            }
            return null;
        }
    }

    public int getFreedSlots(InvItem item) {
        synchronized (items) {
            return item.getDef().isStackable() &&
                    countById(item.getItemId()) > item.getAmount() ? 0 : 1;
        }
    }

    public final int getFreedSlots(List<InvItem> items) {
        synchronized (items) {
            int count = 0;
            for (InvItem item : items) {
                count += getFreedSlots(item);
            }
            return count;
        }
    }

    public int getRequiredSlots(InvItem item) {
        synchronized (items) {
            return item.getDef().isStackable() && items.contains(item) ? 0 : 1;
        }
    }

    public final int getRequiredSlots(List<InvItem> items) {
        synchronized (items) {
            int requiredSlots = 0;
            for (InvItem item : items) {
                requiredSlots += getRequiredSlots(item);
            }
            return requiredSlots;
        }
    }

    public final int indexOf(InvItem item) {
        synchronized (items) {
            return items.indexOf(item);
        }
    }

    public final boolean isEmpty() {
        synchronized (items) {
            return items.isEmpty();
        }
    }

    public final boolean isFull() {
        synchronized (items) {
            return items.size() >= maxItems;
        }
    }

    public int remove(InvItem item) {
        return remove(item.getItemId(), item.getAmount());
    }

    public int remove(int itemId, int amount) {
        InvItem target = null;
        int index = 0;
        synchronized (items) {
            Iterator<InvItem> it = items.iterator();
            while (it.hasNext()) {
                target = it.next();

                if (target.getItemId() == itemId) {
                    if (target.getDef().isStackable() && amount < target.getAmount()) {
                        int newAmount = target.getAmount() - amount;
                        target.setAmount(newAmount);
                        return index;
                    } else {
                        it.remove();
                        return index;
                    }
                }

                index++;
            }
        }
        return -1;
    }

    public InvItem remove(int index) {
        synchronized (items) {
            if (index >= items.size()) {
                return null;
            }

            InvItem removed = items.remove(index);
            sendRemoveItem(index);
            return removed;
        }
    }

    public final ChannelFuture sendInventory() {
        Player p = owner;

        synchronized (items) {
            int size = items.size();
            int packetSize = (size * 6) + 1;

            PacketBuilder pb = new PacketBuilder(packetSize);
            pb.setId(114);
            pb.writeByte(size);
            for (InvItem item : items) {
                pb.writeShort(item.getItemId());
                if (item.getDef().isStackable()) {
                    pb.writeInt(item.getAmount());
                }
            }

            return p.getSession().write(pb.toPacket());
        }
    }

    public final ChannelFuture sendRemoveItem(int slot) {
        Player p = owner;

        synchronized (items) {
            PacketBuilder pb = new PacketBuilder(1);
            pb.setId(191);
            pb.writeByte(slot);

            return p.getSession().write(pb.toPacket());
        }
    }

    public final int size() {
        synchronized (items) {
            return items.size();
        }
    }

    public String toString() {
        Credentials creds = owner.getCredentials();
        StringBuilder sb = new StringBuilder(1000);
        sb.append("Inventory contents for ");
        sb.append(creds.getUsername()).append("\n");

        synchronized (items) {
            for (InvItem item : items) {
                sb.append("\t").append(item).append("\n");
            }
            sb.append("INVENTORY END");
            return sb.toString();
        }
    }

    public final ChannelFuture updateInventoryItem(int slot, InvItem item) {
        Player p = owner;

        synchronized (items) {
            PacketBuilder pb = new PacketBuilder(7);
            pb.setId(228);
            pb.writeByte(slot);
            pb.writeShort(item.getItemId() + (item.isWielded() ? 32768 : 0));
            if (item.getDef().isStackable()) {
                pb.writeInt(item.getAmount());
            }

            return p.getSession().write(pb.toPacket());
        }
    }
}
