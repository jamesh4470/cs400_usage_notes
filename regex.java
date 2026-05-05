import java.util.regex.Matcher;
import java.util.regex.Pattern;

String email = "firstname.lastname@cs.wisc.edu";
Pattern validEmailPattern = Pattern.compile("[\\w\\.]+@[\\w\\.]+"); // double backslash to prevent compiler error
Matcher emailMatcher = validEmailPattern.matcher(email);
emailMatcher.matches(); // returns true if matches

//shorthand for testing a string
Pattern.matches(""[\\w\\.]+@[\\w\\.]+", email);

// extracting multiple patterns
String multipleEmails = "string with multiple emails";
Matcher multipleEmailsMatcher = validEmailPattern.matcher(multipleEmails);
while (multipleEmailsMatcher.find()) {
	String email = multipleEmailsMatcher.group();
	int start = multipleEmailsMatcher.start();
	int end = multipleEmailsmatcher.end();
	System.out.println(email + " at position " + start + " to " + end);
}