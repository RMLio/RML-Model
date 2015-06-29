/***************************************************************************
 *
 * RML Model : GraphtMap Interface
 *
 * Any subject map or predicate-object map may have one
 * or more associated graph maps. Graph maps are 
 * themselves term maps. When RDF triples are generated,
 * the set of target graphs is determined by taking into
 * account any graph maps associated with the subject map
 * or predicate-object map.
 *
 ****************************************************************************/
package be.ugent.mmlab.rml.model.RDFTerm;


public interface GraphMap extends TermMap {

}
