
package be.ugent.mmlab.rml.vocabularies;

/**
 * RML Processor
 *
 * @author andimou
 */
public class CoVocabulary {
    public static String CO_NAMESPACE = "http://combust.iminds.be/co#";

    public enum COTerm implements Term{

        // CO CLASSES
        VALIDATION_CLASS("Validation"),
        COMPLETION_CLASS("Completion"), 
        
        REQUIRES("requires");
        
        private String displayName;

        private COTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

}
