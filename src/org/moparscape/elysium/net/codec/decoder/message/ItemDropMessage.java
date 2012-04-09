package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemDropMessage extends AbstractMessage {

    private final int index;

    public ItemDropMessage(int itemIndex) {
        this.index = itemIndex;
    }

    public int getIndex() {
        return index;
    }
}
