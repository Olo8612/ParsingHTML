package rekawek.aleksander.ParsingHTML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	String url;
	String urlDomainName;

	public Parser(String url) {
		this.url = url;
		this.urlDomainName = getUrlDomainName(url);
	}

	public List<Element> getTagFromURL() {
		List<Element> attributes = new ArrayList<Element>();
		Map<String, Long> countedAttributes = new HashMap<String, Long>();
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
					String domainOfElement = getUrlDomainName(element.attr("abs:href"));
					attributes.add(element);
					if(countedAttributes.containsKey(domainOfElement)){
						countedAttributes.computeIfPresent(domainOfElement, (key, value) -> value + 1L);
					} else {
						countedAttributes.put(domainOfElement, 1L);
					}
					
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Weenętrznych linków:" + counter);
		System.out.println(countedAttributes.toString());
		return attributes;

	}

	public boolean isInnerLink(Element element) {
		String arg = element.attr("abs:href");

		String match = getUrlDomainName(arg);
				
		if (match.contains(urlDomainName)) {
			return true;
		} else {
			return false;
		}
	}
	public String getUrlDomainName(String url) {
		  String domainName = new String(url);

		  int index = domainName.indexOf("://");

		  if (index != -1) {
		    // keep everything after the "://"
		    domainName = domainName.substring(index + 3);
		  }

		  index = domainName.indexOf('/');

		  if (index != -1) {
		    // keep everything before the '/'
		    domainName = domainName.substring(0, index);
		  }

		  // check for and remove a preceding 'www'
		  // followed by any sequence of characters (non-greedy)
		  // followed by a '.'
		  // from the beginning of the string
		  domainName = domainName.replaceFirst("^www.*?\\.", "");

		  return domainName;
		}
}
