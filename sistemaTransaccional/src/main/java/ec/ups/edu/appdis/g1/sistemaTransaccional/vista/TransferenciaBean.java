package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.ClienteDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.datos.CuentaDao;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.TranferenciaLocal;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class TransferenciaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionSistemLocal on;
	@Inject
	private CuentaBean cuentB;
	@Inject
	private ClienteBean clienteB;
	@Inject
	private LoginBean loginB;
	private Cuenta newCuenta;
	private Cliente cliente;	
	private CuentaDao daoCuenta;
	private TranferenciaLocal transferenciaLocal;
	private TransferenciaBean trasnB;
	private List<String> cuentasK;
	private	double saldosK;
	private List<String> nuevasCuentas;
	private List<TranferenciaLocal> listaTransferencias;
	private List<Cuenta> cuentasF;
	private String cuenta;
	private String input;
	private double saldoNuevo;
	private Cuenta trnS;
	private String cuentaObtenida;
	
	
	
	public Cuenta getTrnS() {
		return trnS;
	}

	public void setTrnS(Cuenta trnS) {
		this.trnS = trnS;
	}

	public double getSaldosK() {
		return saldosK;
	}

	public void setSaldosK(double  saldosK) {
		this.saldosK = saldosK;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public TranferenciaLocal getTransferenciaLocal() {
		return transferenciaLocal;
	}

	public void setTransferenciaLocal(TranferenciaLocal transferenciaLocal) {
		this.transferenciaLocal = transferenciaLocal;
	}

	public List<String> getCuentasK() {
		return cuentasK;
	}

	public void setCuentasK(List<String> cuentasK) {
		this.cuentasK = cuentasK;
	}

	public List<TranferenciaLocal> getListaTransferencias() {
		return listaTransferencias;
	}

	public void setListaTransferencias(List<TranferenciaLocal> listaTransferencias) {
		this.listaTransferencias = listaTransferencias;
	}

	public List<Cuenta> getCuentasF() {
		return cuentasF;
	}

	public void setCuentasF(List<Cuenta> cuentasF) {
		this.cuentasF = cuentasF;
	}

	public List<String> getNuevasCuentas() {
		return nuevasCuentas;
	}

	public void setNuevasCuentas(List<String> nuevasCuentas) {
		this.nuevasCuentas = nuevasCuentas;
	}

	public ClienteBean getClienteB() {
		return clienteB;
	}

	public void setClienteB(ClienteBean clienteB) {
		this.clienteB = clienteB;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

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



	public double getSaldoNuevo() {
		return saldoNuevo;
	}

	public void setSaldoNuevo(double saldoNuevo) {
		this.saldoNuevo = saldoNuevo;
	}

	public String getCuentaObtenida() {
		return cuentaObtenida;
	}

	public void setCuentaObtenida(String cuentaObtenida) {
		this.cuentaObtenida = cuentaObtenida;
	}


	@PostConstruct
	public void init() {
		System.out.println("ENTRAS CTM?");
		obtenerSaldoCuenta();
	
		try {
			getCuentaClientesUsuarios();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trnS = new Cuenta();
		newCuenta = new Cuenta();
		cliente = new Cliente();
		transferenciaLocal = new TranferenciaLocal();	
		nuevasCuentas = new ArrayList<String>();
		//cuentasK = new ArrayList<String>();
		cuentasF = new ArrayList<Cuenta>();
		listaTransferencias = new ArrayList<TranferenciaLocal>() ;
		input = new String();
		
	}
	
	
	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion	
	 */
	
	public void obtenerItem() {
		
		if (input == null) {
		input = trasnB.input;
		System.out.println("VALPOR DEL INPUT "+ input);
		}else {
		System.out.println("lleno INPUT "+ input);
		}
	}
	public String doRegistroTransferencia() {	
		try {
		if(on.obtenerClienteCuenta(newCuenta.getNumeroCuenta())==null) {
			System.out.println("cuenta nula");
			}
		else if (newCuenta.getSaldo() > transferenciaLocal.getMonto()) {
			System.out.println("El valor a transferir es mayor al que se tiene en la cuenta "+""+newCuenta.getSaldo());
		}else {
			System.out.println("ENTRA A REGISTRAR TRANSFERENCIA");
			daoCuenta.actaulizarCuentaCliente(input, saldoNuevo);
			
			on.agregarCuentaTransferecia(transferenciaLocal);
		}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	//obtener todas las cuentas que el cliente posee
	public void getCuentaClientesUsuarios() throws Exception {
		Cliente cliente2 = new Cliente();
		cliente2=on.buscarCliente(loginB.getCliente().getCedula());
		System.out.println("CLIENTE 2 "+cliente2);
		cuentasK= new ArrayList<String>();
	
		System.out.println("entras a obtener cuentas");
		for (int i = 0; i < cliente2.getCuentaCliente().size(); i++) {
			System.out.println(cliente2.getCuentaCliente().get(i).getNumeroCuenta());
			cuentasK.add(cliente2.getCuentaCliente().get(i).getNumeroCuenta());
			//saldosK.add(cliente2.getCuentaCliente().get(i).getSaldo());
			System.out.println("CUENTAS TOTAL " + cuentasK+""+saldosK);
		}
		
		
	}
	//obtener todas las transferencias que el usuario ha hecho
	public void getTransferenciasUsuario() {
		listaTransferencias = on.getTransfereciaLocals();
		System.out.println("LISTA DE TRANSFERENCIAS HECHAS POR EL USUARIO " +" "+listaTransferencias);
	}
	//metodo para obtener las cuentas en las que se ha hecho transferencias
	public void getTransferenciasCuentasF() {
		cuentasF = on.getTransfereciasOrigenes("404000000001");
		System.out.println("LISTA DE TRANSFERENCIAS HECHAS POR EL USUARIO " +" "+cuentasF);
	}	
	public void obtenerSaldoCuenta() {
		System.out.println("valor del input escogio"+input);	
			trnS =on.obtenerSaldoClienteCuenta("404000000003");
			saldosK = new Double(saldosK);
			saldosK = trnS.getSaldo();
			System.out.println("SALDO TRANSACION"+saldosK);
	}	
}
