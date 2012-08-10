import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordCountReducer extends MapReduceBase implements Reducer<Text, Text, Text, IntWritable> 
{

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
	
  public void reduce(Text key, Iterator values,OutputCollector output, Reporter reporter) throws IOException
  {
	  	
    String sum = "";
    
    
    while (values.hasNext()) 
    {
    	
      Text value = (Text) values.next();
      sum = value+","+sum; // process value
     
    }
    
    String words[]=sum.split(",");
    List<String> list = Arrays.asList(words);
    Set<String> set = new HashSet<String>(list);
   

    String[] result = new String[set.size()];
    set.toArray(result);
    
    
    sum = implodeArray(result,",");
    
    output.collect(key, new Text(sum));
  }
  
}