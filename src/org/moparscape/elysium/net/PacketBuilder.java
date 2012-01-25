package org.moparscape.elysium.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PacketBuilder {

    private final ChannelBuffer buffer;

    private int id;

    /**
     * Allocates a dynamic buffer with an initial capacity of 256.
     */
    public PacketBuilder() {
        this.buffer = ChannelBuffers.dynamicBuffer();
    }

    /**
     * Allocates a dynamic buffer with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity
     */
    public PacketBuilder(int initialCapacity) {
        this(initialCapacity, false);
    }

    /**
     * Allocates a buffer with the specified capacity.
     * <p/>
     * If fixedCapacity is true then the underlying buffer's capacity will
     * be static.
     * If fixedCapacity is false then the underlying buffer's capacity will
     * be dynamic, and capable of growing to accommodate any number of bytes.
     *
     * @param capacity      The capacity of the underlying buffer
     * @param fixedCapacity True if the underlying buffer's capacity should be
     *                      fixed, or false if it should be dynamic
     */
    public PacketBuilder(int capacity, boolean fixedCapacity) {
        if (fixedCapacity) {
            this.buffer = ChannelBuffers.directBuffer(capacity);
        } else {
            this.buffer = ChannelBuffers.dynamicBuffer(capacity);
        }
    }

    public PacketBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PacketBuilder writeByte(int value) {
        buffer.writeByte(value);
        return this;
    }

    public PacketBuilder writeShort(int value) {
        buffer.writeShort(value);
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

    public PacketBuilder writeBytes(byte[] src) {
        buffer.writeBytes(src);
        return this;
    }

    public PacketBuilder writeBytes(byte[] src, int startIndex, int length) {
        buffer.writeBytes(src, startIndex, length);
        return this;
    }

    public ChannelBuffer toPacket() {
        int dataLen = buffer.readableBytes(); // Length of payload
        int packetLen = dataLen + 1;          // Length of opcode followed by payload

        //System.out.printf("dataLen=%d; packetLen=%d;\n", dataLen, packetLen);

        ChannelBuffer header = ChannelBuffers.buffer(3);
        if (dataLen >= 160) {
            header.writeByte(160 + (packetLen / 256));
            header.writeByte(packetLen & 0xff);
            header.writeByte(id);
            return ChannelBuffers.wrappedBuffer(header, buffer);
        } else {
            header.writeByte(packetLen);                   // Length byte
            if (dataLen > 0) {
                header.writeByte(buffer.getByte(dataLen - 1)); // Last byte of payload
                header.writeByte(id);
                return ChannelBuffers.wrappedBuffer(header, buffer.slice(0, dataLen - 1));
            } else {
                header.writeByte(id);                          // Opcode
                return header;
            }
        }
    }
}
