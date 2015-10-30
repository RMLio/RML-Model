package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.AbstractTermMap;
import be.ugent.mmlab.rml.model.RDFTerm.ObjectMap;
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
 * RML - Model : ObjectMap Implementation
 *
 * An Object Map is a specific Term Map used for generating an RDF object. 
 * 
 * @author mielvandersande, andimou
 * 
 ***************************************************************************
 */
public class StdObjectMap extends AbstractTermMap implements TermMap, ObjectMap {
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(StdObjectMap.class);

	private PredicateObjectMap predicateObjectMap;

	/**
        *
        * @param predicateObjectMap
        * @param constantValue
        * @param dataType
        * @param languageTag
        * @param stringTemplate
        * @param termType
        * @param inverseExpression
        * @param referenceValue
        */
       public StdObjectMap(TriplesMap triplesMap, PredicateObjectMap predicateObjectMap,
			Value constantValue, URI dataType, String languageTag,
			String stringTemplate, URI termType, String inverseExpression,
			ReferenceMap referenceValue)  {
		super(constantValue, dataType, languageTag, stringTemplate, termType,
				inverseExpression, referenceValue);
		setPredicateObjectMap(predicateObjectMap);
                setOwnTriplesMap(triplesMap);
	}
        
        /**
        *
        * @param predicateObjectMap
        * @param constantValue
        * @param dataType
        * @param languageTag
        * @param stringTemplate
        * @param termType
        * @param inverseExpression
        * @param referenceValue
        * @param split
        * @param process
        * @param replace
        */
       /*public StdObjectMap(PredicateObjectMap predicateObjectMap,
			Value constantValue, URI dataType, String languageTag,
			String stringTemplate, URI termType, String inverseExpression,
			ReferenceIdentifier referenceValue, String split,
                        String process, String replace)  {
		super(constantValue, dataType, languageTag, stringTemplate, termType,
				inverseExpression, referenceValue,
                                split, process, replace);
		setPredicateObjectMap(predicateObjectMap);
	}*/

        @Override
        protected void checkSpecificTermType(TermType tt) {
            // If the term map is a subject map: rr:IRI or rr:BlankNode or
            // rr:Literal
            if ((tt != TermType.IRI) && (tt != TermType.BLANK_NODE)
                    && (tt != TermType.LITERAL)) {
                log.error("Invalid Structure "
                        + "[StdObjectMap:checkSpecificTermType] If the term map is a "
                        + "object map: only rr:IRI or rr:BlankNode is required");
            }
        }

        @Override
        protected void checkConstantValue(Value constantValue) {
            //TODO: Add proper URL check
            /*if (!StdIriRdfTerm.isValidURI(constantValue.stringValue())
                    && !StdLiteralRdfTerm
                    .isValidLiteral(constantValue.stringValue())) {
                log.error("Data Error "
                        + "Not a valid URI or literal : "
                        + constantValue);
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
		 * if (predicateObjectMap.getObjectMaps() != null) { if
		 * (!predicateObjectMap.getObjectMaps().contains(this)) throw new
		 * IllegalStateException( "[StdObjectMap:setPredicateObjectMap] " +
		 * "The predicateObject map parent " +
		 * "already contains another Object Map !"); } else {
		 */
		// Update predicateObjectMap if not contains this object map
		if (predicateObjectMap != null) {
			if (predicateObjectMap.getObjectMaps() == null)
				predicateObjectMap.setObjectMaps(new HashSet<ObjectMap>());
			predicateObjectMap.getObjectMaps().add(this);
			// }
		}
		this.predicateObjectMap = predicateObjectMap;
	}
        
        @Override
	public void setOwnTriplesMap(TriplesMap ownTriplesMap) {   
            this.ownTriplesMap = ownTriplesMap;
	}
}
