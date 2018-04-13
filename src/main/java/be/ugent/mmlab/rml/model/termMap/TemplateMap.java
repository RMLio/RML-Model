package be.ugent.mmlab.rml.model.termMap;

import java.util.Set;

/**
 *************************************************************************
 *
 * RML - Model : Template Valued Term Map Interface
 * 
 * @author andimou
 *
 ***************************************************************************
 */
public interface TemplateMap {

    /**
     * Made a replaceAll on the input String to replace all occurrence of the
     * "{parameter}" in.
     *
     * @param input The input String
     * @return
     */
    public String replaceAll(String input, String replaceValue);

    /**
     *
     * @param reference
     * @return
     */
    public ReferenceMap getReferenceValue(String refrence);
    
    /**
     *
     * @param stringTemplate
     * @return
     */
    public Set<String> extractVariablesFromStringTemplate(String stringTemplate);
}
