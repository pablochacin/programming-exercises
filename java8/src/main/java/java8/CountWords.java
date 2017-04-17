package java8;

import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/*
 * Counts the occurrenes of the different words in the text, ignoring case
 * Shows the usage of parallel streams, as well as handling exceptions.
 */
public class CountWords{

    /**
     * Lists files in a directory. If there is an exception accessing 
     * the directoy returns an empty Stream and reports the error.
     *
     * @param p String with the path to the directory
     * @return a Stream of Path with the content of the directory.
     */
	static Path[] listFiles(String path){
		try{
			return  Files.list(Paths.get(path)).filter(Files::isRegularFile).toArray(Path[]::new);
		}catch(IOException e){
			System.err.printf("Error listing files in directory %s: %s",path, e.getMessage());
			return new Path[0];
		}

	}

    /**
     * Reads the content of a text file. If there is an exception 
     * returns an empty stream.
     *
     * @param f Path to the file
     * @return a Stream of String with the content of the file
     */
	static	Function <Path,Stream<String>> lines = (f) -> {
		try {
			return Files.lines(f);
		}catch(IOException e){
			System.err.printf("Error reading from file %s: %s",f,e.getMessage());
			return Stream.empty();
		} 
	};


    /**
     * Counts the occurrences of different words in a set of files pointed by a 
     * path. Prints to stdout a list of words in alphabetical order, with the
     * number of occurences. 
     *
     * @param argv cli arguments. Expects the first one to be a string with the
     *        path to the input files
     */
	public static void main(String[] args){

	       String path = args[0];

               Path[] files = listFiles(path);
  
	       Arrays.stream(files)
                        .parallel()
                        .peek(p->System.err.printf("file %s in thread  %d\n",p,Thread.currentThread().getId()))
			.flatMap(lines)
			.flatMap(s->Arrays.stream(s.split("\\W+")))
			.collect(Collectors.groupingBy(s->s,Collectors.counting()))
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByKey())
			.forEach(e-> System.out.printf("%s %d \n",e.getKey(),e.getValue()));

	}
}
