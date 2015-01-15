package org.moparscape.elysium.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PacketBuilder {

    private final ByteBuf buffer;

    private int id;

    /**
     * Allocates a dynamic buffer with an initial capacity of 256.
     */
    public PacketBuilder() {
        this.buffer = Unpooled.buffer();
    }

    /**
     * Allocates a dynamic buffer with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity
     */
    public PacketBuilder(int initialCapacity) {
        this.buffer = Unpooled.buffer(initialCapacity);
    }

    public PacketBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ByteBuf toPacket() {
        int dataLen = buffer.readableBytes(); // Length of payload
        int packetLen = dataLen + 1;          // Length of opcode followed by payload

        //System.out.printf("dataLen=%d; packetLen=%d;\n", dataLen, packetLen);

        ByteBuf header = Unpooled.buffer(3);
        if (dataLen >= 160) {
            header.writeByte(160 + (packetLen / 256));
            header.writeByte(packetLen & 0xff);
            header.writeByte(id);
            return Unpooled.wrappedBuffer(header, buffer);
        } else {
            header.writeByte(packetLen);                   // Length byte
            if (dataLen > 0) {
                header.writeByte(buffer.getByte(dataLen - 1)); // Last byte of payload
                header.writeByte(id);
                return Unpooled.wrappedBuffer(header, buffer.slice(0, dataLen - 1));
            } else {
                header.writeByte(id);                          // Opcode
                return header;
            }
        }
    }

    public PacketBuilder writeByte(int value) {
        buffer.writeByte(value);
        return this;
    }

    public PacketBuilder writeBytes(byte[] src, int startIndex, int length) {
        buffer.writeBytes(src, startIndex, length);
        return this;
    }

    public PacketBuilder writeBytes(byte[] src) {
        buffer.writeBytes(src);
        return this;
    }

    public PacketBuilder writeInt(int value) {
        buffer.writeInt(value);
        return this;
    }

    public PacketBuilder writeLong(long value) {
        buffer.writeLong(value);
        return this;
    }

    public PacketBuilder writeShort(int value) {
        buffer.writeShort(value);
        return this;
    }
}
