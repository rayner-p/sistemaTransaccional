package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.SesionCliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.sistemaTransaccionaON;

/**
 * Clase de tipo Bean para el manejo de JSF y archivos xhtml
 * 
 * @author rayner
 * @version: 1.0
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private GestionSistemLocal empleadoON;

	private String usuario;

	private String contrasena;

	private String usuarioCliente;
	private String contraCliente;

	private Empleado empleado;
	private String empleado2;

	private Cliente cliente;

	private int contador = 0;

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public LoginBean() {
		init();
	}

	@PostConstruct
	public void init() {
		;

		empleado = new Empleado();
		cliente = new Cliente();
		// empleado2 = new Empleado();
	}

	public String getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(String usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public String getContraCliente() {
		return contraCliente;
	}

	public void setContraCliente(String contraCliente) {
		this.contraCliente = contraCliente;
	}

	public GestionSistemLocal getEmpleadoON() {
		return empleadoON;
	}

	public void setEmpleadoON(sistemaTransaccionaON empleadoON) {
		this.empleadoON = empleadoON;
	}

	/**
	 * Metodo para obtener el usuario
	 * 
	 * @return El usuario o valor digitado en la pagina xhtml
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Metodo para asignar un usuario
	 * 
	 * @param usuario El parametro usuario me permite asignar valor a mi variable
	 *                usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo para obtener la contraseña
	 * 
	 * @return La contraseña que se ingresa en la pagina xhtml
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo para asignar una contraseña
	 * 
	 * @param contrasena El parametro contraseña me permite asignar un valor a mi
	 *                   variable contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Metodo para obtener el empleado
	 * 
	 * @return
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * Asignar un empleado
	 * 
	 * @param empleado El parametro empleado me permite asignar el empleado que se
	 *                 encuentra conectado
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * Metodo para obtener el empleado
	 * 
	 * @return
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Asignar un empleado
	 * 
	 * @param empleado El parametro empleado me permite asignar el empleado que se
	 *                 encuentra conectado
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * metodo que nos permite registrar en la base de datos los intentos correctos e
	 * incorrectos al sistema por parte del cliente
	 * 
	 * @return
	 */

	public boolean guardarsesionEmpleado() {
		boolean bandera = true;
		List<Empleado> lstClis = empleadoON.getEmpleadosT();
		for (int i = 0; i < lstClis.size(); i++) {
			empleado2 = lstClis.get(i).getUsuario();

		}
		int contadoFor = 0;
		// for (Empleado c : empleado) {
		for (Iterator<Empleado> iterator = lstClis.iterator(); iterator.hasNext();) {

			empleado = (Empleado) iterator.next();

			// System.out.println("Esto es el empleado" + empleado.getUsuario());
			// empleado = c;
			System.out.println("FOR Que recorre");
			System.out.println(empleado.getUsuario() + empleado.getContrasenia());
			System.out.println("user" + " " + usuario + " " + "pass" + " " + contrasena);
			if (empleado.getUsuario().equals(usuario) && empleado.getContrasenia().equals(contrasena)) {
				System.out.println("ENTRA AL IF CORRECTO");
				SesionCliente sesioEmpleado = new SesionCliente();
				sesioEmpleado.setEmpleado(empleado);
				sesioEmpleado.setFechaSesion(new Date());
				sesioEmpleado.setEstado("Correcto");
				// System.out.println("ESTO VA A LA BASE" + sesioEmpleado);
				try {
					empleadoON.guardarSesionEmpleado(sesioEmpleado);
					System.out.println("GUARDADO OK");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", empleado);
				} catch (Exception e) {
					System.err.println("sesion nula");
				}

			} else if (empleado.getUsuario() != (usuario) || empleado.getContrasenia() != (contrasena)) {
				System.out.println("ENTRA AL IF MAL");
				contador = contadoFor + 1;
				System.out.println("esto es contador" + contador);
				SesionCliente sesioEmpleadoMAL = new SesionCliente();
				// System.out.println("este cliente va al sis" + empleado);
				sesioEmpleadoMAL.setEmpleado(empleado);
				sesioEmpleadoMAL.setFechaSesion(new Date());
				sesioEmpleadoMAL.setEstado("Incorrecto");
				System.out.println("antes");
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Error al iniciar sesión" + "\n" + "Intentos restantes:" + contadoFor);

				PrimeFaces.current().dialog().showMessageDynamic(message);

				empleadoON.guardarSesionEmpleado(sesioEmpleadoMAL);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", empleado);

				return bandera = false;
			}
		}
		return bandera;
	}

	/*
	 * metodo que me permite validar el inicio de sesión del empleado y redirigirlo
	 * a su respectiva ventana
	 */
	public String validarInicioSesionEmpleado() {
		Empleado emp;
		guardarsesionEmpleado();
		if (contador == 2) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"Solo le queda 1 intento antes de que se bloquee su cuenta");

			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else if (contador == 3) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Message", "Su cuenta está bloqueada");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "login";
		} else {
			try {

				emp = empleadoON.usuario(usuario, contrasena);
				System.out.println("empleado con user" + emp);
				// System.out.println("entra a registro de sesion empleado");
				// System.out.println("***************" + emp.getNombres());
				empleado = emp;
				if (emp != null && emp.getRol().equalsIgnoreCase("Cajero")) {
					try {
						addMessage("OK", "Ingreso");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
						FacesContext contex = FacesContext.getCurrentInstance();
						contex.getExternalContext().redirect("Cajero.xhtml");

					} catch (Exception e) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"No se pudo acceder, revise usuario o contrasenia");

						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				} else if (emp != null && emp.getRol().equalsIgnoreCase("Administrador")) {
					try {

						FacesContext contex = FacesContext.getCurrentInstance();
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
						contex.getExternalContext().redirect("Admin.xhtml");

					} catch (Exception e) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"No se pudo acceder, revise usuario o contrasenia");

						PrimeFaces.current().dialog().showMessageDynamic(message);
					}
				} else if (emp != null && emp.getRol().equalsIgnoreCase("Asistente Captaciones")) {
					try {
						FacesContext contex = FacesContext.getCurrentInstance();
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
						contex.getExternalContext().redirect("Asistente.xhtml");

					} catch (Exception e) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"No se pudo acceder, revise usuario o contrasenia");

						PrimeFaces.current().dialog().showMessageDynamic(message);

					}
				} else {
					// validarInicioSesionCliente();
				}

			} catch (Exception e) {
				System.out.println("NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
				return "login";
			}
		}

		return null;
	}

	/**
	 * metodo que nos permite registrar en la base de datos los intentos correctos e
	 * incorrectos al sistema por parte del cliente
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public String guardarSesionCliente() {
		List<Cliente> lstClis = empleadoON.getClienteT();
		System.out.println("que obtiene la lista cliente?" + lstClis);
		System.out.println("PASO LA LISTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for (Cliente c : lstClis) {
			System.out.println("Dentro del for" + c);
			if (c.getUsuario().equalsIgnoreCase(usuarioCliente) && c.getContrasenia().equalsIgnoreCase(contraCliente)) {
				System.out.println("dentro del if ok");
				SesionCliente sesionCliente = new SesionCliente();
				sesionCliente.setCliente(c);
				sesionCliente.setFechaSesion(new Date());
				sesionCliente.setEstado("Correcto");

				empleadoON.guardarSesionCliente(sesionCliente);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", c);
				System.out.println("vale faces?" + "" + "empleado" + c);
				FacesContext contex = FacesContext.getCurrentInstance();

			} else if (c.getUsuario().equalsIgnoreCase(usuarioCliente)
					|| c.getContrasenia().equalsIgnoreCase(contraCliente)) {
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF MAL");
				SesionCliente sesionCliente2 = new SesionCliente();
				sesionCliente2.setCliente(c);
				sesionCliente2.setFechaSesion(new Date());
				sesionCliente2.setEstado("Incorrecto");
				empleadoON.guardarSesionCliente(sesionCliente2);
				return "login";
			}
		}
		return null;

	}

	/**
	 * metodo que nos permite validar el ingreso del cliente al sistema
	 * 
	 * @return
	 */
	public String validarInicioSesionCliente() {
		System.out.println("vale el usuario?");
		Cliente clien;
		try {
			clien = empleadoON.usuarioCliente(usuarioCliente, contraCliente);
			cliente = clien;
			guardarSesionCliente();
			System.out.println("CLIENTE LOGIN" + cliente);
			if (clien != null) {
				try {
					System.out.println("vale el ingreso");
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", clien);
					contex.getExternalContext().redirect("Cliente.xhtml");
				} catch (Exception e) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
							"No se pudo acceder, revise usuario o contrasenia");

					PrimeFaces.current().dialog().showMessageDynamic(message);
				}
			}
		} catch (Exception e) {
			System.out.println("NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
			return "loginCliente";
		}

		return null;
	}

	public void addMessage(String summary, String detail) {
		System.out.println(summary + "valen los mensajes" + detail);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("entral al logout");
		return "login";

	}

}
