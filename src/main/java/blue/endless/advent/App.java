package blue.endless.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
	
	public static String sneakyData(String name) {
		try {
			return Files.readString(Path.of("src/data/"+name+".txt"));
		} catch (IOException ex) {
			System.out.println("Couldn't get data file for problem "+name);
			System.exit(-1);
		}
		
		return null;
	}
	
	public static void main(String... args) {
		if (args.length != 1) {
			
			System.out.println("usage: AdventOfCode2023 <day><a|b>");
			System.out.println("example: AdventOfCode2023 3a");
			System.exit(-1);
		}
		
		switch(args[0]) {
			case "1a" -> new Day1().a(sneakyData("1a"));
			case "1b" -> new Day1().b(sneakyData("1a"));
		
		
		}
	}
}
