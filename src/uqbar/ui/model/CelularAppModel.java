package uqbar.ui.model;

import uqbar.android.mvc.binding.BusinessEvent;
import uqbar.android.mvc.binding.ObservableObject;
import uqbar.model.celulares.Celular;
import uqbar.model.celulares.ModeloCelular;
import uqbar.ui.celulares.dao.RepositorioCelulares;

public class CelularAppModel extends ObservableObject{
	
	private final Celular model;

	public enum Events implements BusinessEvent {
		ACEPTAR, CANCELAR
	}
	
	public CelularAppModel(Celular celular) {
		this.model = celular;
	}
	
	public void cancelar(){
		fireEvent(Events.CANCELAR);
	}
	
	public void aceptar(){
		RepositorioCelulares.getInstance().update(this.model);
		fireEvent(Events.ACEPTAR);
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
