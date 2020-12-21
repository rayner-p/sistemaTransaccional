package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemRemoto;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class VentanaCrearCuenta {

	private JFrame frmAperturaDeCuenta;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private File fichero; 
	/**
	 * Launch the application.
	 */
	private GestionSistemRemoto remoto;
	JPanel panel = new JPanel();
	static VentanaCrearCuenta window ;
	private JTextField txtCuenta;
	private JTextField txtFecha;
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new VentanaCrearCuenta();
					window.frmAperturaDeCuenta.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaCrearCuenta() {
	
	
		frmAperturaDeCuenta = new JFrame();
		frmAperturaDeCuenta.setTitle("Apertura de Cuenta");
		frmAperturaDeCuenta.setBounds(100, 100, 501, 453);
		frmAperturaDeCuenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel.setBackground(new Color(153, 204, 153));
		frmAperturaDeCuenta.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTIpoDocumento = new JLabel("Cédula");
		lblTIpoDocumento.setBounds(66, 46, 80, 16);
		panel.add(lblTIpoDocumento);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(108, 104, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Apellidos");
		lblCorreo.setBounds(109, 137, 61, 16);
		panel.add(lblCorreo);
		
		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setEditable(false);
		txtCedula.setBounds(148, 41, 182, 26);
		txtCedula.setColumns(10);
		panel.add(txtCedula);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setEnabled(false);
		txtNombre.setBounds(189, 99, 182, 26);
		txtNombre.setColumns(10);
		panel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setEnabled(false);
		txtApellidos.setBounds(190, 132, 182, 26);
		txtApellidos.setColumns(10);
		panel.add(txtApellidos);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 26, -100, 16);
		panel.add(textPane);
		
		JLabel lblBuscarUsuarioAdministrativo = new JLabel("AÑADIR USUARIO");
		lblBuscarUsuarioAdministrativo.setBounds(195, 6, 203, 16);
		lblBuscarUsuarioAdministrativo.setEnabled(false);
		panel.add(lblBuscarUsuarioAdministrativo);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(108, 170, 154, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(190, 170, 182, 26);
		txtDireccion.setColumns(10);
		panel.add(txtDireccion);
		
		JLabel lblCorreo_1 = new JLabel("Correo");
		lblCorreo_1.setBounds(108, 203, 154, 16);
		panel.add(lblCorreo_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setEnabled(false);
		txtCorreo.setBounds(190, 198, 182, 26);
		txtCorreo.setColumns(10);
		panel.add(txtCorreo);
		
		JLabel lblCorreo_1_1 = new JLabel("Teléfono");
		lblCorreo_1_1.setBounds(108, 236, 154, 16);
		panel.add(lblCorreo_1_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setEnabled(false);
		txtTelefono.setBounds(190, 231, 182, 26);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);
		
		final JFileChooser cargar = new JFileChooser();
		cargar.setApproveButtonText("");
		cargar.setToolTipText("");
		cargar.setDialogTitle("Escoger Archivo");
		cargar.setBackground(Color.DARK_GRAY);
		cargar.setBounds(29, 243, 145, 1);
		panel.add(cargar);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBackground(Color.ORANGE);
		btnBuscar.setBounds(328, 41, 145, 29);
		panel.add(btnBuscar);
		
		JButton btnCrearCuenta = new JButton("CREAR CUENTA");
		btnCrearCuenta.setBackground(Color.ORANGE);
		btnCrearCuenta.setBounds(341, 402, 154, 29);
		panel.add(btnCrearCuenta);
		
		JLabel lblCuenta = new JLabel("Cuenta");
		lblCuenta.setBounds(29, 345, 61, 16);
		panel.add(lblCuenta);
		
		txtCuenta = new JTextField();
		txtCuenta.setEditable(false);
		txtCuenta.setBounds(102, 340, 118, 26);
		panel.add(txtCuenta);
		txtCuenta.setColumns(10);
		
		JLabel lblFechaApertura = new JLabel("Fecha Apertura");
		lblFechaApertura.setBounds(244, 345, 104, 16);
		panel.add(lblFechaApertura);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(347, 340, 126, 26);
		panel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(29, 389, 61, 16);
		panel.add(lblSaldo);
		
		textField = new JTextField();
		textField.setBounds(102, 384, 118, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo Cuenta");
		lblNewLabel.setBounds(123, 292, 97, 16);
		panel.add(lblNewLabel);
		
		JComboBox cmbTipoCuneta = new JComboBox();
		cmbTipoCuneta.setModel(new DefaultComboBoxModel(new String[] {"CREDITO", "AHORRO"}));
		cmbTipoCuneta.setBounds(217, 288, 112, 27);
		panel.add(cmbTipoCuneta);
		
	}
}
