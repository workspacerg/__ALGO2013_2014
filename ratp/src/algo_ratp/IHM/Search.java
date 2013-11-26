package algo_ratp.IHM;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import Launcher.AutoCompleteModel;


public class Search extends JPanel {

	private static final long serialVersionUID = 1L;


	private JTextField zoneTexte;
	

	private JWindow fenetreRecherche;


	private JList resultats;
	


	private DefaultListModel modelListe;
	


	private AutoCompleteModel model;


	public Search(AutoCompleteModel model){
		this.model=model;
		zoneTexte = new JTextField();
		modelListe = new DefaultListModel();
		resultats = new JList(modelListe);
		resultats.setBorder(BorderFactory.createEtchedBorder());
		fenetreRecherche = new JWindow();
		fenetreRecherche.add(new JScrollPane(resultats));
		zoneTexte.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(zoneTexte.getText()==null || zoneTexte.getText().length()==0){
					fenetreRecherche.setVisible(false);
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(resultats.getSelectedIndex()<resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()+1);
					}
					else {
						resultats.setSelectedIndex(0);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP){
					if(resultats.getSelectedIndex()!=resultats.getModel().getSize()){
						resultats.setSelectedIndex(resultats.getSelectedIndex()-1);
					}
					else {
						resultats.setSelectedIndex(resultats.getModel().getSize());
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_ENTER){
					zoneTexte.setText(resultats.getSelectedValue().toString());
					fenetreRecherche.setVisible(false);
				}
				else {
					update();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
		setLayout(new GridLayout(1, 0));
		add(zoneTexte);
	}



	public void update(){
		List<String> correspondants = model.getChainesCorrespondates(zoneTexte.getText());
		modelListe.clear();
		if(correspondants.size()==0){
			modelListe.addElement("(vide)");
		}
		else{
			for(String s : correspondants){
				modelListe.addElement(s);
			}
		}
		fenetreRecherche.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()+zoneTexte.getHeight(), getWidth(), 3*zoneTexte.getHeight());
		fenetreRecherche.setVisible(true);
		resultats.setSelectedIndex(0);
	}

}