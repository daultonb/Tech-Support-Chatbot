package Spellcheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//import com.inet.jortho.*;

import GUI.GUI;


public class SpellCheck {
	
	File dictFile = new File("dictionary_en_2013_03//TechBot_dictionary.txt");
	ArrayList<String> dict = new ArrayList<>();
	GUI gui;
	
	public SpellCheck(GUI gui) {
		try {
		this.gui = gui;
		Scanner in = new Scanner(dictFile);
		while(in.hasNext()) {
			dict.add(in.next());
		}
		in.close();
		}catch(FileNotFoundException e) {
			System.out.println("Dictionary could not be found.");
		}
	}
	
	public String checkSpelling(String word){
		String closestMatch = "";
		String lword = word.toLowerCase();
		char[] letters = lword.toCharArray();
		ArrayList<Integer> numMatches = new ArrayList<>(dict.size());
		
		int index = 0;
		int mostMatches = 0;
		for(String current: dict) {
			current = current.toLowerCase();
			char[] currLetters = current.toCharArray();
			Integer num = 0;
			int length = (word.length()>current.length()? current.length():word.length());
			numMatches.add(num);
			for(int i=0; i<length; i++) {
				if(letters[i]==currLetters[i]) {
					numMatches.set(index, ++num);
				}
			}
			index++;
		}
		for(int matches: numMatches) {
			if(mostMatches< matches) {
				mostMatches = matches;
			}
		}
		int halflength = (word.length()%2==0? word.length()/2 : (word.length()/2)+1);
		if(mostMatches==word.length() || mostMatches <= halflength) {
			return word;
		}
		else {
		closestMatch = dict.get(numMatches.indexOf(mostMatches));
		System.out.printf("Closest word was %s with %d matches to %s\n",closestMatch, mostMatches, word);
		gui.setBotOutput("Autocorrected "+word+" to "+closestMatch);
		return closestMatch;
		}
	}
	
	
	
}
