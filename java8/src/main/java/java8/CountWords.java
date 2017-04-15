package java8;

import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Counts the occurrenes of the different words in the text, ignoring case
 */
public class CountWords {

public static void main(String[] args){
  
   BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

   in.lines()
        .flatMap(s->Arrays.stream(s.split("\\W+")))
        .map(String::toLowerCase)
        .collect(Collectors.groupingBy(s->s,Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(e-> System.out.printf("%s %d \n",e.getKey(),e.getValue()));

}
}
