package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 * 
 * RML - Model : DCAT Vocabulary
 * 
 * namespace: DCAT IRI: http://www.w3.org/ns/dcat#
 *
 * @author andimou
 * 
 * *************************************************************************
 */
public class DCATVocabulary {
    public static String DCAT_NAMESPACE = "http://www.w3.org/ns/dcat#";
    
    public enum DcatTerm implements Term{

        // DCAT CLASSES
        DISTRIBUTIION_CLASS("Distribution"),
        DATASET_CLASS("Distribution"),
        // DCAT PROPERTIES
        DISTRIBUTION("distribution"),
        DOWNLOADURL("downloadURL"),
        ACCESSURL("accessURL");
        
        private String displayName;

        private DcatTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
    
}
