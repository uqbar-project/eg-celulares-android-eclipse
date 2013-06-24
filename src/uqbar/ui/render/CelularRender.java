package uqbar.ui.render;

import uqbar.android.ui.list.ItemRender;
import uqbar.model.celulares.Celular;
import uqbar.ui.activity.celulares.R;
import android.view.View;
import android.widget.TextView;

public class CelularRender implements ItemRender<Celular>{

	public int getLayout() {
		return R.layout.celular_render;
	}

	public void draw(View view, Celular celular) {
		((TextView) view.findViewById(R.id.celular_nombre)).setText(celular.getNombre() + ", "+celular.getModeloCelular().getDescripcion());
		((TextView) view.findViewById(R.id.celular_numero)).setText(String.valueOf(celular.getNumero()));
	}

}
