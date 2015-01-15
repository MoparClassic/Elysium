package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AppearancesMessage extends AbstractMessage {

    private final int[] appearanceIds;
    private final int[] indicies;

    public AppearancesMessage(int[] indicies, int[] appearanceIds) {
        this.indicies = indicies;
        this.appearanceIds = appearanceIds;
    }

    public int[] getAppearanceIds() {
        return appearanceIds;
    }

    public int[] getIndicies() {
        return indicies;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < indicies.length; i++) {
            result += String.format("PID: %d -- AppID: %d\n", indicies[i], appearanceIds[i]);
        }
        return result;
    }
}
