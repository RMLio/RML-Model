package be.ugent.mmlab.rml.vocabularies;

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
    

    public static enum HydraTerm implements Term{

        // Hydra CLASSES
        API_DOCUMENTATION_CLASS("APIDocumentation"),
        IRITEMPLATE_CLASS("IriTemplate"),
        // Hydra PROPERTIES
        TEMPLATE("template");
        
        private final String displayName;

        private HydraTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
    
}
