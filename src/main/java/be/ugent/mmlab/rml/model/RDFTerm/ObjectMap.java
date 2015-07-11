package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;

/**
 * *************************************************************************
 *
 * RML - Model : ObjectMap
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface ObjectMap extends TermMap {

    /**
     * A object map knows in own Predicate Object container.
     */
    public PredicateObjectMap getPredicateObjectMap();

    /**
     *
     * @param predicateObjectMap
     */
    public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap);

    /**
     *
     * @param ownTriplesMap
     */
    public void setOwnTriplesMap(TriplesMap ownTriplesMap);
    
    @Override
    public TriplesMap getOwnTriplesMap();
}
