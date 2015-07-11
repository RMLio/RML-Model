package be.ugent.mmlab.rml.vocabulary;

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

    // In this document, examples assume the following namespace 
    // prefix bindings unless otherwise stated:
    public static String RML_NAMESPACE = "http://semweb.mmlab.be/ns/rml#";  

    public enum RMLTerm implements Term{

        // RML CLASSES
        LOGICAL_SOURCE_CLASS("LogicalSource"),
        // RPROPERTIES
        REFERENCE_FORMULATION("referenceFormulation"),
        LOGICAL_SOURCE("logicalSource"),
        REFERENCE("reference"),
        ITERATOR("iterator"),
        VERSION("version"),
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
