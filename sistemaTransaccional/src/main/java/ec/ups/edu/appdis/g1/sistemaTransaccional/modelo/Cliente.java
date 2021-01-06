package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="cliente")
public class Cliente  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=10, nullable=false)
	private String cedula;
	@Column(length=250)
	private String nombres;
	@Column(length=250)
	private String apellidos;
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	@Column(length=2)
	private int edad;
	@Column(length=30)
	private String provincia;
	@Column(length=30)
	private String ciudad;
	
	@Column(name="estado_civil", length =20)
	private String estadoCivil;
	@Column(length=550)
	private String direccion;
	@Column(name="tiempo_residencia")
	private int tiempoResidencia;
	@Column(name = "referencia_domicilio",length=600)
	private String referenciaDomicilio;
	@Column(length=250)
	private String correo;
	@Column(length=10)
	private String celular;
	@Column(length=10)
	private String telefono;
	@Column(length=40)
	private String usuario;;
	@Column(length=40)
	private String contrasenia;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL })
	@JoinColumn(name="cuenta_fk")	
	private List<Cuenta> cuentaCliente;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL })
	@JoinColumn(name="transaccion_fk")	
	private List<Transaccion> transaccionCliente;
	
	/** 
	 * Metodo que permite obtener un valor al atributo nombre 
	 * @return cedula Variable que se asigna al atributo de la clase
	 */
	public String getCedula() {
		return cedula;
	}
	/** 
	 * Metodo que permite asignarle un valor al atributo nombre 
	 * @param nombres Variable que se asigna al atributo de la clase
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}/** 
	 * Metodo que permite obtener un valor al atributo nombre 
	 * @return nombres Variable que se asigna al atributo de la clase
	 */
	public String getNombres() {
		return nombres;
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
	 * Metodo que permite obtener el atributo fecha 
	 * @return El atributo direccion de esta clase
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**  
	 * Metodo que permite asignar el atributo fecha 
	 * @param El atributo direccion de esta clase
	 */
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**  
	 * Metodo que permite asignar el atributo edad 
	 * @return El atributo direccion de esta clase
	 */
	public int getEdad() {
		return edad;
	}
	/**  
	 * Metodo que permite asignar el atributo edad 
	 * @param El atributo direccion de esta clase
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**  
	 * Metodo que permite asignar el atributo provincia 
	 * @return El atributo direccion de esta clase
	 */
	public String getProvincia() {
		return provincia;
	}
	/**  
	 * Metodo que permite asignar el atributo provincia 
	 * @param El atributo direccion de esta clase
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**  
	 * Metodo que permite asignar el atributo ciudad 
	 * @return El atributo direccion de esta clase
	 */
	public String getCiudad() {
		return ciudad;
	}
	
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**  
	 * Metodo que permite asignar el atributo ciudad 
	 * @param El atributo direccion de esta clase
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**  
	 * Metodo que permite asignar el atributo direccion 
	 * @return El atributo direccion de esta clase
	 */
	public String getDireccion() {
		return direccion;
	}
	/**  
	 * Metodo que permite asignar el atributo direccion 
	 * @param El atributo direccion de esta clase
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**  
	 * Metodo que permite asignar el atributo tiempoResidencia 
	 * @return El atributo direccion de esta clase
	 */
	public int getTiempoResidencia() {
		return tiempoResidencia;
	}
	/**  
	 * Metodo que permite asignar el atributo tiempoResidencia 
	 * @param El atributo direccion de esta clase
	 */
	public void setTiempoResidencia(int tiempoResidencia) {
		this.tiempoResidencia = tiempoResidencia;
	}
	/**  
	 * Metodo que permite asignar el atributo referenciaDomicilio 
	 * @return El atributo direccion de esta clase
	 */
	public String getReferenciaDomicilio() {
		return referenciaDomicilio;
	}
	/**  
	 * Metodo que permite asignar el atributo referenciaDomicilio 
	 * @param El atributo direccion de esta clase
	 */
	public void setReferenciaDomicilio(String referenciaDomicilio) {
		this.referenciaDomicilio = referenciaDomicilio;
	}
	/**  
	 * Metodo que permite asignar el atributo correo 
	 * @return El atributo direccion de esta clase
	 */
	public String getCorreo() {
		return correo;
	}
	/**  
	 * Metodo que permite asignar el atributo correo 
	 * @param El atributo direccion de esta clase
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**  
	 * Metodo que permite asignar el atributo celular 
	 * @return El atributo direccion de esta clase
	 */
	public String getCelular() {
		return celular;
	}
	/**  
	 * Metodo que permite asignar el atributo celular 
	 * @param El atributo direccion de esta clase
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**  
	 * Metodo que permite asignar el atributo telefono 
	 * @param El atributo direccion de esta clase
	 */
	public String getTelefono() {
		return telefono;
	}
	/**  
	 * Metodo que permite asignar el atributo telefono 
	 * @param El atributo direccion de esta clase
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**  
	 * Metodo que permite asignar el atributo usuario 
	 * @param El atributo direccion de esta clase
	 */
	public String getUsuario() {
		return usuario;
	}
	/**  
	 * Metodo que permite asignar el atributo usuario 
	 * @param El atributo direccion de esta clase
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**  
	 * Metodo que permite asignar el atributo ncontrasenia
	 * @param El atributo direccion de esta clase
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**  
	 * Metodo que permite asignar el atributo contrasenia 
	 * @param El atributo direccion de esta clase
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**  
	 * Metodo que permite asignar el atributo cuentaCliente 
	 * @return El atributo direccion de esta clase
	 */
	public List<Cuenta> getCuentaCliente() {
		return cuentaCliente;
	}
	/**  
	 * Metodo que permite asignar el atributo cuentaCliente 
	 * @param El atributo direccion de esta clase
	 */
	public void setCuentaCliente(List<Cuenta> cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}
	/**  
	 * Metodo que permite asignar el atributo estadoCivil 
	 * @param El atributo direccion de esta clase
	 */
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	/**  
	 * Metodo que permite asignar el atributo estadoCivil 
	 * @param El atributo direccion de esta clase
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	/**  
	 * Metodo que permite asignar el atributo transaccionCliente 
	 * @return El atributo direccion de esta clase
	 */
	
	public List<Transaccion> getTransaccionCliente() {
		return transaccionCliente;
	}
	/**  
	 * Metodo que permite asignar el atributo transaccionCliente 
	 * @param El atributo direccion de esta clase
	 */
	public void setTransaccionCliente(List<Transaccion> transaccionCliente) {
		this.transaccionCliente = transaccionCliente;
	}
	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", edad=" + edad + ", provincia=" + provincia + ", ciudad=" + ciudad
				+ ", estadoCivil=" + estadoCivil + ", direccion=" + direccion + ", tiempoResidencia=" + tiempoResidencia
				+ ", referenciaDomicilio=" + referenciaDomicilio + ", correo=" + correo + ", celular=" + celular
				+ ", telefono=" + telefono + ", usuario=" + usuario + ", contrasenia=" + contrasenia
				+ ", cuentaCliente=" + cuentaCliente + ", transaccionCliente=" + transaccionCliente + "]";
	}
	
	
	
}
