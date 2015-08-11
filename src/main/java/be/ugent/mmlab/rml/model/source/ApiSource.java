package be.ugent.mmlab.rml.model.source;


/**
 * RML - Data Retrieval Handler : ApiInputSource
 *
 * @author andimou
 */
public class ApiSource extends StdSource {
    private String template;
    
    /**
     *
     * @param name
     */
    public ApiSource(String name){
        super(name);
    }
    
    /**
     *
     * @param name
     * @param template
     */
    public ApiSource(String name, String template){
        super(name, template);
        setTemplate(template);
    }
    
    private void setTemplate(String template){
        this.template = template;
    }
    
    /**
     *
     * @return
     */
    public String getTemplate(){
        return this.template ;
    }
    
}
