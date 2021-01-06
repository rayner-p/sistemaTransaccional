package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.TranferenciaLocal;

@Stateless
public class TransferencialLocalDAO {
	@Inject
	private Connection con;
	@Inject
	private EntityManager em;
	
	/** 
	 * Metodo que permite crear una transferencia en la base de datos
	 * @param transfe TranferenciaLocal que se actualiza en la base
	 */
	public void insert(TranferenciaLocal transfe) {
		em.persist(transfe);
	}

	/** 
	 * Metodo que permite actualizar una transferencia en la base de datos
	 * @param tra TranferenciaLocal que se actualiza en la base
	 */
	public void update(TranferenciaLocal tra) {
		em.merge(tra);
	}
	/** 
	 * Metodo que permite obtener una Transferencia Local de la base de datos
	 * @param codigoTra  Codigo que se utilizara para obtener la Transferencia Local
	 * @return Una Transferencia Local que se encuentre registrado en la base
	 */
	public TranferenciaLocal read(int codigoTra) {
		return em.find(TranferenciaLocal.class, codigoTra);
	}
	/** 
	 * Metodo que permite eliminar una Transferencia Local de la base de datos
	 * @param codigoTra Codigo que se utiliza para poder eliminar la Transferencia Local
	 */
	public void delete(int codigoTra) {
		TranferenciaLocal c = read(codigoTra);
		em.remove(c);
	}
	/** 
	 * Metodo que permite obtener las Transferencias Locales que estan registradas en la base de datos
	 * @return Lista de Transferencias Locales que estan registradas en la base de datos
	 */
	public List<TranferenciaLocal> getTransfereciaLocals() {
		String jpql = "SELECT t FROM TransfereciaLocal t ";

		Query q = em.createQuery(jpql, TranferenciaLocal.class);
		return q.getResultList();
	}
	
	
}
