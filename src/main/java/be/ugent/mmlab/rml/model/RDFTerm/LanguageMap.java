package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import org.openrdf.model.Value;

/**
 *************************************************************************
 *
 * RML - Model : LanguageMap Interface
 * 
 * @author andimou
 * 
 ***************************************************************************
 */

public interface LanguageMap {
    
    public enum LanguageMapType {
        CONSTANT_VALUED,
        REFERENCE_VALUED,
        TEMPLATE_VALUED
    }
    
    /**
     *
     * @return
     */
    public Value getConstantValue();

    /**
     *
     * @param value
     */
    public void setConstantValue(Value value);

    /**
     *
     * @return
     */
    public ReferenceMap getReferenceMap();

    /**
     *
     * @param refrence
     */
    public void setReferenceMap(ReferenceMap refrence);

    /**
     *
     * @return
     */
    public String getStringTemplate();

    /**
     *
     * @param template
     */
    public void setStringTemplate(String template);
    
    /**
     *
     * @param languageMapType
     */
    public LanguageMapType getLanguageMapType();

}
