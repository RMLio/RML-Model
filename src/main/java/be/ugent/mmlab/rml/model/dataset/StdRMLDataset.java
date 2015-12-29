package be.ugent.mmlab.rml.model.dataset;

import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.vocabularies.PROVVocabulary;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.openrdf.model.BNode;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.BNodeImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.memory.MemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 *
 * @author andimou
 */
public class StdRMLDataset implements RMLDataset {
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(StdRMLDataset.class);
    
    protected Repository repository = null;
    protected Integer 
            distinctClasses = 0, distinctProperties = 0,
            distinctSubjects = 0, distinctObjects = 0, 
            distinctEntities = 0, triples = 0;
    protected RDFFormat format = RDFFormat.NTRIPLES;
    protected String metadataLevel = "None";
    protected String metadataFormat = null;
    protected RMLDataset metadataDataset ;
    protected String[] metadataVocab;
    
    public StdRMLDataset() {
        this(false);
    }

    public StdRMLDataset(boolean inferencing) {
        try {
            if (inferencing) {
                repository = new SailRepository(
                        new ForwardChainingRDFSInferencer(new MemoryStore()));
            } else {
                repository = new SailRepository(new MemoryStore());
            }
            repository.initialize();
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        }
    }
    
    //TODO: Spring it
    @Override
    public void add(Resource s, URI p, Value o, Resource... contexts) {
        if (log.isDebugEnabled()) {
            log.debug("Add triple (" + s.stringValue()
                    + ", " + p.stringValue() + ", " + o.stringValue() + ").");
        }
        try {
            RepositoryConnection con = repository.getConnection();
            try {
                ValueFactory myFactory = con.getValueFactory();
                Statement st = myFactory.createStatement((Resource) s, p,
                        (Value) o);
                con.add(st, contexts);
                con.commit();
            } catch (Exception ex) {
                log.error("Exception " + ex);
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
    }
    
    @Override
    public void addReification(
            Resource s, URI p, Value o, TriplesMap map, Resource... contexts) {
        
        log.debug("Add triple (" + s.stringValue()
                + ", " + p.stringValue() + ", " + o.stringValue() + ").");
        
        try {
            RepositoryConnection con = repository.getConnection();
            try {
                ValueFactory myFactory = con.getValueFactory();
                Resource triple = new BNodeImpl(
                        RandomStringUtils.randomAlphanumeric(10));
                Statement st = myFactory.createStatement(triple, RDF.TYPE,
                        RDF.STATEMENT);
                con.add(st, contexts);
                
                //Add subject
                st = myFactory.createStatement(triple, RDF.SUBJECT, (Value) s);
                con.add(st, contexts);
                
                //Add predicate
                st = myFactory.createStatement(triple, RDF.PREDICATE, p);
                con.add(st, contexts);
                
                //Add object
                st = myFactory.createStatement(triple, RDF.OBJECT, o);
                con.add(st, contexts);
                
                //Add prov:wasGeneratedBy
                //TODO:Make it point to the SM/POM after skolemization
                st = myFactory.createStatement(triple, 
                        new URIImpl(PROVVocabulary.PROV_NAMESPACE + 
                        PROVVocabulary.PROVTerm.WASGENERATEDBY.toString()),
                        new URIImpl(map.getName()));
                con.add(st, contexts);
                
                con.commit();                
            } catch (Exception ex) {
                log.error("Exception " + ex);
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
    }

    //TODO: Spring it
    @Override
    public void addFile(String filepath, RDFFormat format) {
        try {
            RepositoryConnection con = repository.getConnection();
            try {
                con.add(new File(filepath), "", format);
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
    }
    
    //TODO: Spring it
    @Override
    public void dumpRDF(OutputStream out, RDFFormat outform) {
        try {
            RepositoryConnection con = repository.getConnection();
            try {
                RDFWriter w = Rio.createWriter(outform, out);
                
                con.export(w);
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
    }

    //TODO: Spring it
    @Override
    public int getSize() {
        ArrayList<Statement> reslist = new ArrayList<Statement>();
        try {
            RepositoryConnection connection = repository.getConnection();
            try {
                RepositoryResult<Statement> repres =
                        connection.getStatements(null, null, null, true);

                while (repres.hasNext()) {
                    reslist.add(repres.next());
                }

            } finally {
                connection.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
        return reslist.size();
    }

    @Override
    public void closeRepository() {
        try {
            log.debug("Closing memory repository..");
            repository.shutDown();
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        }
    }
    
    //TODO: Spring it
    @Override
    public List<Statement> tuplePattern(Resource s, URI p, Value o,
			Resource... contexts) {
        try {
            RepositoryConnection con = repository.getConnection();
            try {
                RepositoryResult<Statement> repres = con.getStatements(s, p, o,
                        true, contexts);
                ArrayList<Statement> reslist = new ArrayList<Statement>();
                while (repres.hasNext()) {
                    reslist.add(repres.next());
                }
                return reslist;
            } finally {
                con.close();
            }
        } catch (Exception ex) {
            log.error("Exception " + ex);
        }
        return null;
    }

    public boolean isBNode(Value value) {
        try {
            @SuppressWarnings("unused")
            BNode test = (BNode) value;
            return true;
        } catch (ClassCastException ex) {
            //log.error("Class Cast Exception " + ex);
            return false;
        }
    }
    
    @Override
    public boolean isEqualTo(RMLDataset dataSet) {
        List<Statement> triples = tuplePattern(null, null, null);
        for (Statement triple : triples) {
            List<Statement> targetTriples = new ArrayList<Statement>();
            if (isBNode(triple.getSubject()) && isBNode(triple.getObject())) {
                targetTriples = tuplePattern(null,
                        triple.getPredicate(), null, triple.getContext());
                if (targetTriples.isEmpty()) {
                    log.debug("No result for triple : " + triple);
                    return false;
                } else {
                    boolean found = false;
                    Statement foundTriple = null;
                    for (Statement targetTriple : targetTriples) {
                        if (isBNode(targetTriple.getSubject())
                                && isBNode(targetTriple.getObject())) {
                            found = true;
                            foundTriple = targetTriple;
                            break;
                        }
                    }
                    if (found) {
                        log.debug(triple + " == " + foundTriple);
                    } else {
                        log.debug("No BNode subject and BNode object found for "
                                + triple);
                        return false;
                    }
                }
            } else if (isBNode(triple.getSubject())) {
                targetTriples = tuplePattern(null,
                        triple.getPredicate(), triple.getObject(),
                        triple.getContext());
                if (targetTriples.isEmpty()) {
                    log.debug("No result for subject : " + triple);
                    return false;
                } else {
                    boolean found = false;
                    Statement foundTriple = null;
                    for (Statement targetTriple : targetTriples) {
                        if (isBNode(targetTriple.getSubject())) {
                            found = true;
                            foundTriple = targetTriple;
                            break;
                        }
                    }
                    if (found) {
                        log.debug(triple + " == " + foundTriple);
                    } else {
                        log.debug("No BNode subject found for " + triple);
                        return false;
                    }
                }

            } else if (isBNode(triple.getObject())) {
                targetTriples = tuplePattern(triple.getSubject(),
                        triple.getPredicate(), null, triple.getContext());
                if (targetTriples.isEmpty()) {
                    log.debug("No result for triple : " + triple);
                    return false;
                } else {
                    boolean found = false;
                    Statement foundTriple = null;
                    for (Statement targetTriple : targetTriples) {
                        if (isBNode(targetTriple.getObject())) {
                            found = true;
                            foundTriple = targetTriple;
                            break;
                        }
                    }
                    if (found) {

                        log.debug(triple + " == " + foundTriple);
                    } else {
                        log.debug("No BNode object found for " + triple);
                        return false;
                    }
                }
            } else {
                targetTriples = tuplePattern(triple.getSubject(),
                        triple.getPredicate(), triple.getObject(),
                        triple.getContext());
                if (targetTriples.size() > 1) {
                    log.debug("Too many result for : " + triple);
                    return false;
                } else if (targetTriples.isEmpty()) {
                    log.debug("No result for triple : " + triple);
                    return false;
                } else {
                    log.debug(triple + " == " + targetTriples.get(0));
                }
            }
        }
        if (dataSet.getSize() != getSize()) {
            log.debug("No same size : " + dataSet.getSize() + " != " + getSize());
        }
        return dataSet.getSize() == getSize();
    }
   
    /**
     *
     * @return
     */
    @Override
    public int getNumberOfDistinctSubjects() {
        return distinctSubjects;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfDistinctObjects() {
        return distinctObjects;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNumberOfDistinctEntities() {
        return distinctEntities;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNumberOfTriples() {
        return triples;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNumberOfClasses() {
        return distinctClasses;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNumberOfProperties() {
        return distinctProperties;
    }
    
    protected boolean checkDistinctSubject(Resource s) {
        RepositoryConnection con = null;
        try {
            con = repository.getConnection();
            RepositoryResult<Statement> results = 
                    con.getStatements(s, null, null, true);
            if(!results.hasNext())
                return true;
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } finally {
            try {
                con.close();
            } catch (RepositoryException ex) {
                log.error("Repository Exception " + ex);
            }
        }
        return false;
    }

    protected boolean checkDistinctObject(Value o) {
        RepositoryConnection con = null;
        try {
            con = repository.getConnection();
            //TODO: Change the following to con.hasStatements
            RepositoryResult<Statement> results = 
                    con.getStatements(null, null, o, true);
            if (!results.hasNext()) {
                return true;
            }
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } finally {
            try {
                con.close();
            } catch (RepositoryException ex) {
                log.error("Repository Exception " + ex);
            }
        }
        return false;
    }
    
    protected boolean checkDistinctClass(Value o) {
        RepositoryConnection con = null;
        try {
            con = repository.getConnection();
            RepositoryResult<Statement> results = 
                    con.getStatements(null, RDF.TYPE, o, true);
            if (!results.hasNext()) {
                return true;
            }
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } finally {
            try {
                con.close();
            } catch (RepositoryException ex) {
                log.error("Repository Exception " + ex);
            }
        }
        return false;
    }
    
    protected boolean checkDistinctProperty(URI p) {
        RepositoryConnection con = null;
        try {
            con = repository.getConnection();
            RepositoryResult<Statement> results = 
                    con.getStatements(null, p, null, true);
            if (!results.hasNext()) {
                return true;
            }
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } finally {
            try {
                con.close();
            } catch (RepositoryException ex) {
                log.error("Repository Exception " + ex);
            }
        }
        return false;
    }
    
    protected void checkDistinctEntities(Resource s, URI p, Value o){
        if(checkDistinctSubject(s)){
            ++distinctSubjects;
            if(checkDistinctObject(s))
                ++distinctEntities;
        }
        
        if(checkDistinctObject(o)){
            ++distinctObjects;
            if(!o.getClass().getSimpleName().equals("LiteralImpl") 
                    && checkDistinctSubject((Resource) o))
                ++distinctEntities;
        }
        
        if(p.equals(RDF.TYPE) && checkDistinctClass(o))
            ++distinctClasses;
        if(checkDistinctProperty(p))
            ++distinctProperties;
    }
    
    @Override
    public RDFFormat getFormat(){
        return format;
    }
    
    @Override
    public void setDatasetMetadata(RMLDataset metadataDataset, 
            String metadataLevel, String metadataFormat, String metadataVocab){
        setMetadataLevel(metadataLevel);
        setMetadataFormat(metadataFormat);
        setMetadataDataset(metadataDataset);
        setMetadataVocab(metadataVocab);
    }
    
    private void setMetadataLevel(String metadataLevel){
        this.metadataLevel = metadataLevel;
    }
    
    private void setMetadataFormat(String metadataFormat){
        this.metadataFormat = metadataFormat;
    }
    
    private void setMetadataDataset(RMLDataset metadataDataset){
        this.metadataDataset = metadataDataset;
    }
    
    private void setMetadataVocab(String metadataVocab){
        String[] vocabs = metadataVocab.split(",");
        this.metadataVocab = vocabs;
    }
    
    @Override
    public String getMetadataLevel(){
        return metadataLevel;
    }
    
    public String getMetadataFormat(){
        return metadataFormat;
    }
    
    @Override
    public RMLDataset getMetadataDataset(){
        return metadataDataset;
    }
    
    public String[] getMetadataVocab(){
        return metadataVocab;
    }
}

