package algo_ratp.IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algo_ratp.IHM.tools.UserControl_Search;


public class IHM_home extends IHM_RATP
{
	private static final long serialVersionUID = 1L;
	private JButton jBt_FindRoad = new JButton("Trouver"); 
	private JButton jBt_AdvancedSearch = new JButton("Recherche avancée"); 
	
	private TextField txt_Departure = new TextField();
	private TextField txt_Arrival = new TextField();
	
	private JLabel jLab_Departure = new JLabel("Départ: ");
	private JLabel jLab_Arrival = new JLabel("Arrivée : ");
	
	public IHM_home()
	{
		this.jLab_Welcome.setText("Bienvenue sur MyTraject");
		this.setTitle("MyTraject");
		
		jPan3.add(jBt_AdvancedSearch);
		jPan3.add(jBt_FindRoad);	
		
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.setPreferredSize(new Dimension(125,32));
        jPan4a.add(jLab_Departure);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);	//top,left,bottom,right
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(new UserControl_Search(AutoCpltMod_Data,txt_Departure), gBC_gBLay_Level_2);
        
        JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4b.setPreferredSize(new Dimension(125,32));
        jPan4b.add(jLab_Arrival);
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 0;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4b, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(new UserControl_Search(AutoCpltMod_Data,txt_Arrival), gBC_gBLay_Level_2);   
       
        txt_Departure.setPreferredSize(new Dimension(115,25));
		txt_Arrival.setPreferredSize(new Dimension(115,25));
		
		txt_Departure.setMinimumSize(new Dimension(115,25));
		txt_Arrival.setMinimumSize(new Dimension(115,25));
		
		txt_Departure.setMaximumSize(new Dimension(115,25));
		txt_Arrival.setMaximumSize(new Dimension(115,25));
		
		txt_Departure.setFont(police);
		txt_Arrival.setFont(police);

		jLab_Departure.setFont(police);
		jLab_Arrival.setFont(police);
		
		jBt_FindRoad.setFont(police);
		jBt_AdvancedSearch.setFont(police);
		
		
		this.setVisible(true);
	}
}