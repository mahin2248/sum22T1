public class Items {
    private String itemID;
    private String itemName;
    private String itemDesc;
    private boolean armor;
    private boolean weapon;
    private double defense;
    private double damage;

    public void setArmor(boolean armor) {
        this.armor = armor;
    }

    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public boolean isArmor() {
        return armor;
    }

    public Items(String itemID, String itemName, String itemDesc) {
        super();
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        armor = false;
        weapon = false;
        defense = 0;
        damage = 0;
    }
    public double getDefense() {
        return defense;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setSellValue(int parseInt) {
    }
}