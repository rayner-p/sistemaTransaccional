package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
@Stateless
public class EmpleadoDao {
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;

	
	public  EmpleadoDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean insert (Empleado empleado) throws SQLException {
		em.persist(empleado);
		return true;
	}
	public Empleado read(String cedula) {
		Empleado empleado = new Empleado();
		empleado = em.find(Empleado.class, cedula);
		return empleado;
	}
	public boolean update (Empleado empleado) {
		em.merge(empleado);
		return true;
	}
	public boolean delete (String cedula) {
		Empleado emple = this.read(cedula);
		em.remove(emple);
		return true;
	}
	public List<Empleado> getEmpleados(){
		String jpql = "SELECT c FROM Empleado c";
		Query q = em.createQuery(jpql, Empleado.class);
		List<Empleado> listado = q.getResultList();
		return listado;
	}
	/**
	 * Metodo para obtener el Empleado
	 * @param id El parametro id me permite obtener el Empleado 
	 * @return EL empleado de acuerdo al parametro id
	 */
	public Empleado obtenerEmpleado(String id) {
		 return em.find(Empleado.class, id);
	}
	
	/** 
	 * Metodo que permite obtener un cliente de la base de datos en base a su usuario y contraseña 
	 * @param usuario Usuario que utilizaremos para poder obtener el cliente
	 * @param contra Contraseña que se utilizara para obtener el cliente
	 * @return Cliente que se encuentre en la base de datos con un usuario y contraseña en especifico. 
	 * @throws Exception
	 */
	public Empleado obtenerClienteUsuarioContraseña(String usuario,String contra) throws Exception {
		try {
			System.out.println("Esto es lo del DAO   " +usuario+""+contra);
			
			String jpl = "select c from Empleado c Where c.usuario =:usu AND c.contrasenia =:contr";
			Query q = em.createQuery(jpl, Empleado.class);
			q.setParameter("usu", usuario);
			q.setParameter("contr", contra);
			System.out.println("datos de la consulta    " +(Empleado)q.getSingleResult());
			return (Empleado)q.getSingleResult();
			
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
	public Empleado obtenerClienteCorreoContraseña(String correo,String contra) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.correo =:corr AND c.contrasenia =:contr";
			Query q = em.createQuery(jpl, Empleado.class);
			q.setParameter("corr", correo);
			q.setParameter("contr", contra);
			System.out.println(jpl);
			FacesMessage me = new FacesMessage(FacesMessage.FACES_MESSAGES, "Datos correctos");
			FacesContext.getCurrentInstance().addMessage(null, me);
			return (Empleado)q.getSingleResult();
			
		} catch (NoResultException e) {
			FacesMessage me = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario incorrecto", " Contrasenia incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, me);
			 System.out.println(e.getMessage());
			 throw new Exception("Revisar datos"); 
		}
		//return null;
	}
}


