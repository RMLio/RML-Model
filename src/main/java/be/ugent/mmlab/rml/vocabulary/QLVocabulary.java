package be.ugent.mmlab.rml.vocabulary;

/**
 * *************************************************************************
 * 
 * RML - Model : QL Vocabulary
 * 
 * namespace:ql IRI: http://semweb.mmlab.be/ns/ql#
 *
 * @author andimou
* 
 * *************************************************************************
 */
public class QLVocabulary {
    public static String QL_NAMESPACE = "http://semweb.mmlab.be/ns/ql#";
    
    public enum QLTerm implements Term{

        XPATH_CLASS("XPath"),
        SQL_CLASS("SQL"),
        JSONPATH_CLASS("JSONPath"),
        CSV_CLASS("CSV"),
        CSS3_CLASS("CSS3");
        
        private String displayName;

        private QLTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
    
    public static QLTerm getQLTerms(String stringValue) {
        for (QLTerm term : QLTerm.values()){
            if (stringValue.equals(QL_NAMESPACE + term)){
                return term;
            }
        }
        return null;
    }
}
