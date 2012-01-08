package org.moparscape.elysium.entity.component;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface Component {

    List<Class<? extends Component>> getDependencies();

    void resolveDependencies(Map<Class<? extends Component>, Component> components);
}
