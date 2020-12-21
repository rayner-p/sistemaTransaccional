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
	public boolean insertarTransaccion(Transaccion transaccion) {
		em.persist(transaccion);
		return true;
		
	}
	public List<Transaccion> obtenerTransaccion (){
		String jpql = "SELECT c FROM Parametrizar c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Transaccion> listado = q.getResultList();
		return listado;
		
	}
}
