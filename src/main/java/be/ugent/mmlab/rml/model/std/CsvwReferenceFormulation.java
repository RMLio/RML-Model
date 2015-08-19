package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.ReferenceFormulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***************************************************************************
 *
 * RML - Model : CSVW (Custom) Reference Formulation
 * 
 * @author andimou
 *
 ****************************************************************************/
public class CsvwReferenceFormulation implements ReferenceFormulation {
    private String delimiter ;
    
    // Log
    private static final Logger log = 
            LoggerFactory.getLogger(CsvwReferenceFormulation.class);
    
    public CsvwReferenceFormulation(){}
    
    public CsvwReferenceFormulation(String delimiter){
        setDelimiter(delimiter);
    }
    
    public String getDelimiter(){
        return this.delimiter;
    }

    public final void setDelimiter(String delimiter){
        this.delimiter = delimiter;
    }
}
