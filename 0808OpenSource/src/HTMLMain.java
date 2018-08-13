import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLMain {
	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
				try {
					URL url = new URL("https://www.tv.naver.com/");
					HttpURLConnection con = (HttpURLConnection)url.openConnection();
					con.setUseCaches(false);
					con.setConnectTimeout(30000);
					
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
							
					StringBuilder sb = new StringBuilder();
					while(true) {
						String line = br.readLine();
						if(null==line) {
							break;
						}
						sb.append(line+"\n");
					}
					String html = sb.toString();
					sb = null;
					br.close();
					con.disconnect();
					
					Document doc = Jsoup.parse(html);
					Elements elements = doc.getElementsByTag("h2");
					
					int i=0;
					while(i<elements.size()) {
						Element element = elements.get(i);
						System.out.println(element.text());
						i++;
					}
//					System.out.println(html);
				} catch(Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		};
		
		th.start();
	}
}