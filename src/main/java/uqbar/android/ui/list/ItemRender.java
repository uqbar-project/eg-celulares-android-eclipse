package uqbar.android.ui.list;

import android.view.View;

public interface ItemRender<T> {
	
	int getLayout();
	
	void draw(View view, T t);

}
