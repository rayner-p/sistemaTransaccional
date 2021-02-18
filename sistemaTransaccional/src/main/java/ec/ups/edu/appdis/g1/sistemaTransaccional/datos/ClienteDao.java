package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
@Stateless
public class ClienteDao {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	/** 
	 * Constructor que permite inicializar la clase
	 * 
	 */
	public ClienteDao() {
		// TODO Auto-generated constructor stub
	}
	
	/** 
	 * Metodo que permite insertar un cliente dentro de la base de datos por emdio de JPA
	 * @param   cliente  Cliente que se va a usar  los servicios
	 * @return true  devuelve verdadero en caso de que el cliente exista.
	 */
	public boolean insert (Cliente cliente) throws SQLException {
		em.persist(cliente);
		return true;
	}
	/** 
	 * Metodo que permite buscar  un cliente a traves de la base por medio de JPA
	 * @param   cedula varianle que se ingresa para la consulta
	 * @return empleado Cliente cuya cedula es igual a la que se ingresa
	 */
	public Cliente read(String cedula) {
		Cliente empleado = new Cliente();
		empleado = em.find(Cliente.class, cedula);
		return empleado;
	}
	
	/** 
	 * Metodo que permite obtener una lista de clientes a tra ves de JPA
	 * 2
	 * @return listado  lista de todos los clientes que tiene la base;
	 */
	public List<Cliente> getEmpleados(){
		
		String jpql = "SELECT c FROM Cliente c";
		
		Query q = em.createQuery(jpql, Cliente.class);
		List<Cliente> listado = q.getResultList();
		System.out.println("ESTO ES DEL DAO CLIENTE LISTAR" +listado);
		return listado;
	}

	/** 
	 * Metodo que permite actualizar clientes
	 * @param  cliente Cliente cuyos datos se quiere actualizr
	 * @return true si se realiza la actualziacion devuelve cerdadero
	 */
	public boolean update (Cliente cliente) {
		em.merge(cliente);
		return true;
	}
	/** 
	 * Metodo que permite eliminar un cliente
	 * @param  cedula Variable que se ingresa y sirve para comparar con una existente
	 * @return true Devuelve verdadero la cedula si esta es igual a la que está en la base
	 */
	public boolean delete (String cedula) {
		Cliente cliente = this.read(cedula);
		em.remove(cliente);
		return true;
	}
	
	/** 
	 * Metodo que permite obtener un cliente de la base de datos en base a su usuario y contraseña 
	 * @param usuario Usuario que utilizaremos para poder obtener el cliente
	 * @param contra Contraseña que se utilizara para obtener el cliente
	 * @return Cliente que se encuentre en la base de datos con un usuario y contraseña en especifico. 
	 * @throws Exception
	 */
	public Cliente obtenerClienteUsuarioContraseña(String usuario,String contra) throws Exception {
		try {
			System.out.println("Esto es lo del DAO   " +usuario+""+contra);
			
			String jpl = "select c from Cliente c Where c.usuario =:usu AND c.contrasenia =:contr";
			Query q = em.createQuery(jpl, Cliente.class);
			q.setParameter("usu", usuario);
			q.setParameter("contr", contra);
			//System.out.println("datos de la consulta    " +(Cliente)q.getSingleResult());
			return (Cliente)q.getSingleResult();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Credenciaales Incorrectas"); 
		}
		return null;
	}  
	/** 
	 * Metodo que permite obtener un cliente dependiendo de su correo y cotraseña 
	 * @param correo Variable de tipo String en donde se asigna el correo de la persona que se desea obtener
	 * @param contra Variable de tipo String en donde se asigna la contraseña de la persona que se desea obtener
	 * @return Cliente que tenga la el correo y contraseña que se han pasado como paramatro
	 * @throws Exception
	 */
	public Cliente obtenerClienteCorreoContraseña(String correo,String contra) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.correo =:corr AND c.contrasenia =:contr";
			Query q = em.createQuery(jpl, Empleado.class);
			q.setParameter("corr", correo);
			q.setParameter("contr", contra);
			return (Cliente)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Revisar datos de cambio"); 
		}
		//return null;
	}
	/** 
	 * Metodo que permite obtener un cliente por medio de su numero de cedula
	 * @param cedula Variable de tipo String que pertenece al cliente
	 * @throws Exception
	 */
	public Cliente obtenerDatosPorCedula (String cedula) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.cedula =:contr";
			Query q = em.createQuery(jpl, Cliente.class);
			q.setParameter("contr", cedula);
			return (Cliente)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Revisar numero de cedula"); 
		}
	}
	
	
}
