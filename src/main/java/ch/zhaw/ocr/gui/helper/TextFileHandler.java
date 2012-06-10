package ch.zhaw.ocr.gui.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ch.zhaw.ocr.Properties;

/**
 * Helper used to handle textfiles
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 *
 */
public class TextFileHandler {

	/**
	 * Saves a text into a textfile
	 * @param file file to be filled
	 * @param text text to be saved
	 * @throws IOException
	 */
	public static void saveTextFile(File file, String text) throws IOException {
		String fileName = file.getName();

		String filepath = file.getAbsolutePath();
		if (!filepath.endsWith(".txt"))
		{
			filepath += ".txt";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(Properties.historyResourcefoler + "/" + fileName));
		writer.write(text);
		writer2.write(text);
		writer.close();
		writer2.close();

	}

	/**
	 * Reads a textfile and returns its contents as a string
	 * @param fileName file name of the textfile
	 * @return content of the given textfile
	 * @throws IOException
	 */
	public static String readTextFile(String fileName) throws IOException {
		File file = new File(fileName);

		if (!file.canRead() || !file.isFile())
			System.exit(0);

		FileReader fr = null;
		int c;
		StringBuffer buff = new StringBuffer();
		fr = new FileReader(file);
		while ((c = fr.read()) != -1) {
			buff.append((char) c);
		}
		fr.close();
		return buff.toString();

	}

}
