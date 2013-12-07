package algo_ratp.IHM;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

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
		
		//Affichage resultat
		
		//****************
		
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
}
