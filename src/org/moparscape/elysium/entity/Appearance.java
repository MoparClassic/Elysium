package org.moparscape.elysium.entity;

import org.moparscape.elysium.util.DataConversions;
import org.moparscape.elysium.util.Formulae;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Appearance {

    private static final int DEFAULT_BODY = 2;
    private static final byte DEFAULT_HAIR_COLOUR = 2;
    private static final int DEFAULT_HEAD = 1;
    private static final byte DEFAULT_SKIN_COLOUR = 0;
    private static final byte DEFAULT_TOP_COLOUR = 8;
    private static final byte DEFAULT_TROUSER_COLOUR = 14;
    private final int body;
    private final byte hairColour;
    private final int head;
    private final byte skinColour;
    private final byte topColour;
    private final byte trouserColour;

    public Appearance() {
        this(DEFAULT_HAIR_COLOUR, DEFAULT_TOP_COLOUR, DEFAULT_TROUSER_COLOUR,
                DEFAULT_SKIN_COLOUR, DEFAULT_HEAD, DEFAULT_BODY);
    }

    public Appearance(int hairColour, int topColour, int trouserColour,
                      int skinColour, int head, int body) {
        this.hairColour = (byte) hairColour;
        this.topColour = (byte) topColour;
        this.trouserColour = (byte) trouserColour;
        this.skinColour = (byte) skinColour;
        this.head = head;
        this.body = body;
    }

    public byte getHairColour() {
        return hairColour;
    }

    public byte getSkinColour() {
        return skinColour;
    }

    public int getSprite(int pos) {
        switch (pos) {
            case 0:
                return head;
            case 1:
                return body;
            case 2:
                return 3;
            default:
                return 0;
        }
    }

    public int[] getSprites() {
        return new int[]{head, body, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public byte getTopColour() {
        return topColour;
    }

    public byte getTrouserColour() {
        return trouserColour;
    }

    public boolean isValid() {
        if (!DataConversions.inArray(Formulae.headSprites, head) || !DataConversions.inArray(Formulae.bodySprites, body)) {
            return false;
        }
        if (hairColour < 0 || topColour < 0 || trouserColour < 0 || skinColour < 0) {
            return false;
        }
        if (hairColour > 9 || topColour > 14 || trouserColour > 14 || skinColour > 4) {
            return false;
        }
        return true;
    }
}
