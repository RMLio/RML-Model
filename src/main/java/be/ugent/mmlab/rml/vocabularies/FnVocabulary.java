package be.ugent.mmlab.rml.vocabularies;

import be.ugent.mmlab.rml.vocabularies.Term;

/**
 * Created by andimou on 7/8/16.
 */
public class FnVocabulary {

    public static String FnML_NAMESPACE = "http://semweb.mmlab.be/ns/fnml#";
    public static String FNO_NAMESPACE = "http://w3id.org/function/ontology#";

    public enum FnTerm implements Term {

        // FML CLASSES
        FUNCTION_TERM_MAP_CLASS("FunctionTermMap"),

        // FnML PROPERTIES
        FUNCTION_VALUE("functionValue"),

        // FNO PROPERTIES
        EXECUTES("executes"),

        // cTerms
        CTERMS_TYPE("type");

        private String displayName;

        private FnTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

    }

}
