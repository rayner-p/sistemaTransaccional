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
	public ClienteDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean insert (Cliente cliente) throws SQLException {
		em.persist(cliente);
		return true;
	}
	public Cliente read(String cedula) {
		Cliente empleado = new Cliente();
		empleado = em.find(Cliente.class, cedula);
		return empleado;
	}
	
	public List<Cliente> getEmpleados(){
		String jpql = "SELECT c FROM Empleado c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Cliente> listado = q.getResultList();
		return listado;
	}
	
	public boolean update (Cliente cliente) {
		em.merge(cliente);
		return true;
	}
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
			String jpl = "select c from Empleado c Where c.usuario =:usu AND c.contrasenia =:contr";
			Query q = em.createQuery(jpl, Empleado.class);
			q.setParameter("usu", usuario);
			q.setParameter("contr", contra);
			return (Cliente)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Credenciaales Incorrectas"); 
		}
		//return null;
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
			String jpl = "select c from Empleado c Where c.correo =:corr AND c.contrasenia =:contr";
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
}
