public class Puzzle
{
    private String puzzleID;
    private String puzzleDesc;
    private String answer;
    private int attempt;
    private String puzzleHint;
    private Items itemDrop;
    private int potionDrop;

    public int getPotionDrop()
    {
        return potionDrop;
    }
    public void setPotionDrop(int potionDrop)
    {
        this.potionDrop = potionDrop;
    }
    public Puzzle(String puzzleID, String puzzleDesc, String answer, int attempt, String puzzleHint) {
        this.puzzleID = puzzleID;
        this.puzzleDesc = puzzleDesc;
        this.answer = answer;
        this.attempt = attempt;
        this.puzzleHint = puzzleHint;
        itemDrop = null;
    }
    public String getPuzzleHint()
    {
        return puzzleHint;
    }
    public void setItemDrop(Items itemDrop)
    {
        this.itemDrop = itemDrop;
    }
    public Items getItemDrop()
    {
        return itemDrop;
    }
    public Puzzle()
    {
        puzzleID = "-1";
        puzzleDesc = "none";
        answer = "none";
        attempt = -1;
    }
    public String getPuzzleID()
    {
        return puzzleID;
    }
    public void setPuzzleID(String puzzleID)
    {
        this.puzzleID = puzzleID;
    }
    public String getPuzzleDesc()
    {
        return puzzleDesc;
    }
    public void setPuzzleDesc(String puzzleDesc)
    {
        this.puzzleDesc = puzzleDesc;
    }
    public String getAnswer()
    {
        return answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    public int getAttempt()
    {
        return attempt;
    }
    public void setAttempt(int attempt)
    {
        this.attempt = attempt;
    }
}