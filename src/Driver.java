import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver
{
    private static Player player;
    private static boolean endGame = false;

    public static void setEndGame(boolean endTheGame)
    {
        endGame = endTheGame;
    }
    static Scanner input= new Scanner(System.in);;
    public static void main(String[] args){
        System.out.println("=======================================");
        System.out.println("\n\n");
        System.out.println("\t\tElusive");
        System.out.println("\n\n");
        System.out.println("=======================================");

        System.out.println("\n\nWhat would you like to do?\n>Create Game\n>Load Game\n>Exit Game");

        boolean invalid = false;
        do
        {
            String choice = input.nextLine();
            invalid = false;
            if(choice.equalsIgnoreCase("Create Game"))
            {
                createGame();
            }
            else if(choice.equalsIgnoreCase("Load Game"))
            {
                invalid = true; // change it for load function
            }
            else if(choice.equalsIgnoreCase("Exit Game"))
            {
                System.exit(0);
            }
            else
            {
                invalid = true;
                System.out.println("Invalid input. Try Again.");
            }
        }while(invalid);


        while (!endGame)
        {
            System.out.println("What would you like to do?");
            //System.out.println("type \"help\" for a list of commands");

            String output = input.nextLine();

            if (output.equalsIgnoreCase("N"))
            {
                player.north();
            }
            else if (output.equalsIgnoreCase("E"))
            {
                player.east();
            }
            else if (output.equalsIgnoreCase("S"))
            {
                player.south();
            }
            else if (output.equalsIgnoreCase("W"))
            {
                player.west();
            }
            else if(output.equalsIgnoreCase("inventory"))
            {
                System.out.println("Player Inventory:");
                player.playerInventory();
            }
            else if(output.equalsIgnoreCase("explore"))
            {
                player.exploreRoom();
            }
            else if(output.equalsIgnoreCase("check stats"))
            {
                player.checkStats();
            }
            else if(output.equalsIgnoreCase("use potion"))
            {
                player.usePotion();
            }

            else if(output.equalsIgnoreCase("exit game"))
            {
                System.out.println("Goodbye.");
                System.exit(0);
            }

            else if(output.equalsIgnoreCase("help"))
            {
                System.out.println("Welcome To The Help Menu! Here Are The List Of Commands:\r\n" +
                        "\r\n" +
                        "Save game\r\n" +
                        "Reload game\r\n" +
                        "Exit game\r\n" +
                        "Inventory\r\n" +
                        "Check stats\r\n" +
                        "Explore\r\n" +
                        "\r\n" +
                        "Items Commands:\r\n" +
                        "Examine [Item Name] - \r\n" +
                        "Pickup [Item Name]\r\n" +
                        "Drop [Item Name]\r\n" +
                        "\r\n" +
                        "Fighting Commands:\r\n" +
                        "Attack \r\n" +
                        "Run \r\n" +
                        "Inventory\r\n" +
                        "");
            }
            else
            {
                String[] stuff = output.split(" ", 2);
                if(stuff.length == 1)
                {
                    System.out.println("Invalid entry.");
                }
                else if(stuff[0].equalsIgnoreCase("talk"))
                {
                    if(stuff[1].substring(0, 2).equals("to"))
                    {
                        String npc = stuff[1].substring(3,stuff[1].length());
                        System.out.println(npc);
                    }
                    else
                    {
                        System.out.println("Invalid entry.");
                    }
                }
                else if(stuff[0].equalsIgnoreCase("pickup"))
                {
                    player.pickup(stuff[1]);
                }
                else if(stuff[0].equalsIgnoreCase("drop"))
                {
                    player.drop(stuff[1]);;
                }
                else if(stuff[0].equalsIgnoreCase("examine"))
                {
                    player.examine(stuff[1]);
                }
                else if(stuff[0].equalsIgnoreCase("equip"))
                {
                    player.equip(stuff[1]);
                }
                else
                {
                    System.out.println("Invalid entry.");
                }
            }
            player.checkEndGame();
        }

        System.out.println("The dragon groans in pain as it falls to the ground, no longer the terrifying beast it once was. You take your weapon and plunge it into the skull of the beast"
                + "\r\nso it may never harm another again. You glance around the room and notice your father laying in the corner of the room.\r\n"
                + "\r\n"
                + "He is no longer breathing. Tears drip down your face as you recall the memories of your childhood.\r\n"
                + "\r\n");
        if(player.checkScroll())
        {
            System.out.println("\r\n"
                    + "A glow resonates from the scroll and appears to transfer to your friends. Unable to speak due to his exhaustion, friend hands you a treasure map."
                    + "\r\nYou think back to when he was first taken, but it all makes sense now. Your friend held the secret to some sort of treasure the dragon wanted. When\r\n"
                    + "the dragon figured he couldn’t extract the treasure, he killed your friend. Now, you could both set onto the adventure to find it.\r\n"
                    + "\r\n"
                    + "The end.");
        }
        else
        {
            System.out.println("The End.");
        }
    }
    public static void createGame()
    {

        System.out.println("\nYou feel the wind brush against your cheek as the sun rises in the distance."
                + "\nOn a regular day you would be inside fast asleep instead of watching the sunrise, but this was no ordinary day."
                + "\nFor weeks, you and your father had this day planned."
                + "\nTypically, dad would be gone before the sun rose, in the mine working until nightfall and then back again the next day. "
                + "\nBut today, you would go fishing.");
        System.out.println("\nWhat is your name?");
        String userName = input.nextLine();
        System.out.println("\nOkay, " + userName);
        System.out.println("\nAfter arriving to the abandoned place, you and some friends got your supplies ready. “Alright, " + userName + ", take out all the equipment.”"
                + "\n(Type in --items-- to see your equipments)");
        String userInput = input.nextLine();
        System.out.println("Good Job! Now swing it into the ethernet box, as hard as you can."
                + "\n(Type in --swing item-- to swing it)");
        userInput = input.nextLine();
        System.out.println("“Now we wait.”");
        System.out.println("You feel any vibration." + "\n“Watch out for thr red light!” "
                + "\n(Type in --reel-- to reel the fish)");
        userInput = input.nextLine();
        System.out.println("\n“There he goes, that’s a big one!”" +
                "\nYou pull out a big wired ethernet box."
                + "\nSuddenly, you hear a vicious roar from the distance as the earth below you shakes. "
                + "\nA giant internet glitch happens in your town, and everyone gets knocked out. " +
                "\nYou wake up in your home, confused. No one is around, you are all another. Many questions floated through your head, but you have the answer to none."
                + "\nYou decide to scrap anything up in your home that you can find, ready to set off on your search.\n\n");
        System.out.println("Now you will be directed to the game.\n\n");

        try
        {
            player = new Player();
        } catch (FileNotFoundException e1)
        {
            System.out.println(e1);
        }
    }
}