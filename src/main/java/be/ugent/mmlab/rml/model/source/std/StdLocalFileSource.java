package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.*;


/**
 * RML - Model : LocalFileSource
 *
 * @author andimou
 */
public class StdLocalFileSource extends StdSource implements LocalFileSource {
    
    /**
     *
     * @param name
     */
    public StdLocalFileSource(String name){
        super(name);
    }
    
    /**
     *
     * @param name
     * @param source
     */
    public StdLocalFileSource(String name, String template){
        super(name, template);
    }
}
