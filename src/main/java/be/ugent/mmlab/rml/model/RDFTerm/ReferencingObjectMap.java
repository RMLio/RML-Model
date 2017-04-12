package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.JoinCondition;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.Set;

/**
 * *************************************************************************
 *
 * RML - Model : Referencing Object Map Interface
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface ReferencingObjectMap {
	
	
	/**
	 * A Referencing Object Map has exactly one rr:parentTriplesMap property.
	 */
	public TriplesMap getParentTriplesMap();
	
	/**
	 * A Referencing Object Map may have one or more rr:joinCondition 
	 * properties, whose values MUST be join conditions.
	 */
	public Set<JoinCondition> getJoinConditions();
	
	/**
	 * The effective reference of the logical source containing the 
	 * Referencing Object Map.
	 */
	public String getChildReference();
	
	/**
	 * The effective reference of the logical source of its Parent Triples Map.
	 */
	public String getParentReference();

        /**
         *
         * The effective reference of the Triples Map containing this Referencing Object Map
         */
        public TriplesMap getOwnTriplesMap();

        /**
        * A Referencing Object Map belongs to a Predicate Object Map.
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
       
       /**
        *
        * @return
        */
       public Set<ReferencingObjectMap> getFallbackReferencingObjectMaps();

		public GraphMap getGraphMap ();

		public void setGraphMap(GraphMap graphMap);
	
}
