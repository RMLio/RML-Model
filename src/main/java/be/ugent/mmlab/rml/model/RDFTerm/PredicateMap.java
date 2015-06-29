/**
 * *************************************************************************
 *
 * RML - Model : PredicateMap
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;

public interface PredicateMap extends TermMap {

    /**
     * A Predicate Map knows in own Predicate Object container.
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
