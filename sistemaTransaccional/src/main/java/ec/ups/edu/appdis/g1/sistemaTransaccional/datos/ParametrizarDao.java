package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
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
	
	/** 
	 * Metodo que permite insertar una Parametrizacióm de las pólizas dentro de la base de datos por emdio de JPA
	 * @param   parametros  parametros que se va a usar  los servicios
	 * @return true  devuelve verdadero en caso de que el Empleado exista.
	 */
	public boolean insertar(Parametrizar parametros) throws SQLException {
		em.persist(parametros);
		return true;
		
	}
	/** 
	 * Metodo que permite obtener los Parametros que se crearon anteriormente
	 * 
	 * @return listado Una lista con todos los parametros ingresado
	 */
	public List<Parametrizar> obtenerParametros (){
		String jpql = "SELECT c FROM Parametrizar c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Parametrizar> listado = q.getResultList();
		return listado;
	}
	/** 
	 * Metodo que permite obtener los Parametros que se crearon anteriormente por medio del tiempo maximo
	 * @param maximo Cantidad de los dias para la poliza
	 * @return listado Una lista con todos los parametros ingresado
	 */
	
	//public List<Parametrizar> obtenerParametrosporDia (int maximo){
	public Parametrizar obtenerParametrosporDia (int maximo){
		System.out.println("LLEGA EL NUMERO?"+ maximo);
		String castInter = String.valueOf(maximo);
		/*@SuppressWarnings("unchecked")
		List<Parametrizar> resultadoParametrizar = em.createNativeQuery(
				"SELECT parametrizar.tasa_interes FROM parametrizar  where parametrizar.maximo = :maxi",
				Parametrizar.class).setParameter("maxi", castInter).getResultList();
		System.out.println("RESULTADO DEL QUERY "+ resultadoParametrizar);
		return resultadoParametrizar;	*/
	
		
		String jpql = "SELECT c FROM Parametrizar c where c.maximo=:maxi";
		Query q = em.createQuery(jpql, Parametrizar.class);
		q.setParameter("maxi", castInter);
		 
		return  (Parametrizar) q.getSingleResult();
	}
}