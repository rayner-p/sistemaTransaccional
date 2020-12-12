package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;

@Remote
public interface GestionSistemRemoto {
	public boolean registarEmpleado(Empleado empleado) throws Exception;
	public Empleado buscarEmpleado(String cedula ) throws Exception ;
	public boolean actualizarEmpleado(Empleado empleado) throws Exception;
	public boolean eliminarEmpleado(String cedula) throws Exception;
	public List<Empleado> getEmpleadosT();
	public boolean insertar(Parametrizar parametros) throws SQLException;

}
