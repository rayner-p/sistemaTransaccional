package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	@Column(name = "referencia_domicilio",length=30)
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
	@OneToMany
	@JoinColumn(name="cuenta_fk")
	private List<Cuenta> cuentaCliente;
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTiempoResidencia() {
		return tiempoResidencia;
	}
	public void setTiempoResidencia(int tiempoResidencia) {
		this.tiempoResidencia = tiempoResidencia;
	}
	public String getReferenciaDomicilio() {
		return referenciaDomicilio;
	}
	public void setReferenciaDomicilio(String referenciaDomicilio) {
		this.referenciaDomicilio = referenciaDomicilio;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public List<Cuenta> getCuentaCliente() {
		return cuentaCliente;
	}
	public void setCuentaCliente(List<Cuenta> cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", cuentaCliente=" + cuentaCliente + "]";
	}
	
	
}
