package ch.zhaw.ocr.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
	//HashMap representing the dictionary
	//Key: Word (String)
	//Value: Emphasis (Integer)
	private HashMap<String, Integer> dictionary;
	private final char[] alphabet = new String("abcdefghijklmnopqrstuvwxyzäöü").toCharArray();
	
	/**
	 * 
	 * @param dictFile
	 * @throws IOException
	 */
	public Dictionary(File dictFile) throws IOException{
		buildDictionaryFromFile(dictFile);
	}
	
	/**
	 * This method 
	 * @param word
	 * @return
	 */
	public String correctWord(String word){
		String rv = null;
		
		//is the given word in our dictionary?
		//if yes return the word
		if(dictionary.containsKey(word)){
			//adjust probability in dictionary
			dictionary.put(word, dictionary.get(word)+1);
			return word;
		}
		
		//build a list of all words with distance 1
		//search these words
		Set<String> distance1 = editDistance1(word);
		Set<String> distance1InDict = findDictionaryEntries(distance1);
		
		rv = getBestWord(distance1InDict);
		if(rv!=null){
			return rv;
		}
		
		//build a list of all words with distance 2
		//search these words
		Set<String> distance2 = editDistance2(distance1);
		Set<String> distance2InDict = findDictionaryEntries(distance2);
		
		rv = getBestWord(distance2InDict);
		if(rv!=null){
			return rv;
		}
		
		//if no correction was found, just return the input word
		return word;
	}
	
	private String getBestWord(Set<String> wordList){
		int max = 0;
		String bestWord = null;
		
		for(String c : wordList){
			if(dictionary.get(c) > max){
				max = dictionary.get(c);
				bestWord = c;
			}
		}
				
		return bestWord;
	}
	
	
	
	private Set<String> editDistance1(String word){
		Set<String> rv = new HashSet<String>();
		
		char[] warray = word.toCharArray();
		
		//replace each character of the word with each character of our given alphabet
		for(int i = 0;i < warray.length;i++){
			for(char c : alphabet){
				char[] copy = warray.clone();
				copy[i] = c;
				rv.add(String.valueOf(copy));
			}
		}
		
		return rv;	
	}
	
	/**
	 * Builds a list of
	 * @param distance1
	 * @return
	 */
	private Set<String> editDistance2(Set<String> distance1){
		Set<String> rv = new HashSet<String>();
		
		for(String word : distance1){
			rv.addAll(editDistance1(word));
		}
		
		return rv;
	}
	
	/**
	 * Searches the dictionary for all words in the input set. Return a set of words contained in the dictionary.
	 * @param searchList list of word variants
	 * @return subset of the input set. Contains only words which are in the "dictionary" set
	 */
	private Set<String> findDictionaryEntries(Set<String> searchList){
		Set<String> rv = new HashSet<String>();
		
		for(String word : searchList){
			if(dictionary.containsKey(word)){
				rv.add(word);
			}
		}
		
		return rv;
	}
	
	/**
	 * Builds a dictionary tree set
	 * @param f File containing a list of words separated by linebreaks
	 * @throws IOException
	 */
	private void buildDictionaryFromFile(File f) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		dictionary = new HashMap<String, Integer>();
		
		String word;
		while ((word = br.readLine()) != null) {
			//word = word.toLowerCase();
			if(dictionary.containsKey(word)){
				dictionary.put(word, dictionary.get(word)+1);
			}else{
				dictionary.put(word, 1);
			}
		}
	}
	
}
