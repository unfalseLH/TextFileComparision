import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class ComparisionTrial{
    Map<String, Integer> wordMap = new HashMap<String, Integer>();

    public void incCount(String word) {
        Integer oldCount = wordMap.get(word);
        wordMap.put(word, oldCount == null ? 1 : oldCount + 1);
    }

    double getCosineSimilarityWith(ComparisionTrial otherVector) {
        double innerProduct = 0;
        for(String w: this.wordMap.keySet()) {
            innerProduct += this.getCount(w) * otherVector.getCount(w);
        }
        return innerProduct / (this.getNorm() * otherVector.getNorm());
    }

    double getNorm() {
        double sum = 0;
        for (Integer count : wordMap.values()) {
            sum += count * count;
        }
        return Math.sqrt(sum);
    }

    int getCount(String word) {
        return wordMap.containsKey(word) ? wordMap.get(word) : 0;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
	
	System.out.println("File1:");
	String file1 = reader.next();
        String doc1 = readStringFromFile(file1);
	System.out.println("File2:");
	String file2 = reader.next();
	String doc2 = readStringFromFile(file2);

        ComparisionTrial v1 = new ComparisionTrial();
        for(String w:doc1.split("[^a-zA-Z]+")) {
            v1.incCount(w);
        }

        ComparisionTrial v2 = new ComparisionTrial();
        for(String w:doc2.split("[^a-zA-Z]+")) {
            v2.incCount(w);
        }

        System.out.println("Similarity = " + v1.getCosineSimilarityWith(v2)*100 + "%");
    }
	 public static String readStringFromFile(String filename)
        {
     String builder = "";
     try
     {
         Scanner fileReader = new Scanner(new File(filename));
         while (fileReader.hasNextLine())
         {
      builder = builder + fileReader.nextLine() + "\n";
         }

         return builder;
     }
     catch (Exception e)
     {
         System.out.println("An error occurred while trying to open the file " + filename + ". Is the file located inside the same folder as the .class file and with the identical name?");
         return null;
     }
        }

}

 
