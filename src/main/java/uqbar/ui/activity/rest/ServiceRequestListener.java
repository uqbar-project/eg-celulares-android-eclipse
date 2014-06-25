package uqbar.ui.activity.rest;

import uqbar.android.mvc.binding.ReflectionUtils;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ServiceRequestListener<T> implements RequestListener<T> {
	private ProgressDialog dialog;
	private Context context;
	private String successMethod;
	private String failureMethod;
	private Object target;

	public ServiceRequestListener(Context context, Object target, String successMethod, String failureMethod ) {
		this.context = context;
		this.target = target;
		this.successMethod = successMethod;
		this.failureMethod = failureMethod;
		dialog = ProgressDialog.show(context, "", "Loading. Please wait...", true);
	}

	@Override
	  public void onRequestFailure(SpiceException exception) {
	    Toast.makeText(context, "Failure", 5*1000);
	    dialog.hide();
	    if(failureMethod != null){
	    	ReflectionUtils.invoke(target, failureMethod, exception);
	    }
	  }

	@Override
	  public void onRequestSuccess(T result) {
	    dialog.hide();
	    ReflectionUtils.invoke(target, successMethod, result);
	  }
}
