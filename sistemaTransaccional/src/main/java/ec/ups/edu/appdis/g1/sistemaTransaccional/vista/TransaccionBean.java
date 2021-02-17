package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ClienteDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.CuentaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class TransaccionBean implements Serializable {

	/**
	 * 
	 */
	private List<String> cuentasK;

	private Empleado empleadoB;
	private String tipoTransaccion;
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionSistemLocal on;
	@Inject
	private CuentaBean cuentB;
	@Inject
	private ClienteBean clienteB;

	private Transaccion newTransaccion;

//	private Cuenta newCuenta;

	private Cliente cliente;

	private String cuenta;
	private String tipoCuenta;
	private double saldoNuevo;
	private String nombrEmpleado;
	private String cuentaObtenida;
	private List<Transaccion> listaTransaccion;
	// private List<Cuenta> cueentaList;
	private Date fechaActu = new Date();
	private String cuentaObt;
	double montoNuevo;
	private List<Transaccion> lstTransacciones;

	private Date fechaInicio;
	private Date fechaFinal;

	public String getCuentaObt() {
		return cuentaObt;
	}

	public void setCuentaObt(String cuentaObt) {
		this.cuentaObt = cuentaObt;
	}

	public List<String> getCuentasK() {
		return cuentasK;
	}

	public void setCuentasK(List<String> cuentasK) {
		this.cuentasK = cuentasK;
	}

	public Empleado getEmpleadoB() {
		return empleadoB;
	}

	public void setEmpleadoB(Empleado empleadoB) {
		this.empleadoB = empleadoB;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public CuentaBean getCuentB() {
		return cuentB;
	}

	public void setCuentB(CuentaBean cuentB) {
		this.cuentB = cuentB;
	}

	public ClienteBean getClienteB() {
		return clienteB;
	}

	public void setClienteB(ClienteBean clienteB) {
		this.clienteB = clienteB;
	}

	/*
	 * public Cuenta getNewCuenta() { return newCuenta; }
	 * 
	 * public void setNewCuenta(Cuenta newCuenta) { this.newCuenta = newCuenta; }
	 */

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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

	public String getCuentaObtenida() {
		return cuentaObtenida;
	}

	public void setCuentaObtenida(String cuentaObtenida) {
		this.cuentaObtenida = cuentaObtenida;
	}

	public List<Transaccion> getListaTransaccion() {
		return listaTransaccion;
	}

	public void setListaTransaccion(List<Transaccion> listaTransaccion) {
		this.listaTransaccion = listaTransaccion;
	}

	public Transaccion getNewTransaccion() {
		return newTransaccion;
	}

	public void setNewTransaccion(Transaccion newTransaccion) {
		this.newTransaccion = newTransaccion;
	}

	public Date getFechaActu() {
		return fechaActu;
	}

	public void setFechaActu(Date fechaActu) {
		this.fechaActu = fechaActu;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public List<Transaccion> getLstTransacciones() {
		return lstTransacciones;
	}

	public void setLstTransacciones(List<Transaccion> lstTransacciones) {
		this.lstTransacciones = lstTransacciones;
	}

	@PostConstruct
	public void init() {
		// newCuenta = new Cuenta();
		cliente = new Cliente();
		newTransaccion = new Transaccion();
		listaTransaccion = new ArrayList<Transaccion>();
		cuentaObt = new String();
		obtenerItem();

	}

	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */
	public String validarTransaccion() {
		// cliente = on.buscarCliente(cliente.getCedula());
		// System.out.println("INGRESO A VALIDAR TRANSACCION " + "" +
		// cliente.getCedula());
		Cuenta cl = on.getCuentaCedulaCliente(cliente.getCedula());
		// System.out.println("VALIDACION " + cl);
		montoNuevo = newTransaccion.getMonto();

		System.out.println("MONTO OBTENIDO DEL XH" + montoNuevo);
		if (newTransaccion.getTipoTransaccion() == "Retiro" && cl.getSaldo() < montoNuevo) {
			System.out.println("El monto a retirar es mayor que el saldo");

		} else {
			System.out.println("Ingreso a RETIRO Validacion" + " " + cl.getSaldo());
			saldoNuevo = cl.getSaldo() - montoNuevo;
			System.out.println(
					"se ha retiro :" + montoNuevo + "" + "de la cuenta" + " " + "SALDO ACTUAL" + " " + saldoNuevo);
		}
		if (newTransaccion.getTipoTransaccion() == "Deposito") {
			System.out.println("Ingreso a DEPOSITO Validacion");
			saldoNuevo = montoNuevo + cl.getSaldo();
			// newCuenta.setSaldo(saldoNuevo);
			System.out.println("se ha aniadido este valor a la cuenta" + saldoNuevo);
		} else {
			System.out.println("deposito");
		}
		return null;
	}

	public void obtenerItem() {
		cuentaObt = new String();
		if (cuentaObt == null) {
			System.out.println("VALPOR DEL INPUT " + cuentaObt);
		} else {
			System.out.println("lleno INPUT " + cuentaObt);
		}
	}

	/**
	 * Metodo para obtener el empleado a cargo de la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */

	public String obtenerEmpleado() {
		if (empleadoB.getNombres() == null) {
			System.out.println("No hay empleado");
		} else {
			nombrEmpleado = empleadoB.getNombres();

		}
		return null;

	}

	public boolean doBuscoCliente() {
		boolean bandera = true;
		try {
			System.out.println("ENTRAS A BUSCAR DATOS CLIENTES?" + cliente.getCedula());
			Cliente emple = on.buscarCliente(cliente.getCedula());

			System.out.println("ESTOS SON DATOS RECOGIDOS DEL USUARIO" + emple);
			cliente.setNombres(emple.getNombres());
			System.out.println(emple.getNombres());
			cliente.setApellidos(emple.getApellidos());
			cliente.setDireccion(emple.getDireccion());
			cliente.setCorreo(emple.getCorreo());
			cliente.setTelefono(emple.getTelefono());
			cliente.setCiudad(emple.getCiudad());
			cliente.setFechaNacimiento(emple.getFechaNacimiento());
			cliente.setCelular(emple.getCelular());
			cliente.setProvincia(emple.getProvincia());
			cliente.setTiempoResidencia(emple.getTiempoResidencia());
			cliente.setEstadoCivil(emple.getEstadoCivil());
			cliente.setReferenciaDomicilio(emple.getReferenciaDomicilio());
			cliente.setCuentaCliente(emple.getCuentaCliente());
			cuentasK = new ArrayList<String>();
			for (int i = 0; i < emple.getCuentaCliente().size(); i++) {
				System.out.println(emple.getCuentaCliente().get(i).getNumeroCuenta());
				cuentasK.add(emple.getCuentaCliente().get(i).getNumeroCuenta());
				System.out.println("CUENTAS TOTAL " + cuentasK);
			}
			System.out.println("CUENTA CLIENTES BUSQEUDA" + emple.getCuentaCliente());

			// usuarioAntiguo = emple.getUsuario();
			// contrasenaAntigua = emple.getContrasenia();
			// System.out.println("USUARIO + CONTRASENIA " +usuarioAntiguo
			// +""+contrasenaAntigua);
		} catch (Exception e) {
			bandera = false;
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return bandera;
	}

	public boolean validarTODOS() {
		boolean bandera = true;
		if (doBuscoCliente() == false & tipoTransaccion == null & montoNuevo == 0.0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Info", "Ingrese todos los campos"));
			bandera = false;
		} else {

		}
		return bandera;
	}

	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */
	public String doRegistroTransaccion() {
		Cuenta cuentaB;
		new Date();
		try {
			listaTransaccion.add(newTransaccion);
			System.err.println("ENTRA A TRANSACCION");
			// System.out.println("LISTA DE CUENTA -->" + listaCuenta);
			if (tipoTransaccion.equalsIgnoreCase("Retiro")) {
				System.out.println(tipoTransaccion);
				System.out.println("ENTRA AL IF PARA CREAR TRANSACCION RETIRO");
				// System.out.println("cuenta dentro if" + newCuenta.getNumeroCuenta());
				System.out.println("Cedula CLiente CUENTA" + clienteB.getNewCliente().getCedula());
				// cuentaObtenida = new String();
				// cuentaObtenida = newCuenta.getNumeroCuenta();
				System.out.println("cuenta obte" + cuentaObt);
				newTransaccion.setTipoTransaccion("Retiro");

				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN TRANSACC");
					newTransaccion.setFechaHora(new Date());
					//newTransaccion.setTransaccion_fk(cliente.getCedula());
					// newTransaccion.setTransaccion_fk(clienteB.getNewCliente().getCedula());
					// System.out.println(listaTransaccion.get(0));
					System.out.println("Clave fk cuenta"+clienteB.getCedu());
					System.out.println("Clave fk cuenta2"+cliente.getCedula());
					if (newTransaccion.getMonto() == 0.0 & newTransaccion.getTipoTransaccion() == null) {
						System.out.println("cuenta en blanco ingrese valor del monto y seleccione el tipo");
					} else {
						validarTransaccion();
						validarTODOS();
						/// System.out.println("INGRESA A VALIDAR ANTES DE GUARDAR" + newCuenta);
						newTransaccion.setEmpleado(empleadoB);
						System.out.println("cuenta afsfsafsaf" + cuentaObt);
						cuentaB = new Cuenta();
						cuentaB = on.obtenerCuentaPorNumero(cuentaObt);
						System.out.println("QUE TIENE CUENTA" + cuentaB);
						newTransaccion.setCuenta(cuentaB);
						newTransaccion.setSucursal(cliente.getCiudad());
						
						on.insertarTransaccion(newTransaccion);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Info", "Transacción Realizada correctamente"));
						
						//cliente.setTransaccionCliente(Set<Transaccion>newTransaccion);
						on.enviarCorreo(cliente.getCorreo(), " RETIRO DE DINERO ", " BANCO INTERNACIONAL\n"
								+ "------------------------------------------------------------------------------\n"
								+ "              Estimado(a): " + cliente.getNombres().toUpperCase() + " "
								+ cliente.getApellidos().toUpperCase() + "\n"
								+ "------------------------------------------------------------------------------\n"
								+ "El sistema transaccional del Banco Internacional le informa a usted que se ha hecho un retiro de $"
								+ montoNuevo + " " + " a su cuenta. \n"
								+ "                       Fecha de la transacción: " + fechaActu
								+ "                                     \n"
								+ "                                                                              \n"
								+ "------------------------------------------------------------------------------\n");

						System.out.println("correo enviado");
						on.actaulizarCuentaCliente(cuentaObt, saldoNuevo);
						System.out.println("cuenta a actualizar " + " " + cuentaObt + "SALDO ACTUALIZADO" + saldoNuevo);

					}
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Info", "No se ha podido realizar el retiro"));
					e.printStackTrace();
				}
			} else if (tipoTransaccion.equalsIgnoreCase("Deposito")) {
				System.out.println("ENTRA AL IF PARA CREAR TRANSAC DEPOSTO");
				newTransaccion.setTipoTransaccion("Deposito");
				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN DEPO");
					newTransaccion.setFechaHora(new Date());

					if (newTransaccion.getMonto() == 0.0 & newTransaccion.getTipoTransaccion().isEmpty()) {
						System.out.println("cuenta en blanco, ingrese valores");
					} else {
						System.out.println("INGRESO AL GUARDAR TRANSACCION");
						validarTransaccion();
						validarTODOS();
						// .out.println("cuenta a actualizar " + " " + newCuenta.getNumeroCuenta());
						newTransaccion.setEmpleado(empleadoB);

						System.out.println("MONTO DE LA TRANSACCION =" + "" + newTransaccion.getMonto());
						newTransaccion.getMonto();
						newTransaccion.setSucursal(cliente.getCiudad());
						cuentaB = new Cuenta();
						cuentaB = on.obtenerCuentaPorNumero(cuentaObt);
						System.out.println("QUE TIENE CUENTA BEAN" + cuentaB);
						newTransaccion.setCuenta(cuentaB);
						System.out.println("numero a buscqr" + cuentaObt);

						on.insertarTransaccion(newTransaccion);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Info", "Transacción realizada  correctamente"));

						on.actaulizarCuentaCliente(cuentaObt, saldoNuevo);
						System.out.println("TRANSACCION CREADA  ---> " + " " + listaTransaccion);
						System.out.println("cuenta a actualizar " + " " + cuentaObt + "SALDO ACTUALIZADO" + saldoNuevo);

					}

				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Info", "No se ha podido realizar el deposito"));
					System.out.println("error guardar transaccion ");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println("error al guardar transaccion" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Metodo que permite obtener una lista de transacciones entre una fecha de
	 * inicio y una fecha final
	 */
	public void ultimosDias() {
		Calendar c = Calendar.getInstance();
		fechaFinal = c.getTime();
		c.add(Calendar.DATE, -30);
		fechaInicio = c.getTime();
		DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String inicioF = hourdateFormat.format(fechaInicio);
		String finalF = hourdateFormat.format(fechaFinal);
		List<Transaccion> listaTrans = on.obtenerTransaccionesFechaHora(newTransaccion.getTransaccion_fk(), inicioF,
				finalF);
		lstTransacciones = listaTrans;
		System.out.println(lstTransacciones.size());
		System.out.println(newTransaccion.getTransaccion_fk());
	}

}
