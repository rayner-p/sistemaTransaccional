package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;
import java.io.Serializable;

@Named
@SessionScoped
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ced;
	private Cliente newCliente;
	private List<SesionCliente> lstSesionesCliente;
	private List<Cuenta> cuentaCLientes;
	private String cedu;
	private String usuario;
	private String contrasenia;
	private String tipoEstado;
	private int tiempo;
	private List<String> cuentasK;
	private Cuenta c;
	Cliente validaroCliente;
	private Date fechaInicio;
	private Date fechaFinal;
	@Inject
	private LoginBean clienteB;

	public List<String> getCuentasK() {
		return cuentasK;
	}

	public void setCuentasK(List<String> cuentasK) {
		this.cuentasK = cuentasK;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getTipoEstado() {
		return tipoEstado;
	}

	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public List<Cuenta> getCuentaCLientes() {
		return cuentaCLientes;
	}

	public void setCuentaCLientes(List<Cuenta> cuentaCLientes) {
		this.cuentaCLientes = cuentaCLientes;
	}

	@Inject
	private GestionSistemLocal on;

	public String getCedu() {
		return cedu;
	}

	public void setCedu(String cedu) {
		this.cedu = cedu;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public LoginBean getClienteB() {
		return clienteB;
	}

	public void setClienteB(LoginBean clienteB) {
		this.clienteB = clienteB;
	}

	/**
	 * Metodo para obtener un tipo de Empleado
	 * 
	 * @return El Tipod de Empleado que se esta asignando en la pagina xhtml
	 */

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@PostConstruct
	public void init() {
		newCliente = new Cliente();
		validaroCliente = new Cliente();
	}

	public Cliente getNewCliente() {
		return newCliente;
	}

	public void setNewEmpleado(Cliente newCliente) {
		this.newCliente = newCliente;
	}

	/**
	 * Metodo que permite obtener el atributo de tipo lista lstSesionesClientes de
	 * la clase
	 * 
	 * @return Atributo de tipo lista lstSesionesClientes de la clase
	 */
	public List<SesionCliente> getLstSesionesCliente() {
		return lstSesionesCliente;
	}

	/**
	 * Metodo que permite asignar un valor al atributo de tipo lista
	 * lstSesionesClientes de la clase
	 * 
	 * @param lstSesionesCliente Variable asignada al atributo de tipo lista
	 *                           lstSesionesClientes de la clase
	 */
	public void setLstSesionesCliente(List<SesionCliente> lstSesionesCliente) {
		this.lstSesionesCliente = lstSesionesCliente;
	}

	/**
	 * Metodo que permite obtener las sesiones de una cliente
	 * 
	 * @param cedula Cedula del cliente
	 * @return Lista de sesiones que tiene el cliente
	 */
	public List<SesionCliente> cargarSesiones() {

		List<SesionCliente> lis = on.obtenerSesionesCliente(clienteB.getCliente().getCedula());
		System.out.println("QUE TRAE ESTO" + " " + lis);
		if (lis != null) {
			lstSesionesCliente = lis;
			return lstSesionesCliente;
		}
		return null;
	}

	/**
	 * Metodo para generar usuarios aleatorios del cliente
	 * 
	 * @return usuario el nombre con elq ue se debe logear el cliente
	 */

	public String obtenerUsuario() {
		usuario = on.generarNombreUsuario(newCliente.getCedula(), newCliente.getNombres(), newCliente.getApellidos());
		newCliente.setUsuario(usuario);
		return usuario;
	}

	/**
	 * Metodo para generar usuarios aleatorios del cliente
	 * 
	 * @return usuario el nombre con elq ue se debe logear el cliente
	 */

	public String generarContrasena() {
		contrasenia = on.generarContrasenia();
		newCliente.setContrasenia(contrasenia);
		System.err.println("Se ha generado una contrasenia al azar" + contrasenia);
		return contrasenia;

	}

	public boolean validarCedulaExistent() {
		boolean bandera = true;

		try {
			validaroCliente = on.obtenerDatosPorCedula(newCliente.getCedula());
		} catch (Exception e) {
			System.out.println("Error al obtener cedula existen");
			bandera = false;
			e.printStackTrace();
		}
		return bandera;
	}

	/**
	 * Metodo para registrar el correo cuando se cree el cliente
	 * 
	 * 
	 */

	public String registrarCorreo() {
		System.out.println("entras al correo?   " + "" + usuario + contrasenia);

		Date fechaA = new Date();
		String destinatario = newCliente.getCorreo();
		String asunto = "   Creación de cuenta en el sistema del Banco Internacional  ";
		String cuerpo = "   Banco Internacional  SISTEMA TRANSACCIONAL\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + newCliente.getNombres().toUpperCase() + " "
				+ newCliente.getApellidos().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ "El sistema transaccional del Banco Internacional le informa que el usuario ha sido habilitado exitosamente.    \n"
				+ "                                                                              \n"
				+ "                       Su usuario es : " + usuario + "                          \n"
				+ "                   	Su clave de acceso es:   " + contrasenia + "               \n"
				+ "                       Fecha: " + fechaA + "                                     \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		String corre = destinatario + " " + asunto + " " + cuerpo;
		System.out.println(corre);
		CompletableFuture.runAsync(() -> {
			try {

				System.out.println("VALE EL CORREO?");
				on.enviarCorreo(destinatario, asunto, cuerpo);
				System.out.println("SE ha enviado un correo a: " + "\n" + "" + newCliente.getCorreo() + "");

			} catch (Exception e) {
				System.out.println("Error al enviar correo con contrasenia");
				e.printStackTrace();
			}
		});
		return null;

	}

	public boolean validarDatos() {
		boolean bandera = true;
		if (newCliente.getCedula().isEmpty() & newCliente.getNombres().isEmpty() & newCliente.getApellidos().isEmpty()
				& newCliente.getCiudad().isEmpty() & newCliente.getCelular().isEmpty()
				& newCliente.getFechaNacimiento() == null & newCliente.getCorreo().isEmpty()
				& newCliente.getEstadoCivil().isEmpty() & newCliente.getProvincia().isEmpty()
				& newCliente.getReferenciaDomicilio().isEmpty() & newCliente.getTelefono().isEmpty()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"Ingrese todos los datos ");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			bandera = false;
		} else {

		}
		return bandera;
	}

	/**
	 * Metodo para registrar el correo cuando se cree el cliente
	 * 
	 * 
	 */
	public String doRegistraCliente() {
		System.out.println(
				"DATOS CREADOS" + this.newCliente.getCedula() + "   " + this.newCliente.getNombres() + tipoEstado);
		try {
			System.out.println("Ingreso al try");
			if (validaroCliente.getCedula() == newCliente.getCedula()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Cedula ingresada ya está en el sistema"));

			} else if (tipoEstado.equalsIgnoreCase("Soltero/a")) {
				System.out.println(tipoEstado);
				System.out.println("ENTRA AL IF  SOLTERO PARA CREAR CLIENTE");

				newCliente.setEstadoCivil("Soltero(a)");

				try {
					System.out.println("ENTRAS AL TRY PARA CREAR?");
					if (validarDatos() == false) {

					} else {
						System.out.println("soltero");
						obtenerUsuario();
						generarContrasena();
						on.registarCliente(newCliente);
						addMessage("Confirmacion", "Cliente Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}

				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			} else if (tipoEstado.equalsIgnoreCase("Casado/a")) {
				System.out.println("ENTRA AL IF CASADO PARA CREAR CLIENTE");
				// newCliente.setTiempoResidencia(tiempo);
				newCliente.setEstadoCivil("Casado(a)");

				try {
					if (validarDatos() == false) {

					} else {
						System.out.println("casad");
						obtenerUsuario();
						generarContrasena();
						on.registarCliente(newCliente);
						addMessage("Confirmacion", "Cliente Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
					addMessage("Confirmacion", "Correo Enviado");
				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			} else if (tipoEstado.equalsIgnoreCase("Divorciado/a")) {
				System.out.println("ENTRA AL IF DVI PARA CREAR CLIENTE");
				// newCliente.setTiempoResidencia(tiempo);
				newCliente.setEstadoCivil("Divorciado(a)");

				try {
					if (validarDatos() == false) {

					} else {
						System.out.println("divo");
						obtenerUsuario();
						generarContrasena();
						on.registarCliente(newCliente);
						addMessage("Confirmacion", "Cliente Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			} else if (tipoEstado.equalsIgnoreCase("Viudo/a")) {
				System.out.println("ENTRA AL IF VIU PARA CREAR CLIENTE");
				// newCliente.setTiempoResidencia(tiempo);
				newCliente.setEstadoCivil("Viudo(a)");

				try {
					if (validarDatos() == false) {

					} else {
						System.out.println("VIUD");
						obtenerUsuario();
						generarContrasena();
						on.registarCliente(newCliente);
						addMessage("Confirmacion", "Cliente Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			} else {

				System.out.println("else");

			}
		}

		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "No se pudo crear el cliente"));
		}

		return null;

	}

	public void addMessage(String summary, String detail) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);

		PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public String doBuscoCliente() {

		System.out.println("ENTRAS A BUSCAR DATOS CLIENTES?");
		if (newCliente.getCedula() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Ingrese una cedula antes de buscar"));
		} else {
			try {
				Cliente emple = on.buscarCliente(newCliente.getCedula());
				System.out.println("ESTOS SON DATOS RECOGIDOS DEL USUARIO" + emple);
				newCliente.setNombres(emple.getNombres());
				System.out.println(emple.getNombres());
				newCliente.setApellidos(emple.getApellidos());
				newCliente.setDireccion(emple.getDireccion());
				newCliente.setCorreo(emple.getCorreo());
				newCliente.setTelefono(emple.getTelefono());
				newCliente.setCiudad(emple.getCiudad());
				newCliente.setFechaNacimiento(emple.getFechaNacimiento());
				newCliente.setCelular(emple.getCelular());
				newCliente.setProvincia(emple.getProvincia());
				newCliente.setTiempoResidencia(emple.getTiempoResidencia());
				newCliente.setEstadoCivil(emple.getEstadoCivil());
				newCliente.setReferenciaDomicilio(emple.getReferenciaDomicilio());
				newCliente.setCuentaCliente(emple.getCuentaCliente());
				cuentasK = new ArrayList<String>();
				for (int i = 0; i < emple.getCuentaCliente().size(); i++) {
					System.out.println(emple.getCuentaCliente().get(i).getNumeroCuenta());
					cuentasK.add(emple.getCuentaCliente().get(i).getNumeroCuenta());
					System.out.println("CUENTAS TOTAL " + cuentasK);
				}
				System.out.println("CUENTA CLIENTES BUSQEUDA" + emple.getCuentaCliente().get(0));

				// usuarioAntiguo = emple.getUsuario();
				// contrasenaAntigua = emple.getContrasenia();
				// System.out.println("USUARIO + CONTRASENIA " +usuarioAntiguo
				// +""+contrasenaAntigua);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return null;

	}

	public int calcularEdad() {
		int edad = 0;
		Date fechaNa = newCliente.getFechaNacimiento();
		int anio = fechaNa.getYear();
		return edad;

	}

	public String doIniciarSesiónEmpleado() {
		try {
			on.IniciarSesion(newCliente.getUsuario(), newCliente.getContrasenia());
			System.out.println("retornas?");
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect("Admin.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public String doIniciarSesiónCliente() {
		try {
			on.IniciarSesion(newCliente.getUsuario(), newCliente.getContrasenia());
			System.out.println("retornas?");
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect("templateCliente.xhtml");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public String doEliminar() {
		try {
			System.out.println("cedula eliminar" + newCliente.getCedula());
			on.eliminarEmpleado(newCliente.getCedula());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message", "Cliente eliminado");

			PrimeFaces.current().dialog().showMessageDynamic(message);
		} catch (Exception e) {
			System.out.println("Error al eliminar al empleado " + " " + e.getMessage());
		}
		return null;
	}

	public String doActualizacion() {

		try {
			System.out.println("entras a la actualizacion");
			System.out.println(this.newCliente.getCedula() + "   " + this.newCliente.getNombres() + " ");

			on.actaulizarCliente(newCliente);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Cliente Actualizado");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			System.out.println("actualizas?");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * Metodo para validar un Empleado
	 * 
	 * @return Mensaje si el Empleado esta registrado en la Base de Datos
	 */
	public String valCedula() {
		System.out.println("*-------*" + newCliente.getCedula());
		if (newCliente.getCedula() != null) {
			Empleado usuarioRegistrado = on.usuarioRegistrado(newCliente.getCedula());
			if (usuarioRegistrado != null) {
				System.out.println("Registrado");
				return "Empleado REGISTRADO";
			}
			try {
				ced = on.validadorDeCedula(newCliente.getCedula());
				System.out.println(ced);
				if (ced) {
					return "Cedula Valida";
				} else if (ced == false) {
					return "Cedula Incorrecta";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return " ";
	}

	/**
	 * Metodo que permite asignar un valor al atributo buscarCuentaDeAhorro de la
	 * clase
	 * 
	 * @param buscarCuentaDeAhorro Variable asignada al atributo
	 *                             buscarCuentaDeAhorro de la clase
	 */
	public void setBuscarCuentaDeAhorro(Cuenta buscarCuentaDeAhorro) {
		// c = on.buscarCuentaD(newCliente.getCuentaCliente());
	}

	public Cuenta getC() {
		return c;
	}

	public void setC(Cuenta c) {
		this.c = c;
	}

}
