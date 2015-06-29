/**
 * *************************************************************************
 *
 * RML Model : Abstract Term Map
 *
 * Partial implementation of a term map.
 *
 * @author mielvandersande, andimou
 *
 ***************************************************************************
 */
package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.std.StdObjectMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import be.ugent.mmlab.rml.model.termMap.TemplateMap;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

public abstract class AbstractTermMap implements TermMap {

        // Log
        private static final Logger log = LogManager.getLogger(AbstractTermMap.class);
        
        private URI dataType; 
        private TermType termType;
        private URI implicitDataType; 
        private String languageTag;
        private String stringTemplate;
        private String inverseExpression;
        protected TriplesMap ownTriplesMap;
        
        private Value constantValue;
        private TemplateMap templateValue;
        private ReferenceMap referenceValue;
        

        protected AbstractTermMap(Value constantValue, URI dataType,
                String languageTag, String stringTemplate, URI termType,
                String inverseExpression, ReferenceMap referenceValue) {

                setConstantValue(constantValue);
                setReferenceMap(referenceValue);
                setTemplateMap(templateValue);
                setLanguageTag(languageTag);
                setStringTemplate(stringTemplate);
                setTermType(termType, dataType);
                setDataType(dataType);
                setInversionExpression(inverseExpression);
                checkGlobalConsistency();
                setOwnTriplesMap(ownTriplesMap);
        }

        /**
         * Check if the global structure of this TermMap is consistent and valid
         * according to R2RML standard.
         *
         */
        private void checkGlobalConsistency() {
        // A term map must be exactly one term map type
            if (getTermMapType() == null) // In db2triples and contrary to the R2RML norm, we accepts
            // auto-assignments of blank nodes.
            {
                if (getTermType() != TermType.BLANK_NODE) {
                    log.error("Invalid [R2]RML Structure "
                            + "[AbstractTermMap:checkGlobalConsistency] A constant RDF Term,"
                            + " a column name or a string template must be specified.");
                }
            }

        }

        private void setInversionExpression(String inverseExpression) {
        // An inverse expression is associated with
        // a column-valued term map or template-value term map
            if (inverseExpression != null && getTermMapType() != null
                    && getTermMapType() == TermMapType.CONSTANT_VALUED) {
                log.error("Invalid Structure "
                        + "[AbstractTermMap:setInversionExpression] An inverseExpression "
                        + "can not be associated with a constant-value term map.");
            }
            // This property is optional
            if (inverseExpression != null) {
                checkInverseExpression(inverseExpression);
            }
            this.inverseExpression = inverseExpression;
        }

        private void checkInverseExpression(String inverseExpression) {
            // An inverse expression must satisfy a lot of conditions
            /*if (!R2RMLToolkit.checkInverseExpression(inverseExpression)) {
                log.error("InvalidR2RMLSyntaxException "
                        + "[AbstractTermMap:checkInverseExpression] Not a valid inverse "
                        + "expression : " + stringTemplate);
            }*/

        }

        /*private void setReferenceValue(ReferenceValuedTermMap referenceValue) {
            // The value of the rml:reference property MUST be a valid reference for this queryLanguage.
            //		if (columnValue != null)
            //			checkColumnValue(columnValue);
            this.referenceValue = referenceValue;
        }*/

//	private void checkColumnValue(String columnValue)
//			throws InvalidR2RMLSyntaxException {
//		if (!SQLDataValidator.isValidSQLIdentifier(columnValue))
//			throw new InvalidR2RMLSyntaxException(
//					"[AbstractTermMap:checkColumnValue] Not a valid column "
//							+ "value : " + termType);
//	}
        
     /**
     *
     * @param termType
     * @param dataType
     */
    protected void setTermType(URI termType, URI dataType) {
        if (termType == null) {
            // If the term map does not have a rr:termType property :
            // rr:Literal by default, if it is an object map and at
            // least one of the following conditions is true
            if ((this instanceof StdObjectMap)
                    && (getReferenceMap() != null || dataType != null
                    || getLanguageTag() != null || constantValue instanceof Literal)) {
                this.termType = TermType.LITERAL;
                log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                        + "[AbstractTermMap:setTermType] No term type specified : use Literal by default.");
            } else {
                // otherwise its term type is IRI
                this.termType = TermType.IRI;
                log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                        + "[AbstractTermMap:setTermType] No term type specified : use IRI by default."
                        + getReferenceMap());
            }

        } else {
            this.termType = checkTermType(termType);
        }
    }

        private TermType checkTermType(URI termType) {
        // Its value MUST be an IRI
            /*if (!RDFDataValidator.isValidURI(termType.stringValue())) {
                log.error("Data Error"
                        + "[AbstractTermMap:checkTermType] Not a valid URI : "
                        + termType);
            }*/
            // (IRIs, blank nodes or literals)
            TermType tt = TermType.toTermType(termType.stringValue());
            if (tt == null) {
                log.error("Invalid Syntax "
                        + "[AbstractTermMap:checkTermType] Not a valid term type : "
                        + termType);
            }
            // Check rules in function of term map nature (subject, predicate ...)
            checkSpecificTermType(tt);
            return tt;
        }

        /**
        *
        * @param tt
        */
       protected abstract void checkSpecificTermType(TermType tt);

        @Override
        public void setStringTemplate(String stringTemplate) {
        // he value of the rr:template property MUST be a
        // valid string template.
            if (stringTemplate != null) {
                checkStringTemplate(stringTemplate);
            }

            this.stringTemplate = stringTemplate;
        }

        /**
         * A string template is a format string that can be used to build
         * strings from multiple components. It can reference column names by
         * enclosing them in curly braces.
         *
         */
        private void checkStringTemplate(String stringTemplate) {
        // Its value MUST be an IRI
            /*if (!R2RMLToolkit.checkStringTemplate(stringTemplate)) {
                log.error("Invalid Syntax "
                        + "[AbstractTermMap:checkStringTemplate] Not a valid string "
                        + "template : " + stringTemplate);
            }*/
        }

        @Override
        public void setLanguageTag(String languageTag) {
            // its value MUST be a valid language tag
            if (languageTag != null) {
                checkLanguageTag(languageTag);
            }
            this.languageTag = languageTag;
        }
        
        /**
         * Check if language tag is valid, as defined by [RFC-3066]
         *
         */
        private void checkLanguageTag(String languageTag) {
        // Its value MUST be an IRI
            /*if (!RDFDataValidator.isValidLanguageTag(languageTag)) {
                log.error("Data Error"
                        + "[AbstractTermMap:checkLanguageTag] Not a valid language tag : "
                        + languageTag);
            }*/
        }

        /**
         * Check if constant value is correctly defined. Constant value is an
         * IRI or literal in function of this term map type.
         */
        protected abstract void checkConstantValue(Value constantValue);
                

        /**
        *
        * @param constantValue
        */
        public void setConstantValue(Value constantValue) {
        // Check if constant value is valid
            if (constantValue != null) {
                checkConstantValue(constantValue);
            }
            this.constantValue = constantValue;
        }

        /**
         * Check if datatype is correctly defined.
         *
         */
        public void checkDataType(URI dataType) {
                // Its value MUST be an IRI
                //MVS: class below prevents datatypes other than XSD
                //if (!RDFDataValidator.isValidDatatype(dataType.stringValue())) {
                
        }

        /**
        *
        * @param dataType
        */
        public void setDataType(URI dataType) {
            if (!isTypeable() && dataType != null) {
                log.error("Invalid Structure "
                        + "[AbstractTermMap:setDataType] A term map that is not "
                        + "a typeable term map MUST NOT have an rr:datatype"
                        + " property.");
            }
            if (dataType != null) {
                // Check if datatype is valid
                checkDataType(dataType);
                //this.dataType = new dataType.stringValue();
                this.dataType = dataType;
            }
        }
        
        /**
        *
        * @param ownTriplesMap
        */
        public void setOwnTriplesMap(TriplesMap ownTriplesMap) {
            this.ownTriplesMap = ownTriplesMap;
        }
        
        @Override
        public Value getConstantValue() {
            return constantValue;
        }

        @Override
        public URI getDataType() {
            return dataType;
        }

        @Override
        public URI getImplicitDataType() {
            return implicitDataType;
        }

        /*public XSDLexicalTransformation.Transformation getImplicitTransformation() {
            if (implicitDataType == null) {
                return null;
            } else {
                return XSDLexicalTransformation
                        .getLexicalTransformation(XSDType.toXSDType(implicitDataType.stringValue()));
            }
        }*/

        @Override
        public String getInverseExpression() {
            return inverseExpression;
        }

        @Override
        public String getLanguageTag() {
            return languageTag;
        }

        //@Override
        public Set<ReferenceMap> getReferencedSelectors() {
        Set<ReferenceMap> references = new HashSet<ReferenceMap>();
        switch (getTermMapType()) {
            case CONSTANT_VALUED:
                // The referenced columns of a constant-valued term map is the
                // empty set.
                break;

            case REFERENCE_VALUED:
                // The referenced columns of a column-valued term map is
                // the singleton set containing the value of rr:column.
                // referencedColumns.add(R2RMLToolkit.deleteBackSlash(columnValue));
                references.add(referenceValue);
                break;

            case TEMPLATE_VALUED:
                // The referenced columns of a template-valued term map is
                // the set of column names enclosed in unescaped curly braces
                // in the template string.
                
                for (String colName : templateValue.extractVariablesFromStringTemplate(stringTemplate)) {
                    references.add(templateValue.getReferenceValue(colName));
                }
                break;

            default:
                break;
        }
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + ": "
                + "[AbstractTermMap:getReferencedSelectors] References are now : "
                + references);
        return references;
    }

        @Override
        public String getStringTemplate() {
            return stringTemplate;
        }

        @Override
        public TermMapType getTermMapType() {
            if (constantValue != null) {
                return TermMapType.CONSTANT_VALUED;
            } else if (referenceValue != null) {
                return TermMapType.REFERENCE_VALUED;
            } else if (stringTemplate != null) {
                return TermMapType.TEMPLATE_VALUED;
            } else if (termType == TermType.BLANK_NODE) {
                return TermMapType.NO_VALUE_FOR_BNODE;
            }
            return null;
        }

        @Override
        public TermType getTermType() {
            return termType;
        }

        @Override
        public ReferenceMap getReferenceMap() {
            return referenceValue;
        }

        @Override
        public boolean isOveridden() {
            if (implicitDataType == null) {
                // No implicit datatype extracted for yet
                // Return false by default
                return false;
            }
            return dataType != implicitDataType;
        }

        @Override
        public boolean isTypeable() {
            return (termType == TermType.LITERAL) && (languageTag == null);
        }

        /**
        *
        * @param implicitDataType
        */
        @Override
        public void setImplicitDataType(URI implicitDataType) {
            this.implicitDataType = implicitDataType;
        }
        
        /**
        *
        * @return this.ownTriplesMap
        */
        @Override
        public TriplesMap getOwnTriplesMap(){
            return this.ownTriplesMap;
        }
        
            @Override
    public void setTermMapType(TermMapType termMapType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReferenceMap(ReferenceMap reference) {
        referenceValue = reference;
    }

    @Override
    public TemplateMap getTemplateMap() {
        return templateValue;
    }

    @Override
    public void setTemplateMap(TemplateMap template) {
        templateValue = template;
    }


}
