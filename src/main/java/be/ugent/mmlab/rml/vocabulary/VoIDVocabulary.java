package be.ugent.mmlab.rml.vocabulary;

/**
 * *************************************************************************
 *
 * RML - Model : Void Vocabulary
 *
 * namespace: void IRI: http://rdfs.org/ns/void#
 * 
 * @author andimou
 *
 ***************************************************************************
 */
public class VoIDVocabulary {
    public static String VOID_NAMESPACE = "http://rdfs.org/ns/void#";

    

    public enum VoIDTerm implements Term{

        // VoID CLASSES
        DATASET_CLASS("Dataset"),
        DATASETDESCRIPTION_CLASS("DatasetDescription"),
        // RPROPERTIES
        DATADUMP("dataDump"),
        DISTINCTOBJECTS("distinctObjects"),
        DISTINCTSUBJECTS("distinctSubjects"),
        DOCUMENTS("documents"),
        ENTITIES("entities"),
        PROPERTIES("properties"), 
        SPARQLENDPOINT("sparqlEndpoint"),
        SUBSET("subset"),
        TRIPLES("triples"),
        URILOOKUPENDPOINT("uriLookupEndpoint"),
        URIREGEXENDPOINT("uriRegexEndpoint"),
        URISPACE("uriSpace"),
        VOCABULARY("vocabulary");
        
        private String displayName;

        private VoIDTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

}
