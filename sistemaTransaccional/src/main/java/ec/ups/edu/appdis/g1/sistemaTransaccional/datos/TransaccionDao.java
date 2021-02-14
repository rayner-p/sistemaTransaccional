package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	 * Metodo que permite actualizar una Transferencia Local en la base de datos
	 * @param t Transferencia Local que se va a actualizar en la base
	 */
	public void update(Transaccion t) {
		em.merge(t);
	}
	
	/** 
	 * Metodo que permite listar todas las  transacciones dentro de la base
	 * 
	 * @return listado si se realiza la actualziacion devuelve la lista con todos los datos existentes
	 */
	public List<Transaccion> obtenerTransaccion (){
		String jpql = "SELECT c FROM Transaccion c";
		Query q = em.createQuery(jpql, Transaccion.class);
		List<Transaccion> listado = q.getResultList();
		return listado;
		
	}
	/**
	 * Metodoq que me permite consultar entre fechas las transacciones realizadas por el cliente.
	 * @param cedula   pertenece al cliente logueado
	 * @param fechI		fecha inicio de la busqeuda
	 * @param fechaF	fecha final de la búsqueda
	 * @return
	 * @throws Exception
	 */
	public List<Transaccion> getListaTransaccionesFechas(String cedula,String fechI , String fechaF ) throws Exception {
		//select * from transaccion where cedula_cliente = 0105011399 AND fecha BETWEEN '2020-06-03 20:21:40.090000' AND '2020-06-03 23:22:39.160000';
		String tl = "select s from Transaccion s where s.cliente.cedula = '"+cedula+"' AND s.fecha BETWEEN '"+fechI+"' AND '"+fechaF+"' ORDER BY s.fecha DESC";
		//select s.tipo_transaccion, s.monto, s.cuenta_trans from transaccion s join cuenta on numero_cuenta = '404000000001' AND s.fecha_hora BETWEEN '2021-01-30 01:04:24.329' AND '2021-01-30 01:04:24.329' ORDER BY s.fecha_hora 	
		try {
			Query q = em.createQuery(tl, Transaccion.class);
			//q.setParameter("ced", cedula);
			//q.setParameter("fcI", fechI);
			//q.setParameter("fcF", fechaF);
			return q.getResultList();
		} catch (NoResultException e) {
			throw new Exception("Erro Consultas Entre Fechas");
		}

	}
}
