package org.moparscape.elysium.entity.component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Health extends AbstractComponent {

    private Skills skills;

    public void resolveDependencies(Map<Class<? extends Component>, Component> components) {
        skills = Skills.class.cast(components.get(Skills.class));
    }
}
