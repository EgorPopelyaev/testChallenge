package utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.Lists;

public class HttpRequestHelper {
	DefaultHttpClient client = new DefaultHttpClient();

	public HttpRequestHelper() {

	}

	public String getHttpPostBody() throws IllegalStateException, IOException {
		HttpPost post = new HttpPost("http://localhost:8081/uploads/fc494/file");
		InputStream is = new BufferedInputStream(client.execute(post)
				.getEntity().getContent());
		String data = is.toString();
		is.close();

		return data;
	}

	public String getPostRequestBodyTitleAndPath(String url) throws IOException {
		HttpPost post = new HttpPost(url);

		ArrayList<BasicNameValuePair> nameValuePairs = Lists
				.newArrayList(new BasicNameValuePair("title", "UploadTest.mp3"));
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = client.execute(post).getEntity()
					.getContent();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

	public String getPostRequestBodyWithConfirmationMessage(String url)
			throws IOException {
		HttpPost post = new HttpPost(url);

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = client.execute(post).getEntity()
					.getContent();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

}
