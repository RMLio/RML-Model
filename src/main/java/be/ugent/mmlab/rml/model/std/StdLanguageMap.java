package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.RDFTerm.LanguageMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import org.openrdf.model.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class StdLanguageMap implements LanguageMap {
    
    // Log
        private static final Logger log = 
                LoggerFactory.getLogger(StdLanguageMap.class);
    
    private String stringTemplate;
    private Value constantValue;
    private ReferenceMap referenceMap;
    private LanguageMapType languageType;
    
    public StdLanguageMap(
            Value constantValue, String template, ReferenceMap reference){
        setConstantValue(constantValue);
        setStringTemplate(template);
        setReferenceMap(reference);
    }

    @Override
    public Value getConstantValue() {
        return this.constantValue;
    }

    @Override
    public void setConstantValue(Value value) {
            this.constantValue = value;
    }

    @Override
    public ReferenceMap getReferenceMap() {
        return this.referenceMap;
    }

    @Override
    public void setReferenceMap(ReferenceMap reference) {
        this.referenceMap = reference;
    }

    @Override
    public String getStringTemplate() {
        return this.stringTemplate;
    }

    @Override
    public void setStringTemplate(String template) {
        this.stringTemplate = template;
    }

    @Override
    public LanguageMap.LanguageMapType getLanguageMapType() {
        if (constantValue != null) {
            return LanguageMap.LanguageMapType.CONSTANT_VALUED;
        } else if (referenceMap != null) {
            return LanguageMap.LanguageMapType.REFERENCE_VALUED;
        } else if (stringTemplate != null) {
            return LanguageMap.LanguageMapType.TEMPLATE_VALUED;
        } 
        return null;
    }

}
