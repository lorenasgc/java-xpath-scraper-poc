# java-xpath-scraper-poc
Java Proof of Concept for web scraping using Jsoup, XPath queries, and Java 17 Records with XML serialization.

💡 Technical Note: While Jsoup's core engine is natively built around CSS Selectors, XPath was deliberately chosen for this PoC to strictly align with the role's technical requirements and to demonstrate node navigation.

## 📌 Context
This Proof of Concept (PoC) specifically simulates the data extraction and XML serialization pipeline typical of systems like WebHarvest, demonstrating how to prepare scraped data for their current infrastructure. 
 Developed to demonstrate data acquisition, DOM navigation, and architectural decoupling using Java. 

## 🚀 Technical Implementation
* **DOM Manipulation & XPath:** Used `Jsoup` to fetch and parse the HTML into a DOM tree, relying exclusively on XPath queries (e.g., `//article[@class='product_pod']`) for node targeting.
* **Data Structures & Algorithmic Complexity:** The extracted elements are mapped to a Java 17 `Record` and stored in an `ArrayList`. This approach ensures $O(n)$ time complexity for iteration and optimal $O(1)$ time complexity for list insertion.
* **Clean Architecture:** Separated the scraping/extraction phase from the serialization phase. This decoupling ensures the data structure can be easily adapted to output JSON or save to a database in the future.
* **Data Cleaning & XML Serialization:** Handled special character sanitization (escaping `&` to `&amp;` to prevent XML parsing errors) and stripped currency symbols to provide clean, database-ready numeric values in a valid XML output.
