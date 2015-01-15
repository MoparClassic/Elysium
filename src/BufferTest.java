import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.PacketBuilder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BufferTest {

    public static void dump(ByteBuf buffer) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append(buffer.getUnsignedByte(0));
        for (int i = 1; i < buffer.readableBytes(); i++) {
            sb.append(", ").append(buffer.getUnsignedByte(i));
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        PacketBuilder pb = new PacketBuilder(8);
        pb.setId(100);
        dump(pb.toPacket());

        pb = new PacketBuilder(8);
        pb.setId(99);
        pb.writeByte(255);
        pb.writeShort(0xAAAA);
        dump(pb.toPacket());

        pb = new PacketBuilder(170);
        pb.setId(88);
        for (int i = 0; i < 20; i++) {
            pb.writeLong(0xFFFFFFFFFFFFFFFFL);
        }
        pb.writeLong(0xAAAAAAAAAAAAAAAAL);
        dump(pb.toPacket());
    }
}
