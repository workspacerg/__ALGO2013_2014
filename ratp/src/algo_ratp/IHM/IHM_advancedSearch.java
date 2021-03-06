package algo_ratp.IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.SimpleAttributeSet;

import algo_ratp.DALProvider;
import algo_ratp.Dijkstra;
import algo_ratp.Plan;
import algo_ratp.Relation;
import algo_ratp.Station;
import algo_ratp.IHM.tools.UserControl_Search;


public class IHM_advancedSearch extends IHM_RATP implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton jBt_FindRoad = new JButton("Trouver"); 
	private JButton jBt_Back = new JButton("Retour");
	
	private TextField txt_Departure = new TextField();
	private TextField txt_Arrival = new TextField();
	
	private JLabel jLab_Departure = new JLabel("Départ: ");
	private JLabel jLab_Arrival = new JLabel("Arrivée : ");
	
	private UserControl_Search jList_Departure = new UserControl_Search(AutoCpltMod_Data,txt_Departure);
	private UserControl_Search jList_Arrival = new UserControl_Search(AutoCpltMod_Data,txt_Arrival);
	
	private JComboBox combo = new JComboBox();
	private JComboBox comboHour = new JComboBox();
	private JComboBox comboMinute = new JComboBox();
	
	private JLabel jLab_Connection = new JLabel();
	
	public IHM_advancedSearch()
	{
		this.jLab_Welcome.setText("Bienvenue sur MyTraject");
		this.setTitle("MyTraject - Recherche Avancée");
		
		jPan3.add(jBt_FindRoad);	
		jPan3.add(jBt_Back);
		
		//ajout pr le label en plus
		jLab_Connection.setText(DALProvider.getInstance().isAuth() ? "Connecté à "+DALProvider.getInstance().getDbPath() : "Vous n'étes pas connecté à une base de données.");
		JPanel jPan4z = new JPanel();
		jPan4z.setBackground(Color.WHITE);
		jPan4z.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
		jPan4z.setPreferredSize(new Dimension(405,32));
		jPan4z.add(jLab_Connection);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 0;
		gBC_gBLay_Level_2.gridwidth = 2;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
		jPan4.add(jPan4z, gBC_gBLay_Level_2);
				
		//ajout pr le label en plus
				
				
		JPanel jPan4a = new JPanel();
        jPan4a.setBackground(Color.WHITE);
        jPan4a.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4a.setPreferredSize(new Dimension(200,32));
        jPan4a.add(jLab_Departure);
		gBC_gBLay_Level_2.gridx = 0;
		gBC_gBLay_Level_2.gridy = 1;
		gBC_gBLay_Level_2.gridwidth = 1;
		gBC_gBLay_Level_2.gridheight = 1;
		gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
		gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);	//top,left,bottom,right
		jPan4.add(jPan4a, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 2;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.anchor = GridBagConstraints.LINE_START;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jList_Departure, gBC_gBLay_Level_2);
        
        JPanel jPan4b = new JPanel();
        jPan4b.setBackground(Color.WHITE);
        jPan4b.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4b.setPreferredSize(new Dimension(200,32));
        jPan4b.add(jLab_Arrival);
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 1;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.fill=GridBagConstraints.REMAINDER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4b, gBC_gBLay_Level_2);
         
        gBC_gBLay_Level_2.gridx = 1;
        gBC_gBLay_Level_2.gridy = 2;
        gBC_gBLay_Level_2.gridwidth = 1;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.fill=GridBagConstraints.REMAINDER;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jList_Arrival, gBC_gBLay_Level_2);
        
        
        combo.setPreferredSize(new Dimension(80,25));
        combo.setMinimumSize(new Dimension(80,25));
        combo.setMaximumSize(new Dimension(80,25));
        combo.addItem("Départ");
        combo.addItem("Arrivée");
        
        comboHour.setPreferredSize(new Dimension(60,25));
        comboHour.setMinimumSize(new Dimension(60,25));
        comboHour.setMaximumSize(new Dimension(60,25));
        for(int i =0 ; i<25; i++)
        {
        	comboHour.addItem(i+"h");
        }
        
        
        comboMinute.setPreferredSize(new Dimension(60, 25));
        comboMinute.setMinimumSize(new Dimension(60,25));
      	comboMinute.setMaximumSize(new Dimension(60,25));
        for(int i =0 ; i<60; i=i+5)
        {
        	comboMinute.addItem(i);
        }
        
        
        JPanel jPan4c = new JPanel();
        jPan4c.setBackground(Color.WHITE);
        jPan4c.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        FlowLayout fl = new FlowLayout();
        jPan4c.setLayout(fl);
        jPan4c.add(combo);
        jPan4c.add(comboHour);
        jPan4c.add(comboMinute);
      		
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 3;
        gBC_gBLay_Level_2.gridwidth =2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4c, gBC_gBLay_Level_2);
         
        //**********//
        
        JRadioButton fasterButton   = new JRadioButton("Le plus rapide"  , true);
        JRadioButton lessConnection    = new JRadioButton("Le moins de correspondance", false);

        ButtonGroup bGrp = new ButtonGroup();
        bGrp.add(fasterButton);
        bGrp.add(lessConnection);

        JPanel jPan4d = new JPanel();
        jPan4d.setLayout(new GridLayout(3, 1));
        jPan4d.add(fasterButton);
        jPan4d.add(lessConnection);
        jPan4d.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Critère"));
        
        JPanel jPan4e = new JPanel();
        jPan4e.setBackground(Color.WHITE);
        jPan4e.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
        jPan4e.add(jPan4d);
        
        gBC_gBLay_Level_2.gridx = 0;
        gBC_gBLay_Level_2.gridy = 4;
        gBC_gBLay_Level_2.gridwidth = 2;
        gBC_gBLay_Level_2.gridheight = 1;
        gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
        gBC_gBLay_Level_2.insets = new Insets(2, 2, 2, 2);
        jPan4.add(jPan4e, gBC_gBLay_Level_2);
        
        txt_Departure.setPreferredSize(new Dimension(195,25));
		txt_Arrival.setPreferredSize(new Dimension(195,25));
		
		txt_Departure.setMinimumSize(new Dimension(195,25));
		txt_Arrival.setMinimumSize(new Dimension(195,25));
		
		txt_Departure.setMaximumSize(new Dimension(195,25));
		txt_Arrival.setMaximumSize(new Dimension(195,25));
		
		txt_Departure.setFont(police);
		txt_Arrival.setFont(police);

		jLab_Departure.setFont(police);
		jLab_Arrival.setFont(police);
		
		jBt_FindRoad.setFont(police);
		jBt_Back.setFont(police);
		ActionListenerForComponent(this.getContentPane());
		Plan.getInstance().backToSvg();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource()==this.jBt_Back)
		{
			this.dispose();
			IHM_search seach = new IHM_search();
		}
		if(e.getSource()==this.jBt_FindRoad)
		{
			if(comboHour.getSelectedItem() == null || comboMinute.getSelectedItem() == null){
				JOptionPane.showMessageDialog (this,"Veuillez renseigner une heure","MyTraject message",1);//1:exclam,1:exclamTriangle,3:interro
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			
			Date d;
			LinkedHashMap<Relation, Date> map = new LinkedHashMap<Relation, Date>();
			try {
				d = sdf.parse(comboHour.getSelectedItem().toString().replace("h","") + ":" + comboMinute.getSelectedItem().toString());
				Dijkstra.execute((Station)jList_Departure.getList().getSelectedValue());
				LinkedList<Relation> res = Dijkstra.getPath((Station)jList_Arrival.getList().getSelectedValue());
				
				DALProvider.getInstance().connect();
				if(this.combo.getSelectedIndex() == 0)
					map = DALProvider.getInstance().GetRealPathWithTime(res, d);
				else
					map = DALProvider.getInstance().GetRealPathWithArrival(res, d);
				DALProvider.getInstance().close();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			this.dispose();
			IHM_result result=new IHM_result(map);
		}
		
	}
}
