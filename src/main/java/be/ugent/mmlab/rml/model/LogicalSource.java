package be.ugent.mmlab.rml.model;

import be.ugent.mmlab.rml.vocabularies.QLVocabulary;

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
     * Every Logical Source has an Input Source
     * where the data reside.
     *
     * @return InputSource
     */
    public Source getSource();
    
    /**
     *
     * @param inputSource
     */
    public void setSource(Source source);

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
    public String getSourceType(Source source);
    
}
