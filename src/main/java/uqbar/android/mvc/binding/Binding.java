package uqbar.android.mvc.binding;

import android.view.View;
import android.widget.TextView;

public class Binding {

	private final int viewId;
	private final String propertyName;
	private final Object model;

	public Binding(int viewId, Object model, String propertyName) {
		this.viewId = viewId;
		this.model = model;
		this.propertyName = propertyName;
	}

	public void updateModel(Container container) {
		Object input = this.getViewValue(container);
		ReflectionUtils.setProperty(this.model, this.propertyName, input);
	}

	protected Object getViewValue(Container container) {
		View aView = container.findViewById(this.viewId);

		TextView editText = (TextView) aView;
		String input = editText.getText().toString();
		return input;
	}

	public void updateView(Container container) {
		View aView = container.findViewById(this.viewId);

		Object value = ReflectionUtils.getProperty(this.model, this.propertyName);
		this.setViewValue(aView, value);
	}

	protected void setViewValue(View view, Object value) {
		TextView textView = (TextView) view;
		ProgramException.assertNotNull(textView, "No se encontro el TextView tratando de bindear la property "
				+ this.propertyName + " al model " + this.model.getClass().getSimpleName());
		if (value == null) {

		}
		else if (value instanceof Integer) {
			textView.setText((Integer) value);
		}
		else if (value instanceof CharSequence) {
			textView.setText((CharSequence) value);
		}
		else {
			textView.setText(value.toString());
		}
	}

	public int getViewId() {
		return this.viewId;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public Object getModel() {
		return this.model;
	}

}
