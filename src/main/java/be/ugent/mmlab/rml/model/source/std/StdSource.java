package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.Source;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML - Model : Std Source
 *
 * @author andimou
 */
public class StdSource implements Source{
    
    // Log
    private static final Logger log = LoggerFactory.getLogger(StdSource.class);
    
    private String name;
    protected String template;
    private Set<TriplesMap> triplesMaps;

    /**
     *
     * @param name
     */
    public StdSource(String name) {
        setName(name);
    }

    /**
     *
     * @param name
     * @param source
     */
    public StdSource(String name, String template) {
        setName(name);
        setTemplate(template);
    }

    /**
     *
     * @param name
     */
    private void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }
    
    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }
    
    public final void setTemplate(String template){
        this.template = template;
    }
    
    public String getTemplate(){
        return this.template;
    }
    
    //@Override
    /*public void setSource(String source) {
        if (source != null) {
            this.source = source;
        }
    }
    
    //@Override
    public String getSource(){
        return this.source;
    }

    /**
     *
     * @param triplesMap
     */
    public void setTriplesMap(TriplesMap triplesMap) {
        if (triplesMap != null) {
            triplesMaps.add(triplesMap);
        }
    }

    public Set<TriplesMap> getTriplesMap() {
        return this.triplesMaps;
    }

}
