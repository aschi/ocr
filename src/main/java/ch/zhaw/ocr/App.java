package ch.zhaw.ocr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import ch.zhaw.ocr.BitmapParser.BitmapParser;
import ch.zhaw.ocr.BitmapParser.CharacterParser;
import ch.zhaw.ocr.BitmapParser.RowParser;
import ch.zhaw.ocr.BitmapParser.SimpleBitmapParser;
import ch.zhaw.ocr.BitmapParser.WordParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       try {
    	BitmapParser bp = new CharacterParser(new WordParser(new SimpleBitmapParser()));   
    	
    	bp.parse(ImageIO.read(new File("img/debian_line.png")));
    	
    	System.out.println(bp.getMatrix(0));
    	
    	//ContrastMatrix cm = ContrastMatrix.parseImage(ImageIO.read(new File("img/debian_line.png")));
    	
    	//System.out.println(cm);
    	
    	//List<Word> wl = Word.parseWords(cm);
    	
    	//List<Character> chl = Character.parseCharacters(wl.get(wl.size()-1).getMatrix());
    	
    	//for(Character c : chl){
    	//	System.out.println(c.getMatrix());
    	//}
    	
    	//System.out.println(wl.get(wl.size()-1).getMatrix());
    	
    	//for(Word m : wl){
    		//System.out.println(m.getMatrix());
    	//}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
