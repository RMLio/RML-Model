package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.SparqlEndpointSource;


/**
 * RML - Model : Std SPARQL Endpoint Source
 *
 * @author andimou
 */
public class StdSparqlEndpointSource extends StdSource implements SparqlEndpointSource {
    
    //private String template;
    
    /**
     *
     * @param name
     * @param template
     */
    public StdSparqlEndpointSource(String name, String template){
        super(name);
        setTemplate(template);
    }   

}
