package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.condition.model.Condition;
import be.ugent.mmlab.rml.condition.model.SplitCondition;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * RML - Model
 *
 * @author andimou
 */
public class StdSplitCondition extends StdCondition implements SplitCondition {
    
    // Log
    private static final Logger log = LogManager.getLogger(StdSplitCondition.class);
    
    /**
     *
     * @param condition
     */
    public StdSplitCondition(String condition) {
        setCondition(condition);
    }
    
    /**
     *
     * @param condition
     * @param nestedConditions
     */
    public StdSplitCondition(String condition, Set<Condition> nestedConditions) {
        setCondition(condition);
        setNestedConditions(nestedConditions);
    }

}
