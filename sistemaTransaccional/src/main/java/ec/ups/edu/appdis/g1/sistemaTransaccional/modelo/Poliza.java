 package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "poliza")
public class Poliza implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 8, nullable = false)
	private int codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_emision")
	private Date fechaEmision;
	@Column(length = 5, name = "monto_poliza")
	private double montoP;
	@Column(name = "tiempo_plazo", length = 5)
	private int tiempoPlazo;
	private double interes;
	@Column(name="tipo_poli")
	private String tipoPoliza;
	@Lob 
	@Column(length=16777216)
    private byte[] archivoCedula; 
	@Lob 
	@Column(length=16777216)
    private byte[] archivoPlanillaServicios; 
	private String estadoPoliza="Pendiente";
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empleado_Fk")
	private Empleado empleadoCaptacion;
	private String cuentaClientePoliza_fk;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clienteP_Fk")
	private Cliente poliza_fk;
	

	public Cliente getCliente() {
		return poliza_fk;
	}

	public void setCliente(Cliente cliente) {
		this.poliza_fk = cliente;
	}

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

	public Empleado getEmpleadoCaptacion() {
		return empleadoCaptacion;
	}

	public void setEmpleadoCaptacion(Empleado empleadoCaptacion) {
		this.empleadoCaptacion = empleadoCaptacion;
	}

	public String getCuentaClientePoliza() {
		return cuentaClientePoliza_fk;
	}

	public void setCuentaClientePoliza(String cuentaClientePoliza) {
		this.cuentaClientePoliza_fk = cuentaClientePoliza;
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
	

	public Cliente getPoliza_fk() {
		return poliza_fk;
	}

	public void setPoliza_fk(Cliente poliza_fk) {
		this.poliza_fk = poliza_fk;
	}

	public String getEstadoPoliza() {
		return estadoPoliza;
	}

	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}

	@Override
	public String toString() {
		return "Poliza [codigo=" + codigo + ", fechaEmision=" + fechaEmision + ", montoP=" + montoP + ", tiempoPlazo="
				+ tiempoPlazo + ", interes=" + interes + ", tipoPoliza=" + tipoPoliza + ", archivoCedula="
				+ Arrays.toString(archivoCedula) + ", archivoPlanillaServicios="
				+ Arrays.toString(archivoPlanillaServicios) + ", estadoPoliza=" + estadoPoliza + ", empleadoCaptacion="
				+ empleadoCaptacion + ", cuentaClientePoliza_fk=" + cuentaClientePoliza_fk + ", poliza_fk=" + poliza_fk
				+ "]";
	}
	
	
}
