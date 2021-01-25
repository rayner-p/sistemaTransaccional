package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.PolizaPOJO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;

@Remote
public interface GestionSistemRemoto {
	public void registarEmpleado(Empleado empleado) throws Exception;
	public Empleado buscarEmpleado(String cedula ) throws Exception ;
	public void actualizarEmpleado(Empleado empleado) throws Exception;
	public void eliminarEmpleado(String cedula) throws Exception;
	public List<Empleado> getEmpleadosT();
	public void insertar(Parametrizar parametros) throws SQLException;
	public void insertarPoliza(Poliza poliza)throws SQLException ;
	public List<PolizaPOJO> obtenerPolizas();
	public void insertarTransaccion(Transaccion transaccion);
	public List<Transaccion> obtenerTransaccion ();
	public String generarNombreUsuario(String cedula, String nombre, String apellido);
	public boolean validadorDeCedula(String cedula) throws Exception;
	public void cambioContrasena(Empleado empleado);
	public int obtenerEdad(Date fechaNacimiento);
	public boolean validarIngresoNumeros(String datos);
	public boolean validarIngresoLetras(String datos);
	public boolean validarIngresoCorreo(String correo);
	public String generarContrasenia();
	public void registarCliente(Cliente empleado) throws Exception;
	public Cliente buscarCliente(String cedula ) throws Exception;
	public void regirestarCuenta(Cuenta cuenta) throws Exception;
	public List<Cuenta> listaCuenta();
	public String generarNumeroDeCuenta();
	public void enviarCorreo(String destinatario, String asunto, String cuerpo);

}
