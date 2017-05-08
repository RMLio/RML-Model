package be.ugent.mmlab.rml.model.source.std;

import be.ugent.mmlab.rml.model.source.JdbcSource;

/**
 * RML - Model : Std JDBC Source
 *
 * @author andimou
 */
public class StdJdbcSource extends StdSource implements JdbcSource {
    
    String jdbcDSN; 
    String jdbcDriver; 
    String username; 
    String password;
    
    public StdJdbcSource(String name){
        super(name);
    }
    
    /**
     *
     * @param jdbcDSN
     * @param jdbcDriver
     * @param username
     * @param password
     */
    public StdJdbcSource(String name, 
            String jdbcDSN, String jdbcDriver, 
            String username, String password) {
        super(name);
        setJdbcDSN(jdbcDSN);
        setJdbcDriver(jdbcDriver);
        setUsername(username);
        setPassword(password);
    }
    
    private void setJdbcDSN(String jdbcDSN){
        this.jdbcDSN = jdbcDSN;
    }
    
    private void setJdbcDriver(String jdbcDriver){
        this.jdbcDriver = jdbcDriver;
    }
    
    private void setUsername(String username){
        this.username = username;
    }
    
    private void setPassword(String password){
        this.password = password;
    }
    
    public String getJdbcDSN(){
        return this.jdbcDSN ;
    }
    
    public String getJdbcDriver(){
        return this.jdbcDriver ;
    }
    
    public String getUsername(){
        return this.username ;
    }
    
    public String getPassword(){
        return this.password ;
    }
    
    @Override
    public String toString(){
        return "JdbcSource [jdbcDSN = \"" + this.jdbcDSN +
                "\", jdbcDriver = \"" + this.jdbcDriver +
                "\", username =  \"" + this.username +
                "\", password = \"" + this.password + "\"].";
    }

}
