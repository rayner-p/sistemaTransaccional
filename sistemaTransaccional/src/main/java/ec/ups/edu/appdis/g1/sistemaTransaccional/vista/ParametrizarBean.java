package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Parametrizar;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class ParametrizarBean implements Serializable {
	/**
	 * 
	 */
	private int diaMi;
	private int diaMa;
	private double Tasa;
	private LoginBean loginB;
	
	private List<Parametrizar> listaP;
	
	private static final long serialVersionUID = 1L;
	
	private Parametrizar newParametros;
	
	@Inject
	private GestionSistemLocal on;

	@PostConstruct
	public void init() {
		newParametros = new Parametrizar();
		
		listaP = new ArrayList<Parametrizar>() ;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public LoginBean getLoginB() {
		return loginB;
	}

	/**
	 * 
	 * @param loginB
	 */

	public void setLoginB(LoginBean loginB) {
		this.loginB = loginB;
	}

	/**
	 * 
	 * @return
	 */

	public List<Parametrizar> getListaP() {
		return listaP;
	}
	/**
	 * 
	 * @param listaP
	 */
	public void setListaP(List<Parametrizar> listaP) {
		this.listaP = listaP;
	}

	/**
	 * 
	 * @return
	 */
	public int getDiaMi() {
		return diaMi;
	}
	/**
	 * 
	 * @param diaMi
	 */

	public void setDiaMi(int diaMi) {
		this.diaMi = diaMi;
	}

	public int getDiaMa() {
		return diaMa;
	}

	public void setDiaMa(int diaMa) {
		this.diaMa = diaMa;
	}

	public double getTasa() {
		return Tasa;
	}

	public void setTasa(double tasa) {
		Tasa = tasa;
	}

	public Parametrizar getNewParametros() {
		return newParametros;
	}

	public void setNewParametros(Parametrizar newParametros) {
		this.newParametros = newParametros;
	}
	
	/**
	 *  metodo que me permite registrar una nueva parametrización
	 * @return null
	 */
	public String registrarParametros () {
		
		try {
			if(newParametros.getMinimo()==null  && newParametros.getMaximo() == null) {
				System.out.println("Ingrese los datos");
			}	
				newParametros.getMaximo();
				newParametros.getMinimo();
				newParametros.getTasaInteres();
				newParametros.setEmpleado(loginB.getEmpleado());
				on.insertar(newParametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Metodo que me permite listar las parametrizaciones de la bd 
	 * @return lista de todos los parametros ingresados
	 */
	public List<Parametrizar> obtenerLista(){	
		listaP = on.obtenerParametros();
		return listaP;
	}
}
