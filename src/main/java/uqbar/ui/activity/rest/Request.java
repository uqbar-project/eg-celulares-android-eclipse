package uqbar.ui.activity.rest;

import java.io.IOException;
import java.io.StringWriter;

import roboguice.util.temp.Ln;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson.JacksonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

public abstract class Request<T> extends GoogleHttpClientSpiceRequest<T> {

    private String baseUrl = "http://10.9.1.83:9000/";

    public Request(Class<T> responseType) {
        super(responseType);
    }

    @Override
    public T loadDataFromNetwork() throws IOException {
    	String requestUrl = baseUrl + serviceURL();
		HttpRequest request = createRequest(new GenericUrl(requestUrl));
        Ln.d( "Call web service " + requestUrl);
        request.setParser( new JacksonFactory().createJsonObjectParser() );
		T response = request.execute().parseAs( getResultType() );
		printObject(response);
		return response;
    }
    
    protected void printObject(Object object){
    	try {
    		StringWriter writer = new StringWriter();
         	JsonGenerator parser;
			parser = new JacksonFactory().createJsonGenerator(writer);
			parser.serialize(object);
			parser.flush();
			Ln.d(writer.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
	  protected abstract HttpRequest createRequest(GenericUrl url) throws IOException;
	  protected abstract String serviceURL();

}
