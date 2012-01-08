package org.moparscape.elysium.net.codec;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumPipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() {
        return new StaticChannelPipeline(
                new ElysiumDecoder(),
                new ElysiumEncoder(),
                new ElysiumConnectionHandler()
        );
    }
}
