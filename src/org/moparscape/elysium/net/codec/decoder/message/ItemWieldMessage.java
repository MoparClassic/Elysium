package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemWieldMessage extends AbstractMessage {

    private final int itemIndex;

    public ItemWieldMessage(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public int getItemIndex() {
        return itemIndex;
    }
}
