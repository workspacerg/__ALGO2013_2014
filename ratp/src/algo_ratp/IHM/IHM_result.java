package algo_ratp.IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import algo_ratp.IHM.tools.PicturesTools;

public class IHM_result extends IHM_RATP implements ActionListener
{
	private JButton jBt_SearchModification = new JButton("Modifier recherche"); 
	private JButton jBt_Exit = new JButton("Exit");
	
	public IHM_result() 
	{
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
	    
		
		int i=0;
	    int numberOfTransport = 8; //exemple 8 en dur pr l'instant
		for(i=1 ;i<numberOfTransport ;i++)
	    {
			PicturesTools icone_wayOfTravel = new PicturesTools();
			String[] s = new String[]{"    STATION1 jusqu'à STATION2 - ","temps de parcours.    "};//Sera un parametre du model via le controlleur

			icone_wayOfTravel.setFichierImage( PicturesTools.createFichierImage(System.getProperty("user.dir" ).toString()+"\\image\\","ratp.jpg")); // "ratp.jpg"==>mettre une variable
			icone_wayOfTravel.setPreferredSize(new Dimension(59,46));
			
		    JPanel jp = createJPanel(Color.WHITE, true);		       
		    jp = defineJPanelLayoutManager(jp);
		    jp.add(icone_wayOfTravel);
		    jp=addElement(s,jp);
		    gBC_gBLay_Level_2.fill=GridBagConstraints.HORIZONTAL;
		    gBC_gBLay_Level_2.gridx = 0;
		    gBC_gBLay_Level_2.gridy = i;
			gBC_gBLay_Level_2.gridwidth = 5;
			gBC_gBLay_Level_2.gridheight = 1;
			gBC_gBLay_Level_2.anchor = GridBagConstraints.CENTER;
			gBC_gBLay_Level_2.insets = new Insets(4, 0, 4, 0);
			jPan4.add(jp, gBC_gBLay_Level_2);
	    }
		
		
		ActionListenerForComponent(this.getContentPane());
		
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
		//l.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 5, Color.BLACK));
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
			//jp.setMaximumSize(new Dimension(480,30));
			jp.setMinimumSize(new Dimension(480,30));
	    	//jp.setPreferredSize(new Dimension(480,30));
	    }
	    return jp;
	}
	
	private JPanel defineJPanelLayoutManager(JPanel jp) 
	{
		BoxLayout bxLay_buttonGroup = new BoxLayout(jp,BoxLayout.X_AXIS);
		jp.setLayout(bxLay_buttonGroup);
		return jp;
	}
	
	private JPanel addElement(String[] s, JPanel jp)
	{
		if(s.length>2)
		{
			return jp;
		}
		int i = 0;
		for(i=0;i<s.length;i++)
		{
			jp.add(createJLabel(s[i].toString()));
		}
		return jp;
	}
}
