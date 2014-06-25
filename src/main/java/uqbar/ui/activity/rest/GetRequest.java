package uqbar.ui.activity.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;

public class GetRequest<T> extends Request<T>{

	private String service;
	private Map<String, ?> params;
	
	public GetRequest(Class<T> responseType, String service) {
		this(responseType, service, new HashMap<String, String>());
	}

	public GetRequest(Class<T> responseType, String service, Map<String, ?> params) {
		super(responseType);
		this.service = service;
		this.params = params;
	}

	@Override
	protected HttpRequest createRequest(GenericUrl url) throws IOException {
		return getHttpRequestFactory().buildGetRequest(url);
	}

	@Override
	protected String serviceURL() {
		StringBuffer buffer = new StringBuffer(service);
		for (Entry<String, ?> param : params.entrySet()) {
			buffer.append(param.getKey());
			buffer.append("=");
			buffer.append(param.getValue());
		}
		return buffer.toString();
	}

}
