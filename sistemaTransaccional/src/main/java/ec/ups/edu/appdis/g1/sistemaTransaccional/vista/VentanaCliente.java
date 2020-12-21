package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class VentanaCliente {

	private JFrame frmAperturaCuencaCliente;
	private JTextField txtTipoDocumento;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	private JTextField txtContrasenia;
	private JTextField txtEdad;
	private JTextField txtTiempoResidencia;
	private JTextField txtCelular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente window = new VentanaCliente();
					window.frmAperturaCuencaCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAperturaCuencaCliente = new JFrame();
		frmAperturaCuencaCliente.setTitle("Apertura Cuenca Cliente");
		frmAperturaCuencaCliente.setBounds(100, 100, 920, 395);
		frmAperturaCuencaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		frmAperturaCuencaCliente.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTIpoDocumento = new JLabel("Cédula");
		lblTIpoDocumento.setBounds(16, 61, 61, 16);
		panel.add(lblTIpoDocumento);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(280, 61, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Apellidos");
		lblCorreo.setBounds(569, 61, 61, 16);
		panel.add(lblCorreo);
		
		txtTipoDocumento = new JTextField();
		txtTipoDocumento.setColumns(10);
		txtTipoDocumento.setBounds(111, 56, 145, 26);
		panel.add(txtTipoDocumento);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(353, 56, 182, 26);
		panel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(642, 56, 182, 26);
		panel.add(txtApellidos);
		
		JButton btnGuardar = new JButton("REGISTRAR");
		btnGuardar.setBackground(Color.ORANGE);
		btnGuardar.setBounds(761, 344, 145, 29);
		panel.add(btnGuardar);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 26, -100, 16);
		panel.add(textPane);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(280, 115, 154, 16);
		panel.add(lblFechaDeNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(431, 110, 104, 26);
		panel.add(txtFechaNacimiento);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(508, 167, 78, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(598, 162, 182, 26);
		panel.add(txtDireccion);
		
		JLabel lblCorreo_1 = new JLabel("Correo");
		lblCorreo_1.setBounds(601, 226, 54, 16);
		panel.add(lblCorreo_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(659, 221, 182, 26);
		panel.add(txtCorreo);
		
		JLabel lblCorreo_1_1 = new JLabel("Teléfono");
		lblCorreo_1_1.setBounds(16, 277, 66, 16);
		panel.add(lblCorreo_1_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(111, 272, 130, 26);
		panel.add(txtTelefono);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(508, 277, 61, 16);
		panel.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(683, 277, 78, 16);
		panel.add(lblContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setEditable(false);
		txtUsuario.setBounds(567, 272, 104, 26);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setEnabled(false);
		txtContrasenia.setEditable(false);
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(761, 272, 144, 26);
		panel.add(txtContrasenia);
		
		JLabel lblNewLabel = new JLabel("Estado Civil");
		lblNewLabel.setBounds(16, 115, 90, 16);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SOLTERA/O", "CASADA/O", "VIUDA/O", "DIVORCIADA/O"}));
		comboBox.setBounds(106, 111, 135, 27);
		panel.add(comboBox);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(569, 115, 61, 16);
		panel.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setEnabled(false);
		txtEdad.setEditable(false);
		txtEdad.setBounds(642, 110, 46, 26);
		panel.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(16, 167, 61, 16);
		panel.add(lblProvincia);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"EL ORO", "GUAYAS", "PICHINCHA", "AZUAY", "MANABI"}));
		comboBox_1.setBounds(111, 163, 130, 27);
		panel.add(comboBox_1);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(280, 167, 61, 16);
		panel.add(lblCiudad);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"MACHALA", "PASAJE", "GUAYAQUIL", "QUITO", "LOJA", "IBARRA", "COTOPAXI"}));
		comboBox_2.setBounds(353, 163, 114, 27);
		panel.add(comboBox_2);
		
		JLabel lblTiempo = new JLabel("Tiempo Residencia");
		lblTiempo.setBounds(16, 226, 130, 16);
		panel.add(lblTiempo);
		
		txtTiempoResidencia = new JTextField();
		txtTiempoResidencia.setBounds(150, 221, 130, 26);
		panel.add(txtTiempoResidencia);
		txtTiempoResidencia.setColumns(10);
		
		JLabel lblReferenciaDomicilio = new JLabel("Referencia Domicilio");
		lblReferenciaDomicilio.setBounds(299, 226, 130, 16);
		panel.add(lblReferenciaDomicilio);
		
		JTextPane txtReferencia = new JTextPane();
		txtReferencia.setBounds(454, 226, 131, 34);
		panel.add(txtReferencia);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(280, 277, 66, 16);
		panel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(353, 272, 130, 26);
		panel.add(txtCelular);
	}
}
