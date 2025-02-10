import java.util.List;
import java.util.ArrayList;

interface Combatable {
    void attack();
    void defend();
}

interface ItemManageable {
    void pickUpItem(Item item);
    void useItem(Item item);
}

interface LevelProgressable {
    void levelUp();
}

class Player implements Combatable, ItemManageable, LevelProgressable {
    private int health;
    private int experience;
    private Inventory inventory;

    public Player() {
        this.health = 100;
        this.experience = 0;
        this.inventory = new Inventory();
    }

    
    public void attack() {
        System.out.println("Player attacks!");
    }

    
    public void defend() {
        System.out.println("Player defends!");
    }

    
    public void pickUpItem(Item item) {
        inventory.addItem(item);
    }

    public void useItem(Item item) {
        item.applyEffect(this);
    }

    
    public void levelUp() {
        experience += 10;
        if (experience >= 100) {
            System.out.println("Player leveled up!");
            experience = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

class Item {
    private String name;
    private String effect;

    public Item(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public void applyEffect(Player player) {
        System.out.println("Applying " + effect + " to player.");
    }
}

class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("Picked up item: " + item);
    }

    public List<Item> getItems() {
        return items;
    }
}

class Enemy implements Combatable {
    private int health;
    
    public Enemy() {
        this.health = 50;
    }

    public void attack() {
        System.out.println("Enemy attacks!");
    }

    public void defend() {
        System.out.println("Enemy defends!");
    }
}

class CombatManager {
    public void performCombat(Combatable attacker, Combatable defender) {
        attacker.attack();
        defender.defend();
        System.out.println("Combat occurs between attacker and defender.");
    }
}

class LevelManager implements LevelProgressable {
    private int level;

    public LevelManager() {
        this.level = 1;
    }

    public void levelUp() {
        level++;
        System.out.println("Level up! New level: " + level);
    }

    public int getLevel() {
        return level;
    }
}

public class GameApp {
    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new Enemy();
        CombatManager combatManager = new CombatManager();
        LevelManager levelManager = new LevelManager();
        
        combatManager.performCombat(player, enemy);
        
        player.levelUp();
        Item sword = new Item("Sword", "Attack Boost");
        player.pickUpItem(sword);
        player.useItem(sword);
        
        levelManager.levelUp();
    }
}
