package game.object.item.background.person;

import game.object.item.background.BackgroundItem;
import game.object.item.collectable.Collectable;
import game.system.Inventory;

/**
 * Can be contained in rooms
 * May have inventories
 * Is either alive or dead
 */
public abstract class Person extends BackgroundItem {

    public static final long serialVersionUID = 1L;

    private Inventory<Collectable> inv;
    private String name;
    private boolean alive;

    private int maxHealth;
    private int health;
    private int maxMana;
    private int mana;
    private int maxHunger;
    private int hunger;
    private int maxCorruption;
    private int corruption;

    /**
    * Default empty Person constructor
    */
    public Person() {
        inv = new Inventory<Collectable>();
        this.alive = true;
    }

    /**
    * Default Person constructor
    */
    public Person(Inventory<Collectable> inv, String name, boolean alive) {
        this.inv = inv;
        this.name = name;
        this.alive = alive;
    }

    public Person(String name) {
        this();
        this.name = name;
    }


    /**
    * Returns value of inv
    * @return
    */
    public Inventory<Collectable> getInv() {
        return this.inv;
    }

    /**
    * Returns value of name
    * @return
    */
    public String getName() {
        return this.name;
    }

    /**
    * Returns value of alive
    * @return
    */
    public boolean isAlive() {
        return this.alive;
    }

    /**
    * Sets new value of inv
    * @param
    */
    public void setInv(Inventory<Collectable> inv) {
        this.inv = inv;
    }

    /**
    * Sets new value of name
    * @param
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Sets new value of alive
    * @param
    */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getCorruption() {
        return this.corruption;
    }

    public void setHealth(int health) {
        if (this.health + health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public void setMana(int mana) {
        if (this.mana + mana < 0) {
            this.mana = 0;
        } else {
            this.mana = mana;
        }
    }

    public void setCorruption(int corruption) {
        if (this.corruption + corruption < 0) {
            this.corruption = 0;
        } else if (this.corruption + corruption > this.health) {
            this.corruption = this.health - 1;
        } else {
            this.corruption = corruption;
        }
    }

    /**
     * Put collectable item in inventory
     * @param Collectable collectable to put inside inventory
     */
    public void take(Collectable collectable) {
        inv.add(collectable);
    }

    /**
     * Put multiple of item in inventory
     * @param int         count       of collectable
     * @param Collectable collectable to put inside inventory
     */
    public void take(int count, Collectable collectable) {
        inv.add(count,collectable);
    }

    public void drop(Collectable collectable) {
        inv.remove(collectable);
    }

    public void drop(int count, Collectable collectable) {
        inv.remove(count,collectable);
    }


}
