import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Duplicate {
	
	public static void main(String[] args) {

String[] data = {"A", "C", "B", "D", "A", "B", "E", "D", "B", "C"};


List<String> list = Arrays.asList(data);
Set<String> set = new HashSet<String>(list);
System.out.print("Remove duplicate result: ");

String[] result = new String[set.size()];
set.toArray(result);
for(String s : result) {
    System.out.println(s + ", ");
	}
}
}
