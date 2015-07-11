
package be.ugent.mmlab.rml.model.termMap;

/**
 * RML - Model : Reference Map
 * 
 * This interface offers a method for replacing a template with the expression it holds
 *
 * @author mielvandersande, andimou
 */
public interface ReferenceMap {

    /**
     *
     * @return
     */
    public String getReference();
    
    /**
     *
     * @param refMap
     */
    public void setReference(String reference);

    /**
     *
     * @param reference
     * @return
     */
    public ReferenceMap getReferenceValue(String reference);
    
    /**
     *
     * @param refMap
     */
    public void setReferenceValue(ReferenceMap refMap);
    
    
    
}
