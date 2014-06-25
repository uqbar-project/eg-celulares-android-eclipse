package uqbar.android.mvc.binding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.util.Log;

public class ObservableObject {

	private Map<BusinessEvent, Set<EventObserver>> observers = new HashMap<BusinessEvent, Set<EventObserver>>();

	public void register(BusinessEvent event, EventObserver eventObserver) {
		getEventObservers(event).add(eventObserver);
	}

	protected Set<EventObserver> getEventObservers(BusinessEvent event) {
		if (!this.observers.containsKey(event)) {
			this.observers.put(event, new HashSet<EventObserver>());
		}

		return this.observers.get(event);
	}

	// TODO: Quizás debería actualizar la vista cuando se tira el evento
	protected void fireEvent(BusinessEvent event) {
		Log.d("EVENT", "Disparando evento: " + event.toString());
		for (EventObserver observer : this.getEventObservers(event)) {
			observer.onEvent();
		}
	}
}
