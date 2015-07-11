package be.ugent.mmlab.rml.model;

import be.ugent.mmlab.rml.vocabulary.QLVocabulary;

/**
 * *************************************************************************
 *
 * RML - Model : LogicalSource
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface LogicalSource {

    /**
     * 
     * Every logical source has an Iterator.
     * The Iterator can be skipped when the data have tabular structure
     * and the iteration is implied that it is per row.
     * 
     * @return String
     * 
     */
    public String getIterator();
    
    /**
     *
     * @param iterator
     */
    public void setIterator(String iterator);

    /**
     * 
     * Every Logical Source refers to a source. 
     * 
     * @return String
     *
     */
    public String getSource();
    
    /**
     *
     * @param source
     */
    public void setSource(String source);
    
    /**
     * 
     * Every Logical Source has an Input Source
     * where the data reside.
     *
     * @return InputSource
     */
    public InputSource getInputSource();
    
    /**
     *
     * @param inputSource
     */
    public void setInputSource(InputSource inputSource);

    /**
     * 
     * Every Logical Source has a Reference Formulation 
     * that specifies the grammar to refer to the input data.
     * 
     * @return QLVocabulary.QLTerm
     * 
     */
    public QLVocabulary.QLTerm getReferenceFormulation();
    
    /**
     *
     * @param referenceFormulation
     * 
     */
    public void setReferenceFormulation(QLVocabulary.QLTerm referenceFormulation);
    
    /**
     *
     * @param inputSource
     * @return
     */
    public String getInputSourceType(InputSource inputSource);
    
}
