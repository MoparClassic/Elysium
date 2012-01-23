import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.moparscape.elysium.net.Bitpacker;
import org.moparscape.elysium.net.PacketBuilder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BufferTest {

    public static void main(String[] args) {
        ChannelBuffer buffer = ChannelBuffers.buffer(30);
        buffer.writeLong(0xCCCCDDDDEEEEFFFFL);
        System.out.println(buffer.readableBytes());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.getUnsignedByte(buffer.writerIndex() - 1));
        dump(buffer);

        System.out.println();

        PacketBuilder pb = new PacketBuilder(30, true);
        pb.setId(22);
        for (int i = 1; i <= 20; i++) {
            pb.writeByte(i);
        }
        ChannelBuffer p1 = pb.toPacket();
        dump(p1);

        pb = new PacketBuilder(256, true);
        pb.setId(99);
        for (int i = 1; i <= 160; i++) {
            pb.writeByte(i);
        }
        ChannelBuffer p2 = pb.toPacket();
        dump(p2);

        System.out.println();

        Bitpacker bp = new Bitpacker();
        bp.addBits(255, 8);
        bp.addBits(1, 1);
    }

    public static void dump(ChannelBuffer buffer) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append(buffer.getUnsignedByte(0));
        for (int i = 1; i < buffer.readableBytes(); i++) {
            sb.append(", ").append(buffer.getUnsignedByte(i));
        }
        System.out.println(sb);
    }
}
