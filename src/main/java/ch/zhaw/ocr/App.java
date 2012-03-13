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
    	ContrastMatrix cm = ContrastMatrix.parseImage(ImageIO.read(new File("img/debian_line.png")));
    	
    	//System.out.println(cm);
    	
    	List<Word> wl = Word.parseWords(cm);
    	
    	List<Character> chl = Character.parseCharacters(wl.get(wl.size()-1).getMatrix());
    	
    	for(Character c : chl){
    		System.out.println(c.getMatrix());
    	}
    	
    	//System.out.println(wl.get(wl.size()-1).getMatrix());
    	
    	for(Word m : wl){
    		//System.out.println(m.getMatrix());
    	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
