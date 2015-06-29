/**
 * *************************************************************************
 *
 * RML - Model : ReferencingObjectMap Interface
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.JoinCondition;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.Set;


public interface ReferencingObjectMap {
	
	
	/**
	 * A referencing object map has exactly one rr:parentTriplesMap property.
	 */
	public TriplesMap getParentTriplesMap();
	
	/**
	 * A referencing object map may have one or more rr:joinCondition 
	 * properties, whose values MUST be join conditions.
	 */
	public Set<JoinCondition> getJoinConditions();
	
	/**
	 * The effective reference of the logical source containing the 
	 * referencing object map.
	 */
	public String getChildReference();
	
	/**
	 * The effective reference of the logical source of its parent triples map.
	 */
	public String getParentReference();

        /**
         *
         * The effective reference of the Triples Map containing this Referencing Object Map
         */
        public TriplesMap getOwnTriplesMap();

        /**
        * A object map knows in own Predicate Object container.
        */
        public PredicateObjectMap getPredicateObjectMap();

       /**
        *
        * @param triplesMap
        */
        public void setParentTriplesMap(TriplesMap triplesMap);
        
	/**
        *
        * @param predicateObjectMap
        */
        public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap);
       
        /**
        *
        * @param joinCondition
        */
        public void setJoinConditions(Set<JoinCondition> joinCondition);
        
        /**
        *
        * @param ownTriplesMap
        */
       public void setOwnTriplesMap(TriplesMap ownTriplesMap);
	
}
