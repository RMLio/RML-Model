package be.ugent.mmlab.rml.vocabulary;

/**
 * *************************************************************************
 * 
 * RML - Model : Hydra Vocabulary
 * 
 * namespace: hydra IRI: http://www.w3.org/ns/hydra/core#
 *
 * @author andimou
 * 
 * *************************************************************************
 */
public class HydraVocabulary {
    public static String HYDRA_NAMESPACE = "http://www.w3.org/ns/hydra/core#";
    
    public enum HydraTerm implements Term{

        // RML CLASSES
        API_DOCUMENTATION_CLASS("APIDocumentation"),
        // RPROPERTIES
        TEMPLATE("template");
        
        private String displayName;

        private HydraTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
    
}
