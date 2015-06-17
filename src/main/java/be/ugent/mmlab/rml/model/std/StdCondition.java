package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.condition.model.Condition;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * RML - Model
 *
 * @author andimou
 */
public class StdCondition {
    
    // Log
    private static final Logger log = LogManager.getLogger(StdCondition.class);
    
    protected String condition;
    protected String value;
    protected Set<Condition> nestedConditions ;
    
    /**
     *
     * @param condition
     */
    protected void setCondition(String condition) {
        if (condition == null) {
            log.error(
                    "A condition must "
                    + "have a condition value.");
        }
        this.condition = condition;
    }
    
    /**
     *
     * @param nestedConditions
     */
    protected void setNestedConditions(Set<Condition> nestedConditions) {
        this.nestedConditions = nestedConditions;
    }
    
    /**
     *
     * @return
     */
    public Set<Condition> getNestedConditions() {
        return nestedConditions;
    }
    
    /**
     *
     * @return
     */
    public String getCondition() {
        return condition;
    }
    
    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }    
}
