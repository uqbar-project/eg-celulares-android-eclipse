package uqbar.ui.activity.model;

import java.util.List;

import uqbar.android.mvc.binding.BusinessEvent;
import uqbar.android.mvc.binding.ObservableObject;
import uqbar.ui.activity.rest.RestActivity;

public class BuscadorCelular extends ObservableObject {

	public enum Events implements BusinessEvent {
		BusquedaFinalizada,
		ModelosCargados
	}

	private RestActivity restApi;
	private ListadoCelulares celulares;
	private ListadoModeloCelular modelos;
	private String nombre = "";

	public BuscadorCelular(RestActivity restApi) {
		this.restApi = restApi;
	}
	
	public void buscar(){ 
		restApi.get("celulares/nombre/"+nombre, ListadoCelulares .class, this, "cargarCelulares");
	}
	
	public void listarModelos(){
		restApi.get("celulares/modelos", ListadoModeloCelular.class, this, "cargarModelos");
	}

	public void  cargarCelulares(ListadoCelulares celulares){
		this.celulares = celulares;
		fireEvent(Events.BusquedaFinalizada);
	}
	
	public void  cargarModelos(ListadoModeloCelular modelos){
		this.modelos = modelos;
		fireEvent(Events.ModelosCargados);
	}
	
	public List<Celular> getCelulares(){
		return celulares.getList();
	}

	public List<ModeloCelular> getModelosDeCelular() {
		return modelos.getList();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ListadoModeloCelular getModelos() {
		return modelos;
	}
}
