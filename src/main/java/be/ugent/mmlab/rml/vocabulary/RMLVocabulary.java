package be.ugent.mmlab.rml.vocabulary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(RMLVocabulary.class);

    // In this document, examples assume the following namespace 
    // prefix bindings unless otherwise stated:
    public static String RML_NAMESPACE = "http://semweb.mmlab.be/ns/rml#";
    public static String RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static String RDFS_NAMESPACE = "http://www.w3.org/2000/01/rdf-schema#";
    public static String XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema#";

    

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
