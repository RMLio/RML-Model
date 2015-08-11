package be.ugent.mmlab.rml.model.source;


/**
 * RML - Data Retrieval Handler : SparqlSdInputSource
 *
 * @author andimou
 */
public class SparqlSdSource extends StdSource {
    
    private String template;
    
    /**
     *
     * @param name
     * @param template
     */
    public SparqlSdSource(String name, String template){
        super(name);
        setTemplate(template);
    }
    
    private void setTemplate(String template){
        this.template = template;
    }
    
    /**
     *
     * @return
     */
    public String setTemplate(){
        return this.template ;
    }
}
