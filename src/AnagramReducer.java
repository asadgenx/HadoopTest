
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * The Anagram reducer class groups the values of the sorted keys that came in and 
 * checks to see if the values iterator contains more than one word. if the values 
 * contain more than one word we have spotted a anagram.
 * @author subbu
 *
 */

public class AnagramReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
        
        private Text outputKey = new Text();
        private Text outputValue = new Text();

        
        public void reduce(Text anagramKey, Iterator<Text> anagramValues,
                        OutputCollector<Text, Text> results, Reporter reporter) throws IOException {
            /*System.out.println("ANAKEY"+anagramKey);  
            System.out.println("ANAVALUES"+anagramValues);  
            System.out.println("RESULTS"+results);  
            System.out.println("REPORTS"+reporter); */ 
            
        	String output = "";
                while(anagramValues.hasNext())
                {
                        Text anagam = anagramValues.next();
                        output = output + anagam.toString() + "~";
                }
                StringTokenizer outputTokenizer = new StringTokenizer(output,"~");
               // System.out.println("  Output count   ==== "+outputTokenizer.countTokens());
                if(outputTokenizer.countTokens()>=0)
                {
                        output = output.replace("~", ",");
                        outputKey.set(anagramKey.toString());
                        outputValue.set(output);
                        results.collect(outputKey, outputValue);
                }
        }

}