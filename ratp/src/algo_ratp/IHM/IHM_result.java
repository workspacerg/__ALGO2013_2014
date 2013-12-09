package algo_ratp.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import algo_ratp.Plan;
import algo_ratp.Relation;
import algo_ratp.IHM.tools.PicturesTools;

public class IHM_result extends IHM_RATP implements ActionListener
{
	private JButton jBt_SearchModification = new JButton("Modifier recherche"); 
	private JButton jBt_Exit = new JButton("Exit");
	private LinkedHashMap<Relation,Date> mapResults;
	private LinkedList<Relation> listRel;
	
	public IHM_result(LinkedHashMap<Relation,Date> map) 
	{
		mapResults = map;
		this.jLab_Welcome.setText("Résultat de la recherche");
		this.setTitle("MyTraject - Itinéraire");
		
		jPan3.add(jBt_SearchModification);
		jPan3.add(jBt_Exit);
		
		//*****************
		
		JScrollPane jScrollPane = new JScrollPane(jPan4);
		//jScrollPane.getViewport().add(jPan4, null);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setViewportBorder(new LineBorder(Color.BLACK));
	    jPan1.add(jScrollPane);
	    
		
		int i = 0;
		int j = 0;
		Entry<Relation,Date> previous = null;
		Calendar calendar = Calendar.getInstance();
		
		for(Entry<Relation,Date> ent : mapResults.entrySet())
	    {
			if(previous == null){
			    JPanel jp = createJPanel(Color.WHITE, true);		       
			    jp = defineJPanelLayoutManager(jp);
			    calendar.setTime(ent.getValue());
			    jp=addElement(new String[]{"Départ à "+String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))},jp);
			    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
			    gBC_gBLay_Level_2.gridx = 0;
			    gBC_gBLay_Level_2.gridy = i++;
				gBC_gBLay_Level_2.gridwidth = 5;
				gBC_gBLay_Level_2.gridheight = 1;
				gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
				gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
				jPan4.add(jp, gBC_gBLay_Level_2);
				previous = ent;
				continue;
			}
			
			int nbWalk = previous.getKey().getLigne().getTypeTransport().equals(algo_ratp.Type.Metro) ? 4 : 7;
			
			if(j>0){
				JPanel jp2 = createJPanel(Color.GRAY,true);
				jp2 = defineJPanelLayoutManager(jp2);
				jp2 = addElement(new String[]{"Marche à pied "+nbWalk+"min"},jp2);
				gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
			    gBC_gBLay_Level_2.gridx = 0;
			    gBC_gBLay_Level_2.gridy = i++;
				gBC_gBLay_Level_2.gridwidth = 5;
				gBC_gBLay_Level_2.gridheight = 1;
				gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
				gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
				jPan4.add(jp2, gBC_gBLay_Level_2);
			}
					
			PicturesTools icone_wayOfTravel = new PicturesTools();			
			String[] s = new String[]{"    "+previous.getKey().getTarget().getName()+" jusqu'à "+ent.getKey().getTarget().getName()+"      " + (((ent.getValue().getTime() - previous.getValue().getTime()) / 60000) - nbWalk)+ "min"};

			String ligne_sn = previous.getKey().getLigne().getShort_name().toUpperCase();
			icone_wayOfTravel.setFichierImage( PicturesTools.createFichierImage(System.getProperty("user.dir" ).toString()+"\\image\\",ligne_sn+".jpg")); 
			icone_wayOfTravel.setPreferredSize(new Dimension(45,45));
			
		    JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp.add(icone_wayOfTravel,BorderLayout.WEST);
		    jp=addElement(s,jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
			previous = ent;
			i++;
			j++;
	    }
		
		if(previous != null){
			JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    calendar.setTime(previous.getValue());
		    jp=addElement(new String[]{"Arrivée à "+String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))},jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i++;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
		}
		else{
			JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp=addElement(new String[]{"Pas de chemin trouvé pour ces stations."},jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i++;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
		}
		
		ActionListenerForComponent(this.getContentPane());
		Plan.getInstance().backToSvg();
		this.setVisible(true);
	}
	
	public IHM_result(LinkedList<Relation> list) 
	{
		listRel = list;
		this.jLab_Welcome.setText("Résultat de la recherche");
		this.setTitle("MyTraject - Itinéraire");
		
		jPan3.add(jBt_SearchModification);
		jPan3.add(jBt_Exit);
		
		//*****************
		
		JScrollPane jScrollPane = new JScrollPane(jPan4);
		//jScrollPane.getViewport().add(jPan4, null);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setViewportBorder(new LineBorder(Color.BLACK));
	    jPan1.add(jScrollPane);
	    
		
		int i = 0;
		Relation previous = null;
		for(Relation rel : listRel)
	    {
			if(previous == null){
				previous = rel;
				continue;
			}
			
			PicturesTools icone_wayOfTravel = new PicturesTools();
			String[] s = new String[]{"    "+previous.getTarget().getName()+" jusqu'à "+rel.getTarget().getName()};
			
			String ligne_sn = previous.getLigne().getShort_name().toUpperCase();
			icone_wayOfTravel.setFichierImage( PicturesTools.createFichierImage(System.getProperty("user.dir" ).toString()+"\\image\\",ligne_sn+".jpg"));
			icone_wayOfTravel.setPreferredSize(new Dimension(45,45));
			
		    JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp.add(icone_wayOfTravel,BorderLayout.WEST);
		    jp=addElement(s,jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
			previous = rel;
			i++;
	    }
		
		if(previous == null){
			JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp=addElement(new String[]{"Pas de chemin trouvé pour ces stations."},jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i++;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
		}
		
		ActionListenerForComponent(this.getContentPane());
		Plan.getInstance().backToSvg();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource()==this.jBt_SearchModification)
		{
			this.dispose();
			IHM_advancedSearch advancedSeach = new IHM_advancedSearch();
		}
		if(e.getSource()==this.jBt_Exit)
		{
			this.dispose();
		}		
	}
	
	private JLabel createJLabel(String s) //trouver solution indexer bt ou les nommer
	{
		JLabel l = new JLabel(s);
		l.setHorizontalAlignment(JLabel.CENTER);
		 return l;
	}
	
	private JButton createJButton(String s) 
	{
		JButton jb = new JButton(s);
		jb.setHorizontalAlignment(JLabel.CENTER); 
		jb.setPreferredSize(new Dimension(70,25));
		return jb;
	}
	
	private JPanel createJPanel(Color colorPanel, Boolean autoSize) 
	{
		JPanel jp = new JPanel();
		jp.setBackground(colorPanel);
		jp.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
		if(autoSize)
	    {
			//jp.setPreferredSize(new Dimension(480,30));
			jp.setMinimumSize(new Dimension(480,30));
	    	//jp.setPreferredSize(new Dimension(480,30));
	    }
	    return jp;
	}
	
	private JPanel defineJPanelLayoutManager(JPanel jp) 
	{
		jp.setLayout(new BorderLayout());
		return jp;
	}
	
	private JPanel addElement(String[] s, JPanel jp)
	{
		if(s.length>2)
			return jp;

		for(int i=0;i<s.length;i++)
			jp.add(createJLabel(s[i].toString()));
		return jp;
	}
}
