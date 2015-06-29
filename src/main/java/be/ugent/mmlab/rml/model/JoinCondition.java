/**
 *****************************************************************************
 *
 * RML Model : Join Condition Interface
 * 
 * @author andimou
 *
 *****************************************************************************
 */
package be.ugent.mmlab.rml.model;

public interface JoinCondition {

    /**
     * Child reference must be a reference in the language defined by the
     * logical source of the triples map that contains the referencing object
     * map
     */
    public String getChild();

    /**
     *
     * @param child
     */
    public void setChild(String child);

    /**
     * Parent reference must be a reference in the language defined by the
     * parent triples map and exist there.
     */
    public String getParent();
    
    /**
     *
     * @param parent
     */
    public void setParent(String parent);
}
