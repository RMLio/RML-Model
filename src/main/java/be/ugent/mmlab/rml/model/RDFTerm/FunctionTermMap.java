package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import org.openrdf.model.URI;

import java.util.Map;
import java.util.Set;

/**
 * Created by andimou on 7/8/16.
 */
public interface FunctionTermMap extends TermMap {
    public void setFunction(URI functionValue);

    public URI getFunction();

    public void setParameters(Set<URI> parameters);

    public Set<URI> getParameters();

    public void setPredicateObjectMap(PredicateObjectMap preObjMap);

    public void setFunctionTriplesMap(TriplesMap functionTriplesMap);

    public TriplesMap getFunctionTriplesMap();

    public void setParameterRefs(Map<String,String> parameterRefs);

    public Map<String,String> getParameterRefs();
}
