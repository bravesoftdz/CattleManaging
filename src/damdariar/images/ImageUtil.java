package damdariar.images;

import javax.swing.ImageIcon;

public class ImageUtil {
	
	public static ImageIcon getImageIcon(String name){
		
		return new ImageIcon(ImageUtil.class.getResource(name));
	}

}
