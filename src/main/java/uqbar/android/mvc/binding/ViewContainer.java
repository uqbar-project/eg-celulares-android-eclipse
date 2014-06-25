package uqbar.android.mvc.binding;

import android.view.View;

public class ViewContainer implements Container{
	
	private View view;

	public ViewContainer(View view) {
		this.view = view;
	}

//	@Override
	public View findViewById(int id) {
		return view.findViewById(id);
	}

//	@Override
	public Object getContenido() {
		return view;
	}

}
