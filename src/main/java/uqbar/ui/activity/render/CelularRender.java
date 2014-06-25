package uqbar.ui.activity.render;

import uqbar.android.ui.list.ItemRender;
import uqbar.ui.activity.R;
import uqbar.ui.activity.model.Celular;
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
