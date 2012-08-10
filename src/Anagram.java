

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;



public class Anagram {

  public static void main(String[] args) {
    JobClient client = new JobClient();
    JobConf conf = new JobConf(WordCount.class);

    // specify output types
    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(Text.class);

    // specify input and output dirs
    FileInputFormat.setInputPaths(conf, new Path("anagram"));
    FileOutputFormat.setOutputPath(conf, new Path("output" + System.currentTimeMillis()));

    // specify a mapper
    conf.setMapperClass(AnagramMapper.class);

    // specify a reducer
    conf.setReducerClass(AnagramReducer.class);
    conf.setCombinerClass(AnagramReducer.class);

    client.setConf(conf);
    try {
      JobClient.runJob(conf);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
