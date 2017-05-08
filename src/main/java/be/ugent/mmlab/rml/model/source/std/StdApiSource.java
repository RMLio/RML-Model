package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.*;
import java.util.List;
import java.util.Map;


/**
 * RML - Model : Std API Source
 *
 * @author andimou
 */
public class StdApiSource extends StdSource implements ApiSource {
    private List<Map<String,Boolean>> mapTemplates;
    
    /**
     *
     * @param name
     */
    public StdApiSource(String name){
        super(name);
    }
    
    /**
     *
     * @param name
     * @param template
     */
    public StdApiSource(String name, String template){
        super(name, template);
    }
    
    /**
     *
     * @param name
     * @param template
     * @param mapTemplates
     */
    public StdApiSource(String name, String template, 
            List<Map<String,Boolean>> mapTemplates){
        super(name, template);
        setMappingTemplates(mapTemplates);
    }
    
    public List<Map<String,Boolean>> getMappingTemplates(){
        return this.mapTemplates;
    }
    
    private void setMappingTemplates (List<Map<String,Boolean>> mapTemplates) {
        this.mapTemplates = mapTemplates;
    }
    
    private void setMappingTemplate(Map<String,Boolean> mapTemplate){
        this.mapTemplates.add(mapTemplate);
    }
    
}
