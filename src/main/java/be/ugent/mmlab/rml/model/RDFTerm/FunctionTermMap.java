package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import org.eclipse.rdf4j.model.IRI;

import java.util.Map;
import java.util.Set;

/**
 * Created by andimou on 7/8/16.
 */
public interface FunctionTermMap extends TermMap {
    public void setFunction(IRI functionValue);

    public IRI getFunction();

    public void setParameters(Set<IRI> parameters);

    public Set<IRI> getParameters();

    public void setPredicateObjectMap(PredicateObjectMap preObjMap);

    public void setFunctionTriplesMap(TriplesMap functionTriplesMap);

    public TriplesMap getFunctionTriplesMap();

    public void setParameterRefs(Map<String,String> parameterRefs);

    public Map<String,String> getParameterRefs();
}
