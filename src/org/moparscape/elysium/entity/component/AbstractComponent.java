package org.moparscape.elysium.entity.component;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public abstract class AbstractComponent implements Component {

    private static final List<Class<? extends Component>> emptyDependencies = Collections.emptyList();

    private final List<Class<? extends Component>> dependencies;

    protected AbstractComponent(Class<? extends Component>... dependencies) {
        // If no dependencies are specified then we can recycle the empty list
        // Otherwise, create a List of dependencies
        if (dependencies.length == 0) {
            this.dependencies = emptyDependencies;
        } else {
            List<Class<? extends Component>> deps = new LinkedList<Class<? extends Component>>();
            deps.addAll(Arrays.asList(dependencies));
            this.dependencies = Collections.unmodifiableList(deps);
        }
    }

    /**
     * Returns a List of other Component classes that this component
     * depends on for its functionality.
     *
     * @return The component's dependencies
     */
    public List<Class<? extends Component>> getDependencies() {
        return dependencies;
    }

    /**
     * An empty implementation of resolveDependencies so that components with no dependencies
     * are not forced to implement an extra method.
     * <p/>
     * This method MUST be overridden by any Component that has one or more dependencies.
     *
     * @param components The map of all components associated with the entity being initialised
     */
    public void resolveDependencies(Map<Class<? extends Component>, Component> components) {
        // Empty placeholder.
    }
}
