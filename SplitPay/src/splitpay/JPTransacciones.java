package splitpay;

import Controladores.CuentaJpaController;
import Controladores.DeudaJpaController;
import Controladores.TransaccionJpaController;
import Negocio.Usuario;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import oracle.jdbc.driver.Message;

public class JPTransacciones extends JPanel {
	private GUIPrincipal principal;
	private PMenu menu;
	private JTextField textField;
	private JTable tableCuenta;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
        private String[] columnSer = {"Nombre","Balance"};
	private Vector rowDataSer;
	private Vector columSerV;
        private List<Integer> cuentaID = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public JPTransacciones(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transacciones");
		lblNewLabel.setBounds(10, 11, 296, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		JButton btnRealizarTransaccion = new JButton("Realizar transaccion");
		btnRealizarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberToMemberTrans(); 
                                mostrarDatos();
			}
		});
		btnRealizarTransaccion.setBounds(593, 389, 176, 44);
		panel.add(btnRealizarTransaccion);
		
		comboBox = new JComboBox();
		comboBox.setBounds(552, 279, 217, 44);
		panel.add(comboBox);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(475, 294, 46, 14);
		panel.add(lblTipo);
		
		textField = new JTextField();
		textField.setBounds(552, 208, 217, 44);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(475, 223, 46, 14);
		panel.add(lblCantidad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 116, 358, 317);
		panel.add(scrollPane);
		
		tableCuenta = getTable();
		scrollPane.setViewportView(tableCuenta);
		
		JLabel lblCuentas = new JLabel("Cuentas");
		lblCuentas.setBounds(21, 91, 46, 14);
		panel.add(lblCuentas);
                mostrarDatos();
	} 
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTable getTable() {
		if (tableCuenta == null) {			
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			tableCuenta = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(tableCuenta);
		}
		return tableCuenta;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
        
        public void mostrarDatos(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
		CuentaJpaController contro = new CuentaJpaController(emf);
                rowDataSer = new Vector();
		List<String> informacion = contro.TablaUsuarioCuentaGrupo(principal.userActual.getId());
		for( String infCuenta : informacion )
		{
			Vector fila = new Vector();
                        System.out.println(infCuenta);
			StringTokenizer st = new StringTokenizer(infCuenta, "$");
			fila.add(st.nextToken().trim());
			fila.add(st.nextToken().trim());
			this.rowDataSer.add(fila);
                        //System.out.println(st.nextToken().trim());
                        cuentaID.add(Integer.parseInt(st.nextToken().trim()));
		}
		tableCuenta = new JTable(this.rowDataSer, this.columSerV);
		System.out.println("#ser");
		scrollPane.setViewportView(tableCuenta);
                comboBox.removeAllItems();
                comboBox.addItem("PayPal");
                comboBox.addItem("Cash");
                comboBox.addItem("Other");
	}
    
        public void memberToMemberTrans()
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
            DeudaJpaController controDeuda = new DeudaJpaController(emf);
            TransaccionJpaController controTrans = new TransaccionJpaController(emf);
            char tipo = ((String) comboBox.getSelectedItem()).charAt(0);
            long cantidad = Long.parseLong(textField.getText());
            long cuentaId = new Long(cuentaID.get(tableCuenta.getSelectedRow()));
            System.out.println("splitpay.JPTransacciones.memberToMemberTrans()");
            short deudaId = (short) controDeuda.DeudasdeUsuario((int) cuentaId, principal.userActual.getId());
            if(controTrans.memberToMemberTrans(principal.userActual.getId(), cuentaId, deudaId, cantidad, tipo))
                JOptionPane.showMessageDialog(null, "La Transaccion se ha realizado", "Transaccion Exitosa",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Transaccion Fallida", "Error", JOptionPane.ERROR_MESSAGE);
        }
}
