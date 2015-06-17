/* 
 * Copyright 2011 Antidot opensource@antidot.net
 * https://github.com/antidot/db2triples
 * 
 * DB2Triples is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * DB2Triples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/***************************************************************************
 *
 * R2RML Model : Standard SubjectMap Class
 *
 * A subject map is a term map. It specifies a rule
 * for generating the subjects of the RDF triples generated 
 * by a triples map.
 * 
 * modified by mielvandersande, andimou
 *
 ****************************************************************************/
package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.AbstractTermMap;
import be.ugent.mmlab.rml.model.GraphMap;
import be.ugent.mmlab.rml.model.SubjectMap;
import be.ugent.mmlab.rml.model.TermType;
import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.reference.ReferenceIdentifier;
import java.util.HashSet;
import java.util.Set;

import net.antidot.semantic.rdf.model.tools.RDFDataValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openrdf.model.URI;
import org.openrdf.model.Value;

public class StdSubjectMap extends AbstractTermMap implements SubjectMap {

	protected Set<URI> classIRIs;
	protected HashSet<GraphMap> graphMaps;
        // Log
        private static final Logger log = LogManager.getLogger(StdObjectMap.class);

	public StdSubjectMap(TriplesMap ownTriplesMap, Value constantValue,
			String stringTemplate, URI termType, String inverseExpression,
			ReferenceIdentifier referenceValue, Set<URI> classIRIs, Set<GraphMap> graphMaps) {
		// No Literal term type
		// ==> No datatype
		// ==> No specified language tag
		super(constantValue, null, null, stringTemplate, termType,
				inverseExpression, referenceValue);
		setClassIRIs(classIRIs);
		setGraphMaps(graphMaps);
		setOwnTriplesMap(ownTriplesMap);
	}
        
        /*public StdSubjectMap(TriplesMap ownTriplesMap, Value constantValue,
			String stringTemplate, URI termType, String inverseExpression,
			ReferenceIdentifier referenceValue, Set<URI> classIRIs, Set<GraphMap> graphMaps, 
                        String split, String process, String replace) {
		super(constantValue, null, null, stringTemplate, termType,
				inverseExpression, referenceValue,
                                split, process, replace);
		setClassIRIs(classIRIs);
		setGraphMaps(graphMaps);
		setOwnTriplesMap(ownTriplesMap);
	}*/
        
     /**
     *
     * @param ownTriplesMap
     * @param constantValue
     * @param stringTemplate
     * @param termType
     * @param inverseExpression
     * @param referenceValue
     * @param classIRIs
     * @param graphMaps
     * @param split
     * @param process
     * @param replace
     * @param equalCondition
     * @param processCondition
     * @param splitCondition
     * @param bindCondition
     */
    /*public StdSubjectMap(TriplesMap ownTriplesMap, Value constantValue,
			String stringTemplate, URI termType, String inverseExpression,
			ReferenceIdentifier referenceValue, Set<URI> classIRIs, Set<GraphMap> graphMaps, 
                        String split, String process, String replace, 
                        Set<EqualCondition> equalCondition, Set<ProcessCondition> processCondition, 
                        Set<SplitCondition> splitCondition, Set<BindCondition> bindCondition) {
		super(constantValue, null, null, stringTemplate, termType,
				inverseExpression, referenceValue, split, process, replace, 
                                equalCondition, processCondition, splitCondition, bindCondition);
		setClassIRIs(classIRIs);
		setGraphMaps(graphMaps);
		setOwnTriplesMap(ownTriplesMap);
	}*/

        @Override
        public void setOwnTriplesMap(TriplesMap ownTriplesMap) {
            // Update triples map if not contains this subject map
            if (ownTriplesMap != null && ownTriplesMap.getSubjectMap() != null) {
                if (ownTriplesMap.getSubjectMap() != this) {
                    log.error(
                            "[StdSubjectMap:setSubjectMap] "
                            + "The own triples map "
                            + "already contains another Subject Map !");
                } else {
                    ownTriplesMap.setSubjectMap(this);
                }
            }
            this.ownTriplesMap = ownTriplesMap;
        }

	private void setGraphMaps(Set<GraphMap> graphMaps) {
		this.graphMaps = new HashSet<GraphMap>();
		if (graphMaps != null)
			this.graphMaps.addAll(graphMaps);
	}

	private void setClassIRIs(Set<URI> classIRIs2) {
		this.classIRIs = new HashSet<URI>();
		if (classIRIs2 != null) {
			checkClassIRIs(classIRIs);
			classIRIs.addAll(classIRIs2);
		}
	}

	private void checkClassIRIs(Set<URI> classIRIs2) {
            // The values of the rr:class property must be IRIs.
            for (URI classIRI : classIRIs) {
                if (!RDFDataValidator.isValidURI(classIRI.stringValue())) {
                    log.error(
                            "[AbstractTermMap:checkClassIRIs] Not a valid URI : "
                            + classIRI);
                }
            }
        }

        @Override
	public Set<URI> getClassIRIs() {
		return classIRIs;
	}

        @Override
        protected void checkSpecificTermType(TermType tt) {
            // If the term map is a subject map: rr:IRI or rr:BlankNode
            if ((tt != TermType.IRI) && (tt != TermType.BLANK_NODE)) {
                log.error("Invalid Structure "
                        + "[StdSubjectMap:checkSpecificTermType] If the term map is a "
                        + "subject map: only rr:IRI or rr:BlankNode is required");
            }
        }

        @Override
	protected void checkConstantValue(Value constantValue) {
            // If the constant-valued term map is a subject map then its constant
            // value must be an IRI.
            if (!RDFDataValidator.isValidURI(constantValue.stringValue())) {
                log.error("Data Error "
                        + "[StdSubjectMap:checkConstantValue] Not a valid URI : "
                        + constantValue);
            }
        }

        @Override
	public Set<GraphMap> getGraphMaps() {
		return graphMaps;
	}

        @Override
	public TriplesMap getOwnTriplesMap() {
		return ownTriplesMap;
	}

        @Override
	public String toString() {
		String result = super.toString() 
                        + " [StdSubjectMap : classIRIs = [";
		for (URI uri : classIRIs)
			result += uri.getLocalName() + ",";
		result += "], graphMaps = [";
		for (GraphMap graphMap : graphMaps)
			result += graphMap + ",";
		result += "]]";
		return result;
	}

}
