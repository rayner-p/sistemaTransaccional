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
	
	private List<Parametrizar> listaP;
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Parametrizar newParametros;
	
	@Inject
	private GestionSistemLocal on;

	@PostConstruct
	public void init() {
		newParametros = new Parametrizar();
		
		listaP = new ArrayList<Parametrizar>() ;
	}
	
	
	public List<Parametrizar> getListaP() {
		return listaP;
	}

	public void setListaP(List<Parametrizar> listaP) {
		this.listaP = listaP;
	}

	public int getDiaMi() {
		return diaMi;
	}

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
	
	
	public String registrarParametros () {
		
		try {
			if(newParametros.getMinimo()==null  && newParametros.getMaximo() == null) {
				System.out.println("Ingrese los datos");
			}	
				newParametros.getMaximo();
				newParametros.getMinimo();
				Tasa =Double.valueOf(newParametros.getTasaInteres());
				newParametros.setTasaInteres(Tasa);
				on.insertar(newParametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String obtenerListas() {
		
		listaP = on.obtenerParametros();
		
		System.out.println("Parametros"+listaP) ;
		return "lista";
	}
}
