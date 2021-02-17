package ec.ups.edu.appdis.g1.sistemaTransaccional.negocio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.ErrorManager;

import javax.activation.DataHandler;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.NoResultException;

import org.primefaces.PrimeFaces;

import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ClienteDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.CuentaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.EmpleadoDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ParametrizarDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.PolizaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.SesionClienteDAO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.TransaccionDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.TransferencialLocalDAO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.PolizaPOJO;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.TranferenciaLocal;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;

@Stateless
public class sistemaTransaccionaON implements GestionSistemLocal {
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
	@Inject
	private TransferencialLocalDAO daoTransferenciaR;

	private String contraN;
	private String usuarioN;
	private String cedulaCliente;

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
		// System.out.println("ESTO CEDULA INGRESADA " + cedula);
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

	/**
	 * Método que permite registar el ingreso de un Empleado al sistema
	 * 
	 * @param empleado que se obtiene por medio de los Beans
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public void registarEmpleado(Empleado empleado) throws Exception {
		if (!validadorDeCedula(empleado.getCedula())) {
			throw new Exception("Cedula Incorrecta");
		} else {

			try {
				System.out.println("INGRESA AL CREAR EMPLEADO");
				daoEmpleado.insert(empleado);
				System.out.println("EMPLEADO CREADO CORRECTO");
			} catch (Exception e) {
				throw new Exception(e.toString());
			}
		}

	}

	/**
	 * Método que permite registar el ingreso de un Cliente al sistema
	 * 
	 * @param empleado que se obtiene por medio de los Beans
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public void registarCliente(Cliente empleado) throws Exception {
		try {
			// System.out.println("BEAN"+empleado);
			System.out.println("entra al on ");
			daoCliente.insert(empleado);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro al insertar");
		}

	}

	/**
	 * Método que permite registar el ingreso de una nueva Cuenta al sistema
	 * 
	 * @param cuenta que se obtiene por medio de los Beans de la aprte gráfica
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */

	public void regirestarCuenta(Cuenta cuenta) throws Exception {

		daoCuenta.insert(cuenta);
		System.out.println("Cuenta creado");

	}

	/**
	 * Método que permite buscar a un cliente por medio de la cedula
	 * 
	 * @param cedula que se obtiene por medio de las cjasa de texto en la interfaz
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public Cliente buscarCliente(String cedula) throws Exception {
		if (!validadorDeCedula(cedula)) {
			System.out.println("ERROR CEDULA");
		}

		Cliente cliente = daoCliente.read(cedula);
		cedulaCliente = cliente.getCedula();
		System.out.println("BUSQUEDAD CLIENTE CORRECTA " + cedulaCliente);
		return cliente;

	}

	/**
	 * Método que permite buscar a un empleado por medio de la cedula
	 * 
	 * @param cedula que se obtiene por medio de las cjasa de texto en la interfaz
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
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

	/**
	 * Método que permite actualizar a un Empleado
	 * 
	 * @param empleado Se obtiene al momento que se instancia este metodo. suele ser
	 *                 usado para la interfaz
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */

	public void actualizarEmpleado(Empleado empleado) throws Exception {
		String empleCedula = empleado.getCedula();
		if (daoEmpleado.read(empleCedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}

		empleado.setUsuario(usuarioN);
		empleado.setContrasenia(contraN);
		daoEmpleado.update(empleado);
		System.out.println("DATOS DEL ON ++" + empleado.getContrasenia());
		System.out.println("Empleado actualizado");

	}

	/**
	 * Método que permite actualizar a un Cliente
	 * 
	 * @param cliente Se obtiene al momento que se instancia este metodo. suele ser
	 *                usado para la interfaz
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public void actaulizarCliente(Cliente cliente) throws Exception {
		String empleCedula = cliente.getCedula();
		if (daoCliente.read(empleCedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}

		cliente.setUsuario(usuarioN);
		cliente.setContrasenia(contraN);
		daoCliente.update(cliente);
		System.out.println("DATOS DEL ON ++" + cliente.getContrasenia());
		System.out.println("Empleado actualizado");

	}

	/**
	 * Nos ayuda a obtener una contrasenia nueva
	 * 
	 * @return contraN nueva contrasenia para el usuario
	 */
	public String getContraN() {
		return contraN;
	}

	/**
	 * Nos ayuda a obtener una contrasenia nueva
	 * 
	 * @return contraN nueva contrasenia para el usuario
	 */
	public void setContraN(String contraN) {
		this.contraN = contraN;
	}

	/**
	 * Nos ayuda a obtener una usuarioN nueva
	 * 
	 * @return usuarioN nueva contrasenia para el usuario
	 */
	public String getUsuarioN() {
		return usuarioN;
	}

	/**
	 * Nos ayuda a obtener una usuarioN nueva
	 * 
	 * @return usuarioN nueva contrasenia para el usuario
	 */
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

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @param cedula El parametro cedula me permite obtener un Empleado que contenga
	 *               la cedual igual al parametro
	 * @return Un Empleado registrado en la Base de Datos
	 */
	public void eliminarEmpleado(String cedula) throws Exception {
		if (daoEmpleado.read(cedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		daoEmpleado.delete(cedula);
		System.out.println("Empleado eliminado");

	}

	/**
	 * Metodo para obtener una lista con todos los empleados
	 * 
	 * 
	 * 
	 * @return daoEmpleado.getEmpleados() Un Empleado registrado en la Base de Datos
	 */
	public List<Empleado> getEmpleadosT() {
		System.out.println("Entra al LIST DEL SESION EMPLEADO");
		return daoEmpleado.getEmpleados();

	}

	/**
	 * Metodo para obtener una lista con todos los empleados
	 * 
	 * 
	 * 
	 * @return daoEmpleado.getEmpleados() Un Empleado registrado en la Base de Datos
	 */
	public List<Cliente> getClienteT() {
		return daoCliente.getEmpleados();

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

	public void insertarTransaccion(Transaccion transaccion) {
		daoTransaccion.insertarTransaccion(transaccion);
	}

	public List<Transaccion> obtenerTransaccion() {
		return daoTransaccion.obtenerTransaccion();
	}

	public List<Cuenta> listaCuenta() {
		List<Cuenta> clientes = daoCuenta.obtenerCuenta2();
		System.out.println("obtener cuenta on cuenta" + clientes);
		return clientes;
	}

	/**
	 * Metodo que nos permite generar un numero de cuenta aleatorio
	 * 
	 *
	 * @return resultadoFinal Nos devuele una cadena de texto con el numero ue se ha
	 *         generado
	 */

	public String generarNumeroDeCuenta() {
		System.out.println("ingresa al on generar num");
		int numeroInicio = 4040;
		List<Cuenta> listaCuentas = listaCuenta();
		System.out.println("lista" + listaCuentas);
		int numero = listaCuentas.size() + 1;
		String resultado = String.format("%08d", numero);
		String resultadoFinal = String.valueOf(numeroInicio) + resultado;
		System.out.println("resultado del crear num cuenta on " + resultadoFinal);
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
		String finalin = "";
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
			finalin = resultadoNombre.toLowerCase() + a.toLowerCase() + ud;
		} catch (Exception e) {
			System.out.println("Debe ingresar una cedula primero");
		}
		return finalin;

	}

	/**
	 * Metodo que nos permite realizar el cambio de la contrasenia de un usuario
	 * 
	 *
	 * @param empleado El objeto Empleado cuya cedula quiere cambiarse
	 */
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

	/**
	 * Metodo que nos permite calcular la edad de la persona en base a la fecha
	 * ingresada e un usuario
	 * 
	 *
	 * @param fechaNacimiento La fecha de nacimiento del Empleado
	 */
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

	/**
	 * Metodo que nos permite calcular la edad de la persona en base a la fecha
	 * ingresada e un usuario
	 * 
	 *
	 * @param fechaNacimiento La fecha de nacimiento del Empleado
	 */
	public boolean validarIngresoNumeros(String datos) {
		if (datos.matches("[0-9]*"))
			System.out.println("Es un número");
		else
			System.out.println("No es un número");
		return true;

	}

	/**
	 * Metodo que nos permite validar que solo ingrese letras el usuario
	 * 
	 *
	 * @param datos La cadena de texto que se quiere validar
	 */
	public boolean validarIngresoLetras(String datos) {
		if (datos.matches("/[A-Za-z ñ]+/")) {
			System.out.println("LETRAS VALIDAS");
		} else {
			System.out.println("No validas letras");
		}
		return true;
	}

	/**
	 * Metodo que nos permite validar el ingreso de correos electronicos de un
	 * usuario
	 * 
	 *
	 * @param correo El correo que se quiere validar
	 */
	public boolean validarIngresoCorreo(String correo) {
		if (correo.matches("[^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$]")) {
			System.out.println("Correo valido");
		} else {
			System.out.println("Correo no valido");
		}
		return true;
	}

	/**
	 * Metodo que nos permite enviar correos a los usuarios y empleados del sistema
	 * 
	 * @param destinatario Es el empleado o cliente que se registra/ó en el sistema
	 * @param asunto       mensaje predeterminado que se va aenviar
	 *
	 * @param cuerpo       contenido del mensaje
	 */

	public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
		System.out.println("ENTR A CORREO ESTO" + " " + destinatario + "    " + asunto + " " + cuerpo);
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.transport.protocol", "smtp");
		propiedad.put("mail.smtp.auth", "true");
		propiedad.put("mail.smtp.socketFactory.fallback", "true");
		propiedad.put("mail.smtp.socketFactory.port", "587");
		propiedad.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		propiedad.put("mail.smtp.socketFactory.fallback", "true");
		propiedad.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// sets SMTP server properties
		// Properties emailProperties = System.getProperties();
		// emailProperties.put("mail.smtp.port", "587");
		// emailProperties.put("mail.ssl.port", "587");
		// emailProperties.put("mail.smtp.auth", "true");
		// emailProperties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(propiedad);

		String correoEnvia = "bancointernacionaprueba@gmail.com";
		String contrasena = "p4t1t0.123";
		propiedad.put("mail.smtp.user", correoEnvia);
		// creates a new session, no Authenticator (will connect() later)

		// *** END CHANGE
		System.out.println("ANTES DEL TRY");
		// creates a new e-mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			System.out.println("ENTRA A; TY");
			msg.setFrom("Banco Internacional <" + correoEnvia + ">");
			// msg.setFrom(new InternetAddress("Banco Internacional <"+correoEnvia+">"));
			InternetAddress[] toAddresses = { new InternetAddress(destinatario) };
			msg.addRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(asunto);

			msg.setText(cuerpo);
			msg.setSender(new InternetAddress(correoEnvia));
			msg.setSentDate(new Date());
			String emailHost = "smtp.gmail.com";
			Transport transport = session.getTransport("smtp");
			System.out.println("inicio" + " " + correoEnvia + contrasena);

			transport.connect(emailHost, correoEnvia, contrasena);

			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("final");
			transport.close();

		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			System.out.println("error al enviar correo1" + e.getLocalizedMessage());
		} catch (MessagingException e1) {
			System.out.println("error al enviar correo2" + e1.getLocalizedMessage());

		}

	}

	/**
	 * Metodo que nos permite generar una contrasenia aleatorio para los usuarios y
	 * empleados del sistema
	 * 
	 * 
	 *
	 * @param clave cadena de texto que contiene la nueva contrasenia para los
	 *              usuarios
	 */
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

	/**
	 * Metodo que nos permite validar el ingreso de sesión para los usuarios y
	 * empleados del sistema
	 * 
	 * @param usuario nombre de usario generado por el sistema
	 * @param clave   cadena de texto que se generó para acceder al sistema
	 */
	public String IniciarSesion(String usuario, String contraseña) {
		try {

			if (daoEmpleado.obtenerClienteUsuarioContraseña(usuario, contraseña) != null) {
				System.out.println("datos que se meten para comparar    " + usuario + "" + contraseña);

			} else {
				System.err.println("No hay datos ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
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
	 * Metodo para obtener un Cliente
	 * 
	 * @param usuario El parametro usuario me permite obtener un Cliente que
	 *                contenga el usuario pasado como parametro
	 * @param contra  El parametro contra permite obtener un Empleado que contenga
	 *                el usuario pasado como parametro
	 * @return Un Empleado con los usuario y contraseña de acuerdo a los parametros
	 * @throws Exception Excepcion cuando no se obtiene ningun usuario
	 */
	public Cliente usuarioCliente(String usuario, String contra) throws Exception {
		try {

			Cliente clie = daoCliente.obtenerClienteUsuarioContraseña(usuario, contra);

			if (clie != null) {
				return clie;
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

	/**
	 * Metodo que permite llevar un registro de los intentos de acceso del cliente
	 * al sistema transacciomnal
	 * 
	 * @param SesionCliente datos del cliente
	 * 
	 */
	public void guardarSesionCliente(SesionCliente sesionCliente) {
		System.out.println("ENTRS AQUI?");
		Cliente cli = sesionCliente.getCliente();
		System.out.println("cliente que guarda sesion clietne" + cli);
		// System.out.println("esto es lo que se obtiene de la sesion ->" + cli);
		String destinatario = cli.getCorreo();
		System.out.println("estado" + sesionCliente.getEstado());
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
	 * Metodo que permite llevar un registro de los intentos de acceso de los
	 * empleados al sistema transacciomnal
	 * 
	 * @param SesionCliente datos del cliente
	 * 
	 */
	public void guardarSesionEmpleado(SesionCliente sesionEmpleado) {
		System.out.println("que trae el sesion empleado");
		Empleado cli = sesionEmpleado.getEmpleado();
		String destinatario = cli.getCorreo();
		System.out.println("estado" + sesionEmpleado.getEstado());

		// sesionEmpleado != null &
		// sesionEmpleado.getEstado().equalsIgnoreCase("Incorrecto")
		if (sesionEmpleado.getEstado().equalsIgnoreCase("Incorrecto")) {
			String asunto = "INICIO DE SESION FALLIDA";
			String cuerpo = " BANCO INTERNACIONAL\n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha hecho intento de sesión fallida en su cuenta.    \n"
					+ "                       Fecha: " + obtenerFecha(sesionEmpleado.getFechaSesion())
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
			System.out.println("correcto");
			String asunto = "INICIO DE SESION CORRECTA";
			String cuerpo = "BANCO INTERNACIONAL \n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombres().toUpperCase() + " "
					+ cli.getApellidos().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "El sistema transaccional del Banco Internacional le informa a usted que se ha iniciado sesión correctamente    \n"
					+ "                       Fecha: " + obtenerFecha(sesionEmpleado.getFechaSesion())
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

		daoSesion.insert(sesionEmpleado);

	}

	/**
	 * Metodo que permite guardar una cuenta de ahorro
	 * 
	 * @param c Cuenta de ahorro que se guarda
	 */
	public void guardarCuentaDeAhorros(Cuenta c) {
		Cliente cliente = new Cliente();
		Date fech = new Date();
		try {
			System.out.println("Cuenta ON +" + c.getNumeroCuenta());
			cliente = buscarCliente(cedulaCliente);

			if (cliente != null) {
				System.out.println("Ingresas al if del cliente");
				String usuario = cliente.getUsuario();
				String contraseña = cliente.getContrasenia();

				String destinatario = cliente.getCorreo(); // A quien le quieres escribir.
				System.out.println("QUE DATOS TIENE ESTO  --?" + usuario + "" + contraseña);
				String asunto = "APERTURA DE CUENTA";
				String cuerpo = "                                              SISTEMA TRANSACCIONAL\n"
						+ "------------------------------------------------------------------------------\n"
						+ "              Estimado(a): " + cliente.getNombres().toUpperCase() + " "
						+ cliente.getApellidos().toUpperCase() + "\n"
						+ "------------------------------------------------------------------------------\n"
						+ "BANCO INTERNACIONAL le informa que la apertura de su cuenta ha sido  exitosa.    \n"
						+ "                       Fecha de apertura: " + fech
						+ "                                     \n"
						+ "                                                                              \n"
						+ "------------------------------------------------------------------------------\n";
				CompletableFuture.runAsync(() -> {
					try {
						if (c.getSaldo() != 0.0) {

							enviarCorreo(destinatario, asunto, cuerpo);
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
									"Se ha enviado un correo a" +destinatario);

							PrimeFaces.current().dialog().showMessageDynamic(message);

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				if (c.getSaldo() != 0.0) {
					c.setCuenta_fk(cedulaCliente);
					daoCuenta.insert(c);
				} else {
					System.out.println("Ingrese Saldo");
				}

			}
		} catch (Exception e) {
			System.out.println("ERROR AL CREAR LA CUENTA");
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que me permite obtener una cuenta de ahorros que pertenezca a un
	 * cliente
	 * 
	 * @param cedulaCliente Cedula del cliente de la cuenta de ahorros
	 * @return Cuenta de ahorro obtenida de la busqueda
	 */
	public Cuenta getCuentaCedulaCliente(String cedulaCliente) {
		System.out.println("entra al buscar cuenta");
		Cuenta cuentaDeAhorro = daoCuenta.getCuentaCedulaCliente(cedulaCliente);
		return cuentaDeAhorro;

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
		List<SesionCliente> sesonCliente = new ArrayList<SesionCliente>();
		try {
			System.out.println("INGRESA ON OBTEN CLIENTE" + cedulaCliente);
			sesonCliente = daoSesion.obtenerSesionCliente(cedulaCliente);
			System.out.println("TRAE ON " + sesonCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sesonCliente;

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
			System.out.println("obtener sesion empleado on" + cedulaCliente);
			return daoSesion.obtenerSesionEmpleado(cedulaCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 */
	public void agregarCuentaTransferecia(TranferenciaLocal transferencia) throws Exception {
		Cuenta c = new Cuenta();
		if (daoCliente.read(c.getNumeroCuenta()) == null) {
			throw new Exception("No existe usuario con esa cédula");
		} else {
			System.out.println("Transferencia");
			daoTransferenciaR.insert(transferencia);
		}

	}

	// metodo que sirve para actualizar los datos del cliente
	public TranferenciaLocal obtenerClienteCuenta(String numeroCuenta) {
		if (daoTransferenciaR.obtenerClienteCuenta(numeroCuenta) == null) {
			System.out.println("No existe cliente con ese número de cuenta");

		} else {
			System.out.println("existe usuario");
		}
		return null;
	}

	/**
	 * Metodo que permite obtener las transferencias locales de un cliente
	 * 
	 * 
	 * 
	 * @return Lista de transferencias de un cliemte
	 */
	/*
	 * public List<TranferenciaLocal> getTransfereciaLocals() { try { return
	 * daoTransferenciaR.getTransfereciaLocals(); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 */
	/**
	 * 
	 */
	public List<Cuenta> getTransfereciasOrigenes(String cuenta) {
		try {
			List<Cuenta> resultados = (List<Cuenta>) daoTransferenciaR.getTransfereciaLocals(cuenta);
			return resultados;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TranferenciaLocal> getTransfereciaLocals() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public Cuenta obtenerSaldoClienteCuenta(String numeroCuenta) {
		Cuenta valor = new Cuenta();
		try {
			valor = daoCuenta.obtenerSaldoClienteCuenta(numeroCuenta);
		} catch (SQLException e) {
			System.err.println("ERROR EN EL ON AL OBTENER SALDO CUENTA");
			e.printStackTrace();
		}
		return valor;
	}

	/**
	 * 
	 */
	public String actaulizarCuentaCliente(String numeroCuenta, double valor) {
		if (numeroCuenta == null) {
			System.out.println("No hay datos en la cuenta");
		} else {
			System.out.println("qOBTIENES s " + "" + numeroCuenta + "" + valor);
			daoCuenta.actaulizarCuentaCliente(numeroCuenta, valor);
			System.out.println("se actualizan cuenta");
		}
		return null;
	}

	/**
	 * 
	 */
	public Cuenta obtenerCuentaPorNumero(String numerCuenta) {
		Cuenta cuentB = new Cuenta();
		if (numerCuenta == null) {
			System.out.println("No hay datos en la cuenta");
		} else {

			cuentB = daoCuenta.obtenerCuentaPorNumero(numerCuenta);
			System.out.println("vale obtener cuenta por num");
		}
		return cuentB;
	}

	public void crearPoliza(Poliza poli) {
		try {
			System.out.println("Poliza a crearse" + " " + poli);
			daoPoliza.insertarPoliza(poli);

		} catch (Exception e) {
			System.err.println("Error al crear la póliza " + " " + e.getLocalizedMessage());
		}
	}

	public Parametrizar obtenerParametrosporDia(int maximo) {

		Parametrizar resultadosP = new Parametrizar();
		try {
			System.out.println("ENTRA AL ON PARA POR DIA");
			resultadosP = daoParametrizar.obtenerParametrosporDia(maximo);

			System.out.println("TASA OBTENIDA " + " " + resultadosP.getTasaInteres());

		} catch (Exception e) {
			System.err.println("ERROR AL MOMENTO DE OBTENER LA TASA DE INTERES " + " " + e.getLocalizedMessage());
		}
		return resultadosP;
	}

	/**
	 * Metodo que permite convertir una clase InputStream en un byte [] arreglo de
	 * bytes para su posterior guardado en la base de datos.
	 * 
	 * @param in Una clase InputStream que continue la información de un archivo que
	 *           se selecciona en el proceso de la solicitud de credito.
	 * @return Un clase byte [] un arreglo de bytes del InputStream pasado como
	 *         parametro.
	 * @throws Exception
	 */

	public byte[] convertirArchivos(InputStream in) throws Exception {

		if (in != null) {
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[5000000];
				int len;
				while ((len = in.read(buffer)) != -1) {
					os.write(buffer, 0, len);

				}
				return os.toByteArray();
			} catch (Exception e) {
				throw new Exception("Error en convertir archivo" + e.getLocalizedMessage());
			}

		} else {
			System.err.println("erro, archivo nulo");
		}
		return null;

	}

	public String actualizarPoliza(String numeroCuenta) {
		System.out.println("Entra al on act poli" + " " + numeroCuenta);
		if (numeroCuenta == null) {
			System.out.println("NUMERO VACIO");
		} else {
			daoPoliza.actualizarPoliza(numeroCuenta);
		}
		return null;
	}

	@Override
	public List<Poliza> obtenerPolizas() {
		System.out.println("ENTRA AL ON OBTENER POLIZA");
		daoPoliza.obtenerPolizas();
		return daoPoliza.obtenerPolizas();
	}

	@Override
	public Cliente obtenerDatosPorCedula(String cedula) throws Exception {
		System.err.println("INGRESA AL ON PARA BUSCAR ");
		if (cedula == null) {
			System.out.println("Cedula vacia");
		} else {
			try {
				System.err.println("INGRESA AL ON PARA BUSCAR CLIENTE PO");
				Cliente cliente = daoCliente.obtenerDatosPorCedula(cedula);
				System.out.println("CLIENTE" + cliente);
				return cliente;

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	/**
	 * Metodo que permite buscar las transacciones de un usuario entre fechas
	 * 
	 * @param cedula Numero de cedula de la persona que busca
	 * @param fechaI La fecha de inicio desde donde se quieren ver las
	 *               transacciones.
	 * @param fechaF La fecha de fin hasta donde se quieren ver las transacciones.
	 * @return Una lista de las transacciones/movimientos del usuario entre las
	 *         fechas indicadas.
	 * @throws Exception Excepción por si el cliente no tiene transacciones.
	 */
	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, String fechaI, String fechaF) {
		String fechaInicio = fechaI + " 00:00:00.000000";
		String fechaFinal = fechaF + " 23:59:59.000000";
		try {
			return daoTransaccion.getListaTransaccionesFechas(cedula, fechaInicio, fechaFinal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
