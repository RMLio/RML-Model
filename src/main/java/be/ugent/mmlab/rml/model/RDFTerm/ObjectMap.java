package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;

/**
 * *************************************************************************
 *
 * RML - Model : Object Map Interface
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface ObjectMap extends TermMap {

    /**
     * An Object Map knows belongs to one Predicate Object Map.
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
    @Override
    public void setOwnTriplesMap(TriplesMap ownTriplesMap);
    
    @Override
    public TriplesMap getOwnTriplesMap();
}
