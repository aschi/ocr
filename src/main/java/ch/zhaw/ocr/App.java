package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       try {
    	ContrastMatrix cm = ContrastMatrix.parseImage(ImageIO.read(new File("img/D.png")));
    	
    	System.out.println(cm);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
