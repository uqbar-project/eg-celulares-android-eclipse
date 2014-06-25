package uqbar.android.mvc.binding;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import uqbar.exception.UserException;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class ModelBinder {

	private Container container;
	private final ObservableObject model;
	private final Set<Binding> bindings;

	public ModelBinder(Activity activity, ObservableObject model) {
		this(model);
		this.container = new ActivityContainer(activity);
	}

	public ModelBinder(View view, ObservableObject model) {
		this(model);
		this.container = new ViewContainer(view);

	}

	private ModelBinder(ObservableObject model) {
		this.model = model;
		this.bindings = new HashSet<Binding>();
	}

	public ModelBinder property(int viewId, String propertyName) {
		return this.property(viewId, this.getModel(), propertyName);
	}

	public ModelBinder booleanProperty(int viewId, String propertyName) {
		BooleanBinding binding = new BooleanBinding(viewId, this.getModel(), propertyName);
		this.booleanCheck(viewId, binding);
		this.bindings.add(binding);
		return this;
	}

	public ModelBinder property(int viewId, Object model, String propertyName) {
		this.bindings.add(new Binding(viewId, model, propertyName));
		return this;
	}

	public ModelBinder action(int viewId, final String methodName) {
		View view = this.container.findViewById(viewId);
		ProgramException.assertNotNull(view, "No se encontro la vista para bindear al metodo " + methodName);
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					ModelBinder.this.updateModel();
					ModelBinder.this.dispatchAction(methodName);
				}catch(Exception exception){
					Toast.makeText((Context) container.getContenido(), "Ocurrió un error." + exception.getCause().getMessage() , 5*1000).show();
				}
			}
		});

		return this;
	}

	public ModelBinder editorAction(int viewId, final String methodName) {
		EditText view = (EditText) this.container.findViewById(viewId);
		EditTextOnChangeListener l = new EditTextOnChangeListener() {
			@Override
			protected void onChange() {
				ModelBinder.this.updateModel();
				ModelBinder.this.dispatchAction(methodName);
			}
		};
		view.setOnFocusChangeListener(l);
		view.setOnEditorActionListener(l);

		return this;
	}

	protected ModelBinder booleanCheck(int viewId, final BooleanBinding binding) {
		CompoundButton view = (CompoundButton) this.container.findViewById(viewId);
		view.setOnCheckedChangeListener(new OnCheckedChangeListener() {

//			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				binding.updateModel(ModelBinder.this.container);
			}
		});

		return this;
	}

	protected void updateModel() {
		for (Binding binding : this.bindings) {
			binding.updateModel(this.container);
		}
	}

	public ModelBinder updateView() {
		for (Binding binding : this.bindings) {
			binding.updateView(this.container);
		}

		return this;
	}

	protected void dispatchAction(String methodName) {
		this.dispatchActionOn(this.getModel(), methodName);
	}

	protected void dispatchActionOn(Object receptor, String methodName) {
		Method aMethod = ReflectionUtils.getMethod(receptor.getClass(), methodName, new Class[] {});
		ReflectionUtils.invoke(receptor, aMethod, new Object[] {});
	}

	public ModelBinder when(BusinessEvent event, final String callbackMethodName) {

		this.getModel().register(event, new EventObserver() {
//			@Override
			public void onEvent() {
				ModelBinder.this.dispatchActionOn(ModelBinder.this.container.getContenido(), callbackMethodName);
			}
		});

		return this;
	}

	public ObservableObject getModel() {
		return model;
	}

}
