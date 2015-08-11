package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.SparqlEndpointSource;


/**
 * RML - Model : Std SPARQL Endpoint Source
 *
 * @author andimou
 */
public class StdSparqlEndpointSource extends StdSource implements SparqlEndpointSource {
    private String supportedLanguage;
    private String resultFormat;
    
    /**
     *
     * @param name
     * @param template
     */
    public StdSparqlEndpointSource(String name, String template){
        super(name);
        setTemplate(template);
    } 
    
    public StdSparqlEndpointSource(String name, String template, 
            String supportedLanguage, String resultFormat){
        super(name);
        setTemplate(template);
        setSupportedLanguage(supportedLanguage);
        setResultFormat(resultFormat);
    } 
    
    private void setSupportedLanguage(String supportedLanguage){
        this.supportedLanguage = supportedLanguage;
    }
    
    public String getSupportedLanguage(){
        return this.supportedLanguage;
    }
    
    private void setResultFormat(String resultFormat){
        this.resultFormat = resultFormat;
    }
    
    public String getResultFormat(){
        return this.resultFormat;
    }

}
