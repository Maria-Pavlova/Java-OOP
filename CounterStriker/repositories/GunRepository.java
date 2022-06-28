package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static CounterStriker.common.ExceptionMessages.*;

public class GunRepository implements Repository<Gun> {
   private Map<String,Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return guns.values();
    }

    @Override
    public void add(Gun gun) {
    if (gun == null){
        throw new NullPointerException(INVALID_GUN_REPOSITORY);
    }
    guns.put(gun.getName(), gun);
    }

    @Override
    public boolean remove(Gun gun) {
        return guns.remove(gun.getName()) != null;
    }

    @Override
    public Gun findByName(String name) {
        return guns.values().stream()
                .filter(g -> g.getName().equals(name))
                .findFirst().orElse(null);
    }
}
