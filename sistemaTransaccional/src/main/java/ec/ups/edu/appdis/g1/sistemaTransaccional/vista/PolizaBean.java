package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named("dtEditView")
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
	@Inject
	private LoginBean clientB;
	private Poliza newPoliza;
	private double montoPolizaInvertir;
	private int tiempoPolizaInvertir;
	private double tasaInteres;
	private int maximoTiempo;
	private Cuenta newCuenta;
	private String cuenta;
	private double saldoNuevo;
	List<Poliza>transac ;
	List<Transaccion>transac2 ;
	private Parametrizar newParametrizar;
	private List<Parametrizar> listaPara;
	private String nombrEmpleado;
	private Date fechaActu = new Date();
	private String estadoPoliza;
	private List<Poliza> polizas = new ArrayList<Poliza>();
	private double saldoSimulación;
	private int nuevoRango = 0;
	private InputStream ceduCliente;
	private InputStream planiCliente;
	private Cliente clienteP;
	private Part fileS;
	private String tipoPolizaSimulacion;

	public Part getFileS() {
		return fileS;
	}

	public void setFileS(Part fileS) {
		this.fileS = fileS;
	}

	public double getMontoPolizaInvertir() {
		return montoPolizaInvertir;
	}

	public void setMontoPolizaInvertir(double montoPolizaInvertir) {
		this.montoPolizaInvertir = montoPolizaInvertir;
	}

	public int getTiempoPolizaInvertir() {
		return tiempoPolizaInvertir;
	}

	public void setTiempoPolizaInvertir(int tiempoPolizaInvertir) {
		this.tiempoPolizaInvertir = tiempoPolizaInvertir;
	}

	public Parametrizar getNewParametrizar() {
		return newParametrizar;
	}

	public void setNewParametrizar(Parametrizar newParametrizar) {
		this.newParametrizar = newParametrizar;
	}

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

	public double getSaldoSimulación() {
		return saldoSimulación;
	}

	public void setSaldoSimulación(double saldoSimulación) {
		this.saldoSimulación = saldoSimulación;
	}

	public String getTipoPolizaSimulacion() {
		return tipoPolizaSimulacion;
	}

	public void setTipoPolizaSimulacion(String tipoPolizaSimulacion) {
		this.tipoPolizaSimulacion = tipoPolizaSimulacion;
	}

	public List<Poliza> getTransac() {
		return transac;
	}

	public void setTransac(List<Poliza> transac) {
		this.transac = transac;
	}

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
		newPoliza = new Poliza();
		newParametrizar = new Parametrizar();
		estadoPoliza = new String();
		listaPara = new ArrayList<Parametrizar>();
		transac = new ArrayList<Poliza>();
		transac2 = new ArrayList<Transaccion>();
		listarPoliza();
		listarTrans();
		try {
			Thread.sleep(400);
			listarTransS();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void obtenerInteres() {
		listaPara = on.obtenerParametros();
		for (Parametrizar s : listaPara) {
			int rangoMinimo = Integer.parseInt(s.getMinimo());
			int rangoMaximo = Integer.parseInt(s.getMaximo());
			System.out.println("rangos" + rangoMinimo + rangoMaximo + maximoTiempo);
			if (maximoTiempo <= rangoMaximo && maximoTiempo >= rangoMinimo) {
				nuevoRango = rangoMaximo;

			}
		}

	}

	/**
	 * metood que nos permite obtener la cuenta del cliente en base al numero
	 * ingresado
	 */
	public void verificarCuenta() {
		try {

			newCuenta = on.obtenerCuentaPorNumero(newPoliza.getCuentaClientePoliza());
		} catch (Exception e) {
			System.err.println("ERROR AL OBTENER DATOS DE LA CUENTA POLIZA" + " " + e.getLocalizedMessage());
		}
	}

	/**
	 * metodo que nos trae el cliente cuyo numero de cedula coincida con el de la
	 * cuenta
	 */
	public void obtenerClientePoliza() {
		clienteP = new Cliente();
		try {
			System.out.println("INGRESA AL CLIENTE POLIZA" + newCuenta.getCuenta_fk());
			clienteP = on.obtenerDatosPorCedula(newCuenta.getCuenta_fk());
		} catch (Exception e) {
			System.err.println("error en el encontrar cliente" + e.getMessage());

		}

	}

	/**
	 * metodo que permite validar el saldo de la cuenta al momento de realizar la
	 * poliza
	 * 
	 * @return bandera, tipo booleano que retorna false en caso de que no exista
	 *         cuenta con el numero ingresado
	 */
	public boolean validarSaldo() {
		System.out.println("ingresa a validar Saldo");
		System.out.println("cuenta" + newCuenta);
		boolean bandera = true;
		if (newCuenta == null) {
			System.out.println("No hay datos");
			bandera = false;

		} else if (newCuenta.getSaldo() < montoPolizaInvertir) {
			System.out.println("SALOD ACTUAL " + newCuenta.getSaldo());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
					"SALDO INGRESADO ES MAYOR AL DE LA CUENTA");

			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else if (montoPolizaInvertir < 500.00) {
			System.out.println("VALOR MENOR");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
					"El VALOR MINIMO PARA UNA POLIZA ES DE $500 ");

			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
		System.out.println("ESTO BANDERA SAL" + bandera);
		return bandera;
	}

	/**
	 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
	 * cedula de la clase
	 * 
	 * @param event Variable de tipo FileUploadEvent
	 * @throws IOException
	 */
	public void subirCedula(FileUploadEvent event) throws IOException {
		System.out.println("entras?");
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		ceduCliente = event.getFile().getInputStream();

	}

	/**
	 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
	 * planilla de la clase
	 * 
	 * @param event Variable de tipo FileUploadEvent
	 * @throws IOException
	 */
	public void subirPlanlilla(FileUploadEvent event) throws IOException {
		System.out.println("entras2?");
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		planiCliente = event.getFile().getInputStream();

	}

	public boolean validarDatosCompletos() {
		boolean bandera = true;
		System.out.println("ENTRAS A DATOS COMPLETO");
		if (newPoliza.getArchivoCedula() == null & newPoliza.getArchivoPlanillaServicios() == null
				& montoPolizaInvertir == 0.0 & maximoTiempo == 0 & newPoliza.getCuentaClientePoliza().isEmpty()) {
			System.out.println("ENTRAS A DATOS COMPLETOS?");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Message",
					"Póliza creada correctamente");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			bandera = false;
		} else {
			bandera = true;
		}
		return bandera;
	}

	/*
	 * metodo que me permite registrar una nueva póliza dentro de la base de datos
	 */
	public String crearPoliza() {
		verificarCuenta();
		obtenerInteres();
		obtenerClientePoliza();
		validarDatosCompletos();
		validarSaldo();
		String mensaje = new String();
		try {
			System.out.println("INGRESA A CREAR POLIZA");
			Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.DATE, maximoTiempo); //
			calendar.add(Calendar.DAY_OF_MONTH, maximoTiempo);
			System.out.println("fecha aniadidad" + calendar);
			Date fechaV = calendar.getTime();
			System.out.println(fechaV);
			newParametrizar = on.obtenerParametrosporDia(nuevoRango);
			tasaInteres = newParametrizar.getTasaInteres();
			newPoliza.setInteres(tasaInteres);
			newPoliza.setFechaEmision(fechaActu);
			newPoliza.setTiempoPlazo(maximoTiempo);
			newPoliza.setFechaVencimiento(fechaV);
			newPoliza.setMontoP(montoPolizaInvertir);
			// newPoliza.setEmpleadoCaptacion(empleadoB.getEmpleado());
			try {
				if (ceduCliente == null & planiCliente == null) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Message",
							"SUBA LOS ARCHIVOS PERTINENTES");

					PrimeFaces.current().dialog().showMessageDynamic(message);
				
				} else {
					newPoliza.setArchivoCedula(on.convertirArchivos(ceduCliente));
					newPoliza.setArchivoPlanillaServicios(on.convertirArchivos(planiCliente));
					newPoliza.setCliente(clienteP);
					try {

						on.insertarPoliza(newPoliza);
						saldoNuevo = newCuenta.getSaldo() - montoPolizaInvertir;
						System.out.println("saldo nuevo de la cuenta por poliza" + " " + saldoNuevo);
						on.actaulizarCuentaCliente(newPoliza.getCuentaClientePoliza(), saldoNuevo);
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message",
								"Póliza creada correctamente");

						PrimeFaces.current().dialog().showMessageDynamic(message);

					} catch (Exception e) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
								"ERROR DATOS INCOMPLETOS");

						PrimeFaces.current().dialog().showMessageDynamic(message);
						System.err.println("ERROR DATOS INCOMPLETOS" + e.getLocalizedMessage());
					}

				}
			} catch (Exception e) {
				throw new Exception("Error al subir archvios" + " " + e.getMessage());
			}

		} catch (

		Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Erro en la póliza"));

			System.err.println("ERROR AL CREAR POLIZA ON " + " " + e.getLocalizedMessage());
		} // TODO: handle exception
		return mensaje;
	}

	/**
	 * Metoodo que obtiene todas las polizas realizadas
	 * 
	 * @return lizas, lista de polizas
	 */
	public List<Poliza> listarPoliza() {
		// List<ClientePOJO> pojo = new ArrayList<ClientePOJO>();
		System.out.println("entra al bena listar");
		List<Poliza> lizas = on.obtenerPolizas();
		System.out.println(lizas);
		if (lizas != null) {
			polizas = lizas;

			return polizas;
		} else {
			System.err.println("ERROR, POLIZA NULL");
		}
		return lizas;
	}

	/**
	 * Metodo para visualizar los documentos de una solicitud
	 * 
	 * @param tipo El parametro tipo nos permite asignar el nombre del documento que
	 *             se desea visualizar
	 * @throws IOException Excepcion para errores de visualizacion
	 */
	@SuppressWarnings("resource")
	public void obtenerDocumentosPDF(String tipo) throws IOException {
		System.out.println("Entra a obtener");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		File file = File.createTempFile("archivoTemp", ".pdf");
		try (FileOutputStream fos = new FileOutputStream(file)) {
			if (tipo.equalsIgnoreCase("cedula")) {
				fos.write(newPoliza.getArchivoCedula());
			} else if (tipo.equalsIgnoreCase("planilla")) {
				fos.write(newPoliza.getArchivoPlanillaServicios());
			}

		}
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open file.
			input = new BufferedInputStream(new FileInputStream(file), 10485760);

			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
			output = new BufferedOutputStream(response.getOutputStream(), 10485760);

			// Write file contents to response.
			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} finally {

		}

		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following exception in the
		// logs:
		// java.lang.IllegalStateException: Cannot forward after response has been
		// committed.
		facesContext.responseComplete();
	}

	/**
	 * metodo que nos permite actualizar el estado de la poliza a nivel de base de
	 * datos
	 * 
	 * @throws Exception
	 */
	public void actualizarEstadoPoliza() throws Exception {
		System.out.println("Ingresa a actualizar");

	}

	public void onRowEdit(RowEditEvent<Poliza> event) {
		FacesMessage msg = new FacesMessage("Poliza Editada", String.valueOf(event.getObject().getCodigo()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * metodo que permite modificar el estado de la póliza por medio de la tabla
	 * 
	 * @param event
	 */
	public void onCellEdit(CellEditEvent<Poliza> event) {
		System.out.println("EVENTO POLIZA" + event);
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		Object numeroC = event.getRowIndex();
		System.out.println("INDEX" + numeroC + "key" + event.getRowKey());

		if (newValue != null && !newValue.equals(oldValue)) {
			try {

				if (estadoPoliza.equalsIgnoreCase("Pendiente")) {
					FacesMessage msg = new FacesMessage("Cambie ");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					try {

						if (estadoPoliza == null) {
							System.err.println("Estado Poliza vacio");
						} else {
							System.out.println("estado Poliza " + estadoPoliza
									+ listarPoliza().get(event.getRowIndex()).getCodigo());

							on.actualizarPoliza(listarPoliza().get(event.getRowIndex()).getCodigo(), estadoPoliza);

							ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
							ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
							System.out.println("que estado tiene la poliza" + " " + estadoPoliza);

						}
					} catch (Exception e) {
						throw new Exception(
								"Se ha producido un error al momento de actualizar la poliza" + " " + e.getMessage());
					}

				}

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
						"Old: " + oldValue + ", New:" + newValue);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * metodo que permite obtener el valor de la simulación de la póliza
	 */
	public void calcularValorSimulación() {
		System.out.println("entra a calcular simulacion");
		obtenerInteres();
		System.out.println("nuevo rango" + nuevoRango);
		newParametrizar = (Parametrizar) on.obtenerParametrosporDia(nuevoRango);
		tasaInteres = newParametrizar.getTasaInteres();
		if (tipoPolizaSimulacion.equals("PERIODICO")) {
			saldoSimulación = montoPolizaInvertir * tasaInteres / 360 * 30;
			System.out.println("saldo antes" + saldoSimulación);
		} else if (tipoPolizaSimulacion.equals("VENCIMIENTO")) {
			saldoSimulación = montoPolizaInvertir * tasaInteres / 360 * maximoTiempo;
			System.out.println("saldo antes" + saldoSimulación);
		} else {
			System.out.println("escoja un valor");
		}

	}

	public void validarNumeros() {
		String pattern = new String();
		pattern.matches("[0-9]");
		double m = Double.parseDouble(pattern);
		if (tiempoPolizaInvertir != m & montoPolizaInvertir != m) {
			FacesMessage msg = new FacesMessage("Ingrese  numeros validos" + montoPolizaInvertir);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	public void listarTransS() throws InterruptedException {
		if(clientB.getCliente().getCedula() == null) {
			Thread.sleep(4000);
		}else {
		transac = on.obtenerPolizasCEDULA(clientB.getCliente().getCedula());
		System.out.println(transac);
		}
	}
	public void listarTrans() {
		System.out.println("INGRESA A LISTAR TRANSACCIONES");
		transac2 = on.obtenerTransaccion();
		System.out.println(transac);
	}
	
}
