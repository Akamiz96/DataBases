package splitpay;

import Controladores.CuentaJpaController;
import Controladores.DeudaJpaController;
import Negocio.Cuenta;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPLimpiarDeudas extends JPanel {
	
	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JTable tableCuenta;
	private JScrollPane scrollPane;
	private String[] columnSer = { "Nombre" };
	private Vector rowDataSer;
	private Vector columSerV;
	private List<Integer> idCuenta = new ArrayList<Integer>();
	
	public JPLimpiarDeudas(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0,790, 460);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("limpiar");
		lblNewLabel.setBounds(315, 11, 296, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		panel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 80, 198, 188);
		panel.add(scrollPane);
		
		tableCuenta = new JTable();
		scrollPane.setColumnHeaderView(tableCuenta);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setBounds(399, 245, 89, 23);
		panel.add(btnLimpiar);
                mostrarDatos();
        }
        
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public JTable getTable() {
		if( tableCuenta == null ) {
			columSerV = new Vector(Arrays.asList(this.columnSer));
			rowDataSer = new Vector();
			tableCuenta = new JTable(this.rowDataSer, columSerV);
			scrollPane.setViewportView(tableCuenta);
		}
		return tableCuenta;
	}
        
        public void mostrarDatos() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
            CuentaJpaController controCuenta = new CuentaJpaController(emf);
            List<Cuenta> cuentas = controCuenta.todasCuentadeGrupo(principal.grupoActual);
            for( Cuenta cuenta : cuentas ) {
                Vector fila = new Vector();
                fila.add(cuenta.getNombre());
                rowDataSer.add(fila);
                idCuenta.add(cuenta.getId().intValue());
            }
            tableCuenta = new JTable(rowDataSer, columSerV);
            scrollPane.setViewportView(tableCuenta);
            emf.close();
        }
        
        public void resolverDeudas() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SplitPayPU");
            DeudaJpaController controDeuda = new DeudaJpaController(emf);
            int idCuen = idCuenta.get(tableCuenta.getSelectedRow());
            controDeuda.resolverDeudas(idCuen);
        }
}