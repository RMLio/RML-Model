package be.ugent.mmlab.rml.model;

import be.ugent.mmlab.rml.model.RDFTerm.GraphMap;
import be.ugent.mmlab.rml.model.RDFTerm.ObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.PredicateMap;
import be.ugent.mmlab.rml.model.RDFTerm.ReferencingObjectMap;
import java.util.Set;

/**
 * *************************************************************************
 *
 * RML - Model : PredicateObjectMap
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface PredicateObjectMap {

    /**
     * A predicate-object map is represented by a resource that references one
     * or more predicate maps.
     */
    public Set<PredicateMap> getPredicateMaps();

    /**
     *
     * @param predicateMaps
     */
    public void setPredicateMaps(Set<PredicateMap> predicateMaps);

    /**
     * A predicate-object map is represented by a resource that references one
     * or more object map or one referencing object map. If this method returns
     * NULL therefore getReferencingObjectMap method will not.
     */
    public Set<ObjectMap> getObjectMaps();

    /**
     *
     * @param objectMaps
     */
    public void setObjectMaps(Set<ObjectMap> objectMaps);

    /**
     * A predicate-object map is represented by a resource that references
     * exactly one object map or one referencing object map. If this method
     * returns NULL therefore getObjectMap method will not.
     */
    public Set<ReferencingObjectMap> getReferencingObjectMaps();

    /**
     *
     * @param referencingOjectMap
     */
    public void setReferencingObjectMap(Set<ReferencingObjectMap> referencingOjectMap);

    /**
     * Indicates if a ReferencingObjectMap is associated with this
     * PredicateObjectMap. If true, it is a ReferencingObjectMap, a "simple"
     * ObjectMap otherwise.
     */
    public boolean hasReferencingObjectMaps();

    /**
     * A Predicate Object Map knows in own Triples Map container.
     */
    public TriplesMap getOwnTriplesMap();

    /**
     *
     * @param ownTriplesMap
     */
    public void setOwnTriplesMap(TriplesMap ownTriplesMap);

    /**
     * Any predicate-object map may have one or more associated graph maps.
     */
    public Set<GraphMap> getGraphMaps();

    /**
     *
     * @param graphmaps
     */
    public void setGraphMaps(Set<GraphMap> graphmaps);
}
