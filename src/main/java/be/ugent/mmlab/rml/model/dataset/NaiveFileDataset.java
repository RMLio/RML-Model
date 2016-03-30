package be.ugent.mmlab.rml.model.dataset;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 * 
 * @author mielvandersande, andimou
 */
public class NaiveFileDataset extends StdRMLDataset {

    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            NaiveFileDataset.class.getSimpleName());
    
    //private FileOutputStream output;
    private File target;
    private BufferedWriter fw;
    private RDFWriter writer;
    private RDFFormat format = RDFFormat.NTRIPLES;
    
    private int size = 0;

    public NaiveFileDataset(String target) {
        try {
            this.target = new File(target);
            fw = new BufferedWriter(new FileWriter(target));
            writer = Rio.createWriter(this.format, fw);
            writer.startRDF();
        } catch (IOException ex) {
            log.error("IOException " + ex);
        } catch (RDFHandlerException ex) {
            log.error("RDF Handler Exception " + ex);
        }
    }
    
    public NaiveFileDataset(String target, String outputFormat) {

        this.target = new File(target);

        try {
            fw = new BufferedWriter(new FileWriter(target));

            //Could also be a File Output Stream
            //RDF Handler should be faster
            //output = new FileOutputStream(target);
            switch (outputFormat) {
                case "ntriples": 
                    this.format = RDFFormat.NTRIPLES; 
                    break;
                case "n3": 
                    this.format = RDFFormat.N3;
                    break;
                case "turtle": 
                    this.format = RDFFormat.TURTLE;
                    break;
                case "nquads": 
                    this.format = RDFFormat.NQUADS;
                    break;
                case "rdfxml": 
                    this.format = RDFFormat.RDFXML;
                    break;
                case "rdfjson": 
                    this.format = RDFFormat.RDFJSON;
                    break;
                case "jsonld": 
                    this.format = RDFFormat.JSONLD;
                    break;
            }
            writer = Rio.createWriter(this.format, fw);
            writer.startRDF();

        } catch (IOException ex) {
            log.error("IOException ", ex);
        } catch ( RDFHandlerException ex) {
            log.error("RDFHandlerException ", ex);
        } 

    }
    
    
    @Override
    public void add(Resource s, URI p, Value o, Resource... contexts) {
        if (log.isDebugEnabled()) {
            log.debug("Add triple (" + s.stringValue()
                    + ", " + p.stringValue() + ", " + o.stringValue() + ").");
        }
        
        Statement st = new StatementImpl(s, p, o);
        try {
            writer.handleStatement(st);
            size++;
        } catch (RDFHandlerException ex) {
            log.error("RDFHandlerException " + ex);
        }

    }
    
    
    
    /**
     * Close current repository.
     *
     */
    @Override
    public void closeRepository() {
        log.debug("Closing file...");
        try {
            fw.flush();
            writer.endRDF();
            fw.close();
        } catch (RDFHandlerException ex) {
            log.error("RDFHandlerException " + ex);
        } catch (IOException ex) {
            log.error("IOException " + ex);
        }  
    }
    
    

    @Override
    public int getSize() {
        return size;
    }
}