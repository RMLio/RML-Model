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

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(CsvwReferenceFormulation.class.getSimpleName());

	private String delimiter;
	private String encoding;

	public CsvwReferenceFormulation() {
		this(",", "UTF-8"); // default values
	}

	public CsvwReferenceFormulation(final String delimiter, final String encoding) {
		this.setDelimiter(delimiter);
		this.setEncoding(encoding);
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public final void setDelimiter(final String delimiter) {
		this.delimiter = delimiter;
	}

	public String getEncoding() {
		return this.delimiter;
	}

	public final void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

}
