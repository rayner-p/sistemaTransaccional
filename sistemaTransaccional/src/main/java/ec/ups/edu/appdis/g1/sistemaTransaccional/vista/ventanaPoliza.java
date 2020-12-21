package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ventanaPoliza {

	private JFrame frmRegistrarPliza;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPoliza window = new ventanaPoliza();
					window.frmRegistrarPliza.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaPoliza() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrarPliza = new JFrame();
		frmRegistrarPliza.setFont(new Font("Adobe Ming Std", Font.PLAIN, 15));
		frmRegistrarPliza.setTitle("Registrar Póliza");
		frmRegistrarPliza.getContentPane().setBackground(new Color(153, 204, 153));
		frmRegistrarPliza.getContentPane().setLayout(null);
		
		JLabel lblTipoPoliza = new JLabel("Tipo de póliza");
		lblTipoPoliza.setBounds(21, 6, 89, 16);
		frmRegistrarPliza.getContentPane().add(lblTipoPoliza);
		
		JComboBox cmbTipoPoliza = new JComboBox();
		cmbTipoPoliza.setModel(new DefaultComboBoxModel(new String[] {"PERIODICO", "VENCIMIENTO"}));
		cmbTipoPoliza.setBackground(new Color(255, 153, 51));
		cmbTipoPoliza.setToolTipText("");
		cmbTipoPoliza.setBounds(21, 34, 127, 27);
		frmRegistrarPliza.getContentPane().add(cmbTipoPoliza);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(21, 77, 61, 16);
		frmRegistrarPliza.getContentPane().add(lblFecha);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(21, 105, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(210, 77, 75, 16);
		frmRegistrarPliza.getContentPane().add(lblSucursal);
		
		JComboBox cmbSucursal = new JComboBox();
		cmbSucursal.setModel(new DefaultComboBoxModel(new String[] {"CUENCA", "QUITO", "MACHALA", "GUAYAQUIL"}));
		cmbSucursal.setBounds(210, 106, 130, 27);
		frmRegistrarPliza.getContentPane().add(cmbSucursal);
		
		JLabel blCuenta = new JLabel("Cuenta");
		blCuenta.setBounds(21, 163, 61, 16);
		frmRegistrarPliza.getContentPane().add(blCuenta);
		
		JLabel lblMontoAbono = new JLabel("Monto Abono");
		lblMontoAbono.setBounds(210, 163, 130, 16);
		frmRegistrarPliza.getContentPane().add(lblMontoAbono);
		
		JLabel lblTiempoPoliza = new JLabel("Tiempo Póliza");
		lblTiempoPoliza.setBounds(410, 163, 127, 16);
		frmRegistrarPliza.getContentPane().add(lblTiempoPoliza);
		
		textField_1 = new JTextField();
		textField_1.setBounds(21, 180, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(210, 180, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(410, 180, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField_3);
		
		JLabel lblCargo = new JLabel("Cargo ");
		lblCargo.setBounds(21, 229, 61, 16);
		frmRegistrarPliza.getContentPane().add(lblCargo);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(21, 246, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField_4);
		
		JLabel lblInteres = new JLabel("INTERES");
		lblInteres.setBounds(210, 229, 61, 16);
		frmRegistrarPliza.getContentPane().add(lblInteres);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(210, 246, 130, 26);
		frmRegistrarPliza.getContentPane().add(textField_5);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setBackground(new Color(255, 153, 51));
		btnRegistrar.setBounds(448, 288, 127, 29);
		frmRegistrarPliza.getContentPane().add(btnRegistrar);
		frmRegistrarPliza.setBounds(100, 100, 581, 345);
		frmRegistrarPliza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
