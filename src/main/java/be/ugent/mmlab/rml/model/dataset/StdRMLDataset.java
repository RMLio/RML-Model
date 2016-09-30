package be.ugent.mmlab.rml.model.dataset;

import be.ugent.mmlab.rml.model.TriplesMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.rdf4j.model.BNode;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
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
            LoggerFactory.getLogger(StdRMLDataset.class.getSimpleName());
    
    protected Repository repository = null;
    protected RDFFormat format = RDFFormat.NTRIPLES;
    protected List metadataVocab = new ArrayList();
    
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
    public void add(Resource s, IRI p, Value o, Resource... contexts) {
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

    //TODO: Spring it
    @Override
    public void closeRepository() {
        try {
            log.debug("Closing memory repository..");
            RepositoryConnection con = repository.getConnection();
            RDFWriter writer = Rio.createWriter(this.format, System.out);
            con.export(writer);
            con.commit();
            con.close();
            repository.shutDown();
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        }
    }
    
    //TODO: Spring it
    @Override
    public List<Statement> tuplePattern(Resource s, IRI p, Value o,
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
    
    @Override
    public RDFFormat selectFormat(String outputFormat) {
        RDFFormat rdfFormat ;
        //TODO: Spring it!
        switch (outputFormat) {
            case "ntriples":
                rdfFormat = RDFFormat.NTRIPLES;
                break;
            case "n3":
                rdfFormat = RDFFormat.N3;
                break;
            case "turtle":
                rdfFormat = RDFFormat.TURTLE;
                break;
            case "nquads":
                rdfFormat = RDFFormat.NQUADS;
                break;
            case "rdfxml":
                rdfFormat = RDFFormat.RDFXML;
                break;
            case "rdfjson":
                rdfFormat = RDFFormat.RDFJSON;
                break;
            case "jsonld":
                rdfFormat = RDFFormat.JSONLD;
                break;
            default:
                rdfFormat = RDFFormat.TURTLE;
        }
        return rdfFormat;
    }

    @Override
    public String getMetadataLevel() {
        return "None";
    }

    @Override
    public void addToRepository(TriplesMap map, Resource s, IRI p, Value o, Resource... contexts) {
        log.error("Not supported yet."); 
    } 
    
    @Override
    public Repository getRepository() {
        return this.repository;

    }
    
    @Override
    public List getMetadataVocab(){
        return metadataVocab;
    }
}
