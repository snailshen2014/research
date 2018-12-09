import java.util.regex.*;
public class TestRegex {
	public static void main(String[] args) {
		String regex = "\\$\\{[a-zA-Z_]*\\}\\d";
		Pattern p = Pattern.compile(regex);
		String sou  =  "dateId=${dateid}";
		Matcher m = p.matcher(sou);
		if (!m.find()) {
			System.out.println("no match");
			
		} else {
			System.out.println("Match \"" + m.group() + "\" at positions " + m.start() + "-" +
					(m.end()-1));
			System.out.println(sou.replaceAll(regex, "2017"));
		}
		
		
	}
}
