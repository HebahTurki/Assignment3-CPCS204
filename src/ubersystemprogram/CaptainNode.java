package ubersystemprogram;

/**
 *
 * @author hebahturki
 */
public class CaptainNode 
{
    private int captainID;
    private String CaptainName;
    private int star;
    private boolean available;
    private CaptainNode right;
    private CaptainNode left;

    public CaptainNode(int captainID, String CaptainName, int star, boolean available, CaptainNode right, CaptainNode left) 
    {
        this.captainID = captainID;
        this.CaptainName = CaptainName;
        this.star = star;
        this.available = available;
        this.right = right;
        this.left = left;
    }

    public CaptainNode() 
    {
        
    }
    
    public CaptainNode(int ID, String name) 
    {
        this(ID, name, 0, true);
    }
 
    public CaptainNode(int ID, String name, int star, boolean available) 
    {
        this(ID, name, star, true, null, null);
    }

    public int getCaptainID() {
        return captainID;
    }

    public void setCaptainID(int captainID) {
        this.captainID = captainID;
    }

    public String getCaptainName() {
        return CaptainName;
    }

    public void setCaptainName(String CaptainName) {
        this.CaptainName = CaptainName;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public CaptainNode getRight() {
        return right;
    }

    public void setRight(CaptainNode right) {
        this.right = right;
    }

    public CaptainNode getLeft() {
        return left;
    }

    public void setLeft(CaptainNode left) {
        this.left = left;
    }
    
}
