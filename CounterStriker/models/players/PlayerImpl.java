package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

import static CounterStriker.common.ExceptionMessages.*;

public class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        setGun(gun);
        this.isAlive = true;
    }

    protected void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    protected void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    protected void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

//    public void setAlive(boolean alive) {
//        isAlive = alive;
//    }

    protected void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void takeDamage(int points) {
        if (points <= armor) {
            armor -= points;
        } else {
            int diff = points - armor;
            armor = 0;
            health -= diff;
        }
        if (health < 0) {
            health = 0;
            isAlive = false;
        }
    }

    @Override
    public String toString() {
        return (String.format("%s: %s%n" +
                        "--Health: %d%n" +
                        "--Armor: %d%n" +
                        "--Gun: %s"
                , this.getClass().getSimpleName(), this.getUsername(), health,
                armor, gun.getName()));


    }
}
