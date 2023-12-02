package blue.endless.advent;

public class Day1 implements Day {
	public static final String EXAMPLE1 =
			"""
			1abc2
			pqr3stu8vwx
			a1b2c3d4e5f
			treb7uchet
			""";
	
	public static final String[] NUMBER_STRINGS = {
		"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
	};
	
	public static int firstAndLastDigit(String s) {
		int firstDigit = -1;
		int lastDigit = -1;
		
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				if (firstDigit == -1) firstDigit = Character.digit(ch, 10);
				lastDigit = Character.digit(ch, 10);
			}
		}
		
		return Integer.valueOf("" + firstDigit + lastDigit);
	}
	
	public static int firstAndLastDigitWithWords(String s) {
		int firstIndex = -1;
		int firstDigit = 0;
		int lastIndex = -1;
		int lastDigit = 0;
		
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				if (firstIndex == -1) {
					firstIndex = i;
					firstDigit = Character.digit(ch, 10);
				}
				lastIndex = i;
				lastDigit = Character.digit(ch, 10);
			}
		}
		
		//See if words preempt our existing indices
		for(int i=0; i<NUMBER_STRINGS.length; i++) {
			int index = s.indexOf(NUMBER_STRINGS[i]);
			if (index > -1 && index < firstIndex) {
				firstIndex = index;
				firstDigit = i;
			}
			
			index = s.lastIndexOf(NUMBER_STRINGS[i]);
			if (index > -1 && index > lastIndex) {
				lastIndex = index;
				lastDigit = i;
			}
		}
		
		
		return Integer.valueOf("" + firstDigit + lastDigit);
	}
	
	@Override
	public void a(String input) {
		String[] lines = input.split("\n");
		int sum = 0;
		for(String line : lines) {
			if (line.isBlank()) continue;
			sum += firstAndLastDigit(line);
		}
		
		System.out.println("Sum: "+sum);
	}
	
	@Override
	public void b(String input) {
		String[] lines = input.split("\n");
		int sum = 0;
		for(String line : lines) {
			if (line.isBlank()) continue;
			sum += firstAndLastDigitWithWords(line);
		}
		
		System.out.println("Sum: "+sum);
	}
	
	@Override
	public String getSampleA() {
		return EXAMPLE1;
	}
	
	@Override
	public String getSampleB() {
		return EXAMPLE1;
	}

	@Override
	public boolean sameDataForB() {
		return true;
	}
}
