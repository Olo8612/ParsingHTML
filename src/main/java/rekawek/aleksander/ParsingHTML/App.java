package rekawek.aleksander.ParsingHTML;

import java.util.List;

import org.jsoup.nodes.Element;

public class App {
	public static void main(String[] args) {
		String url = "http://www.onet.pl";
		Parser parser = new Parser(url);
		List<Element> ahref = parser.getTagFromURL();
	}
}
