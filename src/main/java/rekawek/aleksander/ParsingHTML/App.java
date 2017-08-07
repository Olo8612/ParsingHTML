package rekawek.aleksander.ParsingHTML;

public class App {
	public static void main(String[] args) {
		String url = "http://wawalove.pl";
		Parser parser = new Parser(url);
		parser.getTagFromURL();
	}
}
