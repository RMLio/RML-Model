package be.ugent.mmlab.rml.vocabulary;

/**
 * *************************************************************************
 * 
 * RML - Model : D2RQ Vocabulary
 * 
 * namespace: d2rq IRI: http://www.wiwiss.fu-berlin.de/suhl/bizer/D2RQ/0.1#
 *
 * @author andimou
 * 
 * *************************************************************************
 */
public class D2RQVocabulary {
    public static String D2RQ_NAMESPACE = "http://www.wiwiss.fu-berlin.de/suhl/bizer/D2RQ/0.1#";
    
    public enum D2RQTerm implements Term{

        // RML CLASSES
        DATABASE_CLASS("Database"),
        // RPROPERTIES
        JDBCDSN("jdbcDSN"),
        JDBCDRIVER("jdbcDriver"),
        USERNAME("username"),
        PASSWORD("password");
        
        private String displayName;

        private D2RQTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }

}
