package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;

public class CuentaDao {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	/** 
	 * Metodo que permite registrar una cuenta de ahorro en la base de datos
	 * @param c Cuenta que se registrar
	 */
	public void insert(Cuenta c) {
		em.persist(c);
	}
	
	/**  
	 *  Metodo que permite actualizar una cuenta de ahorro en la base de datos
	 * @param c Cuenta que se actualiza
	 */
	public void update(Cuenta c) {
		em.merge(c);
	}
	
	/** 
	 * Metodo que permite obtener una cuenta de ahorro de la base de datos
	 * @param numeroCuentaDeAhorro Numero de la cuenta que se busca
	 * @return una cuenta de ahorro que este registrada en la base
	 */
	public Cuenta read(String cuenta) {
		return em.find(Cuenta.class, cuenta);
	}
	
	/** 
	 * Metodo que permite eliminar una cuenta de ahorro de la base de datos
	 * @param numeroCuentaDeAhorro Numero de la cuenta que queremos eliminar
	 */
	public void delete(String numeroCuenta) {
		Cuenta c = read(numeroCuenta);
		em.remove(c);
	}
	
	/** 
	 * Metodo que permite obtener las cuentas de ahorro que estan registrados en la base de datos
	 * @return Lista de cuentas de ahorros que estan registradas en la base de datos
	 */
	public List<Cuenta> obtenerCuenta() {
		String jpql = "SELECT c FROM CuentaDeAhorro c ";

		Query q = em.createQuery(jpql, Cuenta.class);
		return q.getResultList();
	}  
	
	/** 
	 * Metodo que permite obtener una cuenta de ahorro en base a su codigo registrado en la base de datos 
	 * @param cedulaCliente Cedula del cliente que queremos buscar
	 * @return Cuenta de ahorro que tenga un cliente registrado en la base
	 */
	public Cuenta getCuentaCedulaCliente(String cedulaCliente) {
		String jpql = "SELECT c FROM Cuenta c WHERE c.cliente.cedula = :cedulaCliente";
		Query q = em.createQuery(jpql, Cuenta.class);  
		q.setParameter("cedulaCliente",cedulaCliente);
		Cuenta cuentaDeAhorro = (Cuenta)q.getSingleResult();
		return cuentaDeAhorro;
	}  
	

}
