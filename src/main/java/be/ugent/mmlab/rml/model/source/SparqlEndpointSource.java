package be.ugent.mmlab.rml.model.source;

import be.ugent.mmlab.rml.model.Source;


/**
 * RML - Data Model : SparqlSdInputSource Interface
 *
 * @author andimou
 */
public interface SparqlEndpointSource extends Source {

    
    /**
     *
     * @param template
     */
    public void setTemplate(String template);
    
    /**
     *
     * @return
     */
    public String getTemplate();
}
