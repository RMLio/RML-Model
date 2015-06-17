package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.condition.model.Condition;
import be.ugent.mmlab.rml.condition.model.BooleanCondition;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * RML - Model
 *
 * @author andimou
 */
public class StdBooleanCondition extends StdCondition implements BooleanCondition {
    
    // Log
    private static final Logger log = LogManager.getLogger(StdBooleanCondition.class);
    
    /**
     *
     * @param condition
     * @param value
     * @throws Exception
     */
    public StdBooleanCondition(String condition, String value) throws Exception {
        setCondition(condition);
        setValue(value);
    }
    
    /**
     *
     * @param condition
     * @param value
     * @param nestedConditions
     * @throws Exception
     */
    public StdBooleanCondition(String condition, String value, Set<Condition> nestedConditions) 
            throws Exception {
        setCondition(condition);
        setValue(value);
        setNestedConditions(nestedConditions);
    }
    
    private void setValue(String value) throws Exception {
        if (value == null) {
            throw new Exception(
                    Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + "An equal condition must "
                    + "have a value.");
        }
        this.value = value;
    }
    
}
