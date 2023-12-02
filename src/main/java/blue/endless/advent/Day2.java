package blue.endless.advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 implements Day {
	private static final String EXAMPLE1 =
			"""
			Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
			Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
			Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
			Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
			Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
			""";
	
	private static final String EXAMPLE2 = EXAMPLE1;
	
	public static class BlockGame {
		public int id;
		public Map<String, Integer> blocksShown = new HashMap<>();
		public boolean isPossibleWithMinimums(int red, int green, int blue) {
			int minRed = blocksShown.getOrDefault("red", 0);
			int minGreen = blocksShown.getOrDefault("green", 0);
			int minBlue = blocksShown.getOrDefault("blue", 0);
			
			return minRed <= red && minGreen <= green && minBlue <= blue;
		}
		
		public String toString() {
			return "Game "+id+": "+blocksShown.toString();
		}
		
		public int getPower() {
			int minRed = blocksShown.getOrDefault("red", 0);
			int minGreen = blocksShown.getOrDefault("green", 0);
			int minBlue = blocksShown.getOrDefault("blue", 0);
			
			return minRed * minGreen * minBlue;
		}
	}
	
	/**
	 * Most of this solution was parsing. I spent a LOT of time parsing this.
	 */
	@Override
	public void a(String input) {
		List<BlockGame> games = new ArrayList<>();
		List<Integer> passingGames = new ArrayList<>();
		
		String[] lines = input.split("\n");
		
		for(String line : lines) {
			if (line.isBlank()) continue;
			BlockGame game = new BlockGame();
			if (line.startsWith("Game ")) line = line.substring("Game ".length());
			int colonIndex = line.indexOf(':');
			if (colonIndex == -1) throw new IllegalArgumentException();
			int gameNumber = Integer.valueOf(line.substring(0, colonIndex));
			game.id = gameNumber;
			line = line.substring(colonIndex + 1).trim();
			
			String[] rounds = line.split(";");
			for(String round : rounds) {
				String[] blocksShown = round.split(",");
				for(String blockDesc : blocksShown) {
					String[] pieces = blockDesc.trim().split(" ");
					int numBlocks = Integer.valueOf(pieces[0].trim());
					String color = pieces[1].trim();
					
					//Actual problem meat
					int existingBlocks = game.blocksShown.getOrDefault(color, 0);
					if (numBlocks > existingBlocks) game.blocksShown.put(color, numBlocks);
				}
			}
			System.out.println("Game " + gameNumber + ": " + game.blocksShown);
			//System.out.println("   "+game.isPossibleWithMinimums(12, 13, 14));
			if (game.isPossibleWithMinimums(12, 13, 14)) passingGames.add(game.id);
			games.add(game);
		}
		
		System.out.println("Passing games: "+passingGames);
		System.out.println("Sum of Ids: "+passingGames.stream().mapToInt(Integer::intValue).sum());
	}

	@Override
	public void b(String input) {
		List<BlockGame> games = new ArrayList<>();
		long sumOfPowers = 0L;
		
		String[] lines = input.split("\n");
		
		for(String line : lines) {
			if (line.isBlank()) continue;
			BlockGame game = new BlockGame();
			if (line.startsWith("Game ")) line = line.substring("Game ".length());
			int colonIndex = line.indexOf(':');
			if (colonIndex == -1) throw new IllegalArgumentException();
			int gameNumber = Integer.valueOf(line.substring(0, colonIndex));
			game.id = gameNumber;
			line = line.substring(colonIndex + 1).trim();
			
			String[] rounds = line.split(";");
			for(String round : rounds) {
				String[] blocksShown = round.split(",");
				for(String blockDesc : blocksShown) {
					String[] pieces = blockDesc.trim().split(" ");
					int numBlocks = Integer.valueOf(pieces[0].trim());
					String color = pieces[1].trim();
					
					//Actual problem meat
					int existingBlocks = game.blocksShown.getOrDefault(color, 0);
					if (numBlocks > existingBlocks) game.blocksShown.put(color, numBlocks);
				}
			}
			System.out.println("Game " + gameNumber + ": " + game.blocksShown + " power: "+game.getPower());
			
			sumOfPowers += game.getPower();
			games.add(game);
		}
		
		System.out.println("Sum of Powers: "+sumOfPowers);
	}
	
	
	@Override
	public String getSampleA() {
		return EXAMPLE1;
	}
	
	@Override
	public String getSampleB() {
		return EXAMPLE2;
	}

	@Override
	public boolean sameDataForB() {
		return true;
	}
}
