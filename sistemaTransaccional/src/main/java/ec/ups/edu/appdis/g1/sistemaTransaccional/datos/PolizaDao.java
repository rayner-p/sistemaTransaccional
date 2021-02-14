package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.PolizaPOJO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.servicios.ClientePOJO;

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
	 * Metodo que permite insertar una irPoliza dentro de la base de datos por emdio
	 * de JPA
	 * 
	 * @param poliza Poliza que se va a usar en los servicios
	 * @return true devuelve verdadero en caso de que el poliza exista.
	 */
	public boolean insertarPoliza(Poliza poliza) {
		em.persist(poliza);
		return true;
	}

	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public Poliza buscarPoliza(String cuenta) {
		Poliza poli = new Poliza();
		poli = em.find(Poliza.class, cuenta);
		return poli;
	}

	/**
	 * 
	 * @param cuenta
	 * @return
	 */
	public boolean deletePolicy(String cuenta) {
		Poliza deleteP = new Poliza();
		em.remove(deleteP);
		return true;
	}

	/**
	 * Metodo que permite obtener Polizas que se crearon anteriormente
	 * 
	 * @return listado Una lista con todos los parametros ingresado
	 */

	public List<Poliza> obtenerPolizas() {
		System.out.println("enrra dao");
		@SuppressWarnings("unchecked")
		List<Poliza> resultadoPoliza = em.createNativeQuery("select p.* from Poliza p", Poliza.class).getResultList();
		return resultadoPoliza;

		/*
		 * select p.codigo, p.cuentaclientepoliza_fk,c.cedula, c.nombres, c.apellidos,
		 * p.fecha_emision, p.estadopoliza, p.monto_poliza, " + "p.tiempo_plazo,
		 * p.archivocedula, p.archivoplanillaservicios from poliza p , cliente c where
		 * p.poliza_fk = c.cedula
		 * 
		 * 
		 * String jpql =
		 * "	select p.codigo, p.cuentaClientePoliza_fk,c.cedula, c.nombres, c.apellidos,"
		 * + "p.fechaEmision, p.estadoPoliza, p.montoP, p.tiempoPlazo, p.archivoCedula,"
		 * +
		 * " p.archivoPlanillaServicios from Poliza p , Cliente c where p.transaccion_fk = c.cedula"
		 * ; Query q = em.createQuery(jpql, Poliza.class);
		 * 
		 * @SuppressWarnings("unchecked") List<Poliza> listado = q.getResultList();
		 * return listado;
		 */
	}

	/**
	 * meteodo que permite actualizar el estado de la póliza al asistente de
	 * captaciones
	 * 
	 * @param numeroCuenta el numero de cuenta cuyo dinero va a ponerse en poliza
	 * @return mensaje que nos indica si se crea o no
	 */
	public String actualizarPoliza(String numeroCuenta) {
		System.out.println("Entra al dao actualiza poliza" + " " + numeroCuenta);
		Query query = em
				.createQuery("UPDATE Poliza p  SET p.estadoPoliza =: valor WHERE c.cuentaClientePoliza_fk =:codigo");
		query.setParameter("codigo", numeroCuenta);
		//int result = query.executeUpdate();
		return "poliza";
	}

}
