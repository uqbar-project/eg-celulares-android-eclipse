package uqbar.ui.activity.celulares;

import java.util.List;

import uqbar.android.mvc.binding.ModelBinder;
import uqbar.android.ui.list.ItemListAdapter;
import uqbar.android.ui.list.ItemSelectionListener;
import uqbar.model.celulares.BuscadorCelular;
import uqbar.model.celulares.Celular;
import uqbar.model.celulares.ModeloCelular;
import uqbar.ui.celulares.dao.RepositorioModelos;
import uqbar.ui.model.BuscadorCelularAppModel;
import uqbar.ui.render.CelularRender;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class BuscadorCelularesActivity extends Activity implements ItemSelectionListener<Celular>{
	
	private BuscadorCelularAppModel model = new BuscadorCelularAppModel(new BuscadorCelular());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_celulares);
        
        new ModelBinder(this, model)
			.property(R.id.buscador_formulario_nombre_text, "model.nombre")
			.action(R.id.buscador_buscar, "buscar")
			.when(BuscadorCelularAppModel.Events.ACTUALIZAR, "actualizarResultado");

        this.cargarModelos();
        this.model.buscar();
    }
    
    
	public void actualizarResultado() {
		ListView list = (ListView) this.findViewById(R.id.buscador_resultado);
		List<Celular> celulares = this.model.getResultados();
		list.setAdapter(new ItemListAdapter<Celular>(this, celulares, new CelularRender(), this));
	}
	
	
	protected void cargarModelos() {
		// TODO hacer que el Binder soporte combos
		final Spinner modeloSpinner = (Spinner) this.findViewById(R.id.buscador_formulario_modelo_selector);

		ArrayAdapter<ModeloCelular> modelosAdapter = new ArrayAdapter<ModeloCelular>(this, R.layout.celular_modelo_spiner, RepositorioModelos.getInstance().getModelos());
		modelosAdapter.setDropDownViewResource(R.layout.spiner_item);
		modeloSpinner.setAdapter(modelosAdapter);
	}


	public void onSelect(Celular celular, View view) {
		Intent intent = new Intent(this, EditarCelularActivity.class);
		intent.putExtra("celular", celular);
		startActivity(intent);
	}
}
