package uqbar.android.mvc.binding;

import android.view.View;
import android.widget.CheckBox;

public class BooleanBinding extends Binding{

	public BooleanBinding(int viewId, Object model, String propertyName) {
		super(viewId, model, propertyName);
	}
	
	@Override
	protected Object getViewValue(Container container) {
		View aView = container.findViewById(this.getViewId());
		return ((CheckBox) aView).isChecked();
	}
	
	@Override
	protected void setViewValue(View view, Object value) {
		((CheckBox) view).setChecked((Boolean) value);
	}



}
