import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;

public class WeatherMain {

	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
				try {
					//다운받을 URL 생성 
					URL url = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1111061500");
					
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					
					con.setUseCaches(false);
					
					con.setConnectTimeout(30000);
					
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
					
					StringBuilder sb = new StringBuilder();
		            while (true) {
		            	String line = br.readLine();
		                if (line == null)
		                	break;
		                sb.append(line);
		            }
		            
		            String xml = sb.toString();
		            
		            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            
		            InputStream is = new ByteArrayInputStream(xml.getBytes());
		            
		            Document doc = builder.parse(is);
		            //루트찾기 -  org.w3c.dom
		            Element root = doc.getDocumentElement();
		            
		            // tmx 태그 전부 찾기
		            NodeList list = root.getElementsByTagName("title");
		            // System.out.println(list);
		               
		            int i = 0;
		            while (i < list.getLength()) {
		                  
		            	Thread.sleep(1000);
		                  
		            	Node item = list.item(i);
		            	Node item1 = list.item(i);  
		            	// 첫번째 자식 찾기
		            	Node child = item.getFirstChild();
		            	Node child1 = item1.getFirstChild();  
		            	// 데이터 찾기
		            	String title = child.getNodeValue();
		            	String tmx1 = child1.getNodeValue();
		            	
		            	tmx1 = tmx1.replaceAll("<p>","");
		            	// 데이터 출력
		            	System.out.println(title);
		                    
		            	i=i+1;
		            }
		               
		               
		               
				}catch(Exception e) {
					System.out.println(e.getMessage());
		               e.printStackTrace();
				}
			}
		};
		th.start();

	}

}
