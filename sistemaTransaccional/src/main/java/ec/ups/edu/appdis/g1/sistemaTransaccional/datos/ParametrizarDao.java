package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
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
	
	public List<Parametrizar> obtenerParametros (){
		String jpql = "SELECT c FROM Parametrizar c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Parametrizar> listado = q.getResultList();
		return listado;
	}
}
