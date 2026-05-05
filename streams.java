import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) {
		Stream.of(3, 6, 4, 7, 6, 5)
            .filter((number) -> number < 6) // intermediate operation
            .map((number) -> number * 2) // intermediate operation
            .forEach((number) -> System.out.println(number)); // terminal operation
		
		Files.lines(Paths.get("data.txt")) // returns stream of strings object
			.filter((line) -> !line.trim().equals("")) // filter out empty lines
			.map((line) -> line.toUpperCase()) // convert each line to uppercase
			.forEach((line) -> System.out.println(line));
		
        String result = 
		List.of("one", "two", "three")
            .stream()
            .reduce("list:", (acc, newValue) -> acc + " " + newValue);

        System.out.println(result); // "list: one two three"
	}
}
