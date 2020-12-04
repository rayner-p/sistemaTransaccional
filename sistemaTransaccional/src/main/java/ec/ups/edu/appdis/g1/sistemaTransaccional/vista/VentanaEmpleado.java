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

public class VentanaEmpleado {

	private JFrame frame;
	private JTextField txtTipoDocumento;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleado window = new VentanaEmpleado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTIpoDocumento = new JLabel("Cédula");
		lblTIpoDocumento.setBounds(16, 61, 130, 16);
		panel.add(lblTIpoDocumento);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(16, 101, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Apellidos");
		lblCorreo.setBounds(16, 147, 61, 16);
		panel.add(lblCorreo);
		
		txtTipoDocumento = new JTextField();
		txtTipoDocumento.setColumns(10);
		txtTipoDocumento.setBounds(179, 56, 182, 26);
		panel.add(txtTipoDocumento);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(179, 96, 182, 26);
		panel.add(txtNombre);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(179, 142, 182, 26);
		panel.add(txtCorreo);
		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setBackground(Color.ORANGE);
		btnLimpiar.setBounds(167, 419, 116, 29);
		panel.add(btnLimpiar);
		
		JButton btnGuardar = new JButton("REGISTRAR");
		btnGuardar.setBackground(Color.ORANGE);
		btnGuardar.setBounds(299, 419, 145, 29);
		panel.add(btnGuardar);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 26, -100, 16);
		panel.add(textPane);
		
		JLabel lblCrearUsuarioAdministrativo = new JLabel("Crear Usuario Administrativo");
		lblCrearUsuarioAdministrativo.setBounds(138, 6, 182, 16);
		panel.add(lblCrearUsuarioAdministrativo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(16, 192, 154, 16);
		panel.add(lblFechaDeNacimiento);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 187, 182, 26);
		panel.add(textField);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(16, 237, 154, 16);
		panel.add(lblDireccin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 232, 182, 26);
		panel.add(textField_1);
		
		JLabel lblCorreo_1 = new JLabel("Correo");
		lblCorreo_1.setBounds(16, 276, 154, 16);
		panel.add(lblCorreo_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 271, 182, 26);
		panel.add(textField_2);
		
		JLabel lblCorreo_1_1 = new JLabel("Teléfono");
		lblCorreo_1_1.setBounds(16, 319, 154, 16);
		panel.add(lblCorreo_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(179, 314, 182, 26);
		panel.add(textField_3);
		
		JLabel lblCorreo_1_2 = new JLabel("Rol");
		lblCorreo_1_2.setBounds(16, 356, 154, 16);
		panel.add(lblCorreo_1_2);
		
		JComboBox cmbRol = new JComboBox();
		cmbRol.setBounds(179, 352, 172, 27);
		panel.add(cmbRol);
	}
}
