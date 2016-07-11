package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.*;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

import java.util.Set;

/**
 * Created by andimou on 7/8/16.
 */
public class StdFunctionTermMap extends AbstractTermMap implements FunctionTermMap, ObjectMap, TermMap {
    private URI functionURI ;
    private TriplesMap functionTriplesMap;
    private Set<URI> parameters;
    private PredicateObjectMap preObjMap = null;

    public StdFunctionTermMap(TriplesMap triplesMap, PredicateObjectMap predicateObjectMap,
                              Value constantValue, URI dataType, String languageTag,
                              String stringTemplate, URI termType, String inverseExpression,
                              ReferenceMap referenceValue, GraphMap graphMap,
                              Value value, TriplesMap functionTriplesMap, URI functionURI, Set<URI> parameters){
        super(constantValue, dataType, languageTag, stringTemplate, termType, inverseExpression, referenceValue, graphMap);
        setPredicateObjectMap(predicateObjectMap);

        //this.functionTriplesMap = triplesMap;
        setFunctionTriplesMap(functionTriplesMap);
        setFunction(functionURI);
        setParameters(parameters);
    }

    public void setFunctionTriplesMap(TriplesMap functionTriplesMap){
        this.functionTriplesMap = functionTriplesMap;
    }

    public TriplesMap getFunctionTriplesMap(){
        return this.functionTriplesMap;
    }

    public void setFunction(URI functionValue){
        this.functionURI = functionValue ;
    }

    public URI getFunction(){
        return functionURI;
    }

    public void setParameters(Set<URI> parameters){
        this.parameters = parameters;
    }

    public Set<URI> getParameters(){
        return this.parameters;
    }

    public PredicateObjectMap getPredicateObjectMap() { return this.preObjMap; }

    public void setPredicateObjectMap(PredicateObjectMap pom){ this.preObjMap = pom; };

    @Override
    protected void checkSpecificTermType(TermType tt) { }

    @Override
    protected void checkConstantValue(Value constantValue) { }

}
