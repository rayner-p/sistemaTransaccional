package ec.ups.edu.appdis.g1.sistemaTransaccional.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;

public class EmpleadoDao {
	@Inject
	private Connection con;
	
	public  EmpleadoDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean insert (Empleado empleado) throws SQLException {
		String sql = "INSERT INTO cliente (dni, email, nombre, tipoDocumento)"
				+ "VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, empleado.getCedula());
		ps.setString(2, empleado.getNombres());
		ps.setString(3, empleado.getApellidos());
		ps.setDate(4, (Date) empleado.getFechaNacimiento());
		ps.setString(5, empleado.getDireccion());
		ps.setString(6, empleado.getCorreo());
		ps.setString(7, empleado.getTelefono());
		ps.setString(8, empleado.getRol());
		ps.executeUpdate();
		ps.close();
		System.out.print("Empleado Creado");
		return true;
		
	}
	public boolean update (Empleado client, String dni) throws SQLException {
		
		return true;
	}
	
	public Empleado read(int codigo) throws SQLException {
		
		return null;
	}
	public boolean delete (String cedula) throws SQLException {
		String sql = "DELETE FROM Cliente"
                + " WHERE cliente_cedula= " + "'" + cedula + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		return true;

}

}
