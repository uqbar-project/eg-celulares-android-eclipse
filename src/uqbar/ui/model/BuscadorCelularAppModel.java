package uqbar.ui.model;

import java.util.List;

import uqbar.android.mvc.binding.BusinessEvent;
import uqbar.android.mvc.binding.ObservableObject;
import uqbar.model.celulares.BuscadorCelular;
import uqbar.model.celulares.Celular;

public class BuscadorCelularAppModel extends ObservableObject {

	public enum Events implements BusinessEvent {
		ACTUALIZAR
	}

	private BuscadorCelular model;

	public BuscadorCelularAppModel(BuscadorCelular buscador) {
		this.model = buscador;
	}
	
	public void buscar(){
		this.model.search();
		fireEvent(Events.ACTUALIZAR);
	}

	public BuscadorCelular getModel() {
		return model;
	}

	public void setModel(BuscadorCelular model) {
		this.model = model;
	}
	
	
	public List<Celular> getResultados(){
		return this.model.getResultados();
	}

}
