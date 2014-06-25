package uqbar.ui.activity;

import uqbar.android.mvc.binding.ModelBinder;
import uqbar.ui.activity.model.Celular;
import uqbar.ui.activity.model.CelularAppModel;
import uqbar.ui.activity.model.ListadoModeloCelular;
import uqbar.ui.activity.model.ModeloCelular;
import uqbar.ui.activity.rest.RestActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditarCelularActivity extends RestActivity implements OnItemSelectedListener{
	
	private CelularAppModel model;
	private ListadoModeloCelular modelosDeCelular;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_celular);
        
        Celular celular = (Celular) getIntent().getSerializableExtra("celular");
        modelosDeCelular = (ListadoModeloCelular) getIntent().getSerializableExtra("modelos");
        this.model = new CelularAppModel(celular, this);
        
        new ModelBinder(this, this.model)
			.property(R.id.editar_celular_field_nombre_text, "model.nombre")
			.property(R.id.editar_celular_field_numero_text, "numero")
			.booleanProperty(R.id.editar_celular_field_resumen_check, "model.recibeResumenCuenta")
			.action(R.id.editar_celular_aceptar, "aceptar")
			.action(R.id.editar_celular_cancelar, "cancelar")
			.when(CelularAppModel.Events.GUARDADO, "volver")
			.when(CelularAppModel.Events.CANCELAR, "volver")
			.updateView();
        
        cargarModelos();
    }
    
	
	protected void cargarModelos() {
		// TODO hacer que el Binder soporte combos
		final Spinner modeloSpinner = (Spinner) this.findViewById(R.id.editar_celular_field_modelo_spinner);

		ArrayAdapter<ModeloCelular> modeloAdapter = new ArrayAdapter<ModeloCelular>(this, R.layout.celular_modelo_spiner, modelosDeCelular.getList());
		modeloAdapter.setDropDownViewResource(R.layout.spiner_item);
		modeloSpinner.setAdapter(modeloAdapter);
		modeloSpinner.setOnItemSelectedListener(this);
		
		int modeloIndex = modelosDeCelular.getList().indexOf(this.model.getModel().getModeloCelular());
		modeloSpinner.setSelection(modeloIndex);
	}

    
    public void volver(){
    	finish();
    }
    
	public void onItemSelected(AdapterView<?> adapter, View view, int arg2, long arg3) {
		this.model.setModelo((ModeloCelular) adapter.getSelectedItem());
	}
	
	public void onNothingSelected(AdapterView<?> arg0) {
	
	}
    
}
