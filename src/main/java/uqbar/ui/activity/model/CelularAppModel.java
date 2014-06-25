package uqbar.ui.activity.model;

import uqbar.android.mvc.binding.BusinessEvent;
import uqbar.android.mvc.binding.ObservableObject;
import uqbar.ui.activity.rest.RestActivity;
import uqbar.ui.activity.rest.StatusResponce;

public class CelularAppModel extends ObservableObject{
	
	private final Celular model;
	private RestActivity restApi;

	public enum Events implements BusinessEvent {
		GUARDADO, CANCELAR
	}
	
	public CelularAppModel(Celular celular, RestActivity restApi) {
		this.model = celular;
		this.restApi = restApi;
	}
	
	public void cancelar(){
		fireEvent(Events.CANCELAR);
	}
	
	public void aceptar(){
		restApi.post("celulares/guardar", model, StatusResponce.class, this, "onSave");
	}
	
	public void onSave(StatusResponce status){
		fireEvent(Events.GUARDADO);
	}
	
	public String getNumero() {
		return String.valueOf(this.model.getNumero());
	}

	public void setNumero(String numero) {
		this.model.setNumero(Integer.valueOf(numero));
	}

	
	public Celular getModel() {
		return model;
	}

	public void setModelo(ModeloCelular modelo) {
		this.model.setModeloCelular(modelo);
	}


}
