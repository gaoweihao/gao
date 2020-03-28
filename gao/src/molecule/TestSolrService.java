package molecule;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class TestSolrService {

	@SuppressWarnings("unused")
	private void mian() {
		HttpGet httpgets = new HttpGet("http://localhost:8080/solrservice/operate/deleteById.action?solrId=3&id=24150");
		@SuppressWarnings({ "resource" })
		HttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.execute(httpgets);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
