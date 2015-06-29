/***************************************************************************
 *
 * RML Model : Join Condition
 *
 * A join condition is a resource that has 
 * exactly two properties: 
 * 	- rr:child, whose value is known as the join condition child reference
 * 	- rr:parent, whose value is known as the join condition parent reference
 * 
 ****************************************************************************/
package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.JoinCondition;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StdJoinCondition implements JoinCondition {

    // Log
    private static final Logger log = LogManager.getLogger(StdJoinCondition.class);
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
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + "A join condition must "
                    + "have a parent reference.");
        }
        this.parent = parent;
    }

    @Override
    public void setChild(String child) {
        if (child == null) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + "A join condition must "
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
