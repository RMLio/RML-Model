package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 *
 * RML - Model : RML Vocabulary
 *
 * namespace: rml IRI: http://semweb.mmlab.be/rml#
 * 
 * @author andimou
 *
 ***************************************************************************
 */
public class RMLVocabulary {
    public static String RML_NAMESPACE = "http://semweb.mmlab.be/ns/rml#";  

    public enum RMLTerm implements Term{

        // RML CLASSES
        LOGICAL_SOURCE_CLASS("LogicalSource"),
        LANGUAGE_MAP_CLASS("LanguageMap"),
        // RML PROPERTIES
        REFERENCE_FORMULATION("referenceFormulation"),
        LOGICAL_SOURCE("logicalSource"),
        REFERENCE("reference"),
        ITERATOR("iterator"),
        LANGUAGE_MAP("languageMap"),
        VERSION("version"),
        QUERY("query"),
        SOURCE("source"), 
        PROCESS("process"),
        REPLACE("replace"),
        SPLIT("split");
        
        private String displayName;

        private RMLTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
