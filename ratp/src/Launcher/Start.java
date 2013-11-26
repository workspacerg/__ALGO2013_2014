package Launcher;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import algo_ratp.IHM.Frame;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Runnable r = new Runnable(){
			public void run(){
				new Frame();
			}
			
		};
		
		SwingUtilities.invokeLater(r);
	}

}
