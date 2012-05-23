package ch.zhaw.ocr.gui.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ch.zhaw.ocr.Property;

public class TextFileHandler {

	public static void saveTextFile(File datei, String text) throws IOException {
		
		String fileName = datei.getName();

		String filepath = datei.getAbsolutePath();
		if (!filepath.endsWith(".txt"))
		{
			filepath += ".txt";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(Property.historyResourcefoler + "/" + fileName));
		writer.write(text);
		writer2.write(text);
		writer.close();
		writer2.close();

	}

	public static String readTextFile(String datei) throws IOException {
		File file = new File(datei);

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
