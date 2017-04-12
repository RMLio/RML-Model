package be.ugent.mmlab.rml.model.std;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML - Model : Template Map Implementation
 * 
 * This class holds an expression that refers to a certain value.
 *
 * @author mielvandersande, andimou
 */
public class StdTemplateMap {

    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(StdTemplateMap.class.getSimpleName());
    private String template = null;

    public StdTemplateMap(String template) {
        this.template = template;
    }

    /**
     * Build a Reference Identifier from a RML config file.
     *
     * @param referenceName The reference.
     * @return
     */
        public static StdReferenceMap getReferenceValue(String reference) {
        if (reference == null) {
            return null;
        }

        return new StdReferenceMap(reference);
    }

        public String replaceAll(String input, String replaceValue) {
        // Try simple replace...
        String localResult = input.replaceAll("\\{" + template + "\\}",
                replaceValue);
        // Must have replaced something
        assert !localResult.equals(input) : ("Impossible to replace "
                + template + " in " + input);
        return localResult;
    }

    @Override
    public String toString() {
        return template;
    }

    /**
     *
     * @param stringTemplate
     * @return
     */
    public static Set<String> extractVariablesFromStringTemplate(
            String stringTemplate) {
        Set<String> result = new HashSet<String>();
        // Curly braces that do not enclose column names MUST be
        stringTemplate = stringTemplate.replaceAll("\\\\\\{", "");
        stringTemplate = stringTemplate.replaceAll("\\\\\\}", "");
        if (stringTemplate != null) {
            StringTokenizer st = new StringTokenizer(stringTemplate, "{}", true);
            boolean keepNext = false;
            String next = null;
            while (st.hasMoreElements()) {
                String element = st.nextElement().toString();
                if (keepNext) {
                    next = element;
                }
                keepNext = element.equals("{");
                if (element.equals("}") && element != null) {
                    log.debug(
                            Thread.currentThread().getStackTrace()[1].getMethodName() + ": " 
                            + "Extracted variable "
                            + next + " from string template " + stringTemplate);
                    result.add(next);
                    next = null;
                }
            }
        }
        return result;
    }

}
