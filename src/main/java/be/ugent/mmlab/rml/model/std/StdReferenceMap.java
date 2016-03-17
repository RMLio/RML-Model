package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * *************************************************************************
 *
 * RML - Model : Reference Valued Term Map
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public final class StdReferenceMap implements ReferenceMap {
    
    private String reference;
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            StdReferenceMap.class.getSimpleName());
      
    //private String reference = null;
    
    @Override
    public ReferenceMap getReferenceValue(String reference) {
        if (reference == null) {
            return null;
        }
        return new StdReferenceMap(reference);
    }
    
    public void setReferenceValue(String reference){
        this.reference = reference;
    }    

    public StdReferenceMap(String reference) {
        setReference(reference);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getReference(){
        return reference;
    }
    
    @Override
    public void setReference(String reference){
        this.reference = reference;
    }    

    @Override
    public void setReferenceValue(ReferenceMap refMap) {
        
    }

}
