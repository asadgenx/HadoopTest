import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

class testing
{
	public void mytesting()
	{
		System.out.println("Testing dsfuasoif");
	}
	
	public static String implodeArray(String[] inputArray, String glueString) {

		/** Output variable */

		String output = "";
	
		 
	
		if (inputArray.length > 0) {

		    StringBuilder sb = new StringBuilder();

		    sb.append(inputArray[0]);
	
		 
	
		    for (int i=1; i<inputArray.length; i++) {
		
		        sb.append(glueString);
	
		        sb.append(inputArray[i]);
	
		    }
	
	
		    output = sb.toString();

		}

		 
	
		return output;
		
		}

	public String punctuation(String x)
	{
		 String[] punctuationMarks = {"\"", "'", ".",":",";",","};
		for(int i=0;i<punctuationMarks.length;i++)
 	    {
 	    x= x.replace( punctuationMarks[i],"");
 	   
 	    }
		return x;
	}
	 public static ArrayList readFile(String fileName) {
		  String line = "";
		  ArrayList data = new ArrayList();//consider using ArrayList<int>
		  try {
		   FileReader fr = new FileReader(fileName);
		   BufferedReader br = new BufferedReader(fr);//Can also use a Scanner to read the file
		   while((line = br.readLine()) != null) {
		 
		    data.add(line);
		   }
		  }
		  catch(FileNotFoundException fN) {
		   fN.printStackTrace();
		  }
		  catch(IOException e) {
		   System.out.println(e);
		  }
		  return data;
		 } 
	 public static String stopWordsRemover(String str)
		{	
			
			List<String> list =new ArrayList<String>(readFile("/home/asadgenx/workspace/testingHadoop/src/stopWords.txt"));
	 	    String [] stopWords = list.toArray(new String[list.size()]);
	 	   for(int i=0;i<stopWords.length;i++)
		    {
	 		  str= str.replaceAll(stopWords[i],"");
		   
		    }
			return str;
		}
	 public static String Dictwords(String str)
		{	
			
			List<String> list =new ArrayList<String>(readFile("/usr/share/dict/words"));
	 	    String [] stopWords = list.toArray(new String[list.size()]);
	 	   for(int i=0;i<stopWords.length;i++)
		    {
	 		  if(stopWords[i]==str);
	 		  {
	 			 System.out.println(str);
	 			  break;
	 		  }
	 		  
		   
		    }
			return str;
		}
	 public String replaceWordsInSentence(String sentence, String oldWord, String newWord) {  
	        return sentence.replaceAll(oldWord , newWord);  
	    }  
	public static String replaceAllWords2(String original, String find, String replacement) {
		    StringBuilder result = new StringBuilder(original.length());
		    String delimiters = "+-*/(),. ";
		    StringTokenizer st = new StringTokenizer(original, delimiters, true);
		    while (st.hasMoreTokens()) {
		        String w = st.nextToken();
		        if (w.equals(find)) {
		            result.append(replacement);
		        } else {
		            result.append(w);
		        }
		    }
		    return result.toString();
		}
	
}

public class cmd
{		
		
       public static final void main(String args[])
       {
    	   String[] stringArray = {"is", "String2", "String3"};
    	   testing m= new testing();
    	  // System.out.println(m.implodeArray(stringArray, ","));
    	    String str="Today, is a \"bi'g day.Its all about nothing";
    	//   System.out.println(m.punctuation(str));
    	   
    	  
    	  //  System.out.println(m.stopWordsRemover(str));
    	    String oldWord = "so";  
    	    String newWord = "newWord";  
    	    String sentence = "roted";  
    	    String newSentence = m.Dictwords(sentence);  
    	      //System.out.println(newSentence);
    	    
    	    
    	  

    	      
        }
} 