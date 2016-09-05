package be.ugent.mmlab.rml.model.dataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.URI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
import org.eclipse.rdf4j.repository.config.RepositoryConfigException;
import org.eclipse.rdf4j.repository.manager.LocalRepositoryManager;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.repository.sail.config.SailRepositoryConfig;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.nativerdf.NativeStore;
import org.eclipse.rdf4j.sail.nativerdf.config.NativeStoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RML Processor
 * 
 * @author andimou
 */
public class FileDataset extends StdRMLDataset {

    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(
            FileDataset.class.getSimpleName());
    
    private File target;
        
    public FileDataset(String target) {
        try {
            this.target = new File(target);
            String indexes = "spoc";
            
            // Get the operating system temporary directory
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property) + "/RML-Processor";
            log.debug("OS current temporary directory is " + tempDir); 
            repository = new SailRepository(new NativeStore(new File(tempDir), indexes));
            repository.initialize();

        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        }
    }
    
    /**
     *
     * @param target
     * @param outputFormat
     * @param manager
     * @param repositoryID
     */
    public FileDataset(String target, String outputFormat, 
           LocalRepositoryManager manager, String repositoryID) {
        //Set the final output
        this.target = new File(target);

        try {
            //TODO: Move that to super
            String indexes = "spoc";
            SailRepositoryConfig repositoryTypeSpec = 
                    new SailRepositoryConfig(new NativeStoreConfig(indexes));
            RepositoryConfig repConfig = 
                    new RepositoryConfig(repositoryID, repositoryTypeSpec);
            manager.addRepositoryConfig(repConfig);
            repository = manager.getRepository(repositoryID);
            if(!repository.isInitialized())
                repository.initialize();

            //Clean up repo from previous use
            RepositoryConnection con = repository.getConnection();
            con.clear();
            con.commit();
            con.close();
            
            this.format = selectFormat(outputFormat);
            
        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } catch (RepositoryConfigException ex) {
            log.error("Repository Config Exception " + ex);
        }

    }
    
    @Override
    public void add(Resource s, URI p, Value o, Resource... contexts) {
        log.debug("Add triple (" + s.stringValue()
                + ", " + p.stringValue() + ", " + o.stringValue() + ").");
        
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
    
    /**
     * Close current repository.
     *
     */
    @Override
    public void closeRepository() {
        try {
            RepositoryConnection con = repository.getConnection();
            RDFWriter writer;

            //Prepare writer
            if(this.target.exists()){
                this.target.delete();
                this.target.createNewFile();
            }

            //Prepare file output stream
            FileOutputStream output = 
                    new FileOutputStream(this.target);

            writer = Rio.createWriter(this.format, output);
            con.export(writer);
            con.commit();
            con.close();
            repository.shutDown();
            
            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property) + "/RML-Processor";
            File file = new File(tempDir);
            file.delete();

        } catch (RepositoryException ex) {
            log.error("Repository Exception " + ex);
        } catch (FileNotFoundException ex) {
            log.error("File Not Found Exception " + ex);
        } catch (RDFHandlerException ex) {
            log.error("RDF Handler Exception " + ex);
        } catch (IOException ex) {
            log.error("IO Exception " + ex);
        } 
    }
       
}