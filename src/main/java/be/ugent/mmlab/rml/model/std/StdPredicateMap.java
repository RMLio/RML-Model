package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.AbstractTermMap;
import be.ugent.mmlab.rml.model.RDFTerm.PredicateMap;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.TermMap;
import be.ugent.mmlab.rml.model.RDFTerm.TermType;
import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

/**
 *************************************************************************
 *
 * RML - Model : Predicate Map Implementation
 *
 * A predicate map is a specific term map used for generating RDF predicates. 
 * 
 * @author mielvandersande, andimou
 * 
 ***************************************************************************
 */
public class StdPredicateMap extends AbstractTermMap implements TermMap,
        PredicateMap {
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(StdPredicateMap.class);

    private PredicateObjectMap predicateObjectMap;

    /**
     *
     * @param predicateObjectMap
     * @param constantValue
     * @param stringTemplate
     * @param inverseExpression
     * @param referenceValue
     * @param termType
     */
    public StdPredicateMap(PredicateObjectMap predicateObjectMap,
            Value constantValue, String stringTemplate,
            String inverseExpression, ReferenceMap referenceValue, URI termType) {
        // No Literal term type
        // ==> No datatype
        // ==> No specified language tag
        // No class IRI
        super(constantValue, null, null, stringTemplate, termType,
                inverseExpression, referenceValue);
        setPredicateObjectMap(predicateObjectMap);
        setOwnTriplesMap(ownTriplesMap);
    }

    @Override
    protected void checkSpecificTermType(TermType tt) {
        // If the term map is a predicate map: rr:IRI
        if (tt != TermType.IRI) {
            log.error("Invalid Structure "
                    + "[StdPredicateMap:checkSpecificTermType] If the term map is a "
                    + "predicate map: only rr:IRI  is required");
        }
    }

    @Override
    protected void checkConstantValue(Value constantValue) {
        // If the constant-valued term map is a predicate map then its constant
        // value must be an IRI.
        //TODO: Add proper URL check
        /*if (!StdIriRdfTerm.isValidURI(constantValue.stringValue())) {
            log.error("Data Error " + "Not a valid URI : " + constantValue);
        }*/
    }

    @Override
    public PredicateObjectMap getPredicateObjectMap() {
        return predicateObjectMap;
    }

    /**
     *
     * @param predicateObjectMap
     */
    @Override
    public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap) {
        /*
         * if (predicateObjectMap.getPredicateMaps() != null) { if
         * (!predicateObjectMap.getPredicateMaps().contains(this)) throw new
         * IllegalStateException(
         * "[StdPredicateObjectMap:setPredicateObjectMap] " +
         * "The predicateObject map parent " +
         * "already contains another Predicate Map !"); } else {
         */
        if (predicateObjectMap != null) {
            // Update predicateObjectMap if not contains this object map
            if (predicateObjectMap.getPredicateMaps() == null) {
                predicateObjectMap
                        .setPredicateMaps(new HashSet<PredicateMap>());
            }
            predicateObjectMap.getPredicateMaps().add(this);
        }
        // }
        this.predicateObjectMap = predicateObjectMap;

    }

    @Override
    public void setOwnTriplesMap(TriplesMap ownTriplesMap) {
        this.ownTriplesMap = ownTriplesMap;
    }
}
