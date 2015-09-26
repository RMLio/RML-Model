package be.ugent.mmlab.rml.vocabularies;

/**
 * *************************************************************************
 * 
 * RML - Model : Hydra Vocabulary
 * 
 * namespace: hydra IRI: http://www.w3.org/ns/hydra/core#
 *
 * @author andimou
 * 
 * *************************************************************************
 */
public class HydraVocabulary {
    public static String HYDRA_NAMESPACE = "http://www.w3.org/ns/hydra/core#";
    

    public static enum HydraTerm implements Term{

        // Hydra CLASSES
        API_DOCUMENTATION_CLASS("ApiDocumentation"),
        IRITEMPLATE_CLASS("IriTemplate"),
        PAGEDCOLLECTION_CLASS("PagedCollection"),
        // Hydra PROPERTIES
        //IriTemplate properties
        TEMPLATE("template"),
        MAPPING("mapping"),
        //IriTemplateMapping properties
        VARIABLE("variable"),
        REQUIRED("required"),
        //PagedCollection properties
        MEMBER("member"),
        ITEMSPERPAGE("itemsPerPage"),
        FIRSTPAGE("firstPage"),
        NEXTPAGE("nextPage"),
        PREVIOUSPAGE("previousPage"),
        LASTPAGE("lastPage"),
        TOTALITEMS("totalItems"),
        SEARCH("search");
        
        private final String displayName;

        private HydraTerm(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
        
    }
    
}
