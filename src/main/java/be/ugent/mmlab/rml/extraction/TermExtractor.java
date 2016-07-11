package be.ugent.mmlab.rml.extraction;

import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.model.std.StdReferenceMap;
import be.ugent.mmlab.rml.model.termMap.ReferenceMap;
import be.ugent.mmlab.rml.vocabularies.R2RMLVocabulary;
import be.ugent.mmlab.rml.vocabularies.RMLVocabulary;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

/**
 * *************************************************************************
 *
 * RML - Mapping Document Handler : TermMapExtractor
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public class TermExtractor {
    
    // Log
    static final Logger log = LoggerFactory.getLogger(
            TermExtractor.class.getSimpleName());
    
    /**
     *
     * @param termType
     * @param term
     * @param triplesMap
     * @return
     */
    static public Value extractValueFromTermMap(
            Repository repository, Resource termType, Enum term, TriplesMap triplesMap) {
        RepositoryResult<Statement> statements ;
        Value value = null;
        try {
            RepositoryConnection connection = repository.getConnection();
            
            statements = connection.getStatements(
                    termType, RMLTermExtractor.getTermURI(repository, term), null, true);
            
            if (!statements.hasNext()) 
                return null;
            else{
                Statement statement = statements.next();
                log.debug("Extracted " + term + " : " 
                        + statement.getObject().stringValue());
                value = statement.getObject();
            }
            connection.close();
        } catch (RepositoryException ex) {
            log.error("RepositoryException " + ex);
        }
        return value;
    }
    
    static public Value extractValueFromTermMap(
            Repository repository, Resource termType, URI uri, TriplesMap triplesMap) {
        RepositoryResult<Statement> statements ;
        Value value = null;
        try {
            RepositoryConnection connection = repository.getConnection();
            
            statements = connection.getStatements(
                    termType, uri, null, true);
            
            if (!statements.hasNext()) 
                return null;
            else{
                Statement statement = statements.next();
                log.debug("Extracted " + uri.stringValue() );
                value = statement.getObject();
            }
            connection.close();
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        }
        return value;
    }
    
    public static Set<Value> extractValuesFromResource(
            Repository repository, Resource termType, Enum term){
        RepositoryResult<Statement> statements ;
        Set<Value> values = new HashSet<Value>();
        try {
            RepositoryConnection connection = repository.getConnection();
            URI p = RMLTermExtractor.getTermURI(repository, term);
            statements = connection.getStatements(termType, p, null, true);
            
            while (statements.hasNext()) {
                Value value = statements.next().getObject();
                log.debug("Extracted "
                        + term + " : " + value.stringValue());
                values.add(value);
            }
            connection.close();
        } catch (RepositoryException ex) {
            log.error("RepositoryException " + ex);
        }
        return values;
    }
    
    static public String extractLiteralFromTermMap(
            Repository repository, Resource termType, Enum term, TriplesMap triplesMap) {
        RepositoryResult<Statement> statements;
        String result = null;
        try {
            RepositoryConnection connection = repository.getConnection();
            statements = connection.getStatements(
                    termType, RMLTermExtractor.getTermURI(repository, term), null, true);
            
            if (statements.hasNext()) {
                result = statements.next().getObject().stringValue();
                if (log.isDebugEnabled()) {
                    log.debug("Extracted "
                            + term + " : " + result);
                }
            }
            connection.close();
        } catch (RepositoryException ex) {
            log.error("RepositoryException " + ex);
        }
        return result;
    }   
    
    public static Set<URI> extractURIsFromTermMap(
            Repository repository, Resource termType, R2RMLVocabulary.R2RMLTerm term){
        Set<URI> uris = new HashSet<URI>();
        RepositoryResult<Statement> statements;
        try {
            RepositoryConnection connection = repository.getConnection();    
            URI p = RMLTermExtractor.getTermURI(repository, term);
            statements = connection.getStatements(termType, p, null, true);
            
            while (statements.hasNext()) {
                URI uri = (URI) statements.next().getObject();
                log.debug(term + " : " + uri.stringValue());
                uris.add(uri);
            }
            connection.close();
        } catch (RepositoryException ex) {
            log.error("RepositoryException " + ex);
        }
        return uris;
    } 
    
    /**
     *
     * @param resource
     * @param triplesMap
     * @return
     */
    public ReferenceMap extractReferenceIdentifier(
            Repository repository, Resource resource, TriplesMap triplesMap) {

        String columnValueStr = extractLiteralFromTermMap(
                repository, resource, R2RMLVocabulary.R2RMLTerm.COLUMN, triplesMap);
        String referenceValueStr = extractLiteralFromTermMap(
                repository, resource, RMLVocabulary.RMLTerm.REFERENCE, triplesMap);

        if (columnValueStr != null && referenceValueStr != null) {
            log.error(resource
                    + " has a reference and column defined.");
        }
        
        if (columnValueStr != null) {
            ReferenceMap refMap = new StdReferenceMap(columnValueStr);
            return refMap.getReferenceValue(columnValueStr);
        }
        ReferenceMap refMap = new StdReferenceMap(referenceValueStr);

        return refMap.getReferenceValue(referenceValueStr);

    }

}
