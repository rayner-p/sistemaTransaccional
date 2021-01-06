package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;

@Local
public interface GestionSistemLocal {
	public Empleado usuarioRegistrado(String cedula) ;
	public void registarEmpleado(Empleado empleado) throws Exception;
	public Empleado buscarEmpleado(String cedula ) throws Exception ;
	public void actualizarEmpleado(Empleado empleado) throws Exception;
	public void eliminarEmpleado(String cedula) throws Exception;
	public List<Empleado> getEmpleadosT();
	public void insertar(Parametrizar parametros) throws SQLException;
	public void insertarPoliza(Poliza poliza)throws SQLException ;
	public List<Poliza> obtenerPolizas();
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
	public String IniciarSesion(String usuario, String contraseña);
	public Empleado usuario(String usuario, String contra) throws Exception ;
	public String obtenerFecha(Date fecha);
	public List<SesionCliente> obtenerSesionesEmpleados(String cedulaCliente);
	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente);
	public SesionCliente buscarSesionCliente(int codigoSesionCliente);
	public void guardarSesionEmpleado(SesionCliente sesionCliente);
	public void guardarSesionCliente(SesionCliente sesionCliente);
	public void actaulizarCliente(Cliente cliente) throws Exception;
	public List<Cliente> getClienteT();
	public Cuenta buscarCuentaD(String cedulaCliente);
	public void guardarCuentaDeAhorros(Cuenta c);
	public List<Parametrizar> obtenerParametros ();
	public Cliente usuarioCliente(String usuario, String contra) throws Exception;
	





}
