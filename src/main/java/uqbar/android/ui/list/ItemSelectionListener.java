package uqbar.android.ui.list;

import android.view.View;

public interface ItemSelectionListener<T> {

	void onSelect(T item, View view);
}
