package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.*;


public class StateRepository implements Repository<State>{
    private Map<String,State> states;

    public StateRepository() {
        states = new LinkedHashMap<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(states.values());
    }

    @Override
    public void add(State state) {
    states.put(state.getName(),state);
    }

    @Override
    public boolean remove(State state) {
        return states.remove(state.getName()) != null;
    }


    @Override
    public State byName(String name) {
        return states.get(name);
    }
}
