package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
@Stateless
public class TransaccionDao {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	public TransaccionDao() {
		// TODO Auto-generated constructor stub
	}
	/** 
	 * Metodo que permite insertar una transaccion dentro de la base de datos por emdio de JPA
	 * @param   transaccion  transaccion que se va a usar  los servicios
	 * @return true  devuelve verdadero en caso de que el cliente exista.
	 */
	public boolean insertarTransaccion(Transaccion transaccion) {
		em.persist(transaccion);
		return true;
		
	}
	/** 
	 * Metodo que permite listar todas las  transacciones dentro de la base
	 * 
	 * @return listado si se realiza la actualziacion devuelve la lista con todos los datos existentes
	 */
	public List<Transaccion> obtenerTransaccion (){
		String jpql = "SELECT c FROM Parametrizar c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Transaccion> listado = q.getResultList();
		return listado;
		
	}
}
