package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.util.Date;

import javax.persistence.Column;

public class Empleado {
	@Column(length=10)
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	

}
