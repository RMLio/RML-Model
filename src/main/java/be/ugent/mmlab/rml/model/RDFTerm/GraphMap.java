package be.ugent.mmlab.rml.model.RDFTerm;

/**
 *************************************************************************
 *
 * RML - Model : GraphtMap Interface
 *
 * Any Subject Map or Predicate-Object Map may have one
 * or more associated graph maps. 
 * Graph maps are themselves Term Maps. 
 * When RDF triples are generated, 
 * the set of target graphs is determined by taking into
 * account any Graph Maps associated with the Subject Map
 * or Predicate-Object Map.
 *
 ***************************************************************************
 */
public interface GraphMap extends TermMap {

}
