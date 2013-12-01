package Launcher;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import algo_ratp.IHM.IHM_search;

public class Start {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		{
	        if ("Nimbus".equals(info.getName())) 
	        {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
		
		Runnable r = new Runnable()
		{
			public void run()
			{
				new IHM_search();
			}
			
		};		
		SwingUtilities.invokeLater(r);
	}

}
