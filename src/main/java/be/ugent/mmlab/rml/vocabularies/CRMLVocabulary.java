package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 * 
 * RML - Model : cRML Vocabulary
 * 
 * namespace:crml namespace: http://semweb.mmlab.be/ns/rml/condition#
 *
 * @author andimou
  * 
 * *************************************************************************
 */
public class CRMLVocabulary {
    public static String CRML_NAMESPACE = "http://semweb.mmlab.be/ns/crml#";
    
    public enum cRMLTerm implements Term{

        // cRML CLASSES
        BINDING_CONDITION_CLASS("BindingCondition"),
        BOOLEAN_CONDITION_CLASS("BooleanCondition"),
        PROCESS_CONDITION_CLASS("ProcessCondition"),
        SPLIT_CONDITION_CLASS("SplitCondition"),
        // cRML RPROPERTIES
        BINDING_CONDITION("binding"),
        BOOLEAN_CONDITION("booleanCondition"),
        PROCESS_CONDITION("processCondition"),
        SPLIT_CONDITION("splitCondition"),
        CONDITION("condition"),
        EXPRESSION("expression"),
        VALUE("value"),
        VARIABLE("variable"),
        REPLACE("replace");
        
        private String displayName;

        private cRMLTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
}
