package be.ugent.mmlab.rml.model.source;


/**
 * RML - Data Retrieval Handler : LocalFileSource
 *
 * @author andimou
 */
public class LocalFileSource extends StdSource  {
    
    /**
     *
     * @param name
     */
    public LocalFileSource(String name){
        super(name);
    }
    
    /**
     *
     * @param name
     * @param source
     */
    public LocalFileSource(String name, String source){
        super(name, source);
    }
}
