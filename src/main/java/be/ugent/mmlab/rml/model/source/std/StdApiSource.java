package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.*;


/**
 * RML - Model : Std API Source
 *
 * @author andimou
 */
public class StdApiSource extends StdSource implements ApiSource {
    
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
    
}
