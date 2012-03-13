package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    	ContrastMatrix cm = ContrastMatrix.parseImage(ImageIO.read(new File("img/debian_word.png")));
    	
    	System.out.println(cm);
    	
    	List<Character> chm = Character.parseWord(cm);
    	for(Character m : chm){
    		System.out.println(m.getMatrix());
    	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
