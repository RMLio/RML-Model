package be.ugent.mmlab.rml.extraction;

import be.ugent.mmlab.rml.vocabularies.R2RMLVocabulary;
import be.ugent.mmlab.rml.vocabularies.RMLVocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.rdf4j.model.URI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;

/**
 * *************************************************************************
 *
 * RML - Mapping Document Handler : RMLTermExtractor
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public class RMLTermExtractor {

    // Log
    static final Logger log = LoggerFactory.getLogger(
            RMLTermExtractor.class.getSimpleName());

    public static URI getTermURI(Repository repository, Enum term) {
        String namespace = R2RMLVocabulary.R2RML_NAMESPACE;
        ValueFactory vf ;
        URI uri = null;
        try {
            RepositoryConnection connection = repository.getConnection();
            vf = connection.getValueFactory();
            

            if (term instanceof RMLVocabulary.RMLTerm) {
                namespace = RMLVocabulary.RML_NAMESPACE;
            } else if (!(term instanceof R2RMLVocabulary.R2RMLTerm)) {
                log.error(term + " is not valid.");
            }
            uri = vf.createURI(namespace + term);

            connection.close();
        } catch (RepositoryException ex) {
            log.error("RepositoryException " + ex);
        }
        return uri;
    }
}
