package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
@Stateless
public class PolizaDao {
	
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	
	public PolizaDao() {
		// TODO Auto-generated constructor stub
	}
	/** 
	 * Metodo que permite insertar una irPoliza dentro de la base de datos por emdio de JPA
	 * @param   poliza Poliza que se va a usar en  los servicios
	 * @return true  devuelve verdadero en caso de que el poliza exista.
	 */
	public boolean insertarPoliza(Poliza poliza) {
		em.persist(poliza);
		return true;
	}
	/** 
	 * Metodo que permite obtener Polizas que se crearon anteriormente
	 * 
	 * @return listado Una lista con todos los parametros ingresado
	 */
	public List<Poliza> obtenerPolizas(){
		String jpql = "SELECT c FROM Poliza c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Poliza> listado = q.getResultList();
		return listado;
	}

}
