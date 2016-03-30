package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.JoinCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *************************************************************************
 *
 * RML - Model : Join Condition Implementation.
 *
 * A join condition is a resource that has 
 * exactly two properties: 
 * 	- rr:child, whose value is known as the join condition child reference
 * 	- rr:parent, whose value is known as the join condition parent reference
 * 
 ***************************************************************************
 */
public class StdJoinCondition implements JoinCondition {

    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(StdJoinCondition.class.getSimpleName());
    private String child;
    private String parent;

    /**
     *
     * @param child
     * @param parent
     */
    public StdJoinCondition(String child, String parent) {
        setChild(child);
        setParent(parent);
    }

    @Override
    public void setParent(String parent) {
        if (parent == null) {
            log.error("A join condition must "
                    + "have a parent reference.");
        }
        this.parent = parent;
    }

    @Override
    public void setChild(String child) {
        if (child == null) {
            log.error("A join condition must "
                    + "have a child reference.");
        }
        this.child = child;

    }

    @Override
    public String getChild() {
        return child;
    }

    @Override
    public String getParent() {
        return parent;
    }
}
