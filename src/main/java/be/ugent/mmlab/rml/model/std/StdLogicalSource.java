package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.Source;
import be.ugent.mmlab.rml.model.LogicalSource;
import be.ugent.mmlab.rml.model.ReferenceFormulation;
import be.ugent.mmlab.rml.vocabularies.QLVocabulary.QLTerm;
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
    private Source source;
    private ReferenceFormulation dialect = null; //custom reference formulation

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
    public StdLogicalSource(Source inputSource, QLTerm referenceFormulation) {
        setReferenceFormulation(referenceFormulation);
        setSource(inputSource);
    }

    /**
     *
     * @param iterator
     * @param inputSource
     * @param referenceFormulation
     * 
     */
    public StdLogicalSource(String iterator, Source inputSource, QLTerm referenceFormulation) {
        setIterator(iterator);
        setSource(inputSource);
        setReferenceFormulation(referenceFormulation);
    }
    
    public StdLogicalSource(String iterator, Source inputSource, 
            QLTerm referenceFormulation, ReferenceFormulation dialect) {
        setIterator(iterator);
        setSource(inputSource);
        setReferenceFormulation(referenceFormulation);
        setCustomReferenceFormulation(dialect);
    }

    @Override
    public String getIterator() {
        return iterator;
    }

    @Override
    public final void setIterator(String iterator) {
        this.iterator = iterator;
    }

    @Override
    public QLTerm getReferenceFormulation() {
        return referenceFormulation;
    }

    @Override
    public final void setReferenceFormulation(QLTerm referenceFormulation) {
        this.referenceFormulation = referenceFormulation;
    }
    
    @Override
    public ReferenceFormulation getCustomReferenceFormulation() {
        return dialect;
    }

    @Override
    public final void setCustomReferenceFormulation(ReferenceFormulation dialect) {
        this.dialect = dialect;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public final void setSource(Source inputSource) {
        this.source = inputSource;
    }
    
    @Override
    public String getSourceType(Source source) {
        log.info("Input source " + source.getClass().getSimpleName());
        if (source.getClass().getSimpleName().equals("String")) {
            return Literal.class.getSimpleName();
        } //object input
        else {
            log.info("Input source " + source.getClass().getSimpleName());
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
                    + "; Input Source " + source
                    + "; Reference Formulation = " + referenceFormulation
                    + "]";
        }
    }
}
