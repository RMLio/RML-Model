
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
        VERIFICATION_CLASS("Verification"),
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
