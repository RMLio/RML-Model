package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.AbstractTermMap;
import be.ugent.mmlab.rml.model.RDFTerm.GraphMap;
import be.ugent.mmlab.rml.model.RDFTerm.TermType;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

/***************************************************************************
 *
 * RML - Model : Graph Map Implementation
 *
 * A Term Map may have one or more associated graph maps. 
 * A Graph Map is a Term Map itself. 
 * 
 * @author mielvandersande, andimou
 *
 ****************************************************************************/
public class StdGraphMap extends AbstractTermMap implements GraphMap {
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            StdGraphMap.class.getSimpleName());

    /**
     *
     * @param constantValue
     * @param stringTemplate
     * @param inverseExpression
     * @param referenceValue
     * @param termType
     */
    public StdGraphMap(Value constantValue,
            String stringTemplate, String inverseExpression,
            ReferenceMap referenceValue, URI termType) {
        // No Literal term type
        // ==> No datatype
        // ==> No specified language tag
        // Only termType possible : IRI => by default
        super(constantValue, null, null, stringTemplate,
                termType, inverseExpression, referenceValue,null);

    }

    /**
     *
     * @param tt
     */
    @Override
    protected void checkSpecificTermType(TermType tt) {
        // If the term map is a predicate map: rr:IRI
        if (tt != TermType.IRI) {
            log.error("If the term map is a "
                    + "graph map: only rr:IRI  is required");
        }
    }

    @Override
    protected void checkConstantValue(Value constantValue) {
        // If the constant-valued term map is a graph map then its constant
        // value must be an IRI.
        //TODO: Add proper URL check
        /*if (!StdIriRdfTerm.isValidURI(constantValue.stringValue())) {
            log.error("Not a valid URI : " + constantValue);
        }*/
    }

}
