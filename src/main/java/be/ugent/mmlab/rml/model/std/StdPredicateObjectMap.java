package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.GraphMap;
import be.ugent.mmlab.rml.model.RDFTerm.ObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.PredicateMap;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.ReferencingObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *************************************************************************
 *
 * RML - Model : Predicate Object Map Implementation
 *
 * A Predicate-Object Map 
 * generates Predicate-Object pairs from Logical Sources.
 * It is used in conjunction with a subject
 * map to generate RDF triples in a Triples Map.
 * 
 * @author andimou
 * 
 ***************************************************************************
 */
public class StdPredicateObjectMap implements PredicateObjectMap {
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(StdPredicateObjectMap.class.getSimpleName());

	private Set<ObjectMap> objectMaps;
	private Set<ReferencingObjectMap> refObjectMaps;
	private Set<PredicateMap> predicateMaps;
	protected TriplesMap ownTriplesMap;
	private HashSet<GraphMap> graphMaps;

	private StdPredicateObjectMap(Set<PredicateMap> predicateMaps) {
		setPredicateMaps(predicateMaps);
	}

	public StdPredicateObjectMap(Set<PredicateMap> predicateMaps,
			Set<ObjectMap> objectMaps) {
		this(predicateMaps);
		setObjectMaps(objectMaps);
	}
	
	public StdPredicateObjectMap(Set<PredicateMap> predicateMaps,
                Set<ObjectMap> objectMaps, 
                Set<ReferencingObjectMap> referencingObjectMaps) {
		this(predicateMaps, objectMaps);
		setReferencingObjectMap(referencingObjectMaps);
	}

        @Override
	public void setReferencingObjectMap(Set<ReferencingObjectMap> refObjectMaps) {
		if (refObjectMaps == null)
			this.refObjectMaps = new HashSet<ReferencingObjectMap>();
		else {
			for (ReferencingObjectMap refObjectMap : refObjectMaps) {
				if (refObjectMap != null)
					refObjectMap.setPredicateObjectMap(this);
			}
			this.refObjectMaps = refObjectMaps;
		}
	}

        @Override
	public Set<ObjectMap> getObjectMaps() {
		return objectMaps;
	}

        @Override
	public Set<PredicateMap> getPredicateMaps() {
		return predicateMaps;
	}

        @Override
	public Set<ReferencingObjectMap> getReferencingObjectMaps() {
		return refObjectMaps;
	}

        @Override
	public boolean hasReferencingObjectMaps() {
		return refObjectMaps != null && !refObjectMaps.isEmpty();
	}

        @Override
	public TriplesMap getOwnTriplesMap() {
		return ownTriplesMap;
	}

        @Override
	public void setObjectMaps(Set<ObjectMap> objectMaps) {
		if (objectMaps == null)
			this.objectMaps = new HashSet<ObjectMap>();
		else {
			for (ObjectMap objectMap : objectMaps) {
				if (objectMap != null)
					objectMap.setPredicateObjectMap(this);
			}
			this.objectMaps = objectMaps;
		}
	}

        @Override
	public void setOwnTriplesMap(TriplesMap ownTriplesMap) {
		// Update triples map if not contains this subject map
		if (ownTriplesMap.getSubjectMap() != null)
			if (!ownTriplesMap.getPredicateObjectMaps().contains(this))
				ownTriplesMap.setPredicateObjectMap(this);
		this.ownTriplesMap = ownTriplesMap;
	}

        @Override
	public void setPredicateMaps(Set<PredicateMap> predicateMaps) {
		if (predicateMaps == null)
			this.predicateMaps = new HashSet<PredicateMap>();
		else {
			for (PredicateMap predicateMap : predicateMaps) {
				if (predicateMap != null)
					predicateMap.setPredicateObjectMap(this);
			}
			this.predicateMaps = predicateMaps;
		}
	}
	
        @Override
	public Set<GraphMap> getGraphMaps() {
		return graphMaps;
	}
	
        @Override
	public void setGraphMaps(Set<GraphMap> graphMaps) {
		this.graphMaps = new HashSet<GraphMap>(graphMaps);
	}


}
