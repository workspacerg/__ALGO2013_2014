package algo_ratp.IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class IHM_InitIdentifier extends IHM_RATP implements ActionListener
{	
	private static final long serialVersionUID = 1L;
	private  JButton jBt_ConnexionString = new JButton("Valider"); 
	private JButton jBt_Cancel = new JButton("Retour");
	
	private TextField txt_DataBaseName = new TextField();
	private TextField txt_ConnexionString = new TextField("ex: Data Source=.SQLEXPRESS; Initial Catalog=Base1; Integrated Security=true" ); 
	private JPasswordField txt_Password = new JPasswordField();
	private JPasswordField txt_PasswordBis = new JPasswordField();
	
	private JLabel jLab_DataBase = new JLabel("Base de donnée : ");
	private JLabel jLab_ConnexionString = new JLabel("Chaine de Connection : ");
	private JLabel jLab_Password = new JLabel("Mot de passe : ");
	private JLabel jLab_PasswordBis = new JLabel("Confirmer le mot de passe : ");
	
	public IHM_InitIdentifier()
	{
		
		this.jLab_Welcome.setText("Entrez les informations de connection obligatoires");
		this.setTitle("Parametre de Connection");
		
		jPan3.add(jBt_Cancel);
		jPan3.add(jBt_ConnexionString);
		
		//*******************************************
        JPanel jPan4d = new JPanel();
        jPan4d.setBackground(Color.WHITE);
        jPan4d.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4d.setMaximumSize(new Dimension(170,32));
        jPan4d.add(jLab_ConnexionString);
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 0;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4d, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 4;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(txt_ConnexionString, gBC_gBLay_Level_2); 
        //*********************************************
        
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.setPreferredSize(new Dimension(170,32));
        jPan4a.add(jLab_DataBase);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 2;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 2;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(txt_DataBaseName, gBC_gBLay_Level_2);
        
        //*******************************************
        JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4b.setPreferredSize(new Dimension(170,32));
        jPan4b.add(jLab_Password);
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 3;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4b, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 3;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(txt_Password, gBC_gBLay_Level_2);   
        
        
        JPanel jPan4c = new JPanel();
        jPan4c.setBackground(Color.WHITE);
        jPan4c.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4c.setPreferredSize(new Dimension(170,32));
        jPan4c.add(jLab_PasswordBis);
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 4;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4c, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 4;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(txt_PasswordBis, gBC_gBLay_Level_2); 
        
        txt_DataBaseName.setPreferredSize(new Dimension(125,25));
		txt_ConnexionString.setPreferredSize(new Dimension(125,30));
        txt_Password.setPreferredSize(new Dimension(125,30));
		txt_PasswordBis.setPreferredSize(new Dimension(125,30));
		
		txt_DataBaseName.setMinimumSize(new Dimension(125,25));
		txt_ConnexionString.setMinimumSize(new Dimension(125,25));
		txt_Password.setMinimumSize(new Dimension(125,30));
		txt_PasswordBis.setMinimumSize(new Dimension(125,30));
		
		txt_DataBaseName.setMaximumSize(new Dimension(125,25));
		txt_ConnexionString.setMaximumSize(new Dimension(125,25));
		txt_Password.setMaximumSize(new Dimension(125,30));
		txt_PasswordBis.setMaximumSize(new Dimension(125,30));
		
		txt_DataBaseName.setFont(police);
		txt_ConnexionString.setFont(police);
		txt_Password.setFont(police);
		txt_PasswordBis.setFont(police);
		
		jLab_DataBase.setFont(police);
		jLab_ConnexionString.setFont(police);
		jLab_Password.setFont(police);
		jLab_PasswordBis.setFont(police);
		
		jBt_ConnexionString.setFont(police);
		jBt_Cancel.setFont(police);
		
		ActionListenerForComponent(this.getContentPane());
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		//System.out.println("test");
		if(e.getSource()==this.jBt_Cancel)
		{
			this.dispose();
			IHM_search search = new IHM_search();
		}
		if(e.getSource()==this.jBt_ConnexionString)
		{
			this.dispose();
			IHM_search search = new IHM_search();
		}
		
	}
}