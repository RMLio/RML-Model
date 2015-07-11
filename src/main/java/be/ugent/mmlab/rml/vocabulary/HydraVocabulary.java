package be.ugent.mmlab.rml.vocabulary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    // Log
    private static final Logger log = LoggerFactory.getLogger(HydraVocabulary.class);
    
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
