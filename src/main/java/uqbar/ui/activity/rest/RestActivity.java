package uqbar.ui.activity.rest;

import android.app.Activity;

import com.octo.android.robospice.JacksonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;

/**
 * This class is the base class of all activities of the sample project. This class offers all
 * subclasses an easy access to a {@link SpiceManager} that is linked to the {@link Activity}
 * lifecycle. Typically, in a new project, you will have to create a base class like this one and
 * copy the content of the {@link RestActivity} into your own class.
 */
public abstract class RestActivity extends Activity implements RestApi {
    private SpiceManager spiceManager = new SpiceManager(JacksonGoogleHttpClientSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    public SpiceManager getSpiceManager() {
        return spiceManager;
    }
    
    @Override
	public <R> void get(String serviceMethod, Class<R> responseType, Object target, String callback){
		GetRequest<R> request = new GetRequest<R>(responseType, serviceMethod);
		this.getSpiceManager().execute(request, new ServiceRequestListener<R>(this, target, callback, null));
    }
    
    @Override
	public <T, R> void post(String serviceMethod, T body, Class<R> responseType, Object target, String callback){
		PostRequest<T, R> request = new PostRequest<T,R>(responseType, serviceMethod, body);
		this.getSpiceManager().execute(request, new ServiceRequestListener<R>(this, target, callback, null));
    }

}
