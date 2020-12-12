package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.EmpleadoDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ParametrizarDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;

@Stateless
public class sistemaTransaccionaON implements GestionSistemRemoto{
	@Inject
	private EmpleadoDao daoEmpleado;
	@Inject
	private ParametrizarDao daoParametrizar;
	
	
	public boolean registarEmpleado(Empleado empleado) throws Exception {
		if(empleado.getCedula().length()!=10)
			throw new Exception("Cédula Incorrecta");
		try {
				daoEmpleado.insert(empleado);
				System.out.println("Empleado creado");

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro al insertar");
		}
		return true;
		
	}
	public Empleado buscarEmpleado(String cedula ) throws Exception {
		Empleado empleadoL = daoEmpleado.read(cedula);
		System.out.println("BUSQUEDAD EMPLEADO CORRECTA");
		return empleadoL;
		
	}
	
	public boolean actualizarEmpleado(Empleado empleado) throws Exception {
		String empleCedula = empleado.getCedula();
		if(daoEmpleado.read(empleCedula)==null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		daoEmpleado.update(empleado);
		System.out.println("Empleado actualizado");
		return true;
	}
	
	
	public boolean eliminarEmpleado(String cedula) throws Exception {
		if (daoEmpleado.read(cedula)== null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		daoEmpleado.delete(cedula);
		System.out.println("Empleado eliminado");
		return true;
	}
	public List<Empleado> getEmpleadosT(){
		return daoEmpleado.getEmpleados();
		
	}
	@Override
	public boolean insertar(Parametrizar parametros) throws SQLException {
		daoParametrizar.insertar(parametros);
		return false;
	}
}
