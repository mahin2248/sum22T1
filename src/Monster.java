import java.util.Scanner;
import java.util.Random;
public class Monster {
    protected String name;
    protected String monsterID;
    protected Items itemDrop;
    protected double healthPoints;
    protected double currentHP;
    protected String monsterDesc;
    protected Scanner sc;
    protected Random rand;
    protected boolean invulnerable;
    protected boolean flyingEnemy;
    protected double damagedHealth;
    protected boolean damageReduction;
    protected int potionDrop;
    protected int coinDrop;


    public Monster() {
        name = "none";
        monsterID = "Not Assigned";
        healthPoints = 0;
        monsterDesc = "This monster doesn't exist";
        invulnerable = false;
        flyingEnemy = false;
        damageReduction = false;
        potionDrop = 0;
        coinDrop = 0;
    }

    public Monster(String name, String monsterID, double healthPoints, String monsterDesc) {
        this();
        this.name = name;
        this.monsterID = monsterID;
        this.healthPoints = healthPoints;
        this.monsterDesc = monsterDesc;
    }

    public void setItemDrop(Items itemDrop) {
        this.itemDrop = itemDrop;
    }

    public void setPotionDrop(int potionDrop) {
        this.potionDrop = potionDrop;
    }

    public void setCoinDrop(int coinDrop) {
        this.coinDrop = coinDrop;
    }

    public void CombatMode(Player p) {
        double baseHealth = healthPoints;
        if(flyingEnemy) {
            boolean playerCanFight = false;
            for(Items i : p.getPlayerInventory()) {
                if(i.getItemName().equals("Oozlum Wings")) { //FIX CODE FOR WINGS
                    playerCanFight = true;
                    break;
                }
            }
            if(!playerCanFight) {
                System.out.println("You can’t reach this enemy, they are flying! There must be something else you need…");
                System.out.println("\n\nYou are no longer in combat.");
                return;
            }

        }
        boolean playerTurn = true;
        System.out.println("Your health: " + p.getCurrentHealth());
        System.out.println(name + " health " + healthPoints);
        System.out.println("You have engaged in combat with " + name + ". What will you do?");

        sc = new Scanner(System.in);

        boolean attackMode = false;
        boolean firstAttack = true;
        while(true) {
            if(p.getCurrentHealth() <= 0) {
                p.setCurrentHealth(p.getBASE_HEALTH());
                healthPoints = baseHealth;
                System.out.println("You have died....");
                System.out.println("\nYou see a light at the end of the tunnel"
                        + " but your spirit refuses to leave...");
                System.out.println("\n\n\nYou're back in the realm of the living.\nYou're no longer in combat.");
                return;
            }

            if(healthPoints <= 0) {
                System.out.println("You defeated " + name + "!");
                System.out.println("You collected");
                if(itemDrop != null) {
                    p.getPlayerInventory().add(itemDrop);
                    System.out.println("> " + itemDrop.getItemName());
                }
                if(coinDrop > 0) {
                    p.coins += coinDrop;
                    System.out.println("> " + coinDrop + " Elusive coins");
                }
                if (potionDrop > 0) {
                    p.potions += potionDrop;
                    System.out.println("> " + potionDrop + " Potion");
                }
                name = "none";
                return;
            }

            System.out.println("Your health: " + p.getCurrentHealth());
            System.out.println(name + " health " + healthPoints);

            if(playerTurn) {

                if(p.terrified) {
                    System.out.println("Your turn was skipped!");
                    p.terrified = false;
                    playerTurn = false;
                    continue;
                }

                System.out.println("It is your turn. What action will you perform?");
                if (!firstAttack) {
                    System.out.println(">Help\n>Run\nUse Potion\n>Stab\n>Slash\n>Punch\n>Parry");
                }
                else {
                    System.out.println(">Help\n>Run\n>Attack\n>Use Potion");
                }
                String playerResponse = sc.nextLine();
                if (firstAttack) {
                    if(playerResponse.equalsIgnoreCase("attack")) {
                        attackMode = true;
                        System.out.println("Which attack would you like to do?");
                        System.out.println(">Stab\n>Slash\n>Punch\n>Parry");
                        playerResponse = sc.nextLine();
                    }
                }

                if(playerResponse.equalsIgnoreCase("help")) {
                    System.out.println("You can run to escape the fight, attack your opponent, or use potion"
                            + ".\n*Note: Using consumables uses up a turn!");
                }

                else if(playerResponse.equalsIgnoreCase("run")) {
                    System.out.println("You ran away from " + name + ".");
                    invulnerable = false;
                    return;
                }

                else if(playerResponse.equalsIgnoreCase("use potion")) {
                    System.out.println("You have " + p.potions + " potion(s) your inventory. Do you want to use one?");
                    playerResponse =  sc.nextLine();

                    if(playerResponse.equalsIgnoreCase("yes")) {
                        if (p.potions > 0) {
                            p.usePotion();
                            System.out.println();
                            playerTurn = false;
                            invulnerable = false;
                        }

                    }

                    else {
                        continue;
                    }
                }

                else if(attackMode) {


                    if(invulnerable) {
                        System.out.println("Your attacks can't go through! Your opponent is invulnerable this turn!");
                        playerTurn = false;
                        invulnerable = false;
                        continue;
                    }

                    if (!firstAttack) {
                        //display player attacks
                    }
                    double beforeAttackHealth = healthPoints;
                    firstAttack = false;

                    if(playerResponse.equalsIgnoreCase("parry")) {
                        p.parry = true;
                    }

                    else if(playerResponse.equalsIgnoreCase("stab")) {
                        healthPoints -= p.stab();
                    }

                    else if(playerResponse.equalsIgnoreCase("slash")) {
                        healthPoints -= p.slash();
                    }
                    else if(playerResponse.equalsIgnoreCase("punch")) {
                        healthPoints -= p.punch();
                    }
                    double afterAttackHealth = healthPoints;
                    double damageDealt = beforeAttackHealth - afterAttackHealth;
                    if(damageReduction) {
                        healthPoints += 5;
                        damageDealt -= 5;
                        damageReduction = false;
                    }
                    if (p.confused) {
                        if (p.parry) {
                            System.out.println("You parried the confusion!");
                            p.parry = false;
                        }
                        else {
                            healthPoints += damageDealt;
                            p.setCurrentHealth(p.getCurrentHealth() - (damageDealt / 2) );
                            System.out.println("You hit yourself due to being confused");
                        }
                        p.confused = false;
                    }
                    playerTurn = false;
                }

                else {
                    System.out.println("Invalid input! Please try again.");
                }

            }

            else {

                double currentHealth = p.getCurrentHealth();
                rand = new Random();
                int attackNum = rand.nextInt(3) + 1;

                if(attackNum == 1) {
                    FirstAttack(p);
                }

                else if(attackNum == 2) {
                    SecondAttack(p);
                }

                else if(attackNum == 3) {
                    ThirdAttack(p);
                }
                else {
                    System.out.println("Error with Random in Combat Mode");
                    p.setCurrentHealth(0);
                }

                if(p.parry) {
                    System.out.println("You used parry!");
                    if(p.terrified) {
                        System.out.println("You parried the scare!");

                        p.terrified = false;
                        playerTurn = true;
                        continue;
                    }
                    double damage = p.parry(currentHealth - damagedHealth);
                    healthPoints -= damage;
                    System.out.println("You reflected " + damage + " damage to " + name + ".");
                    p.parry = false;
                }

                playerTurn = true;
            }
        }
    }

    public Items getItemDrop() {
        return itemDrop;
    }

    public void FirstAttack(Player p) {
        String attackName = "Kick";
        double attackDmg = 15;
        System.out.println(name + " has used " + attackName);
        p.setCurrentHealth(p.getCurrentHealth() - (attackDmg - ( attackDmg * (p.getDefense() /100) )));
        damagedHealth = p.getCurrentHealth();
    }

    public void SecondAttack(Player p) {
        String attackName = "Stab";
        double attackDmg = 20;
        System.out.println(name + " has used " + attackName);
        p.setCurrentHealth(p.getCurrentHealth() - (attackDmg - (attackDmg * (p.getDefense() / 100) )));
        damagedHealth = p.getCurrentHealth();
    }

    public void ThirdAttack(Player p) {
        int attackNum = rand.nextInt(2) + 1;

        if(attackNum == 1) {
            FirstAttack(p);
        }

        else if(attackNum == 2) {
            SecondAttack(p);
        }

        else {
            System.out.println("Error with Random in Third Attack");
            p.setCurrentHealth(0);
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
