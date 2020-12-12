package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
}


