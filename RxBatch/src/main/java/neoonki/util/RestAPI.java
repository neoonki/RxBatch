package neoonki.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class RestAPI {
	private Logger log = Logger.getLogger(this.getClass());
	private String oauthKey;
	
	private String convertParameterToGET(String apiURL, Map param) {
		StringBuilder sb = new StringBuilder();
		List<String> keys = new ArrayList(param.keySet());
		boolean isFirst = true;
		sb.append(apiURL);
		sb.append("?");
		for(String key : keys) {
			if(isFirst) {
				sb.append("&");
				isFirst = false;
			}
			sb.append(key);
			sb.append("=");
			sb.append(param.get(key));
		}
		return sb.toString();
	}
	
	public Map get(String apiURL, HttpMethod httpMethod, Map parameters) {
		Map result = new HashMap();
		if(httpMethod == null) {
			httpMethod = HttpMethod.GET;
		}
		
		if(httpMethod == HttpMethod.GET && parameters != null) {
			apiURL = convertParameterToGET(apiURL, parameters);
		}
		
		HttpsURLConnection conn;
		OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;
        try {
            final URL url = new URL(apiURL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toString());

//            if (adminApiPaths.contains(apiPath)) {
//                conn.setRequestProperty("Authorization", "KakaoAK " + this.adminKey);
//            } else {
//                conn.setRequestProperty("Authorization", "Bearer " + this.accessToken);
//            }

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

//            if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
//                conn.setDoOutput(true);
//                writer = new OutputStreamWriter(conn.getOutputStream());
//                writer.write(params);
//                writer.flush();
//            }

            final int responseCode = conn.getResponseCode();
            log.info(String.format("\nSending '%s' request to URL : %s", httpMethod, apiURL));
            log.info("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream());
            else
                isr = new InputStreamReader(conn.getErrorStream());

            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            JsonParser jsonParser = new BasicJsonParser();
            log.info(buffer.toString());
            result = jsonParser.parseMap(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
            if (reader != null) try { reader.close(); } catch (Exception ignore) { }
            if (isr != null) try { isr.close(); } catch (Exception ignore) { }
        }
		return result;
	}
}
