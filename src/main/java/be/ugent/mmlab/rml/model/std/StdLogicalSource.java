package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.InputSource;
import be.ugent.mmlab.rml.model.LogicalSource;
import be.ugent.mmlab.rml.vocabulary.QLVocabulary.QLTerm;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *  RML - Model : Logical Source Implementation
 * 
 * @author mielvandersande, andimou
 * 
 */
public class StdLogicalSource implements LogicalSource {

    //Log
    private static final Logger log = LoggerFactory.getLogger(StdLogicalSource.class);
    
    private String iterator;
    private QLTerm referenceFormulation = QLTerm.SQL_CLASS;
    private String source;
    private InputSource inputSource;

    /**
     *
     * @param inputSource
     * @param referenceFormulation
     * 
     * Constructor for tabular-structured data 
     * where the Iteration is known
     * and the source is described by a Resource.
     * 
     */
    public StdLogicalSource(InputSource inputSource, QLTerm referenceFormulation) {
        setReferenceFormulation(referenceFormulation);
        setInputSource(inputSource);
    }
    
    /**
     * 
     * Constructor for tabular-structured data 
     * where the Iteration is known
     * and the source is described by a Literal.
     *
     * @param source
     * @param referenceFormulation
     * 
     */
    public StdLogicalSource(String source, QLTerm referenceFormulation) {
        setReferenceFormulation(referenceFormulation);
        setSource(source);
    }

    /**
     *
     * @param iterator
     * @param inputSource
     * @param referenceFormulation
     * 
     */
    public StdLogicalSource(String iterator, InputSource inputSource, QLTerm referenceFormulation) {
        setIterator(iterator);
        setInputSource(inputSource);
        setReferenceFormulation(referenceFormulation);
    }
    
    /**
     *
     * @param iterator
     * @param source
     * @param referenceFormulation
     * 
     */
    public StdLogicalSource(String iterator, String source, QLTerm referenceFormulation) {
        setIterator(iterator);
        setSource(source);
        setReferenceFormulation(referenceFormulation);
    }

    @Override
    public String getIterator() {
        return iterator;
    }

    @Override
    public void setIterator(String iterator) {
        this.iterator = iterator;
    }

    @Override
    public QLTerm getReferenceFormulation() {
        return referenceFormulation;
    }

    @Override
    public void setReferenceFormulation(QLTerm referenceFormulation) {
        this.referenceFormulation = referenceFormulation;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public InputSource getInputSource() {
        return inputSource;
    }

    @Override
    public void setInputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }
    
    @Override
    public String getInputSourceType(InputSource inputSource) {
        log.info("Input source " + inputSource.getClass().getSimpleName());
        if (inputSource.getClass().getSimpleName().equals("String")) {
            return Literal.class.getSimpleName();
        } //object input
        else {
            log.info("Input source " + inputSource.getClass().getSimpleName());
            return URI.class.getSimpleName();
        }
    }

    /**
     *
     * @return String
     * 
     */
    @Override
    public String toString() {
        if (source != null) {
            return "[StdLogicalSource : iterator = " + iterator
                    + "; source " + source
                    + "; referenceFormulation = " + referenceFormulation
                    + "]";
        } else {
            return "[StdLogicalSource : Iterator = " + iterator
                    + "; Input Source " + inputSource
                    + "; Reference Formulation = " + referenceFormulation
                    + "]";
        }
    }
}
