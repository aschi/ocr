package ch.zhaw.ocr.dictionary;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.dictionary.Dictionary;

public class DictionaryTest {

	Dictionary dict;
	
	@Before
	public void setUp() throws Exception {
		dict = new Dictionary("debug");
		
		List<String> testList = new LinkedList<String>();
		testList.add("hallo");
		testList.add("hallo");
		testList.add("halle");
		testList.add("guter");
		
		dict.addToDicitionary(testList);
	}

	@Test
	public void correctWordTest(){
		String testInput = "quter";
		String expectedOutput = "guter";
		String output = dict.correctWord(testInput);
		
		assertTrue(expectedOutput.equals(output));
	}
	
	@Test
	public void dontCorrectWordTest(){
		String testInput = "halle";
		String output = dict.correctWord(testInput);
		
		assertTrue(testInput.equals(output));
	}
	
	@Test
	public void unknownCharTest(){
		String testInput = Properties.unknownChar+"alle";
		String expectedOutput = "halle";
		String output = dict.correctWord(testInput);
		
		assertTrue(expectedOutput.equals(output));
	}
	
	@Test
	public void capitalLetterTest(){
		String testInput = "Hallc";
		String expectedOutput = "Hallo";
		String output = dict.correctWord(testInput);
		
		assertTrue(expectedOutput.equals(output));
	}
	
	@Test
	public void firstSignNonAlphanumericTest(){
		String testInput = "«Hallc";
		String expectedOutput = "«Hallo";
		String output = dict.correctWord(testInput);
		
		assertTrue(expectedOutput.equals(output));
	}
	
	@Test
	public void lastSignNonAlphanumericTest(){
		String testInput = "quter.";
		String expectedOutput = "guter.";
		String output = dict.correctWord(testInput);
				
		assertTrue(expectedOutput.equals(output));
	}
}
