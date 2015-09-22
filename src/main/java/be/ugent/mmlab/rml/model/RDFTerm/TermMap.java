package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import be.ugent.mmlab.rml.model.termMap.TemplateMap;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

/**
 *************************************************************************
 *
 * RML - Model : TermMap Interface
 *
 * A Term Map generates an RDF term from a Logical Source. 
 * 
 * @author andimou, mielvandersande
 * 
 ***************************************************************************
 */
public interface TermMap {

    /**
     * A Term Map can be one of the following:
     * Subject Map Predicate Map Object Map or Graph Map.
     * A Term Map must be exactly one of the following types: 
     * CONSTANT_VALUED REFERENCE_VALUED TEMPLATE_VALUED
     */
    public enum TermMapType {
        // A constant-valued Term Map always generates the same RDF term
        // which is equal to the provided constant value
        CONSTANT_VALUED,
        // A reference-valued Term Map is a Term Map that is represented by a
        // resource that has exactly one rml:reference property.
        REFERENCE_VALUED,
        // A template-valued Term Map is a Term Map that is represented by a
        // resource that has exactly one rr:template property
        TEMPLATE_VALUED,
        // auto-assignments of blank nodes.
        NO_VALUE_FOR_BNODE
    }

    /**
     *
     * @return TriplesMap
     * 
     */
    public TriplesMap getOwnTriplesMap();
    
    /**
     *
     * @param ownTriplesMap
     */
    public void setOwnTriplesMap(TriplesMap ownTriplesMap);

    /**
     *
     * @return
     */
    public TermMapType getTermMapType();
    
    /**
     *
     * @param termMapType
     */
    public void setTermMapType(TermMapType termMapType);

    /**
     * The constant value of a constant-valued Term Map is the RDF term 
     * that is the value of its rr:constant property. 
     */
    public Value getConstantValue();
    
    /**
     *
     * @param value
     */
    public void setConstantValue(Value value);

    /**
     * The value of the Term Map is the data value of that reference. 
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
    public TemplateMap getTemplateMap();
    
    /**
     *
     * @param refrence
     */
    public void setTemplateMap(TemplateMap template);

    /**
     * The value of the rr:template property MUST be a valid string template. A
     * string template is a format string that can be used to build strings from
     * multiple components. It can reference column names by enclosing them in
     * curly braces. Only if TEMPLATE_VALUED type.
     */
    public String getStringTemplate();
    
    /**
     *
     * @param template
     */
    public void setStringTemplate(String template);

    /**
     * If the Term Map has an optional rr:termType property, 
     * then its term type is the value of that property.
     */
    public TermType getTermType();

    /**
     * A Term Map with a term type of rr:Literal MAY have a specified language
     * tag. It must be valid too.
     */
    public String getLanguageTag();
    
    /**
     *
     * @param language
     */
    public void setLanguageTag(String language);
    
    /**
     *
     * @return
     */
    public LanguageMap getLanguageMap();
    
    /**
     *
     */
    public void setLanguageMap(LanguageMap languageMap);

    /**
     * A typeable Term Map is a Term Map with a term type of rr:Literal that
     * does not have a specified language tag.
     */
    public boolean isTypeable();

    /**
     * Typeable Term Maps may generate typed literals. 
     * The datatype of these literals can be explicitly specified using rr:datatype.
     */
    public URI getDataType();
    
    /**
     *
     * @param uri
     */
    public void setDataType(URI uri);

    /**
     * A typeable Term Map has an implicit datatype. 
     * If the Term Map is a reference-valued Term Map, 
     * then the implicit datatype is the corresponding
     * RDF datatype of the respective reference in the Logical Source.
     * Otherwise, the Term Map must be a template-valued term map 
     * and its implicit datatype must be empty.
     */
    public URI getImplicitDataType();
    
    /**
     *
     * @param uri
     */
    public void setImplicitDataType(URI uri);

    /**
     * A datatype override is in effect on a typeable term map if it has a
     * specified datatype, and the specified datatype is different from its
     * implicit datatype.
     */
    public boolean isOveridden();

}
