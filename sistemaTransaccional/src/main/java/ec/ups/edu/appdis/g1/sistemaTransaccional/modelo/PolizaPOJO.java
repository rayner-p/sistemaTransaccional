package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.util.Date;


public class PolizaPOJO {
	private int codigo;
	private Date fechaEmision;
	private double montoP;
	private int tiempoPlazo;
	private double interes;
	private String tipoPoliza;
	private byte[] archivoCedula;
	private byte[] archivoPlanillaServicios;
	private String estadoPoliza = "Pendiente";
	private Empleado empleadoCaptacion;
	private String cuentaClientePoliza_fk;
	private Cliente cliente;
	private String cedula;
	private String nombres;
	private String apellidos;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public double getMontoP() {
		return montoP;
	}
	public void setMontoP(double montoP) {
		this.montoP = montoP;
	}
	public int getTiempoPlazo() {
		return tiempoPlazo;
	}
	public void setTiempoPlazo(int tiempoPlazo) {
		this.tiempoPlazo = tiempoPlazo;
	}
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public String getTipoPoliza() {
		return tipoPoliza;
	}
	public void setTipoPoliza(String tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}
	public byte[] getArchivoCedula() {
		return archivoCedula;
	}
	public void setArchivoCedula(byte[] archivoCedula) {
		this.archivoCedula = archivoCedula;
	}
	public byte[] getArchivoPlanillaServicios() {
		return archivoPlanillaServicios;
	}
	public void setArchivoPlanillaServicios(byte[] archivoPlanillaServicios) {
		this.archivoPlanillaServicios = archivoPlanillaServicios;
	}
	public String getEstadoPoliza() {
		return estadoPoliza;
	}
	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}
	public Empleado getEmpleadoCaptacion() {
		return empleadoCaptacion;
	}
	public void setEmpleadoCaptacion(Empleado empleadoCaptacion) {
		this.empleadoCaptacion = empleadoCaptacion;
	}
	public String getCuentaClientePoliza_fk() {
		return cuentaClientePoliza_fk;
	}
	public void setCuentaClientePoliza_fk(String cuentaClientePoliza_fk) {
		this.cuentaClientePoliza_fk = cuentaClientePoliza_fk;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
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
	
	
}
