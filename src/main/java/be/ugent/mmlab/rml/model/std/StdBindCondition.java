package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.condition.model.BindCondition;
import be.ugent.mmlab.rml.condition.model.Condition;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * RML - Model
 *
 * @author andimou
 */
public class StdBindCondition extends StdCondition implements BindCondition{
    private String reference;
    
    // Log
    private static final Logger log = LogManager.getLogger(StdBindCondition.class);
    
    /**
     *
     * @param condition
     * @param value
     * @throws Exception
     */
    public StdBindCondition(String condition, String value) {
        setCondition(condition);
        setValue(value);
    }
    
    public StdBindCondition(
            String condition, String value, String reference, Set<Condition> nestedConditions) 
            throws Exception {
        //setCondition(condition);
        setValue(value);
        setReference(reference);
        setNestedConditions(nestedConditions);
    }
    
    private void setValue(String value) {
        if (value == null) {
            log.error(
                    Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + "A bind condition must "
                    + "have a value.");
        }
        this.value = value;
    }
    
    private void setReference(String reference) {
        if (reference == null) {
            log.error(
                    Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                    + "A bind condition must "
                    + "have a value.");
        }
        this.reference = reference;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getReference() {
        return this.reference;
    }    
    
}
