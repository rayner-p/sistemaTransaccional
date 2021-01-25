package ec.ups.edu.appdis.g1.sistemaTransaccional.servicios;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Produces;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@WebService
public class ClienteServicioSOAP {
	@Inject
	private GestionSistemLocal on;
	private Transaccion tran;
	private Cuenta c;
	private double saldoNuevo;
	private Date fechaA;
	@WebMethod
	public void realizarTransaccion(String cedula,String cuenta, String tipoTransaccion, double monto) {
		
		tran = new Transaccion();
		Cuenta cl = on.buscarCuentaD(cedula);
		System.out.println("datos recogidos"+" "+cuenta+" "+tipoTransaccion+" "+monto);	
		if (tipoTransaccion.equals("Retiro") &&  cl.getSaldo() < monto) {
			System.out.println("El monto a retirar es mayor que el saldo");

		} else {
			System.out.println("Ingreso a RETIRO Validacion"+" "+cl.getSaldo());
			saldoNuevo = cl.getSaldo() - monto;
			System.out.println("se ha retiro :" + monto + "" + "de la cuenta"+" "+"SALDO ACTUAL"+" "+saldoNuevo);
		}
		if (tipoTransaccion.equals("Deposito")) {
			System.out.println("Ingreso a DEPOSITO Validacion");
			saldoNuevo = monto + cl.getSaldo();
			cl.setSaldo(saldoNuevo);
			System.out.println("se ha aniadido este valor a la cuenta" + saldoNuevo);
		} else {
			System.out.println("error en la valdiacion");
		}
		try {
			fechaA = new Date();
			System.out.println("fecha Actual"+fechaA);
			System.out.println("entras?");
		
			tran.setTipoTransaccion(tipoTransaccion);
			tran.setMonto(monto);
			tran.setFechaHora(fechaA);
			c = new Cuenta();
			c = on.obtenerCuentaPorNumero(cuenta);
			tran.setCuenta(c);
			System.out.println("que trae la cuenta" + c);
			on.insertarTransaccion(tran);
			on.actaulizarCuentaCliente(cuenta, saldoNuevo);
			System.out.println("transaccion creada"+" "
			+tran);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@WebMethod
	public List<Transaccion> obtenerTransacciones() {
		return on.obtenerTransaccion();
	}

}
