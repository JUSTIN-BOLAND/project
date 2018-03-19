/**  
* @Title: ImageUtils.java
* @Package com.yiqi.util
* @Description: 深圳市得壹科技有限公司
*/ 
package com.deyi.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Description: TODO
 * @author hejx
 * @date 2015年10月31日 
 *
 */
public class ImageUtils {

	public static double DEFAULT_SMALL_IMG_WIDTH = 167.0D;
	  public static double DEFAULT_BIG_IMG_WIDTH = 1000.0D;
	  public static double SCALE = 1.0D;

	  public static void main(String[] args)
	    throws Exception
	  {
	    File file = new File("D://test/2016092417220233.jpg");

	    InputStream stream = new FileInputStream(file);
	    drawMiniImage(stream,"D://test/2.jpg",1000,2000);
	  }

	  @SuppressWarnings("restriction")
	public static void drawMiniImage(InputStream stream, String deskURL, double comHeight, double comWidth)
	    throws Exception
	  {
	    Image src = ImageIO.read(stream);
	    int srcHeight = src.getHeight(null);
	    int srcWidth = src.getWidth(null);
	    int deskHeight = 0;
	    int deskWidth = 0;
	    double srcScale = srcHeight / srcWidth;
	    double comScale = comHeight / comWidth;
	    if ((comHeight <= 0.0D) || (comWidth <= 0.0D)) {
	      deskHeight = srcHeight;
	      deskWidth = srcWidth;
	    } else if (srcScale > comScale) {
	      deskHeight = (int)comHeight;
	      deskWidth = srcWidth * deskHeight / srcHeight;
	    } else {
	      deskWidth = (int)comWidth;
	      deskHeight = srcHeight * deskWidth / srcWidth;
	      if (deskHeight > comHeight) {
	        deskHeight = (int)comHeight;
	        deskWidth = srcWidth * deskHeight / srcHeight;
	      }

	    }

	    BufferedImage tag = new BufferedImage(deskWidth, deskHeight, 
	      5);
	    tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null);
	    FileOutputStream deskImage = new FileOutputStream(deskURL);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
	    encoder.encode(tag);
	    deskImage.close();
	    stream.close();
	  }

}
