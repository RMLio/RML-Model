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
 * A Term Map generates an RDF term from a logical source expression. 
 * 
 * @author andimou, mielvandersande
 * 
 ***************************************************************************
 */
public interface TermMap {

    /**
     * A term map must be exactly one of the following types: 
     * CONSTANT_VALUED REFERENCE_VALUED TEMPLATE_VALUED
     */
    public enum TermMapType {
        // A constant-valued term map is a term map 
        // which always generates the same RDF term
        // equal to a constant value
        CONSTANT_VALUED,
        // A reference-valued term map is a term map that is represented by a
        // resource that has exactly one rml:reference property.
        REFERENCE_VALUED,
        // A template-valued term map is a term map that is represented by a
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
     * The referenced columns of a term map are the set of references referenced
     * in the term map and depend on the type of term map.
     */
    //public Set<ReferenceValuedTermMap> getReferencedSelectors();

    /**
     * The constant value of a constant-valued term map is the RDF term that is
     * the value of its rr:constant property. Only if CONSTANT_VALUED type.
     */
    public Value getConstantValue();
    
    /**
     *
     * @param value
     */
    public void setConstantValue(Value value);

    /**
     * The value of the term map is the data value of that reference. Only if
     * REFERENCE_VALUED type.
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
     * If the term map has an optional rr:termType property, then its term type
     * is the value of that property.
     */
    public TermType getTermType();

    /**
     * A term map with a term type of rr:Literal MAY have a specified language
     * tag. It must be valid too.
     */
    public String getLanguageTag();
    
    /**
     *
     * @param language
     */
    public void setLanguageTag(String language);

    /**
     * A typeable term map is a term map with a term type of rr:Literal that
     * does not have a specified language tag.
     */
    public boolean isTypeable();

    /**
     * Typeable term maps may generate typed literals. The datatype of these
     * literals can be explicitly specified using rr:datatype.
     */
    public URI getDataType();
    
    /**
     *
     * @param uri
     */
    public void setDataType(URI uri);

    /**
     * A typeable term map has an implicit datatype. If the term map is a
     * column-valued term map, then the implicit datatype is the corresponding
     * RDF datatype of the respective reference in the logical source.
     * Otherwise, the term map must be a template-valued term map and its
     * implicit datatype is empty
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

    /**
     * A typeable term map has an implicit datatype and an implicit transform.
     */
    //public XSDLexicalTransformation.Transformation getImplicitTransformation();

    /**
     * An inverse expression is a string template associated with a
     * column-valued term map or template-value term map. It is represented by
     * the value of the rr:inverseExpression property.
     *
     * Inverse expressions are useful for optimizing term maps that reference
     * derived columns in R2RML views.
     *
     * An inverse expression MUST satisfy some conditions. (see ref.)
     */
    public String getInverseExpression();
    //TODO:move the followings separately
    /*
     * 
     * See what we do with this underneath!!!!!!!
     * 
     */
    /**
     * The generated RDF term of a term map for a given logical source is
     * determined as follows: If the term map is a constant-valued term map,
     * then the generated RDF term is the term map's constant value. If the term
     * map is a column-valued term map, then the generated RDF term is
     * determined by applying the term generation rules to its column value. If
     * the term map is a template-valued term map, then the generated RDF term
     * is determined by applying the term generation rules to its template
     * value.
     *
     * @param dbValues
     * @return
     */
    //public String getValue(Map<ColumnIdentifier, byte[]> dbValues, ResultSetMetaData dbTypes) ;
}
