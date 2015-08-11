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
    public static String FORMATS_NAMESPACE = "http://www.w3.org/ns/formats/data/";

    public enum SparqlSdTerm implements Term {

        // SPARQL-SD CLASSES
        SERVICE_CLASS("Service"),
        // SPARQL-SD PROPERTIES
        SPARQL_QUERY_TEMPLATE("sparqlQueryTemplate"),
        SUPPORTEDLANGUAGE("supportedLanguage"),
        RESULTFORMAT("resultFormat"),
        //SPARQL-SD INSTANCES
        SPARQL10QUERY("SPARQL10Query"), 
        SPARQL11QUERY("SPARQL11Query"), 
        SPARQL11UPDATE("SPARQL11Update"),
        SPARQL_RESULTS_XML("SPARQL_Results_XML"),
        SPARQL_RESULTS_JSON("SPARQL_Results_JSON"),
        SPARQL_RESULTS_CSV("SPARQL_Results_CSV");
        
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
