package ec.ups.edu.appdis.g1.sistemaTransaccional.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

public class Resources {
	
	String dsName = "java:jboss/datasources/sistemaDS";

    @Produces
    private Connection createConnection() throws SQLException, NamingException {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource)ic.lookup(dsName);
        return ds.getConnection();
    }
    
    @SuppressWarnings("unused")
	private void closeConnection(@Disposes Connection conn) throws SQLException {
        conn.close();
    }
    @Produces
	@PersistenceContext
	private EntityManager em;
}

