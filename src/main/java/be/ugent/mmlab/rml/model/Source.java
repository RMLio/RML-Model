package be.ugent.mmlab.rml.model;

/**
 * *************************************************************************
 *
 * RML - Model : InputSource
 *
 *
 * @author andimou
 *
 ***************************************************************************
 */
public interface Source {
       
    
    /**
     *
     * @param template
     */
    void setTemplate(String template);
    
    
    /**
     *
     * @return
     */
    String getTemplate();
    
    /**
     *
     * @return TriplesMap
     * 
     */
    //public Set<TriplesMap> getTriplesMap();

    /**
     *
     * @param triplesMap
     * 
     */
    //public void setTriplesMap(TriplesMap triplesMap);
    
    /**
     *
     * @return
     */
    //public String getSource();
    
    /**
     *
     * @param source
     */
    //public void setSource(String source);
    
}
