package uqbar.android.mvc.binding;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public abstract class EditTextOnChangeListener implements OnFocusChangeListener, OnEditorActionListener {

	private long lastUpdate;

//	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (isDuplicatedEvent(System.currentTimeMillis())) {
			return;
		}
		this.lastUpdate = System.currentTimeMillis();
		if (!hasFocus) {
			onChange();
		}
	}

	private boolean isDuplicatedEvent(long currentTimeMillis) {
		return currentTimeMillis - this.lastUpdate < 1000;
	}

//	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (isDuplicatedEvent(System.currentTimeMillis())) {
			return false;
		}
		this.lastUpdate = System.currentTimeMillis();
		onChange();
		return true;
	}

	protected abstract void onChange();

}