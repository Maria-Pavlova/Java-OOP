package CounterStriker.models.field;


import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.*;

public class FieldImpl implements Field {


    @Override
    public String start(Collection<Player> players) {
        List<Player> terrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        while (terrorists.stream().anyMatch(Player::isAlive) && counterTerrorists.stream().anyMatch(Player::isAlive)) {

            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    counterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }
        counterTerrorists = counterTerrorists.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(counterTerrorist.getGun().fire());
                }
       terrorists = terrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
            }
        }
        if (terrorists.isEmpty()) {
            return COUNTER_TERRORIST_WINS;
        } else {
            return TERRORIST_WINS;
        }

    }
}
