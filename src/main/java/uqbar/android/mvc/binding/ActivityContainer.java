package uqbar.android.mvc.binding;

import android.app.Activity;
import android.view.View;

public class ActivityContainer implements Container{
	
	private Activity activity;

	public ActivityContainer(Activity activity) {
		this.activity = activity;
	}

	public View findViewById(int id) {
		return activity.findViewById(id);
	}

	public Object getContenido() {
		return activity;
	}
}
