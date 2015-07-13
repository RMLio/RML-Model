package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;

/**
 * *************************************************************************
 *
 * RML - Model : Predicate Map
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface PredicateMap extends TermMap {

    /**
     * A Predicate Map belongs to a Predicate Object Map.
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
}
