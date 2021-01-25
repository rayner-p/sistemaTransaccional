package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class PolizaBean implements Serializable {

	/**
	 * 
	 */
	private List<String> cuentasK;
	private LoginBean empleadoB;
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionSistemLocal on;
	private Poliza newPoliza;
	private double tasaInteres;
	private int maximoTiempo;
	private Cuenta newCuenta;
	private String cuenta;
	private double saldoNuevo;
	private Parametrizar newParametrizar;
	private String nombrEmpleado;
	private Date fechaActu = new Date();
	private String estadoPoliza;
	private List<Poliza> polizas = new ArrayList<Poliza>();

	private InputStream ceduCliente;
	private InputStream planiCliente;
	private Cliente clienteP;

	public Cliente getClienteP() {
		return clienteP;
	}

	public void setClienteP(Cliente clienteP) {
		this.clienteP = clienteP;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	public String getEstadoPoliza() {
		return estadoPoliza;
	}

	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public List<String> getCuentasK() {
		return cuentasK;
	}

	public void setCuentasK(List<String> cuentasK) {
		this.cuentasK = cuentasK;
	}

	public LoginBean getEmpleadoB() {
		return empleadoB;
	}

	public void setEmpleadoB(LoginBean empleadoB) {
		this.empleadoB = empleadoB;
	}

	public int getMaximoTiempo() {
		return maximoTiempo;
	}

	public void setMaximoTiempo(int maximoTiempo) {
		this.maximoTiempo = maximoTiempo;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public double getSaldoNuevo() {
		return saldoNuevo;
	}

	public void setSaldoNuevo(double saldoNuevo) {
		this.saldoNuevo = saldoNuevo;
	}

	public String getNombrEmpleado() {
		return nombrEmpleado;
	}

	public void setNombrEmpleado(String nombrEmpleado) {
		this.nombrEmpleado = nombrEmpleado;
	}

	public Date getFechaActu() {
		return fechaActu;
	}

	public void setFechaActu(Date fechaActu) {
		this.fechaActu = fechaActu;
	}

	public Poliza getNewPoliza() {
		return newPoliza;
	}

	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}

	public InputStream getCeduCliente() {
		return ceduCliente;
	}

	public void setCeduCliente(InputStream ceduCliente) {
		this.ceduCliente = ceduCliente;
	}

	public InputStream getPlaniCliente() {
		return planiCliente;
	}

	public void setPlaniCliente(InputStream planiCliente) {
		this.planiCliente = planiCliente;
	}

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
		newPoliza = new Poliza();
		newParametrizar = new Parametrizar();
		listarPoliza();

	}

	public void obtenerInteres() {
		newParametrizar = (Parametrizar) on.obtenerParametros();
		System.out.println("OBTENER TIEMPO METOOD INTERES" + maximoTiempo);
		int miniPo = Integer.parseInt(newParametrizar.getMinimo());
		int maxiPo = Integer.parseInt(newParametrizar.getMaximo());
		if (maximoTiempo >= miniPo & maximoTiempo <= maxiPo) {
			newParametrizar = on.obtenerParametrosporDia(maximoTiempo);
			tasaInteres = newParametrizar.getTasaInteres();
			System.out.println("TASA DE INTERES DEL BEAN POLIZA" + " " + tasaInteres);
		} else {
			System.out.println("error al sacar el rango");
		}

		/*
		 * for (int i = 0; i < parametrizar.size(); i++) { tasaInteres = i;
		 * System.out.println("LA TASA DE INTERES OBTENIDA ES:" + " " + tasaInteres); }
		 */
	}

	public void verificarCuenta() {
		try {
			newCuenta = on.obtenerCuentaPorNumero(newPoliza.getCuentaClientePoliza());
			System.out.println("OBTIENE DATOS DE LA CUENTA");
		} catch (Exception e) {
			System.err.println("ERROR AL OBTENER DATOS DE LA CUENTA POLIZA" + " " + e.getLocalizedMessage());
		}
	}

	public void obtenerClientePoliza() {
		clienteP = new Cliente();
		try {
			System.out.println("INGRESA AL CLIENTE POLIZA" + newCuenta.getCuenta_fk());
			clienteP = on.obtenerDatosPorCedula(newCuenta.getCuenta_fk());
			System.out.println("DATOS OBTENIDOS CLIENTE" + clienteP);
		} catch (Exception e) {
			System.err.println("error en el encontrar cliente" + e.getMessage());

		}

	}

	public boolean validarSaldo() {
		boolean bandera = true;
		if (newCuenta == null) {
			System.out.println("No hay datos");
			bandera = false;

		} else if (newCuenta.getSaldo() < newPoliza.getMontoP()) {
			System.out.println("EL MONTO ES MAYOR AL SALDO DE LA CUENTA");

		}

		return bandera;
	}

	public String crearPoliza() {
		verificarCuenta();
		// obtenerInteres();
		obtenerClientePoliza();
		String mensaje = new String();
		try {
			System.out.println("INGRESA A CREAR POLIZA");
			if (validarSaldo() == false) {
				System.out.println("no se puede crear poliza");
			} else {
				System.out.println("ENTRAS?");
				System.out.println("clientP" + clienteP);
				newPoliza.setInteres(tasaInteres);
				newPoliza.setFechaEmision(fechaActu);
				newPoliza.setTiempoPlazo(maximoTiempo);
				// newPoliza.setEmpleadoCaptacion(empleadoB.getEmpleado());
				// newPoliza.setArchivoCedula(on.convertirArchivos(ceduCliente));
				// newPoliza.setArchivoPlanillaServicios(on.convertirArchivos(planiCliente));
				System.out.println("QUE TRAE EL CLIENTE EN EL DAO POLIZA" + clienteP);
				newPoliza.setCliente(clienteP);
				System.out.println("que se inserta en la poliza" + newPoliza);
				on.insertarPoliza(newPoliza);
				saldoNuevo = newCuenta.getSaldo() - newPoliza.getMontoP();
				System.out.println("saldo nuevo de la cuenta por poliza" + " " + saldoNuevo);
				on.actaulizarCuentaCliente(newPoliza.getCuentaClientePoliza(), saldoNuevo);
				mensaje = "POLIZA  CREADA";
				System.out.println(mensaje + newPoliza);
			}

		} catch (Exception e) {
			System.err.println("ERROR AL CREAR POLIZA ON " + " " + e.getLocalizedMessage());
		} // TODO: handle exception
		return mensaje;
	}

	/**
	 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
	 * cedula de la clase
	 * 
	 * @param event Variable de tipo FileUploadEvent
	 * @throws IOException
	 */
	public void subirCedula(FileUploadEvent event) throws IOException {
		// FacesMessage msg = new FacesMessage("Successful",
		// event.getFile().getFileName() + " is uploaded.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("ENTRA A CARGAR CEDU");
		ceduCliente = event.getFile().getInputStream();
		System.out.println("OBITIENES ALGO" + ceduCliente);
	}

	/**
	 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
	 * planilla de la clase
	 * 
	 * @param event Variable de tipo FileUploadEvent
	 * @throws IOException
	 */
	public void subirPlanilla(FileUploadEvent event) throws IOException {
		System.out.println("ENTRA A CARGAR PLANI");
		planiCliente = event.getFile().getInputStream();
		System.out.println("OBITIENES ALGO" + planiCliente);
	}

	public List<Poliza> listarPoliza() {
		// List<ClientePOJO> pojo = new ArrayList<ClientePOJO>();
		List<Poliza> lizas = on.obtenerPolizas();
		if (lizas != null) {
			polizas = lizas;
			System.out.println("entra al listar" + polizas);
			return polizas;
		} else {
			System.err.println("ERROR, POLIZA NULL");
		}
		return lizas;
	}

	public void actualizarEstadoPoliza() throws Exception {
		if (estadoPoliza.equalsIgnoreCase("Pendiente")) {
			FacesMessage msg = new FacesMessage("Cambie ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			try {

				if (estadoPoliza == null) {
					System.err.println("Estado Poliza vacio");
				} else {
					on.actualizarPoliza(estadoPoliza);
					System.out.println("que estado tiene la poliza" + " " + estadoPoliza);
				}
			} catch (Exception e) {
				throw new Exception(
						"Se ha producido un error al momento de actualizar la poliza" + " " + e.getMessage());
			}
		}

	}

	public void editar(CellEditEvent<Poliza> event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambio el valor",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void onCellEdit(CellEditEvent<Poliza> event) {
		  Object oldValue = event.getOldValue();
		  Object newValue = event.getNewValue();
		  if (newValue != null && !newValue.equals(oldValue)) {
		    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		  }
		}

}
