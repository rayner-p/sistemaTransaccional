package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.servicios.ClienteServicioSOAP;
import ec.ups.edu.appdis.g1.sistemaTransaccional.servicios.ServiciosRest;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ventanaTransaccionREST {

	private JFrame frmTransaccion;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtCuenta;
	private JTextField txtSaldo;
	private JTextField txtApellido;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField;
	private ServiciosRest servicios;
	private List<String> cuentasK;
	private double saldoCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaTransaccionREST window = new ventanaTransaccionREST();
					window.frmTransaccion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaTransaccionREST() {
		initialize();
		servicios = new ServiciosRest();
		cuentasK = new ArrayList<String>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmTransaccion = new JFrame();
		frmTransaccion.setTitle("Registro Transacción");
		frmTransaccion.getContentPane().setBackground(new Color(153, 204, 153));
		frmTransaccion.getContentPane().setLayout(null);

		JLabel lblCedula = new JLabel("CEDULA");
		lblCedula.setBounds(131, 11, 61, 16);
		frmTransaccion.getContentPane().add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(244, 6, 130, 26);
		frmTransaccion.getContentPane().add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtenerCliente();
				obtenerD();
			}
		});
		btnBuscar.setBackground(new Color(255, 153, 51));
		btnBuscar.setBounds(381, 6, 117, 29);
		frmTransaccion.getContentPane().add(btnBuscar);

		JLabel lblNombres = new JLabel("NOMBRES");
		lblNombres.setBounds(131, 71, 61, 16);
		frmTransaccion.getContentPane().add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		txtNombres.setColumns(10);
		txtNombres.setBounds(244, 66, 130, 26);
		frmTransaccion.getContentPane().add(txtNombres);

		JLabel lblApellido = new JLabel("APELLIDOS");
		lblApellido.setBounds(131, 109, 83, 16);
		frmTransaccion.getContentPane().add(lblApellido);

		txtApellido = new JTextField();

		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(244, 104, 130, 26);
		frmTransaccion.getContentPane().add(txtApellido);

		JLabel lblDireccion = new JLabel("DIRECCION");
		lblDireccion.setBounds(131, 142, 83, 16);
		frmTransaccion.getContentPane().add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(244, 137, 130, 26);
		frmTransaccion.getContentPane().add(txtDireccion);

		JLabel lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setBounds(131, 175, 83, 16);
		frmTransaccion.getContentPane().add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(244, 170, 130, 26);
		frmTransaccion.getContentPane().add(txtTelefono);

		JLabel lblCorreo = new JLabel("CORREO");
		lblCorreo.setBounds(131, 208, 61, 16);
		frmTransaccion.getContentPane().add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(244, 203, 130, 26);
		frmTransaccion.getContentPane().add(txtCorreo);

		JLabel lblCuenta = new JLabel("CUENTA");
		lblCuenta.setBounds(131, 236, 61, 16);
		frmTransaccion.getContentPane().add(lblCuenta);

		txtCuenta = new JTextField();
		txtCuenta.setEditable(false);
		txtCuenta.setColumns(10);
		txtCuenta.setBounds(244, 236, 130, 26);
		frmTransaccion.getContentPane().add(txtCuenta);

		JLabel lblSaldoCuenta = new JLabel("SALDO CUENTA");
		lblSaldoCuenta.setBounds(131, 274, 101, 16);
		frmTransaccion.getContentPane().add(lblSaldoCuenta);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 204));
		panel.setBounds(22, 308, 516, 187);
		frmTransaccion.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Fecha y Hora");
		lblNewLabel.setBounds(6, 11, 93, 16);
		panel.add(lblNewLabel);

		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(132, 6, 109, 26);
		panel.add(textField_8);

		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(269, 11, 93, 16);
		panel.add(lblSucursal);

		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(6, 49, 93, 16);
		panel.add(lblMonto);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(132, 44, 109, 26);
		panel.add(textField_9);

		JLabel lblTipoTransaccin = new JLabel("Tipo Transacción");
		lblTipoTransaccin.setBounds(6, 82, 130, 16);
		panel.add(lblTipoTransaccin);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "RETIRO", "DEPOSITO" }));
		comboBox_1.setBounds(132, 78, 109, 27);
		panel.add(comboBox_1);

		JLabel lblCajero = new JLabel("Cajero");
		lblCajero.setBounds(6, 120, 130, 16);
		panel.add(lblCajero);

		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(132, 110, 142, 26);
		panel.add(textField_10);

		JLabel lblTipoTransaccin_1_1 = new JLabel("ID");
		lblTipoTransaccin_1_1.setBounds(6, 153, 130, 16);
		panel.add(lblTipoTransaccin_1_1);

		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(132, 148, 59, 26);
		panel.add(textField_11);

		JLabel lblNewLabel_1 = new JLabel("Código Transacción");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(288, 165, 142, 16);
		panel.add(lblNewLabel_1);

		JLabel lblCodigoTransaccion = new JLabel("");
		lblCodigoTransaccion.setEnabled(false);
		lblCodigoTransaccion.setBounds(442, 165, 61, 16);
		panel.add(lblCodigoTransaccion);

		txtSaldo = new JTextField();
		txtSaldo.setEnabled(false);
		txtSaldo.setBounds(338, 6, 130, 26);
		panel.add(txtSaldo);
		txtSaldo.setEditable(false);
		txtSaldo.setColumns(10);

		JButton btnRealizarTransaccion = new JButton("REALIZAR TRANSACCION");
		btnRealizarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnRealizarTransaccion.setBackground(new Color(255, 153, 51));
		btnRealizarTransaccion.setBounds(319, 515, 219, 29);
		frmTransaccion.getContentPane().add(btnRealizarTransaccion);

		textField = new JTextField();
		textField.setEditable(false);

		textField.setColumns(10);
		textField.setBounds(254, 274, 130, 26);
		frmTransaccion.getContentPane().add(textField);
		frmTransaccion.setBounds(100, 100, 560, 565);
		frmTransaccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void obtenerCliente() {
		String cuenta = new String();
		try {
			System.out.println(txtCedula.getText());

			Client client = ClientBuilder.newClient();

			Cliente c = servicios.obtenerTransacciones(txtCedula.getText());
			WebTarget target = client.target("http://localhost:8080/sistemaTransaccional/ws/transacciones/cliente?cedula="+txtCedula.getText()+"");
			Invocation.Builder solicitud = target.request();
			//BaseReq req = new BaseReq();
			Gson gson = new Gson();
			//String jsonString = gson.toJson(req);
			//System.out.println(jsonString);

			System.out.println("cliente?" + " " + c);
			txtNombres.setText(c.getNombres());
			txtApellido.setText(c.getApellidos());
			txtDireccion.setText(c.getDireccion());
			txtTelefono.setText(c.getCelular());
			txtCorreo.setText(c.getCorreo());
			System.out.println(c.getCuentaCliente());
			/*
			 * for (int i = 0; i < c.getCuentaCliente().size(); i++) {
			 * System.out.println(c.getCuentaCliente().get(i).getNumeroCuenta());
			 * cuentasK.add(c.getCuentaCliente().get(i).getNumeroCuenta()); cuenta =
			 * c.getCuentaCliente().get(i).getNumeroCuenta(); saldoCuenta =
			 * c.getCuentaCliente().get(i).getSaldo(); } txtCuenta.setText(cuenta); String
			 * valorS =Double.toString(saldoCuenta); txtSaldo.setText(valorS);
			 */
		} catch (Exception e) {
			System.out.println("error al buscar cliente por SWING");
			e.printStackTrace();
		}
	}
	public void obtenerD() {
	     List<String> resultadosList;
		 try {

	            URL url = new URL("http://localhost:8080/sistemaTransaccional/ws/transacciones/cliente?cedula="+txtCedula.getText()+"");//your url i.e fetch data from .
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");
	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);
	           String output =  br.readLine();
	           resultadosList = new ArrayList<String>();
	           resultadosList.add(output);	
	          /* while ((output = br.readLine()) != null) {
	                System.out.println(output);
	            }*/
	            System.out.println("SACAR EL PRIMER DATO" +output.charAt(1));
	           
	            String datos;
	            for (int i = 0; i < resultadosList.size(); i++) {
	           
	            	System.out.println("SACAR LOS DATOS SEPARADOS"+resultadosList.get(i));
	          
				}
	           
	            
	            conn.disconnect();
	        } catch (Exception e) {
	            System.out.println("Exception :- " + e);
	        }
	}
}
