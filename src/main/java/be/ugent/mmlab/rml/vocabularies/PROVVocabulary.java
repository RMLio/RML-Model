package be.ugent.mmlab.rml.vocabularies;

/**
 * RML Model - PROV-O Vocabulary
 * 
 * namespace prov IRI: http://www.w3.org/ns/prov#
 *
 * @author andimou
 */
public class PROVVocabulary {
    public static String PROV_NAMESPACE = "http://www.w3.org/ns/prov#";
    
    public enum PROVTerm implements Term{

        // PROV CLASSES
        ENTITY_CLASS("Entity"),
        ACTIVITY_CLASS("Activity"),
        // PROV PROPERTIES
        WASGENERATEDBY("wasGeneratedBy"),
        WASDERIVEDFROM("wasDerivedFrom"),
        WASATTRIBUTEDTO("wasAttributedTo"),
        STARTEDATTIME("startedAtTime"),
        USED("used"),
        WASINFORMEDBY("wasInformedBy"),
        ENDEDATTIME("endedAtTime"),
        WASASSOCIATEDWITH("wasAssociatedWith"), 
        ACTEDONBEHALFOF("actedOnBehalfOf");
        
        private String displayName;

        private PROVTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

}
