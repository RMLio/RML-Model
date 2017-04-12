package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 * 
 * RML - Model : cRML Vocabulary
 * 
 * namespace:crml namespace: http://semweb.mmlab.be/ns/crml#
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
        BOOLEAN_CONDITION_CLASS("EqualCondition"),
        PROCESS_CONDITION_CLASS("ProcessCondition"),
        SPLIT_CONDITION_CLASS("SplitCondition"),
        // cRML RPROPERTIES
        BINDING_CONDITION("binding"),
        BOOLEAN_CONDITION("equalCondition"),
        NEGATION_CONDITION("negationOf"),
        PROCESS_CONDITION("processCondition"),
        SPLIT_CONDITION("splitCondition"),
        FALLBACK("fallbackMap"),
        CONDITION("condition"),
        EXPRESSION("expression"),
        VALUE("value"),
        VARIABLE("variable"),
        REPLACE("replace"),
        
        // cRML METRICS
        JACCARD_CLASS("JaccardMetric"),
        LEVENSTEIN_CLASS("LevensteinMetric"),
        METRIC("metric");
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
