package uqbar.ui.activity;

import java.util.List;

import uqbar.android.mvc.binding.ModelBinder;
import uqbar.android.ui.list.ItemListAdapter;
import uqbar.android.ui.list.ItemSelectionListener;
import uqbar.ui.activity.model.BuscadorCelular;
import uqbar.ui.activity.model.Celular;
import uqbar.ui.activity.model.ModeloCelular;
import uqbar.ui.activity.render.CelularRender;
import uqbar.ui.activity.rest.RestActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class BuscadorCelularesActivity extends RestActivity implements ItemSelectionListener<Celular>{
	
	private BuscadorCelular model = new BuscadorCelular(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_celulares);
        
        new ModelBinder(this, model)
			.property(R.id.buscador_formulario_nombre_text, "nombre")
			.action(R.id.buscador_buscar, "buscar")
			.when(BuscadorCelular.Events.BusquedaFinalizada, "actualizarResultado")
			.when(BuscadorCelular.Events.ModelosCargados, "cargarModelos");

        this.model.buscar();
        this.model.listarModelos();
    }
    
    
	public void actualizarResultado() {
		ListView list = (ListView) this.findViewById(R.id.buscador_resultado);
		List<Celular> celulares = this.model.getCelulares();
		list.setAdapter(new ItemListAdapter<Celular>(this, celulares, new CelularRender(), this));
	}
	
	
	public void cargarModelos() {
		// TODO hacer que el Binder soporte combos
		final Spinner modeloSpinner = (Spinner) this.findViewById(R.id.buscador_formulario_modelo_selector);

		ArrayAdapter<ModeloCelular> modelosAdapter = new ArrayAdapter<ModeloCelular>(this, R.layout.celular_modelo_spiner, this.model.getModelosDeCelular());
		modelosAdapter.setDropDownViewResource(R.layout.spiner_item);
		modeloSpinner.setAdapter(modelosAdapter);
	}


	public void onSelect(Celular celular, View view) {
		Intent intent = new Intent(this, EditarCelularActivity.class);
		intent.putExtra("celular", celular);
		intent.putExtra("modelos", this.model.getModelos());
		startActivity(intent);
	}
}
