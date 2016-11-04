package damdariar.gui.editor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.hibernate.Hibernate;

import damdariar.beans.PropertyMetaData;
import damdariar.util.Util;

public class DImageEditor extends JButton implements EditorI,ActionListener{

	private PropertyMetaData propertyMetaData;
	private int sequenceNo;
	private static final long serialVersionUID = 1L;
	static int Width  = 150;
	static int Height = 150;
	Image   img ;
	String  fileExtension;
	Blob    value;

	DImageEditor(){
		
		setPreferredSize(new Dimension(Width,Height));
		addActionListener(this);
//		addMouseListener((MouseListener) this);
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(new Dimension(Width,Height));
	}

	@Override
	public Object getValue() {
		if(fileExtension == null && value != null)
			return  value;
		if(img != null){
			try{
			/*ImageIcon ico = (new ImageIcon(img));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			BufferedImage bfImage = new BufferedImage(ico.getIconWidth(),ico.getIconHeight(),BufferedImage.TYPE_INT_RGB);*/
			ByteArrayOutputStream out = new ByteArrayOutputStream();	
			ImageIO.write(toBufferedImage(img),"jpeg" , out);
			return (value = Hibernate.createBlob(out.toByteArray()));
			}catch(Exception e){
				return null;
			}
		}
		else
			return null;
		
		
	}
	
	// This method returns a buffered image with the contents of an image
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
    
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
    
        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent Pixels
        boolean hasAlpha = hasAlpha(image);
    
        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }
    
            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
    
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
    
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
    
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
    
        return bimage;
    }
    
    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }
    
        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
         PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }
    
        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }
	
	public byte[] serializeObjectToBytearray(Object o) {
	    byte[] array;
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(o);
	      array = baos.toByteArray();
	    }
	    catch (IOException ioe) {
	      ioe.printStackTrace();
	      return null;
	    }
	    return array;
	  }

	public PropertyMetaData getEditorProperty() {
		// TODO Auto-generated method stub
		return this.propertyMetaData;
	}

	public void setEditorProperty(PropertyMetaData property) {
		// TODO Auto-generated method stub
		this.propertyMetaData = property;
		
	}

	@Override
	public int getEditorSequence() {
		// TODO Auto-generated method stub
		return sequenceNo;
	}

	@Override
	public void setEditorSequence(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}


	@Override
	public void setValue(Object value) {
		if(value != null){
			try {
				this.value = (Blob) value;
				BufferedImage buffImage = ImageIO.read(new ByteArrayInputStream(Util.toByteArray((Blob) value)));
				setImage(buffImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
		  this.value = null;
		  this.fileExtension = null;
		  this.img = null;
		  setIcon(null);	
		}
	}
	
	



		
	public void setImage(BufferedImage img){
		Image image = img.getScaledInstance(Width, Height, BufferedImage.SCALE_DEFAULT);
		setIcon(new ImageIcon(image));
		this.img  = img.getScaledInstance(img.getWidth(), img.getHeight(), BufferedImage.SCALE_DEFAULT);
		
	}
	
	public void setImage(String fileName,Image img){
		Image image = img.getScaledInstance(Width, Height, BufferedImage.SCALE_DEFAULT);
		setIcon(new ImageIcon(image));
		this.img = img;
		this.fileExtension = fileName.substring(fileName.lastIndexOf('.')+1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		ImagePreviewPanel preview = new ImagePreviewPanel();
		chooser.setAccessory(preview);
		chooser.addPropertyChangeListener(preview);
		int result = chooser.showOpenDialog(this);
        if (result == chooser.APPROVE_OPTION){
            String filename = chooser.getSelectedFile().getAbsolutePath();
            Image image = Toolkit.getDefaultToolkit().getImage(filename);
            if(image != null)
            	setImage(filename,image);
        }
		
	}
}
