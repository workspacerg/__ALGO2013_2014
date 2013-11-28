package algo_ratp.IHM.tools.copy;

import java.awt.GridLayout;
import java.awt.TextField;
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



public class UserControl_Search extends JPanel
{
	private TextField zoneText;	
	private JWindow windowSearch;
	private JList results;
	private DefaultListModel modelList;
	private AutoCompleteModel model;

	public UserControl_Search(AutoCompleteModel model, TextField t)
	{
		this.model=model;
		zoneText = t;
		modelList = new DefaultListModel();
		results = new JList(modelList);
		results.setBorder(BorderFactory.createEtchedBorder());
		windowSearch = new JWindow();
		windowSearch.add(new JScrollPane(results));
		
		zoneText.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(zoneText.getText()==null || zoneText.getText().length()==0)
				{
					windowSearch.setVisible(false);
				}
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					if(results.getSelectedIndex()<results.getModel().getSize())
					{
						results.setSelectedIndex(results.getSelectedIndex()+1);
					}
					else 
					{
						results.setSelectedIndex(0);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					if(results.getSelectedIndex()!=results.getModel().getSize())
					{
						results.setSelectedIndex(results.getSelectedIndex()-1);
					}
					else {
						results.setSelectedIndex(results.getModel().getSize());
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					zoneText.setText(results.getSelectedValue().toString());
					windowSearch.setVisible(false);
				}
				else 
				{
					update();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
		this.add(zoneText);
	}

	public void update()
	{
		List<String> correspondants = model.getChainesCorrespondates(zoneText.getText());
		modelList.clear();
		if(correspondants.size()==0)
		{
			modelList.addElement("(vide)");
		}
		else
		{
			for(String s : correspondants)
			{
				modelList.addElement(s);
			}
		}
		windowSearch.setBounds((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY()+zoneText.getHeight(), getWidth(), 3*zoneText.getHeight());
		windowSearch.setVisible(true);
		results.setSelectedIndex(0);
	}
}
