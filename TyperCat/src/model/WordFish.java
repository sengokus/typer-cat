package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordFish {
  
  private List<String> words;
  private Random random;
 
public WordFish(String fileName) {

    words = new ArrayList<>();
    random = new Random();


    try {

        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
      while ((line = reader.readLine()) != null) {
          words.add(line);
      }
      reader.close();

    } catch (FileNotFoundException e) {
      System.err.println("Could not find file: " + fileName);
      words = Collections.emptyList();
      System.exit(0);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
}
    public String getRandomWord() {
	  // Check if the words list is empty
	  if (words.isEmpty()) {
	    // If the words list is empty, return null
	    return null;
	  }
	  
	  // Generate a random index in the range [0, words.size())
	  int index = random.nextInt(words.size());
	  
	  // Return the word at the randomly generated index
	  return words.get(index);
		}
}
