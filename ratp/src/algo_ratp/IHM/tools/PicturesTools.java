package algo_ratp.IHM.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;


public class PicturesTools extends JComponent 
{
    private BufferedImage myPhoto;
    private File myFilePicture;
    private double myPictureRate;
    
	public PicturesTools() 
	{
	      setSize(150, 150);
	      setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public static File createFichierImage(String aPath, String filePictureName) 
    {
		File aFilePicture = null;
		aFilePicture= new File(aPath, filePictureName); 
		return aFilePicture;
    }
	
	public void setFichierImage(String aFilePicture) 
    {
	     try 
	     {
		     myPhoto = ImageIO.read(getClass().getResource("/"+aFilePicture+".JPG"));
		     myPictureRate = (double)myPhoto.getWidth()/myPhoto.getHeight();
		     //setSize(getWidth(), (int)(getWidth()/myPictureRate));
		     repaint();      
	     } 
	     catch (IOException ex) 
	     { 
	    	 //CreerUneDialogResult comme en C#
	     }
    }
	
	public File getFichierImage() 
	{
		return myFilePicture;
	}
 
    

    @Override
    public void setBounds(int x, int y, int width, int height) 
    {
    	 if (myPhoto==null) 
         {
    		 super.setBounds(x, y, width, height);
         }
         else
         {
        	 super.setBounds(x, y, width, (int)(width/myPictureRate));
         }
    }
   
    @Override
    protected void paintComponent(Graphics g) 
    {
         if (myPhoto!=null)
         {
            g.drawImage(myPhoto, 0, 0, getWidth(), (int)(getWidth()/myPictureRate), null);
         }
    }   
}