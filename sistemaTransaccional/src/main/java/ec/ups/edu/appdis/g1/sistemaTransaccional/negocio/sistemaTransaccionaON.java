package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import javax.activation.DataHandler;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.NoResultException;

import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ClienteDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.CuentaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.EmpleadoDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ParametrizarDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.PolizaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.SesionClienteDAO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.TransaccionDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;

@Stateless
public class sistemaTransaccionaON implements GestionSistemRemoto, GestionSistemLocal {
	@Inject
	private EmpleadoDao daoEmpleado;
	@Inject
	private ParametrizarDao daoParametrizar;
	@Inject
	private PolizaDao daoPoliza;
	@Inject
	private TransaccionDao daoTransaccion;
	@Inject
	private ClienteDao daoCliente;
	@Inject
	private CuentaDao daoCuenta;
	@Inject
	private SesionClienteDAO daoSesion;
	
	private String contraN;
	private String usuarioN;
	
	FacesMessage msg = null;
	

	/**
	 * Método que permite valida el numero de cedula ingresado por el cliente
	 * 
	 * @param cedula que ingresa el usuario por la caja de texto
	 * @return cedula correcta o no en base al metodo.
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public boolean validadorDeCedula(String cedula) throws Exception {

		boolean cedulaCorrecta = false;
		System.out.println("INGRESA VALIDACION CEDULA");
		System.out.println("ESTO CEDULA INGRESADA " + cedula);
		try {
			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
				System.out.println("ESTAS EN EL VALIDADAOR?");
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
			throw new Exception("Error cedula  " + err.getMessage());
		}
		if (!cedulaCorrecta) {
			return cedulaCorrecta;
		}
		return cedulaCorrecta;
	}

	public void registarEmpleado(Empleado empleado) throws Exception {
		if (!validadorDeCedula(empleado.getCedula())) {
			throw new Exception("Cedula Incorrecta");
		} else {

			try {
				daoEmpleado.insert(empleado);
				System.out.println("creado");
			} catch (Exception e) {
				throw new Exception(e.toString());
			}
		}

	}

	public void registarCliente(Cliente empleado) throws Exception {
		if (!validadorDeCedula(empleado.getCedula())) {
			throw new Exception("Cedula Incorrecta ssdsd");
		}
		try {
			daoCliente.insert(empleado);
			System.out.println("Cliente creado");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro al insertar");
		}

	}

	public Cliente buscarCliente(String cedula) throws Exception {
		if (!validadorDeCedula(cedula)) {
			System.out.println("ERROR CEDULA");
		}
		Cliente cliente = daoCliente.read(cedula);
		System.out.println("BUSQUEDAD CLIENTE CORRECTA");
		return cliente;

	}

	public void regirestarCuenta(Cuenta cuenta) throws Exception {

		daoCuenta.insert(cuenta);
		System.out.println("Cuenta creado");

	}

	public Empleado buscarEmpleado(String cedula) throws Exception {
		if (!validadorDeCedula(cedula)) {
			System.out.println("NO PASA VALIDACION");
		}
		
		Empleado empleadoL = daoEmpleado.read(cedula);
		contraN = empleadoL.getContrasenia();
		usuarioN = empleadoL.getUsuario();
		System.out.println("BUSQUEDAD EMPLEADO CORRECTA");
		return empleadoL;

	}

	public void actualizarEmpleado(Empleado empleado) throws Exception {
		String empleCedula = empleado.getCedula();
		if (daoEmpleado.read(empleCedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		
		empleado.setUsuario(usuarioN);
		empleado.setContrasenia(contraN);
		daoEmpleado.update(empleado);
		System.out.println("DATOS DEL ON ++" +empleado.getContrasenia());
		System.out.println("Empleado actualizado");

	}
	public void actaulizarCliente(Cliente cliente) throws Exception {
		String empleCedula = cliente.getCedula();
		if (daoEmpleado.read(empleCedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		
		cliente.setUsuario(usuarioN);
		cliente.setContrasenia(contraN);
		daoCliente.update(cliente);
		System.out.println("DATOS DEL ON ++" +cliente.getContrasenia());
		System.out.println("Empleado actualizado");

	}

	public String getContraN() {
		return contraN;
	}

	public void setContraN(String contraN) {
		this.contraN = contraN;
	}

	public String getUsuarioN() {
		return usuarioN;
	}

	public void setUsuarioN(String usuarioN) {
		this.usuarioN = usuarioN;
	}

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @param cedula El parametro cedula me permite obtener un Empleado que contenga
	 *               la cedual igual al parametro
	 * @return Un Empleado registrado en la Base de Datos
	 */
	public Empleado usuarioRegistrado(String cedula) {
		return daoEmpleado.read(cedula);
	}

	public void eliminarEmpleado(String cedula) throws Exception {
		if (daoEmpleado.read(cedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		daoEmpleado.delete(cedula);
		System.out.println("Empleado eliminado");

	}

	public List<Empleado> getEmpleadosT() {
		return daoEmpleado.getEmpleados();

	}
	public List<Cliente> getClienteT() {
		return  daoCliente.getEmpleados();

	}

	public void insertar(Parametrizar parametros) throws SQLException {
		daoParametrizar.insertar(parametros);

	}

	public List<Parametrizar> obtenerParametros() {
		return daoParametrizar.obtenerParametros();
	}

	public void insertarPoliza(Poliza poliza) throws SQLException {
		daoPoliza.insertarPoliza(poliza);

	}

	public List<Poliza> obtenerPolizas() {
		return daoPoliza.obtenerPolizas();
	}

	public void insertarTransaccion(Transaccion transaccion) {
		daoTransaccion.insertarTransaccion(transaccion);

	}

	public List<Transaccion> obtenerTransaccion() {
		return daoTransaccion.obtenerTransaccion();
	}

	public List<Cuenta> listaCuenta() {
		List<Cuenta> clientes = daoCuenta.obtenerCuenta();
		return clientes;
	}

	public String generarNumeroDeCuenta() {
		int numeroInicio = 4040;
		List<Cuenta> listaCuentas = listaCuenta();
		int numero = listaCuentas.size() + 1;
		String resultado = String.format("%08d", numero);
		String resultadoFinal = String.valueOf(numeroInicio) + resultado;
		return resultadoFinal;
	}

	/**
	 * Metodo que nos permite generar un nombre de usuario aleatorio
	 * 
	 * @param cedula   que el usuario haya ingresado en la creacion de la cuenta
	 * @param nombre   que haya ingresado el usuario en la creacion de la cuenta
	 * @param apellido que haya ingresado el usuario en la creacion de la cuent
	 * @return Nos devuele un nombre de usuario en base al nombre y apellido que se
	 *         haya ingresado
	 */
	public String generarNombreUsuario(String cedula, String nombre, String apellido) {
		String finalin = "" ;
		try {
			System.out.println(cedula);
			System.out.println(nombre);
			System.out.println(apellido);
			String ud = cedula.substring(cedula.length() - 1);
			String resultadoNombre = nombre.substring(0, 1);
			int it = 0;
			for (int i = 0; i < apellido.length(); i++) {
				if (apellido.charAt(i) == 32) {
					it = i;
				}
			}
			String a = "";
			if (it == 0) {
				a = apellido.substring(0, apellido.length());
			} else {
				a = apellido.substring(0, it);
			}
			System.out.println(resultadoNombre);
			finalin =resultadoNombre.toLowerCase() + a.toLowerCase() + ud;
		} catch (Exception e) {
			System.out.println("Debe ingresar una cedula primero");
		}
		return finalin;
		
	}

	public void cambioContrasena(Empleado empleado) {
		String destinatario = empleado.getCorreo();
		String asunto = "CAMBIO DE CONTRASEÑA";
		String cuerpo = "BANCO INTERNACIONAL                                               SISTEMA TRANSACCIONAL\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + empleado.getNombres().toUpperCase() + "          "
				+ empleado.getApellidos().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ " le informamos que su contraseña ha sido cambiada exitosamente.   \n"
				+ "                                                                              \n"
				+ "                   Su nueva contraseña es:   " + empleado.getContrasenia() + "       \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		CompletableFuture.runAsync(() -> {
			try {
				enviarCorreo(destinatario, asunto, cuerpo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public int obtenerEdad(Date fechaNacimiento) {
		Calendar a = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		a.setTime(fechaNacimiento);
		b.setTime(new Date());
		int edad = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			edad--;
		}
		return edad;
	}

	public boolean validarIngresoNumeros(String datos) {
		if (datos.matches("[0-9]*"))
			System.out.println("Es un número");
		else
			System.out.println("No es un número");
		return true;

	}

	public boolean validarIngresoLetras(String datos) {
		if (datos.matches("/[A-Za-z ñ]+/")) {
			System.out.println("LETRAS VALIDAS");
		} else {
			System.out.println("No validas letras");
		}
		return true;
	}

	public boolean validarIngresoCorreo(String correo) {
		if (correo.matches("[^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$]")) {
			System.out.println("Correo valido");
		} else {
			System.out.println("Correo no valido");
		}
		return true;
	}

	@SuppressWarnings("static-access")
	public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
		//String mailhost = "smtp.gmail.com";
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.transport.protocol", "smtp");
		propiedad.put("mail.smtp.auth", "plain");
		propiedad.put("mail.smtp.socketFactory.port", "587");
		propiedad.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		propiedad.put("mail.smtp.socketFactory.fallback", "false");
		propiedad.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		
		String correoEnvia = "bancointernacionaprueba@gmail.com";
        String contrasena = "P4T1T0.123@456";
		Session sesion = Session.getDefaultInstance(propiedad);
		System.out.println("vale la session del correo? ");
		
		sesion.getInstance(propiedad, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(correoEnvia, contrasena);
	        }
	    });
		try {
			MimeMessage mail = new MimeMessage(sesion);
			DataHandler handler = new DataHandler(new ByteArrayDataSource(cuerpo.getBytes(), "text/plain"));
			mail.setFrom("Banco Internacional <" + correoEnvia + ">");
	        InternetAddress[] toAddresses = { new InternetAddress(destinatario) };
			mail.addRecipients(Message.RecipientType.TO, toAddresses);
			mail.setSubject(asunto);
			mail.setText(cuerpo);
			mail.setSender(new InternetAddress(correoEnvia));
			Transport transportar = sesion.getTransport("smtp");
			transportar.connect(correoEnvia, contrasena);
			System.out.println(transportar);
			//transportar.sendMessage(mail, mail.getAllRecipients());
			
			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transportar.close();
		} catch (AddressException ex) {
			System.out.println("Errror en la direccion");
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} catch (MessagingException ex) {
			System.out.println("Errror al enviar mensje");
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		
	}

	public String generarContrasenia() {
		String simbolos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz0123456789!#$%&()*+,-./:;<=>?@_";

		int tam = simbolos.length() - 1;
		System.out.println(tam);
		String clave = "";
		for (int i = 0; i < 10; i++) {
			int v = (int) Math.floor(Math.random() * tam + 1);
			clave += simbolos.charAt(v);
		}

		return clave;
	}

	public String IniciarSesion(String usuario, String contraseña) {
		try {

			if (daoEmpleado.obtenerClienteUsuarioContraseña(usuario, contraseña) != null) {
				System.out.println("datos que se menten para comparar    " + usuario + "" + contraseña);
			} else {
				System.err.println("No hay datos ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @param usuario El parametro usuario me permite obtener un Empleado que
	 *                contenga el usuario pasado como parametro
	 * @param contra  El parametro contra permite obtener un Empleado que contenga
	 *                el usuario pasado como parametro
	 * @return Un Empleado con los usuario y contraseña de acuerdo a los parametros
	 * @throws Exception Excepcion cuando no se obtiene ningun usuario
	 */
	public Empleado usuario(String usuario, String contra) throws Exception {
		try {
			Empleado em = daoEmpleado.obtenerClienteUsuarioContraseña(usuario, contra);
			if (em != null) {
				return em;
			}
		} catch (NoResultException e) {
			throw new Exception("Credenciales Incorrectas");
		}
		return null;

	}

	/**
	 * Metodo que permite cambiar el formato de la fecha
	 * 
	 * @param fecha Fecha que se cambiara el formato
	 * @return La fecha en un formato requerido de tipo texto.
	 */
	public String obtenerFecha(Date fecha) {
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(fecha);
	}

	public void guardarSesionCliente(SesionCliente sesionCliente) {
		Cliente cli = sesionCliente.getCliente();
		String destinatario = cli.getCorreo();
		if (sesionCliente.getEstado().equalsIgnoreCase("Incorrecto")) {

			String asunto = "INICIO DE SESION FALLIDA";
			String cuerpo = " BANCO INTERNACIONAL\n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha hecho intento de sesión fallida en su cuenta.    \n"
					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
					+ "                                     \n"
					+ "                                                                              \n"
					+ "------------------------------------------------------------------------------\n";
			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		} else {

			String asunto = "INICIO DE SESION CORRECTA";
			String cuerpo = "BANCO INTERNACIONAL \n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha iniciado sesión correctamente    \n"
					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
					+ "                                     \n"
					+ "                                                                              \n"
					+ "------------------------------------------------------------------------------\n";

			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		daoSesion.insert(sesionCliente);

	}
	public void guardarSesionEmpleado(SesionCliente sesionCliente) {
		Empleado cli = sesionCliente.getEmpleado();
		String destinatario = cli.getCorreo();
		if (sesionCliente.getEstado().equalsIgnoreCase("Incorrecto")) {

			String asunto = "INICIO DE SESION FALLIDA";
			String cuerpo = " BANCO INTERNACIONAL\n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha hecho intento de sesión fallida en su cuenta.    \n"
					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
					+ "                                     \n"
					+ "                                                                              \n"
					+ "------------------------------------------------------------------------------\n";
			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		} else {

			String asunto = "INICIO DE SESION CORRECTA";
			String cuerpo = "BANCO INTERNACIONAL \n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha iniciado sesión correctamente    \n"
					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
					+ "                                     \n"
					+ "                                                                              \n"
					+ "------------------------------------------------------------------------------\n";

			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		daoSesion.insert(sesionCliente);

	}

	/**
	 * Metodo que permite buscar una Sesion
	 * 
	 * @param codigoSesionCliente Codigo de la sesion que se desea buscar
	 * @return Sesion obtenida de la busqueda
	 */

	public SesionCliente buscarSesionCliente(int codigoSesionCliente) {
		return daoSesion.read(codigoSesionCliente);
	}

	/**
	 * Metodo que permite obtener las sesiones de un cliente
	 * 
	 * @param cedulaCliente Cedula del cliente que tiene la sesion que se desea
	 *                      buscar
	 * @return Lista de sesiones de un cliente
	 */
	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente) {
		try {
			return daoSesion.obtenerSesionCliente(cedulaCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * Metodo que permite obtener las sesiones de un cliente
	 * 
	 * @param cedulaCliente Cedula del empleado que tiene la sesion que se desea
	 *                      buscar
	 * @return Lista de sesiones de un empleado
	 */
	public List<SesionCliente> obtenerSesionesEmpleados(String cedulaCliente) {
		try {
			return daoSesion.obtenerSesionEmpleado(cedulaCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	

}
