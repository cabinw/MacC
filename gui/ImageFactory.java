/********************************************
 * @author cabin
 * @update   2009/11/26
 * @email  cabinw@gmail.com
 * 
 * This class is used to manager the image resource,
 * you can generator a Image object like in this way:
 * Image image = ImageFactory.loadImage(Display.getCurrent(), ImageFactory.HOMEPAGE);
 * and dispose all the resources by calling ImageFactory.dispose() at last
 * 
 * 
 *******************************************/

package com.macc.gui;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory {
	public static final String PATH = "resources//";
	public static final String ICON = "image.gif";
	
	
	private static Hashtable htImage = new Hashtable();
	
	private ImageFactory(){}
	
	/**
	 * 
	 * The second parameter you can get in the properties file
	 * or the static constants of ImageFactory
	 * 
	 * @param display
	 * @param imageName
	 * @return
	 */
	public static Image loadImage(Display display, String imageName){
		Image image = (Image)htImage.get(imageName.toUpperCase());
		if(image == null ){
			image = new Image(display,PATH+imageName);
			htImage.put(imageName.toUpperCase(), image);
		}
		return image;
	}
	
	public static void dispose(){
		Enumeration e=htImage.elements();
		while(e.hasMoreElements()){
			Image image = (Image)e.nextElement();
			if(!image.isDisposed())
				image.dispose();
		}
	}
}
