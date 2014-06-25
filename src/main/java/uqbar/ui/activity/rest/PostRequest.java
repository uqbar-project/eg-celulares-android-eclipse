package uqbar.ui.activity.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.jackson.JacksonFactory;

public class PostRequest<R, T> extends Request<T>{

	private String service;
	private R body;

	public PostRequest(Class<T> responseType, String service, R body) {
		super(responseType);
		this.service = service;
		this.body = body;
	}

	@Override
	protected HttpRequest createRequest(GenericUrl url) throws IOException {
		JsonHttpContent content = new JsonHttpContent(new JacksonFactory(), body);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    content.writeTo(baos);
	    printObject(body);
		return getHttpRequestFactory().buildPostRequest(url, content);
	}

	@Override
	protected String serviceURL() {
		return service;
	}

}