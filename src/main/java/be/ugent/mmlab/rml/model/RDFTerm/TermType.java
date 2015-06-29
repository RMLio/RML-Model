/***************************************************************************
 *
 * RML Model : TermType
 *
 * The term type of a reference-valued term map or 
 * template-valued term map determines the kind 
 * of the generated RDF term (IRIs, blank nodes or literals).
 * 
 * @author andimou
 *
 ****************************************************************************/
package be.ugent.mmlab.rml.model.RDFTerm;

import be.ugent.mmlab.rml.vocabulary.R2RMLVocabulary;

public enum TermType {

	IRI(R2RMLVocabulary.R2RMLTerm.IRI.toString()), 
	BLANK_NODE(R2RMLVocabulary.R2RMLTerm.BLANK_NODE.toString()),
	LITERAL(R2RMLVocabulary.R2RMLTerm.LITERAL.toString());

	private String displayName;

	private TermType(String displayName) {
		// The value MUST be an IRI
		this.displayName = R2RMLVocabulary.R2RML_NAMESPACE + displayName;
	}

        /**
        *
        * @return
        */
       @Override
	public String toString() {
		return displayName;
	}

	/**
        *
        * @return
        */
       public String getDisplayName() {
		return displayName;
	}

	/**
	 * Converts a termType from its display name.
	 * 
	 * @param displayName
	 * @return
	 */
	public static TermType toTermType(String displayName) {
		for (TermType termType : TermType.values()) {
			if (termType.getDisplayName().equals(displayName))
				return termType;
		}
		return null;
	}

}
