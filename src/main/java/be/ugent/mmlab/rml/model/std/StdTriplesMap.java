package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.LogicalSource;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.SubjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *************************************************************************
 *
 * RML - Model : Triples Map
 *
 * A Triples Map specifies a rule for generating zero or more RDF triples
 * from each iteration over the Logical Source.
 * 
 ***************************************************************************
 */
public final class StdTriplesMap implements TriplesMap {
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            StdTriplesMap.class.getSimpleName());

	private Set<PredicateObjectMap> predicateObjectMaps;
	private SubjectMap subjectMap;
	private LogicalSource logicalSource;
	private String name;

	/**
     *
     * @param logicalSource
     * @param predicateObjectMaps
     * @param subjectMap
     * @param name
     */
    public StdTriplesMap(LogicalSource logicalSource,
			Set<StdPredicateObjectMap> predicateObjectMaps,
			SubjectMap subjectMap, String name) {
		setSubjectMap(subjectMap);
		setLogicalSource(logicalSource);
		setPredicateObjectMap(predicateObjectMaps);
		setName(name);
	}

    @Override
    public void setLogicalSource(LogicalSource logicalSource) {
		this.logicalSource = logicalSource;
	}

    public void setPredicateObjectMap(
            Set<StdPredicateObjectMap> predicateObjectMaps) {
        this.predicateObjectMaps = new HashSet<PredicateObjectMap>();
        if (predicateObjectMaps == null) {
            return;
        }
        this.predicateObjectMaps.addAll(predicateObjectMaps);
        // Update prediacte object map
        for (PredicateObjectMap pom : predicateObjectMaps) {
            if (pom.getOwnTriplesMap() != null) {
                if (pom.getOwnTriplesMap() != this) {
                    log.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                            //throw new IllegalStateException(
                            + "The predicateObject map child "
                            + "already contains another triples Map !");
                }
            } else {
                pom.setOwnTriplesMap(this);
            }
        }
    }

        @Override
	public LogicalSource getLogicalSource() {
		return logicalSource;
	}

        @Override
	public Set<PredicateObjectMap> getPredicateObjectMaps() {
		return predicateObjectMaps;
	}

        @Override
	public SubjectMap getSubjectMap() {
		return subjectMap;
	}

        @Override
	public void setSubjectMap(SubjectMap subjectMap) {
		this.subjectMap = subjectMap;

	}

        @Override
	public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap) {
		if (predicateObjectMap != null)
			predicateObjectMaps.add(predicateObjectMap);
	}

        @Override
	public String getName() {
		return this.name;
	}
        
        public String getShortName(){
            String[] name = getName().split("#");
            return name[1];
        }

        @Override
	public void setName(String name) {
		if (name != null)
			this.name = name;
		
	}

}
