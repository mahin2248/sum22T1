import java.util.ArrayList;
public class Room
{
    private String roomID;
    private String roomName;
    private String roomDesc;
    private boolean lockedRoom;
    private String northRoomID;
    private String southRoomID;
    private String westRoomID;
    private String eastRoomID;
    private final ArrayList<Items> item;
    private Puzzle puzzle;
    private Monster monster;

    public Monster getMonster()
    {
        return monster;
    }
    public Room(String roomID,String roomName, String roomDesc,boolean lockedRoom, String northRoomID,String southRoomID, String westRoomID, String eastRoomID)
    {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.lockedRoom = lockedRoom;
        this.northRoomID = northRoomID;
        this.southRoomID = southRoomID;
        this.westRoomID = westRoomID;
        this.eastRoomID = eastRoomID;
        item = new ArrayList<>();
        puzzle = new Puzzle();
    }
    public String getRoomID()
    {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    public String getRoomName()
    {
        return roomName;
    }
    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }
    public String getRoomDesc()
    {
        return roomDesc;
    }
    public void setRoomDesc(String roomDesc)
    {
        this.roomDesc = roomDesc;
    }
    public boolean lockedRoom()
    {
        return lockedRoom;
    }
    public void lockedRoom(boolean lockedRoom)
    {
        this.lockedRoom = lockedRoom;
    }
    public String getNorthRoomID()
    {
        return northRoomID;
    }
    public void setNorthRoomID(String northRoomID)
    {
        this.northRoomID = northRoomID;
    }
    public String getSouthRoomID()
    {
        return southRoomID;
    }
    public void setSouthRoomID(String southRoomID)
    {
        this.southRoomID = southRoomID;
    }
    public String getWestRoomID()
    {
        return westRoomID;
    }
    public void setWestRoomID(String westRoomID)
    {
        this.westRoomID = westRoomID;
    }
    public String getEastRoomID()
    {
        return eastRoomID;
    }
    public boolean isLockedRoom()
    {
        return lockedRoom;
    }
    public void setLockedRoom(boolean lockedRoom)
    {
        this.lockedRoom = lockedRoom;
    }
    public void setEastRoomID(String eastRoomID)
    {
        this.eastRoomID = eastRoomID;
    }
    public ArrayList<Items> getItem()
    {
        return item;
    }
    public Puzzle getPuzzle()
    {
        return puzzle;
    }
    public void setPuzzle(Puzzle puzzle)
    {
        this.puzzle = puzzle;
    }
}
