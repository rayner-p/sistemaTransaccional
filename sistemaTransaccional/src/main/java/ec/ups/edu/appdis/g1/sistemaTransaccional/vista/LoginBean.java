package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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

	private Cliente cliente;

	public LoginBean() {
		init();
	}

	@PostConstruct
	public void init() {
		;

		empleado = new Empleado();
		cliente = new Cliente();
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

	public String validarEmpleado() {
		List<Empleado> lstClis = empleadoON.getEmpleadosT();
		System.out.println("PASO LA LISTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for (Empleado c : lstClis) {
			System.out.println(c);
			System.out.println("ENTROOOOOOOOOOOO EN EL FORRRRRR");
			if (c.getUsuario().equalsIgnoreCase(usuario) && c.getContrasenia().equalsIgnoreCase(contrasena)) {
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF CORRECTO");
				SesionCliente sesionCliente = new SesionCliente();
				sesionCliente.setEmpleado(c);
				sesionCliente.setFechaSesion(new Date());
				sesionCliente.setEstado("Correcto");
				empleadoON.guardarSesionEmpleado(sesionCliente);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", c);
				System.out.println("vale faces?" + "" + "empleado" + c);
				// FacesContext contex = FacesContext.getCurrentInstance();

				// contex.getExternalContext().redirect("Cliente.xhtml");
			} else if (c.getUsuario().equalsIgnoreCase(usuario) || c.getContrasenia().equalsIgnoreCase(contrasena)) {
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF MAL");
				SesionCliente sesionCliente2 = new SesionCliente();
				sesionCliente2.setEmpleado(c);
				sesionCliente2.setFechaSesion(new Date());
				sesionCliente2.setEstado("Incorrecto");
				empleadoON.guardarSesionEmpleado(sesionCliente2);
				return "login";
			}
		}
		return null;
	}

	public String validarUsuario() {
		Empleado emp;

		try {
			emp = empleadoON.usuario(usuario, contrasena);
			
				System.out.println("entra a registro de sesion empleado");
				//validarEmpleado();
			
			System.out.println("***************" + emp.getNombres());
			empleado = emp;
			if (emp != null && emp.getRol().equalsIgnoreCase("Cajero")) {
				try {
					addMessage("OK", "Ingreso");

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					FacesContext contex = FacesContext.getCurrentInstance();

					contex.getExternalContext().redirect("Cajero.xhtml");

				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("Administrador")) {
				try {

					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("Admin.xhtml");

				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("Asistente Captaciones")) {
				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("templateAsistentePoliza.xhtml");

				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			System.out.println("NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
			return "login";
		}

		return null;
	}

	public String validarUsu() {
		System.out.println("vale el usuario?");
		Cliente clien;
		try {
			clien = empleadoON.usuarioCliente(usuarioCliente, contraCliente);
			cliente = clien;
			//validarCliente();
			System.out.println("CLIENTE LOGIN" + cliente);
			if (clien != null) {
				try {
					System.out.println("vale el ingreso");
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", clien);
					contex.getExternalContext().redirect("Cliente.xhtml");

				} catch (Exception e) {
				}

			}

		} catch (Exception e) {
			System.out.println("NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
			return "loginCliente";
		}

		return null;
	}

	public String validarCliente() {
		List<Cliente> lstClis = empleadoON.getClienteT();
		System.out.println("que obtiene la lista?" + lstClis);
		System.out.println("PASO LA LISTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for (Cliente c : lstClis) {
			System.out.println(c);
			System.out.println("ENTROOOOOOOOOOOO EN EL FORRRRRR");
			if (c.getUsuario().equalsIgnoreCase(usuarioCliente) && c.getContrasenia().equalsIgnoreCase(contraCliente)) {
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF CORRECTO");
				SesionCliente sesionCliente = new SesionCliente();
				sesionCliente.setCliente(c);
				sesionCliente.setFechaSesion(new Date());
				sesionCliente.setEstado("Correcto");
				empleadoON.guardarSesionCliente(sesionCliente);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", c);
				System.out.println("vale faces?" + "" + "empleado" + c);
				// FacesContext contex = FacesContext.getCurrentInstance();
				// contex.getExternalContext().redirect("Cliente.xhtml");
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

	public void addMessage(String summary, String detail) {
		System.out.println(summary + "valen los mensajes" + detail);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";

	}

}
