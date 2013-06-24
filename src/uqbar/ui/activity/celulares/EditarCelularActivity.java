package uqbar.ui.activity.celulares;

import java.util.List;

import uqbar.android.mvc.binding.ModelBinder;
import uqbar.model.celulares.Celular;
import uqbar.model.celulares.ModeloCelular;
import uqbar.ui.celulares.dao.RepositorioModelos;
import uqbar.ui.model.CelularAppModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditarCelularActivity extends Activity implements OnItemSelectedListener{
	
	private CelularAppModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_celular);
        
        Celular celular = (Celular) getIntent().getSerializableExtra("celular");
        this.model = new CelularAppModel(celular);
        
        new ModelBinder(this, this.model)
			.property(R.id.editar_celular_field_nombre_text, "model.nombre")
			.property(R.id.editar_celular_field_numero_text, "numero")
			.booleanProperty(R.id.editar_celular_field_resumen_check, "model.recibeResumenCuenta")
			.action(R.id.editar_celular_aceptar, "aceptar")
			.action(R.id.editar_celular_cancelar, "cancelar")
			.when(CelularAppModel.Events.ACEPTAR, "volver")
			.when(CelularAppModel.Events.CANCELAR, "volver")
			.updateView();
        
        cargarModelos();
    }
    
	
	protected void cargarModelos() {
		// TODO hacer que el Binder soporte combos
		final Spinner modeloSpinner = (Spinner) this.findViewById(R.id.editar_celular_field_modelo_spinner);

		List<ModeloCelular> modelos = RepositorioModelos.getInstance().getModelos();
		ArrayAdapter<ModeloCelular> modeloAdapter = new ArrayAdapter<ModeloCelular>(this, R.layout.celular_modelo_spiner, modelos);
		modeloAdapter.setDropDownViewResource(R.layout.spiner_item);
		modeloSpinner.setAdapter(modeloAdapter);
		modeloSpinner.setOnItemSelectedListener(this);
		
		int modeloIndex = modelos.indexOf(this.model.getModel().getModeloCelular());
		modeloSpinner.setSelection(modeloIndex);
	}

    
    public void volver(){
    	startActivity(new Intent(this, BuscadorCelularesActivity.class));
    }
    
	public void onItemSelected(AdapterView<?> adapter, View view, int arg2, long arg3) {
		this.model.setModelo((ModeloCelular) adapter.getSelectedItem());
	}
	
	public void onNothingSelected(AdapterView<?> arg0) {
	
	}
    
}
