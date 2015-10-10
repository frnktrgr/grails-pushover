package grails.plugin.pushover

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import groovy.json.JsonSlurper;

class HttpHelper {
	static Map doPost(URI uri, Map params) {
		doPost(uri.toString(), params)
	}
	static Map doPost(String uri, Map params) {
		ResponseHandler<Map> rh = new ResponseHandler<Map>() {
			@Override
			public Map handleResponse(final HttpResponse response) throws IOException {
				StatusLine statusLine = response.getStatusLine()
				HttpEntity entity = response.getEntity()
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(),statusLine.getReasonPhrase())
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content")
				}
				ContentType contentType = ContentType.getOrDefault(entity)
				Charset charset = contentType.getCharset()
				Reader reader = new InputStreamReader(entity.getContent(), charset)
				JsonSlurper jsonSlurper = new JsonSlurper();
				Object result = jsonSlurper.parse(reader);
				Map jsonResult = (Map) result;
				Map headers = response.getAllHeaders().collectEntries { [it.name, it.value] }
				return [headers: headers, body: jsonResult]
			}
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault()
		List<NameValuePair> formparams = new ArrayList<NameValuePair>()
		params.each { k, v -> formparams.add(new BasicNameValuePair(k, v)) }
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8)
		HttpPost httppost = new HttpPost(uri)
		httppost.setEntity(entity)
		httpclient.execute(httppost, rh)
	}
	
	static Map doGet(URI uri, Map params=[:]) {
		doGet(uri.toString(), params)
	}
	
	static Map doGet(String uri, Map params=[:]) {
		ResponseHandler<Map> rh = new ResponseHandler<Map>() {
			@Override
			public Map handleResponse(final HttpResponse response) throws IOException {
				StatusLine statusLine = response.getStatusLine()
				HttpEntity entity = response.getEntity()
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(),statusLine.getReasonPhrase())
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content")
				}
				ContentType contentType = ContentType.getOrDefault(entity)
				Charset charset = contentType.getCharset()?:Consts.ISO_8859_1
				Reader reader = new InputStreamReader(entity.getContent(), charset)
				JsonSlurper jsonSlurper = new JsonSlurper();
				Object result = jsonSlurper.parse(reader);
				Map jsonResult = (Map) result;
//				String theString = IOUtils.toString(reader);
				Map headers = response.getAllHeaders().collectEntries { [it.name, it.value] }
				return [headers: headers, body: jsonResult]
			}
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault()
		HttpGet httpget = new HttpGet(uri)
		httpclient.execute(httpget, rh)
	}
}