package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 *
 * RML - Model : CSVW Metadata Vocabulary
 *
 * namespace: csvw IRI: http://www.w3.org/ns/csvw#
 * 
 * @author andimou
 *
 ***************************************************************************
 */
public class CSVWVocabulary {
    public static String CSVW_NAMESPACE = "http://www.w3.org/ns/csvw#";

    public enum CSVWTerm implements Term{

        // CSVW CLASSES
        DIALECT_CLASS("Dialect"),
        FOREIGNKEY_CLASS("ForeignKey"),
        SCHEMA_CLASS("Schema"),
        TABLE_CLASS("Table"),
        TABLEGROUP_CLASS("TableGroup"),
        TABLEREFERENCE_CLASS("TableReference"),
        TRANSFORMATION_CLASS("Transformation"),
        
        // VoID PROPERTIES
        URL("url"),
        TABLESCHEMA("tableSchema"),
        COLUMNS("columns"),
        NAME("name"),
        TITLES("titles"),
        DATATYPE("datatype"), 
        PRIMARYKEY("primaryKey"),
        ABOUTURL("aboutUrl"),
        DIALECT("dialect"),
        ENCODING("encoding"),
        HEADER("header"),
        QUOTECHAR("quoteChar"),
        PROPERTYURL("propertyUrl");
        
        private String displayName;

        private CSVWTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

}
