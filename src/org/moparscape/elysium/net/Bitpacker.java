package org.moparscape.elysium.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Bitpacker {

    private static final int DEFAULT_CAPACITY = 64;

    /**
     * Bitmasks for <code>addBits()</code>
     */
    private static int bitmasks[] = {
            0, 0x1, 0x3, 0x7,
            0xf, 0x1f, 0x3f, 0x7f,
            0xff, 0x1ff, 0x3ff, 0x7ff,
            0xfff, 0x1fff, 0x3fff, 0x7fff,
            0xffff, 0x1ffff, 0x3ffff, 0x7ffff,
            0xfffff, 0x1fffff, 0x3fffff, 0x7fffff,
            0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff,
            0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff,
            -1
    };

    private int bitPosition = 0;

    private byte[] payload;

    private int id = -1;

    public Bitpacker() {
        this(DEFAULT_CAPACITY);
    }

    public Bitpacker(int initialCapacity) {
        this.payload = new byte[initialCapacity];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity >= payload.length) {
            int newCapacity = (payload.length + 1) * 2;
            byte[] newPayload = new byte[newCapacity];
            System.arraycopy(payload, 0, newPayload, 0, payload.length);

            payload = newPayload;
        }
    }

    public Bitpacker setId(int id) {
        this.id = id;
        return this;
    }

    public Bitpacker addBits(int value, int numBits) {
        int bytePos = bitPosition >> 3;
        int bitOffset = 8 - (bitPosition & 7);
        bitPosition += numBits;
        int curLength = (bitPosition + 7) / 8;
        ensureCapacity(curLength);
        for (; numBits > bitOffset; bitOffset = 8) {
            payload[bytePos] &= ~bitmasks[bitOffset];     // mask out the desired area
            payload[bytePos++] |= (value >> (numBits - bitOffset)) & bitmasks[bitOffset];

            numBits -= bitOffset;
        }
        if (numBits == bitOffset) {
            payload[bytePos] &= ~bitmasks[bitOffset];
            payload[bytePos] |= value & bitmasks[bitOffset];
        } else {
            payload[bytePos] &= ~(bitmasks[numBits] << (bitOffset - numBits));
            payload[bytePos] |= (value & bitmasks[numBits]) << (bitOffset - numBits);
        }
        return this;
    }

    public ByteBuf toPacket() {
        int dataLen = (bitPosition + 7) / 8;
        int packetLen = dataLen + 1;

        ByteBuf header = Unpooled.buffer(3);
        if (dataLen >= 160) {
            header.writeByte(160 + (packetLen / 256));
            header.writeByte(packetLen & 0xff);
            header.writeByte(id);
            return Unpooled.wrappedBuffer(header, Unpooled.wrappedBuffer(payload, 0, dataLen));
        } else {
            header.writeByte(packetLen);            // Length byte
            if (dataLen > 0) {
                header.writeByte(payload[dataLen - 1]); // Last byte of payload
                header.writeByte(id);
                return Unpooled.wrappedBuffer(header, Unpooled.wrappedBuffer(payload, 0, dataLen - 1));
            } else {
                header.writeByte(id);                   // Opcode
                return header;
            }
        }
    }
}
