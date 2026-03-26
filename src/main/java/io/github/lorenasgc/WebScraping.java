package io.github.lorenasgc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class WebScraping {

    private record Book(int id, String title, String price) {}

    public static void main(String[] args) {
        String url = "http://books.toscrape.com/";

        List<Book> scrapedBooks = new ArrayList<>();

        try {
            // PHASE 1: EXTRACTION (Web Scraping with XPath)
            Document doc = Jsoup.connect(url).get();
            Elements articleNodes = doc.selectXpath("//article[@class='product_pod']");

            // Populate the List (Time Complexity: O(n))
            for (int i = 0; i < articleNodes.size(); i++) {
                Element node = articleNodes.get(i);

                String title = node.selectXpath(".//h3/a").get(0).attr("title");
                title = title.replace("&", "&amp;"); // Sanitize for XML

                String priceText = node.selectXpath(".//p[@class='price_color']").get(0).text();
                String cleanPrice = priceText.replace("£", "");

                // Instantiate the Record and save it
                scrapedBooks.add(new Book((i + 1), title, cleanPrice));
            }

            // PHASE 2: XML SERIALIZATION (Decoupled)
            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            System.out.println("<books>");

            for (Book book : scrapedBooks) {
                System.out.println("    <book id=\"" + book.id() + "\">");
                System.out.println("        <title>" + book.title() + "</title>");
                System.out.println("        <price currency=\"£\">" + book.price() + "</price>");
                System.out.println("    </book>");
            }

            System.out.println("</books>");

        } catch (Exception e) {
            System.err.println("Extraction error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

