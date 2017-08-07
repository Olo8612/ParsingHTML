package rekawek.aleksander.ParsingHTML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	String url;

	public Parser(String url) {
		this.url = url;
	}

	public List<String> getTagFromURL() {
		List<String> attributes = new ArrayList<String>();
		Connection conn = Jsoup.connect(this.url);
		int counter = 0;
		try {
			Document doc = conn.get();
			Elements elements = doc.select("a[href]");
			for (Element element : elements) {
				if (isInnerLink(element)) {
					counter++;

				} else {
					System.out.println(element.attr("abs:href"));
					// System.out.println(element.toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(counter);
		return attributes;

	}

	public boolean isInnerLink(Element element) {
		String arg = element.attr("abs:href");

		String match = "";
		try {
			match = arg.substring(0, this.url.length());
		} catch (StringIndexOutOfBoundsException f) {
			return false;
		}
		if (this.url.equals(match)) {
			return true;
		} else {
			// System.out.println(match);
			return false;
		}
	}
}
