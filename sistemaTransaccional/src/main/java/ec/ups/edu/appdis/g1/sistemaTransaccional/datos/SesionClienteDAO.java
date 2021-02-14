package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;

/** 
 *  Esta clase me permite hacer las funciones basicas en una base de datos utilizando la clase SesionCliente
 * @author rayner
 * @version 1.0
 */
@Stateless
public class SesionClienteDAO {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	
	/** 
	 * Metodo que permite registrar una sesion en la base de datos
	 * @param s Sesion que se inserta en la base de datos
	 */
	public void insert(SesionCliente sesison) {
		
		System.out.println("a insertar"+ sesison);
		try {
			em.persist(sesison);
			System.out.println("termina de instar");
		}catch (Exception e) {
			System.err.println("Error al crear sesion"+e.getLocalizedMessage());
		}
		
	}


	/** 
	 * Metodo que permite obtener una sesion de la base de datos
	 * @param codigoSesion Codigo de la sesion que se busca
	 * @return una sesion que este registrada en la base de datos
	 */
	public SesionCliente read(int codigoSesion) {
		return em.find(SesionCliente.class, codigoSesion);
	}
	
	
	/** 
	 * Metodo que permite obtener las sesiones que estan registrados en la base de datos
	 * @return Lista de sesiones que estan registradas en la base de datos
	 */
	public List<SesionCliente> getSesionClientes() {
		String jpql = "SELECT s FROM SesionCliente s ";

		Query q = em.createQuery(jpql, SesionCliente.class);
		return q.getResultList();
	}  
	
	/** 
	 * Metodo que permite obtener las sesiones de un cliente registrado en la base de datos
	 * @param cedulaCliente Cedula del cliente que debe tener las sesiones registradas en la base
	 * @return lista de sesiones de un cliente en especifico
	 * @throws Exception Control de errores a la hora de realizar la consulta
	 */
	public List<SesionCliente> obtenerSesionCliente(String cedulaCliente) throws Exception { 
		try {
			String jpql = "SELECT s FROM SesionCliente s WHERE s.cliente.cedula = :cedulaCliente order by s.fechaSesion desc";
			Query q = em.createQuery(jpql, SesionCliente.class);  
			q.setParameter("cedulaCliente",cedulaCliente);
			return q.getResultList();
		} catch (Exception e) {
			throw new Exception("No ha ingresado ni una sola vez");
		}
		
	}
	public List<SesionCliente> obtenerSesionEmpleado(String cedulaCliente) throws Exception { 
		try {
			String jpql = "SELECT s FROM SesionCliente s WHERE s.empleado.cedula = :cedulaCliente order by s.fechaSesion desc";
			Query q = em.createQuery(jpql, SesionCliente.class);  
			q.setParameter("cedulaCliente",cedulaCliente);
			return q.getResultList();
		} catch (Exception e) {
			throw new Exception("No ha ingresado ni una sola vez");
		}
		
	}
}
