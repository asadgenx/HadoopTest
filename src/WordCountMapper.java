import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.FileSplit;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


public class WordCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>
{

 
  private Text word = new Text();
  
  //Function to remove all the occurences of the punctuation marks
  
  public String punctRemover(String x)
	{
		 String[] punctuationMarks = {"\"", "'", ".",":",";",",","´","!","?","...","(",")","[","]","-","_","~","/","+"};
		for(int i=0;i<punctuationMarks.length;i++)
	    {
	    x= x.replace(punctuationMarks[i],"");
	   
	    }
		return x;
	}
  
  // Function to Read the stopWords.txt file and convert it into array
  
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
  // function to replace the entire occurance of the stop word not (done in word wise not in character wise)
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
  // Function to filter the stop words
	public static String stopWordsRemover(String str, String[] stopList )
	{	
		
		
 	   for(int i=0;i<stopList.length;i++)
	    {
 		  str= replaceAllWords2(str, stopList[i], "");
	   
	    }
		return str;
	}
	//function to get only dictionary words
	public static String dictWordsOnly(String str, String[] dictList )
	{	
		
		for(int i=0;i<dictList.length;i++)
	    {
			
 		  if(dictList[i].toLowerCase().equals(str))
 		  {
 			return str;
 		  }
 		  
	    }
		  return "";
		  
		
	}
@Override
public void map(LongWritable arg0, Text arg1,
		OutputCollector<Text, Text> arg2, Reporter arg3)
		throws IOException {
	// TODO Auto-generated method stub
	String filename = new String();
	String filex = new String();
	String line = arg1.toString();
    StringTokenizer itr = new StringTokenizer(line.toLowerCase());
    //loading the stoplist
    List<String> list =new ArrayList<String>(readFile("/home/asadgenx/workspace/testingHadoop/src/stopWords.txt"));
	String [] stopWords = list.toArray(new String[list.size()]);
	//dictionary words
	 List<String> list2 =new ArrayList<String>(readFile("/usr/share/dict/words"));
		String [] dictWords = list2.toArray(new String[list2.size()]);
	   while(itr.hasMoreTokens()) {
    	
    	filename =  ((FileSplit)arg3.getInputSplit()).getPath().toString();
    	String[] partsCollArr;
        String delimiter = "/";  
        partsCollArr = filename.split(delimiter); 
        filex=partsCollArr[6];
        Text one = new Text(filex);
        
    	arg1.set(itr.nextToken());
    	
    	//removing the punctuation marks
    	arg1=new Text(punctRemover(arg1.toString()));
    	
    	//Just stop words
    	
    	/*String check = stopWordsRemover(arg1.toString(),stopWords);
    	
    	
    	
    	if(check=="")
    	{
    		continue;
    	}
    	else
    	{
    	arg1=new Text(check);
    	arg2.collect(arg1, one);
    	   	
    	}*/
    	
    	
    	//dictionary words without stopwords
    	
    	/*String dictcheck = dictWordsOnly(arg1.toString(),dictWords);
    	
    	if(dictcheck!="")
    	{
    		arg1=new Text(dictcheck);
    		
    		arg2.collect(arg1, one);
    	}*/
    	
    	
    	
    	//removing the stopwords and dictionary words
    	
    	String check = stopWordsRemover(arg1.toString(),stopWords);
    	
    	
    	
    	if(check=="")
    	{
    		continue;
    	}
    	else
    	{
    	arg1=new Text(check);
    	
    	String dictcheck = dictWordsOnly(arg1.toString(),dictWords);
    	
    	if(dictcheck!="")
    	{
    		arg1=new Text(dictcheck);
    		
    		arg2.collect(arg1, one);
    	}
    	
    	}
    	
  	
    }
}
}