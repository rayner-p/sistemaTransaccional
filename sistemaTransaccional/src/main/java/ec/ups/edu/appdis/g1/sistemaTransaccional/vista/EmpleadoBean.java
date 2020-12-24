package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;
import java.io.Serializable;

@Named
@SessionScoped
public class EmpleadoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean ced;
	private Empleado newEmpleado;
	private String tipoEmpleado;
	private List<SesionCliente> lstSesionesCliente;
	private String cedu;
	private String usuario;
	private String contrasenia;
	@Inject
	private LoginBean loginB;


	@Inject
	private GestionSistemLocal on;

	public EmpleadoBean() {
		init();
	}

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

	/**
	 * Metodo para obtener un tipo de Empleado
	 * 
	 * @return El Tipod de Empleado que se esta asignando en la pagina xhtml
	 */
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	/**
	 * Metodo para asignar el tipo de Empleado
	 * 
	 * @param tipoEmpleado El parametro tipoEmpleado me permite asignar el tipo de
	 *                     empleado seleccionado en la pagina xhtml
	 */
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@PostConstruct
	public void init() {
		newEmpleado = new Empleado();
	}

	public Empleado getNewEmpleado() {
		return newEmpleado;
	}

	public void setNewEmpleado(Empleado newEmpleado) {
		this.newEmpleado = newEmpleado;
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
		List<SesionCliente> lis = on.obtenerSesionesEmpleados(newEmpleado.getCedula());
		if (lis != null) {
			lstSesionesCliente = lis;
			return lstSesionesCliente;
		}
		return null;
	}

	/**
	 * Metodo para guardar datos del Empleado
	 * 
	 * @return La paguina con la lista de los Empleados registrados
	 */

	public String obtenerUsuario() {
		usuario = on.generarNombreUsuario(newEmpleado.getCedula(), newEmpleado.getNombres(),
				newEmpleado.getApellidos());

		newEmpleado.setUsuario(usuario);
		System.out.println("Se ha generado un nombre de usuario al azar");
		return usuario;
	}

	public String generarContrasena() {
		contrasenia = on.generarContrasenia();

		newEmpleado.setContrasenia(contrasenia);
		System.err.println("Se ha generado una contrasenia al azar");
		System.out.println(contrasenia);
		return contrasenia;

	}

	public String registrarCorreo() {
		System.out.println("entras al correo?   " + "" + usuario + contrasenia);

		Date fechaA = new Date();
		String destinatario = newEmpleado.getCorreo();
		String asunto = "   Creación de cuenta en el sistema del Banco Internacional  ";
		String cuerpo = "   Banco Internacional  SISTEMA TRANSACCIONAL\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + newEmpleado.getNombres().toUpperCase() + " "
				+ newEmpleado.getApellidos().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ "El sistema transaccional del Banco Internacional le informa que el usuario ha sido habilitado exitosamente.    \n"
				+ "                                                                              \n"
				+ "                       Su usuario es : " + usuario + "                          \n"
				+ "                   	Su clave de acceso es:   " + contrasenia + "               \n"
				+ "                       Fecha: " + fechaA + "                                     \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		String corre = destinatario + asunto + cuerpo;
		System.out.println(corre);
		CompletableFuture.runAsync(() -> {
			try {

				
					System.out.println("VALE EL CORREO?");
					on.enviarCorreo(destinatario, asunto, cuerpo);
					System.out.println("SE ha enviado un correo a: " + "\n" + "" + newEmpleado.getCorreo() + "");
			
				

			} catch (Exception e) {
				System.out.println("Error al enviar correo con contrasenia");
				e.printStackTrace();
			}
		});
		return null;

	}

	public String doRegistrarEmpleado() {

		System.out.println(this.newEmpleado.getCedula() + "   " + this.newEmpleado.getNombres() + tipoEmpleado);

		try {
			if (tipoEmpleado.equalsIgnoreCase("Administrador")) {
				newEmpleado.setRol("Administrador");
				obtenerUsuario();
				generarContrasena();

				try {
					

					if(newEmpleado.getCedula().isEmpty() && newEmpleado.getNombres().isEmpty() 
							&& newEmpleado.getApellidos().isEmpty() && newEmpleado.getCorreo().isEmpty()
							&& newEmpleado.getRol().isEmpty()) {
							System.out.println("Ingrese los datos priemero");
							on.registarEmpleado(newEmpleado);
					}else {
						addMessage("Confirmacion", "Empleado Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
					
				} catch (Exception e) {
					System.out.println("error guardar empleado con correo");
					e.printStackTrace();
				}
			} else if (tipoEmpleado.equalsIgnoreCase("Cajero")) {
				newEmpleado.setRol("Cajero");
				obtenerUsuario();
				generarContrasena();

				try {
					if(newEmpleado.getCedula().isEmpty() && newEmpleado.getNombres().isEmpty() 
							&& newEmpleado.getApellidos().isEmpty() && newEmpleado.getCorreo().isEmpty()
							&& newEmpleado.getRol().isEmpty()) {
							System.out.println("Ingrese los datos priemero");
							on.registarEmpleado(newEmpleado);
					}else {
						addMessage("Confirmacion", "Empleado Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
				} catch (Exception e) {
					System.out.println("error guardar empleado con correo");
					e.printStackTrace();
				}
			} else if (tipoEmpleado.equalsIgnoreCase("Asistente Captaciones")) {
				newEmpleado.setRol("Asistente Captaciones");
				obtenerUsuario();
				generarContrasena();
				try {
					if(newEmpleado.getCedula().isEmpty() && newEmpleado.getNombres().isEmpty() 
							&& newEmpleado.getApellidos().isEmpty() && newEmpleado.getCorreo().isEmpty()
							&& newEmpleado.getRol().isEmpty()) {
							System.out.println("Ingrese los datos priemero");
							on.registarEmpleado(newEmpleado);
					}else {
						addMessage("Confirmacion", "Empleado Guardado");
						registrarCorreo();
						addMessage("Confirmacion", "Correo Enviado");
					}
				} catch (Exception e) {
					System.out.println("error guardar empleado con correo");
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void addMessage(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,
		// detail);
		// FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String doBuscarEmpleado() {
		try {
			System.out.println("ENTRAS A BUSCAR DATOS?");
			Empleado emple = on.buscarEmpleado(newEmpleado.getCedula());
			System.out.println("ESTOS SON DATOS RECOGIDOS DEL USUARIO" + emple);
			newEmpleado.setNombres(emple.getNombres());
			System.out.println(emple.getNombres());
			newEmpleado.setApellidos(emple.getApellidos());
			newEmpleado.setDireccion(emple.getDireccion());
			newEmpleado.setCorreo(emple.getCorreo());
			newEmpleado.setTelefono(emple.getTelefono());
			newEmpleado.setRol(emple.getRol());
			newEmpleado.setFechaNacimiento(emple.getFechaNacimiento());
			// usuarioAntiguo = emple.getUsuario();
			// contrasenaAntigua = emple.getContrasenia();
			// System.out.println("USUARIO + CONTRASENIA " +usuarioAntiguo
			// +""+contrasenaAntigua);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	
	public String doEliminar() {
		try {
			System.out.println("cedula eliminar"+newEmpleado.getCedula());
			on.eliminarEmpleado(newEmpleado.getCedula());
			System.out.println("EMPLEADO ELIMINADO");
		}catch (Exception e) {
			System.out.println("Error al eliminar al empleado "  +" "+e.getMessage());
		}
		return null;
	}
	public String doActualizacion() {

		try {
			System.out.println("entras a la actualizacion");
			System.out.println(this.newEmpleado.getCedula() + "   " + this.newEmpleado.getNombres() + " "
					+ this.newEmpleado.getRol());
			tipoEmpleado = this.newEmpleado.getRol();
			System.out.println(tipoEmpleado);
			// newEmpleado.setContrasenia(contrasenaAntigua);
			// newEmpleado.setUsuario(usuarioAntiguo);
			// System.out.println("contrasenia base " +newEmpleado.getContrasenia());
			if (tipoEmpleado.equalsIgnoreCase("Administrador")) {
				newEmpleado.setRol("Administrador");
				on.actualizarEmpleado(newEmpleado);
				System.out.println("actualizas?");

			} else if (tipoEmpleado.equalsIgnoreCase("Cajero")) {
				newEmpleado.setRol("Cajero");

				on.actualizarEmpleado(newEmpleado);
				System.out.println("actualizas?");

			} else if (tipoEmpleado.equalsIgnoreCase("Asistente Captaciones")) {
				newEmpleado.setRol("Asistente Captaciones");

				try {
					on.actualizarEmpleado(newEmpleado);
					System.out.println("actualizas?");
				} catch (Exception e) {
					System.out.println("error actualizar empleado");
					e.printStackTrace();
				}
			}

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
		System.out.println("*-------*" + newEmpleado.getCedula());
		if (newEmpleado.getCedula() != null) {
			Empleado usuarioRegistrado = on.usuarioRegistrado(newEmpleado.getCedula());
			if (usuarioRegistrado != null) {
				System.out.println("Registrado");
				return "Empleado REGISTRADO";
			}
			try {
				ced = on.validadorDeCedula(newEmpleado.getCedula());
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

}
