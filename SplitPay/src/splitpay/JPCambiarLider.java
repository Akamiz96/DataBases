package splitpay;

import java.awt.Color;
import java.awt.Font;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Controladores.GrupoJpaController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class JPCambiarLider extends JPanel {

	/**
	 * Create the panel.
	 */
	private GUIPrincipal principal;
	private PMenu menu;
	private JComboBox comboBox;
	private List<String> miembros;
	private List<String> Miembro = new ArrayList<String>();
	private List<Long> codigos = new ArrayList<Long>();

	public JPCambiarLider(GUIPrincipal principal, PMenu menu) {
		this.principal = principal;
		this.menu = menu;
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SplitPayPU");
		miembros = new GrupoJpaController(emf)
				.UsuariosdeGrupoConUsername(principal.grupoActual);
		for (String miembro : miembros) {
			StringTokenizer st = new StringTokenizer(miembro, "$");
			Miembro.add(st.nextToken().trim());
			codigos.add(Long.parseLong(st.nextToken().trim()));
		}
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 790, 460);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("cambiar lider");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(10, 11, 296, 44);
		panel.add(lblNewLabel);

		comboBox = new JComboBox(new Vector(Miembro));
		comboBox.setBounds(34, 92, 536, 91);
		panel.add(comboBox);

		JButton btnConfirmarCambio = new JButton("Confirmar cambio");
		btnConfirmarCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO cambiar Lider
				int index = comboBox.getSelectedIndex();
				if(index != -1){
					Long codigo = codigos.get(index);
					new GrupoJpaController(emf).CambiarLider(principal.grupoActual, Integer.valueOf(codigo+""));
					menu.navGruposNor();
					menu.tutoGrupo();
				}
				else{
					//TODO MOSTRAR QUE NO selecciono un objeto valido 
				}
			}
		});
		btnConfirmarCambio.setBounds(195, 212, 234, 66);
		panel.add(btnConfirmarCambio);

	}

	public JComboBox getComboBox() {
		return comboBox;
	}
}
