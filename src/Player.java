import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player
{
    private Room currentRoom;
    private Map map;
    private ArrayList<Items> playerInventory;
    private final double BASE_HEALTH = 100;
    private double currentHealth;
    private double defense;
    public boolean confused;
    public boolean terrified;
    public boolean parry;
    public int coins = 10;
    public int potions;
    public double strength = 35;
    private Items armor = null;
    private Items weapon = null;
    private boolean crown = false;

    public double getCurrentHealth()
    {
        return currentHealth;
    }

    public double getBASE_HEALTH()
    {
        return BASE_HEALTH;
    }

    public void setCurrentHealth(double currentHealth)
    {
        this.currentHealth = currentHealth;
    }
    public double getDefense()
    {
        return defense;
    }
    public ArrayList<Items> getPlayerInventory()
    {
        return playerInventory;
    }
    public Player() throws FileNotFoundException
    {
        confused = false;
        terrified = false;
        parry = false;
        map = new Map();
        currentRoom = map.getRooms("1");
        playerInventory = new ArrayList<Items>();
        System.out.println(currentRoom.getRoomDesc());
        currentHealth = BASE_HEALTH;
        defense = 50;
        potions = 1;
    }
    public void checkEndGame()
    {
        for(Items i: playerInventory)
        {
            if(i.getItemID().equals("A00"))
            {
                Driver.setEndGame(true);
            }
        }
    }
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public Map getMap()
    {
        return map;
    }

    public void north()
    {
        if(currentRoom.getNorthRoomID().equals("0"))
        {
            System.out.println("you can't go in that direction");
        }
        else {
            if(map.getRooms(currentRoom.getNorthRoomID()).isLockedRoom())
            {
                unlockDoor(map.getRooms(currentRoom.getNorthRoomID()));
                if (map.getRooms(currentRoom.getNorthRoomID()).isLockedRoom())
                {
                    return;
                }
            }

            currentRoom = map.getRooms(currentRoom.getNorthRoomID());
            System.out.println(currentRoom.getRoomDesc());
        }
    }
    public void south()
    {
        if(currentRoom.getSouthRoomID().equals("0"))
        {
            System.out.println("you can't go in that direction");
        }
        else
        {
            if(map.getRooms(currentRoom.getSouthRoomID()).isLockedRoom())
            {
                unlockDoor(map.getRooms(currentRoom.getSouthRoomID()));
                if (map.getRooms(currentRoom.getSouthRoomID()).isLockedRoom())
                {
                    return;
                }
            }
            currentRoom = map.getRooms(currentRoom.getSouthRoomID());
            System.out.println(currentRoom.getRoomDesc());
        }
    }
    public void west()
    {
        if(currentRoom.getWestRoomID().equals("0"))
        {
            System.out.println("you can't go in that direction");
        }
        else
        {
            if(map.getRooms(currentRoom.getWestRoomID()).isLockedRoom())
            {
                unlockDoor(map.getRooms(currentRoom.getWestRoomID()));
                if (map.getRooms(currentRoom.getWestRoomID()).isLockedRoom())
                {
                    return;
                }
            }

            currentRoom = map.getRooms(currentRoom.getWestRoomID());
            System.out.println(currentRoom.getRoomDesc());
        }
    }
    public void east()
    {
        if(currentRoom.getEastRoomID().equals("0"))
        {
            System.out.println("you can't go in that direction");
        }
        else
        {
            if(map.getRooms(currentRoom.getEastRoomID()).isLockedRoom())
            {
                unlockDoor(map.getRooms(currentRoom.getEastRoomID()));
                if (map.getRooms(currentRoom.getEastRoomID()).isLockedRoom())
                {
                    return;
                }
            }

            currentRoom = map.getRooms(currentRoom.getEastRoomID());
            System.out.println(currentRoom.getRoomDesc());

        }
    }

    public void exploreRoom()
    {
        System.out.println();
        if (currentRoom.getMonster() == null)
        {
            System.out.println("There is no enemy to fight here.");
        }
        else if (currentRoom.getMonster().name.equals("none"))
        {
            System.out.println("There is no enemy to fight here.");
        }
        else {
            System.out.println(currentRoom.getMonster().monsterDesc);
            //if monster is a bandit
            if(currentRoom.getMonster().name.equals("Bandit"))
            {
                System.out.println("What do you want to do?");
                System.out.println("> Give 20 Gold\n> Deny Request");
                Scanner in = new Scanner(System.in);
                String playerAnswer = in.nextLine();
                boolean valid = false;
                while(!valid)
                {
                    if(playerAnswer.equalsIgnoreCase("give 20 gold"))
                    {
                        valid = true;
                        if(coins < 20)
                        {
                            System.out.println("You don't have enough money to pay the bandit! Looks like you're going to have to fight.");
                            currentRoom.getMonster().CombatMode(this);
                        }
                        else
                        {
                            coins -= 20;
                            System.out.println("You pay the bandit to avoid conflict. You have " + coins + " Elusive coins now");
                            currentRoom.getMonster().setName("none");
                        }
                    }

                    else if(playerAnswer.equalsIgnoreCase("deny request"))
                    {
                        System.out.println("That may have made the bandit angry. Prepare for a fight!");
                        currentRoom.getMonster().CombatMode(this);
                        valid = true;
                    }

                    else
                    {
                        System.out.println("Invalid input. Please Try Again.");
                    }
                }

            }

            else
            {
                currentRoom.getMonster().CombatMode(this);
            }
            System.out.println();
        }

        if(!currentRoom.getPuzzle().getPuzzleID().equals("-1"))
        {
            System.out.println("There is a puzzle in this room. Would you like to solve it? "
                    + "Please type \"yes\" to solve, and \"no\" or \"ignore\" to ignore the puzzle.");
            Scanner in = new Scanner(System.in);
            boolean invalid = true;
            while(invalid)
            {
                String option = in.nextLine();

                if(option.equalsIgnoreCase("yes"))
                {
                    puzzleMode();
                    invalid = false;
                }

                else if(option.equalsIgnoreCase("no") || option.equalsIgnoreCase("ignore"))
                {
                    invalid = false;
                }
                else
                {
                    System.out.println("Invalid command");
                }
            }
        }

        if(currentRoom.getItem().size() == 0)
        {
            System.out.println("There's no item to explore in this room.");
        }
        else
        {
            System.out.println("Items available in room: ");

            for (Items i : currentRoom.getItem()) System.out.println(i.getItemName());
        }
    }
    public void examine(String name)
    {
        System.out.println();
        if(playerInventory.size() == 0)
        {
            System.out.println("You do not have any item in your inventory to examine.");
        }

        else if(name.equalsIgnoreCase("potion") || name.equalsIgnoreCase("health potion"))
        {
            System.out.println("A small vial of red liquid that restores vitality of the player");
        }

        else
        {
            for(int i = 0; i < playerInventory.size();i++)
            {
                if(name.equalsIgnoreCase(playerInventory.get(i).getItemName()))
                {
                    System.out.println("You have examined: " + name);
                    System.out.println(playerInventory.get(i).getItemDesc());
                    return;
                }

            }
            System.out.println("This item isn't available to examine.");
        }

    }
    public void pickup(String name)
    {
        System.out.println();
        if(currentRoom.getItem().size() == 0)
        {
            System.out.print("There is no item to pickup");
        }
        else
        {
            for(int i = 0; i < currentRoom.getItem().size();i++)
            {
                if(name.equalsIgnoreCase(currentRoom.getItem().get(i).getItemName()))
                {
                    playerInventory.add(currentRoom.getItem().get(i));
                    currentRoom.getItem().remove(i);
                    System.out.println("You have picked up " + playerInventory.get(playerInventory.size() - 1).getItemName());
                    return;
                }
            }
            System.out.println("This item isn't available to pickup.");
        }
    }

    public void drop(String name)
    {
        System.out.println();
        if(playerInventory.size() == 0)
        {
            System.out.print("You do not have any item in your inventory to drop.");
        }
        else
        {
            for(int i = 0; i < playerInventory.size();i++)
            {
                if(name.equalsIgnoreCase(playerInventory.get(i).getItemName()))
                {
                    currentRoom.getItem().add(playerInventory.get(i));
                    playerInventory.remove(i);
                    System.out.println("You have dropped the chosen item.");
                    return;
                }
            }
            System.out.println("This item isn't available to drop.");
        }
    }

    public void playerInventory()
    {
        if(playerInventory.size() == 0 && coins == 0 && potions == 0 && armor == null && weapon == null) {
            System.out.println("You do not have any item in your inventory.");
        }

        if(armor == null)
        {
            System.out.println("Armor: Not Equipped");
        }

        else
        {
            System.out.println("Armor: " + armor.getItemName());
        }

        if(weapon == null)
        {
            System.out.println("Weapon: Not Equipped");
        }

        else
        {
            System.out.println("Weapon: " + weapon.getItemName());
        }

        if(playerInventory.size() == 0 && (coins > 0 || potions > 0))
        {
            System.out.println("Elusive coins: " + coins);
            System.out.println("Health Potions: " + potions);
        }
        else
        {
            for(int i = 0; i < playerInventory.size();i++)
            {
                System.out.println(playerInventory.get(i).getItemName());
            }
            System.out.println("Elusive coins: " + coins);
            System.out.println("Health Potions: " + potions);
        }
    }
    public void puzzleMode()
    {
        if(!currentRoom.getPuzzle().getPuzzleID().equals("-1"))
        {
            int attempts = currentRoom.getPuzzle().getAttempt();
            System.out.println("Please answer the following puzzle:");
            Scanner input = new Scanner(System.in);
            for(int a = attempts - 1;a >= 0;a--)
            {
                System.out.println(currentRoom.getPuzzle().getPuzzleDesc());
                String playerAns = input.nextLine();
                if(playerAns.equalsIgnoreCase(currentRoom.getPuzzle().getAnswer()))
                {
                    currentRoom.getPuzzle().setPuzzleID("-1");
                    System.out.println("Puzzle solved. Amazing job!");
                    System.out.println("You have received: ");
                    if(currentRoom.getPuzzle().getItemDrop() != null)
                    {
                        getPlayerInventory().add(currentRoom.getPuzzle().getItemDrop());
                        System.out.println("> " + currentRoom.getPuzzle().getItemDrop().getItemName());
                    }

                    if (currentRoom.getPuzzle().getPotionDrop() > 0)
                    {
                        potions += currentRoom.getPuzzle().getPotionDrop();
                        System.out.println("> " + currentRoom.getPuzzle().getPotionDrop() + " potion(s)");
                    }
                    return;
                }
                else
                {
                    System.out.println("That is incorrect. You have " + a + " attempts remaining.");
                    if (a == 2) {
                        System.out.println(currentRoom.getPuzzle().getPuzzleHint());
                    }
                }
            }

            System.out.println("You answer to this puzzle is: " + currentRoom.getPuzzle().getAnswer());
            System.out.println("You have lost your chance to solve the puzzle this time.");
        }
    }

    //added this
    public double parry(double damage)
    {
        Random r = new Random();
        int randomNum = r.nextInt(10) + 1;
        if (randomNum <= 7) {
            currentHealth += damage;
            System.out.println("You dodged the attack!");
        }
        else
        {
            System.out.println("You did not dodge the attack.");
        }
        return damage * 0.2;
    }
    public double stab()
    {
        System.out.print("You used Stab.");
        Random r = new Random();
        int rand = r.nextInt(10) + 1;
        if(rand <= 6)
        {
            System.out.println(" It was effective!");
            return 35 + strength;
        }
        else
        {
            System.out.println(" It was ineffective!");
            return 0;
        }

    }
    public void checkStats()
    {
        System.out.println("Player Stats:");
        System.out.println("HP: " + currentHealth);
        System.out.println("Defense: " + defense);
        System.out.println("Strength: " + strength);
    }
    public double slash()
    {
        System.out.print("You used Slash.");
        Random r = new Random();
        int rand = r.nextInt(10) + 1;
        if(rand <= 8)
        {
            System.out.println(" It was effective!");
            return 25 + strength;
        }
        else
        {
            System.out.println(" It was ineffective!");
            return 0;
        }
    }
    public double punch()
    {
        System.out.print("You used Punch.");
        System.out.println(" It was effective!");
        return 15 + strength;
    }

    public void confuse()
    {
        Random r = new Random();
        int randomNum = r.nextInt(10) + 1;
        if(randomNum >= 3) {
            confused = true;
        }

        else
        {
            confused = false;
        }
    }
    public void usePotion()
    {
        if(potions > 0)
        {
            if(currentHealth == BASE_HEALTH)
            {
                System.out.println("You're already at full health!");
                return;
            }
            potions -= 1;
            currentHealth += 20;
            System.out.println("You used your potion.");
            if (currentHealth > BASE_HEALTH)
            {
                currentHealth = BASE_HEALTH;
            }
            System.out.println("Your health is now " + currentHealth);
        }
        else
        {
            System.out.println("You're out of potions!");
        }
    }

    public void sellPotion()
    {
        potions -= 1;
        coins += 5;
        System.out.println("You sold your potion and got 5 Elusive coin.");
    }
    private void unlockDoor(Room currentRoom)
    {
        for(int i = 0; i < playerInventory.size(); i++)
        {
            if(playerInventory.get(i).getItemName().equals("Magic Key"))
            {
                currentRoom.setLockedRoom(false);
                System.out.println("You have used the magic key to unlock the door");
                return;
            }
        }
        System.out.println("You must have Magic Key in your inventory, try exploring other areas first.");
    }
    public void equip(String name)
    {
        Items item = null;
        for(Items i: playerInventory)
        {
            if(name.equalsIgnoreCase(i.getItemName()))
            {
                item = i;
                break;
            }
        }

        if(item == null)
        {
            System.out.println("Item is not in inventory.");
            return;
        }

        if(item.isArmor())
        {
            if(armor != null)
            {
                defense -= armor.getDefense();
                System.out.print(armor.getItemName() + " is unequipped. ");
                playerInventory.add(armor);
            }
            defense += item.getDefense();
            armor = item;
            System.out.println(item.getItemName() + " is equipped. You gained +" + item.getDefense() + " defense");
            for(Items i: playerInventory)
            {
                if(item.equals(i))
                {
                    playerInventory.remove(i);
                    break;
                }
            }
        }

        else if(item.isWeapon())
        {
            if(weapon != null)
            {
                if(!crown)
                {
                    strength -= weapon.getDamage();
                }

                else
                {
                    strength /= 1.25;
                    strength -= weapon.getDamage();
                }

                System.out.print(weapon.getItemName() + " is unequipped. ");
                playerInventory.add(weapon);
            }
            strength += item.getDamage();
            weapon = item;
            System.out.println(item.getItemName() + " is equipped. You gained +" + item.getDamage() + " strength");
            if(crown)
            {
                strength = strength + strength * 0.25;
                System.out.println("The strength has been increased by the crown to " + strength + " strength");
            }
            for(Items i: playerInventory)
            {
                if(item.equals(i)) {
                    playerInventory.remove(i);
                    break;
                }
            }
        }
        else if(item.getItemID().equals("A08"))
        {
            crown = true;
            playerInventory.remove(item);
            strength = strength + strength * 0.25;
            System.out.println(item.getItemName() + " is equipped. You now gain a bonus of 25% damage increase");
        }
        else
        {
            System.out.println("This item is not equipable.");
        }
    }
    public boolean checkScroll()
    {
        for(int i = 0; i < playerInventory.size(); i++) {
            if(playerInventory.get(i).getItemName().equals("Resurrection Scroll"))
            {
                System.out.println("You have used the resurrection scroll!");
                return true;
            }
        }
        System.out.println("You do not have the resurrection scroll...");
        return false;
    }
}