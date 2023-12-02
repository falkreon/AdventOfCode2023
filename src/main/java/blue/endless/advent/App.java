package blue.endless.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class App {
	
	public static final List<Day> DAYS = List.of(
			new Day1(), new Day2()
			);
	
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
		boolean useTestData = false;
		if (args.length == 2 && args[1].equals("--testdata")) {
			useTestData = true;
		} else if (args.length != 1) {
			
			System.out.println("usage: AdventOfCode2023 <day><a|b>");
			System.out.println("example: AdventOfCode2023 3a");
			System.exit(-1);
		}
		
		if (args[0].endsWith("a")) {
			//Part 1
			int day = Integer.valueOf(args[0].substring(0,args[0].length()-1));
			if (day < 1 || day > DAYS.size()) {
				System.out.println("Invalid day: "+day);
				System.exit(-1);
				return;
			}
			
			System.out.println("Day " + day + " Part 1:");
			Day dayObject = DAYS.get(day - 1);
			
			String data = (useTestData) ? dayObject.getSampleA() : sneakyData(args[0]);
			
			dayObject.a(data);
		} else if (args[0].endsWith("b")) {
			//Part 2
			int day = Integer.valueOf(args[0].substring(0,args[0].length()-1));
			if (day < 1 || day > DAYS.size()) {
				System.out.println("Invalid day: "+day);
				System.exit(-1);
				return;
			}
			
			System.out.println("Day " + day + " Part 2:");
			Day dayObject = DAYS.get(day-1);
			
			String data = (useTestData) ? dayObject.getSampleB() : (dayObject.sameDataForB() ? sneakyData(day+"a") : sneakyData(args[0])) ;
			
			dayObject.b(data);
		} else {
			System.out.println("No part specified. Running part 1.");
			
		}
		
		switch(args[0]) {
			case "1a" -> new Day1().a(sneakyData("1a"));
			case "1b" -> new Day1().b(sneakyData("1a"));
		
		
		}
	}
}
