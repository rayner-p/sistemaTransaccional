package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name= "empleado")
public class Empleado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=10, nullable=false)
	private String cedula;
	@Column(length=30)
	private String nombres;
	@Column(length=30)
	private String apellidos;
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	@Column(length=50)
	private String direccion;
	@Column(length=50)
	private String correo;
	@Column(length=10)
	private String telefono;
	@Column(length=40)
	private String rol;
	@Column(length=40)
	private String usuario;;
	@Column(length=40)
	private String contrasenia;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL })
	@JoinColumn(name="cedula_empleado")
	private SesionCliente sesion; 
	/** 
	 * Metodo que permite obtener el atributo cedula 
	 * @return El atributo cedula de esta clase
	 */
	public String getCedula() {
		return cedula;
	}
	/** 
	 * Metodo que permite asignarle un valor al atributo cedula 
	 * @param cedula parametro para poder obtener 
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/** 
	 *  Metodo que permite obtener el atributo nombre  
	 * @return El atributo nombre de esta clase
	 */
	public String getNombres() {
		return nombres;
	}
	/** 
	 * Metodo que permite asignarle un valor al atributo nombre 
	 * @param cedula Variable que se asigna al atributo de la clase
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/** 
	 * Metodo que permite obtener el atributo apellido 
	 * @return El atributo apellido de esta clase
	 */
	public String getApellidos() {
		return apellidos;
	}
	/** 
	 * Metodo que me permite asignarle un valor al atributo apellido 
	 * @param apellido Variable que se asigna al atributo de la clase
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**  
	 * Metodo que permite obtener el atributo direccion 
	 * @return El atributo direccion de esta clase
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/** 
	 * Metodo que me permite asignarle un valor al atributo direccion 
	 * @return direccion Variable que se asigna al atributo de la clase
	 */
	public String getDireccion() {
		return direccion;
	}
	/** 
	 * Metodo que me permite asignarle un valor al atributo direccion 
	 * @param direccion Variable que se asigna al atributo de la clase
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/** 
	 * Metodo que me permite asignarle un parametro al atributo corroe 
	 * @param correo Variable que se asigna al atributo de la clase
	 */
	public String getCorreo() {
		return correo;
	}
	
	/** 
	 * Metodo que me permite asignarle un parametro al atributo corroe 
	 * @param correo Variable que se asigna al atributo de la clase
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/** 
	 * Metodo que me permite obtener un parametro al atributo telefono 
	 * @return telefono Variable que se asigna al atributo de la clase
	 */
	public String getTelefono() {
		return telefono;
	}
	/** 
	 * Metodo que me permite asignar un parametro al atributo telefono 
	 * @param telefono Variable que se asigna al atributo de la clase
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/** 
	 * Metodo que me permite asignar un parametro al atributo telefono 
	 * @param telefono Variable que se asigna al atributo de la clase
	 */
	public String getRol() {
		return rol;
	}
	/** 
	 * Metodo que me permite asignar un parametro al atributo rol 
	 * @param rol Variable que se asigna al atributo de la clase
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	/** 
	 * Metodo que me permite obtemmner un parametro al atributo usaurio 
	 * @param usuario Variable que se asigna al atributo de la clase
	 */
	public String getUsuario() {
		return usuario;
	}
	/** 
	 * Metodo que me permite asignar un parametro al atributo telefono 
	 * @param usario Variable que se asigna al atributo de la clase
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/** 
	 * Metodo que me permite obtener un parametro al atributo telefono 
	 * @return contrasenia Variable que se asigna al atributo de la clase
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/** 
	 * Metodo que me permite asignar un parametro al atributo telefono 
	 * @param contrasenia Variable que se asigna al atributo de la clase
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public SesionCliente getSesion() {
		return sesion;
	}
	public void setSesion(SesionCliente sesion) {
		this.sesion = sesion;
	}
	@Override
	public String toString() {
		return "Empleado [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", direccion=" + direccion + ", correo=" + correo + ", telefono=" + telefono
				+ ", rol=" + rol + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", sesion=" + sesion + "]";
	}
	
	

}
