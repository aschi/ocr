package ch.zhaw.ocr.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TextFileParser is used to easily parse textfiles
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class TextFileParser {

	/**
	 * Parses a textfile and returns its contents as string. removes punctuations and other stuff
	 * @param f file
	 * @return String containing the contents of the given file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public String parseFile(File f) throws IOException,
			FileNotFoundException {
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		StringBuffer contents = new StringBuffer();
		
		try {
			String line = null; // not declared within while loop
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(" ");
			}
		} finally {
			input.close();
		}
		
		
		//Remove punctuations / generate word list
		return contents.toString().toLowerCase().replaceAll("\\p{Punct}", " ").replaceAll("\\d", " ").replaceAll("  ", " ").replaceAll("«","").replaceAll("ß", "ss").replaceAll("»", "");
	}

}
