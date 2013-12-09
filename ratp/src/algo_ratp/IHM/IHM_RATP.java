package algo_ratp.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algo_ratp.DALProvider;
import algo_ratp.Ligne;
import algo_ratp.Plan;
import algo_ratp.Station;
import algo_ratp.IHM.tools.AutoCompleteModel;
import algo_ratp.IHM.tools.PicturesTools;

public class IHM_RATP extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	protected PicturesTools icone_RATP = new PicturesTools();

	protected JLabel jLab_Welcome = new JLabel("Description",JLabel.LEFT);
	protected JLabel jLabfootPage = new JLabel("Gabel Romain - Mirabel Andy - Mokhtar Rahmani Rayane, 4AL - Copyright 2013",JLabel.RIGHT); //alt 0169
	
	protected BorderLayout bLay_Level_0 = new BorderLayout();
	protected BorderLayout bLay_Level_1 = new BorderLayout();
	protected BorderLayout bLay_Level_2 = new BorderLayout();
	
	protected FlowLayout fLay_Level_2 = new FlowLayout();	
	
	protected GridBagLayout gBLay_Level_2 = new GridBagLayout();
	protected GridBagConstraints gBC_gBLay_Level_2 = new GridBagConstraints();
	
	protected JPanel jPan0 = new JPanel();
	protected JPanel jPan1 = new JPanel();
	protected JPanel jPan2 = new JPanel(); 
	protected JPanel jPan3 = new JPanel();
	protected JPanel jPan4 = new JPanel();
	protected JPanel jPanFoot = new JPanel();
	
	protected Font police = new Font("TimesRoman", Font.BOLD, 12);
	protected Font policeSmall = new Font("TimesRoman", Font.BOLD, 9);
	protected Font police1 = new Font("TimesRoman", Font.ITALIC, 10);
	
	protected AutoCompleteModel AutoCpltMod_Data = initAutoCompleteMod();
	
	public IHM_RATP() 
	{
		this.setTitle("Page MERE ");
		this.setBackground(Color.BLUE);
		this.setSize(550,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		this.setContentPane(jPan0);
		jPan0.setLayout(bLay_Level_0);
		jPan0.add(jPan1,bLay_Level_0.CENTER);
		jPan0.add(jPanFoot,bLay_Level_0.SOUTH);
		
		jPanFoot.setBackground(Color.BLACK);
		jPanFoot.add(jLabfootPage);
		
		jPan1.setBackground(Color.LIGHT_GRAY);
		jPan1.setLayout(bLay_Level_1);
		jPan1.add(jPan2,bLay_Level_1.NORTH);
		jPan1.add(jPan4,bLay_Level_1.CENTER);
		jPan1.add(jPan3,bLay_Level_1.SOUTH);
		
		jPan2.setBackground(Color.WHITE);
		jPan2.setLayout(bLay_Level_2);
		jPan2.add(jLab_Welcome,bLay_Level_2.CENTER);
		jPan2.add(icone_RATP,bLay_Level_2.EAST);
		
		icone_RATP.setFichierImage( PicturesTools.createFichierImage(System.getProperty("user.dir" ).toString()+"\\image\\","ratp.jpg"));
		icone_RATP.setPreferredSize(new Dimension(59,46));
		
		jPan3.setBackground(Color.DARK_GRAY);
		jPan3.setLayout(fLay_Level_2);
		fLay_Level_2.setAlignment(fLay_Level_2.CENTER);
		
		jPan4.setBackground(Color.LIGHT_GRAY);
		jPan4.setLayout(gBLay_Level_2);
		//gBLay_Level_2.layoutContainer(jPan4);
		jPan4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		jLab_Welcome.setFont(police);
		Border engraved = BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK);
		jLab_Welcome.setBorder(BorderFactory.createTitledBorder(engraved, "", TitledBorder.LEFT, TitledBorder.LEFT));
		//jLab_Welcome.setBorder(BorderFactory.createMatteBorder(2,1, 2, 1, Color.BLACK));
		
		jLabfootPage.setFont(police1);
		jLabfootPage.setForeground(Color.WHITE);
		
		this.setVisible(true);
	}
	
	public AutoCompleteModel initAutoCompleteMod()
	{	
		AutoCompleteModel acm = new AutoCompleteModel();
		
		if(DALProvider.getInstance().isAuth()){
			Plan.getInstance().getPlan();
			acm.addAll(Plan.getInstance().GetStations());
		}
		
		return acm;
	}
	
	public void ActionListenerForComponent(Container cont_temps)
	{
		if(cont_temps instanceof Container)
		{
			int i;
			int j =cont_temps.getComponentCount();
			for(i =0; i< j ;i++)
			{
				Component cmp_temps = cont_temps.getComponents()[i];
				//System.out.println(j);
						
				if(cmp_temps instanceof JButton)
				{	
					((JButton) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JTextField )
				{
					((JTextField) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JPasswordField )
				{
					((JPasswordField) cmp_temps).addActionListener(this);
				}
				if(cmp_temps instanceof JComboBox)
				{
					ActionListenerForComponent((JComboBox<String>) cmp_temps);
				}
				if(cmp_temps instanceof Container)
				{
					ActionListenerForComponent((Container) cmp_temps); 
				}
			}
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

