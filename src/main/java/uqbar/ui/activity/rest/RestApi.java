package uqbar.ui.activity.rest;

public interface RestApi {

	public <T> void get(String serviceMethod, Class<T> returnType, Object target, String callback);
	public <T, R> void post(String serviceMethod, T body, Class<R> responseType, Object target, String callback);

}