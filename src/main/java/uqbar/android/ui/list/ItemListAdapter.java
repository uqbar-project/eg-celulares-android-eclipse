package uqbar.android.ui.list;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ItemListAdapter<T> extends ArrayAdapter<T> {
	private final ItemRender<T> render;
	private final ItemSelectionListener<T> itemListener;

	public ItemListAdapter(Activity activity, List<T> items, ItemRender<T> render, ItemSelectionListener<T> listener) {
		super(activity, 0, items);
		this.render = render;
		this.itemListener = listener;
		this.setNotifyOnChange(true);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.render.getLayout(), null);
		}

		final T item = this.getItem(position);
		
		this.drawView(view, item);
		return view;
	}

	protected void drawView(View view, final T item) {
		view.setOnClickListener(new OnClickListener() {
//			@Override
			public void onClick(View v) {
				ItemListAdapter.this.itemListener.onSelect(item, v);
			}
		});
		
		this.render.draw(view, item);
	}
}