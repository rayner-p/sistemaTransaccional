package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
@Stateless 
public class ParametrizarDao {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	public ParametrizarDao() {
		
		
	}
	public boolean insertar(Parametrizar parametros) throws SQLException {
		em.persist(parametros);
		return true;
		
	}
}
