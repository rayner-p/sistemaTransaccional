package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.SQLException;
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
	 * 
	 * @param c Cuenta que se registrar
	 */
	public void insert(Cuenta c) {
		em.persist(c);
	}

	/**
	 * Metodo que permite actualizar una cuenta de ahorro en la base de datos
	 * 
	 * @param c Cuenta que se actualiza
	 */
	public void update(Cuenta c) {
		em.merge(c);
	}

	/**
	 * Metodo que permite obtener una cuenta de ahorro de la base de datos
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta que se busca
	 * @return una cuenta de ahorro que este registrada en la base
	 */
	public Cuenta read(String cuenta) {
		return em.find(Cuenta.class, cuenta);
	}

	/**
	 * Metodo que permite eliminar una cuenta de ahorro de la base de datos
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta que queremos eliminar
	 */
	public void delete(String numeroCuenta) {
		Cuenta c = read(numeroCuenta);
		em.remove(c);
	}

	/**
	 * Metodo que permite obtener las cuentas de ahorro que estan registrados en la
	 * base de datos
	 * 
	 * @return Lista de cuentas de ahorros que estan registradas en la base de datos
	 */
	public List<Cuenta> obtenerCuenta() {
		String jpql = "SELECT c FROM Cuenta c ";

		Query q = em.createQuery(jpql, Cuenta.class);
		return q.getResultList();
	}

	/**
	 * Metodo que permite obtener una cuenta de ahorro en base a su codigo
	 * registrado en la base de datos
	 * 
	 * @param cedulaCliente Cedula del cliente que queremos buscar
	 * @return Cuenta de ahorro que tenga un cliente registrado en la base
	 */
	public Cuenta getCuentaCedulaCliente(String cedulaCliente) {
		String jpql = "SELECT c FROM Cuenta c WHERE c.cuenta_fk = :cedulaCliente";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("cedulaCliente", cedulaCliente);
		Cuenta cuentaDeAhorro = (Cuenta) q.getSingleResult();
		return cuentaDeAhorro;
	}

	public Cuenta obtenerCuentaPorNumero(String numerCuenta) {
		String jpql = "SELECT c FROM Cuenta c WHERE c.numeroCuenta = :cedulaCliente";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("cedulaCliente", numerCuenta);
		Cuenta cuentaDeAhorro = (Cuenta) q.getSingleResult();
		return cuentaDeAhorro;
	}

	public String actaulizarCuentaCliente(String numeroCuenta, double valor) {
		System.out.println("Entra al dao");
		Query query = em.createQuery(
			    "UPDATE Cuenta c  SET c.saldo =: valor WHERE c.numeroCuenta =:codigo");
			query.setParameter("valor", valor);
			query.setParameter("codigo", numeroCuenta);
		int result = query.executeUpdate();
		
		return null;
	}

	public Cuenta obtenerSaldoClienteCuenta(String numeroCuenta) throws SQLException {
		String jpql = "SELECT c FROM Cuenta c   WHERE Cuenta.numeroCuenta = :numCuenta";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("numCuenta", numeroCuenta);
		return (Cuenta) q.getSingleResult();
	}

}
