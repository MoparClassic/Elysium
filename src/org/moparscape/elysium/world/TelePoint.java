package org.moparscape.elysium.world;

public final class TelePoint extends Point {

    private final String command;

    public TelePoint(int x, int y, String command) {
        super(x, y);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public int hashCode() {
        int hash = command.hashCode() << 16;
        hash |= (short) (x << 8 | (byte) y);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof TelePoint)) {
            return false;
        }

        TelePoint tp = (TelePoint) o;
        return super.x == tp.x && super.y == tp.y && this.command.equals(tp.command);
    }
}