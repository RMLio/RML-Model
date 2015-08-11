package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 * 
 * RML - Model : SPARQL-SD Vocabulary
 * 
 * namespace:sd IRI:http://www.w3.org/ns/sparql-service-description#
 *
 * @author andimou
 * 
 * *************************************************************************
 */
public class SPARQLSDVocabulary {
    public static String SPARQLSD_NAMESPACE = "http://www.w3.org/ns/sparql-service-description#";

    public enum SparqlSdTerm implements Term {

        // SPARQL-SD CLASSES
        SERVICE_CLASS("Service"),
        // SPARQL-SD PROPERTIES
        SPARQL_QUERY_TEMPLATE("sparqlQueryTemplate");
        private String displayName;

        private SparqlSdTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
