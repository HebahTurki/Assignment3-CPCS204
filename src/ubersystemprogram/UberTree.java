package ubersystemprogram;
import java.io.*;
/**
 *
 * @author hebahturki
 */
public class UberTree 
{
    private CaptainNode root;

    public UberTree() 
    {
        
    }

    public UberTree(CaptainNode root)
    {
        this.root = root;
    }
    
    public void add_Captain(int ID, String name)
    { 
        //insert new node to the BST
        root = add_Captain(root, new CaptainNode(ID, name));
    }
    
    private CaptainNode add_Captain(CaptainNode currentRoot, CaptainNode newNode)
    {
        if (currentRoot == null)
        { 
            //if tree is empty, set the new node as the root node directly.
            return newNode;
        } 
        else
        { 
            //if tree is not empty:
            if(currentRoot.getCaptainID() > newNode.getCaptainID())
            { 
                //if new player's id is less than the root's, insert to left.
                currentRoot.setLeft(add_Captain(currentRoot.getLeft(), newNode));
            } 
            else
            { 
                // else means the new player's id is greater than the root's
                currentRoot.setRight(add_Captain(currentRoot.getRight(), newNode));
            }
        }
        return currentRoot; //return the updated root of the tree.
    }
    
    public CaptainNode findCaptain(int id)
    {
        return findCaptain(root, id);
    }
    
    private CaptainNode findCaptain(CaptainNode p, int id) 
    {
        if (p==null)
        {
            return null;
        } 
        else
        {
            if(p.getCaptainID()==id)
            {
                return p;
            } 
            else if (id < p.getCaptainID())
            {
                return findCaptain(p.getLeft(),id);
            } 
            else 
            {
                return findCaptain(p.getRight(),id);
            }
        }
    }
    
    public void BOOK_RIDE(int id, PrintWriter pr){
        BOOK_RIDE(root, id, pr);
    }
    private void BOOK_RIDE(CaptainNode p, int id, PrintWriter pr) {
        CaptainNode Captain = findCaptain(id);
        if(Captain != null)
        {
            if(Captain.isAvailable())
            {
                Captain.setAvailable(false);//change the availablty
                pr.println("Command BOOK_RIDE: Book a new Ride with captin "+Captain.getCaptainID()+"\n");
            }
            else
            {
                pr.println("Command BOOK_RIDE: The captain "+Captain.getCaptainName()+" is not available. He is on another ride!"+"\n");
            }
        } 
        else 
        {
            pr.println("Command BOOK_RIDE: Couldn't find any captain with ID number "+id+"\n");
        }
    }
    
    public void FINISH_RIDE(int id,int score,PrintWriter pr){
        FINISH_RIDE(root, id, score, pr);
    }
    private void FINISH_RIDE(CaptainNode p, int id, int score, PrintWriter pr) {
        CaptainNode Captain = findCaptain(id);
        if(Captain != null)
        {
            if(!Captain.isAvailable())
            {
                Captain.setAvailable(true);//change the availablty
                int star = Captain.getStar();
                if(score==0)
                {
                    if(star>0&&star<=5)//rate down
                    Captain.setStar(star-1);
                }
                else if(score==1)
                {
                    if(star>=0&&star<=5)//rate up
                    Captain.setStar(star+1);
                }
                    
                pr.println("Command FINISH_RIDE: Finish ride with captin "+Captain.getCaptainID());
                display_all_Captain_info_by_ID(Captain.getCaptainID(),pr);
            }
            else
            {
                pr.println("Command FINISH_RIDE: The  captain "+Captain.getCaptainName()+" is not in a ride!");
                pr.println("----------------------------------------------------------------");
            }
        } 
        else 
        {
            pr.println("Command FINISH_RIDE: Couldn't find any captain with ID number "+id+"\n");
            pr.println("----------------------------------------------------------------");
        }
    }
    
    public void display_all_Captain_info(PrintWriter pr)
    {
        display_all_Captain_info(root,pr);
    }
    
    private void display_all_Captain_info(CaptainNode p, PrintWriter pr)
    {
        if(p!=null)
        {
            String isAvailable = p.isAvailable()+"";
            if(isAvailable.equals("true"))
                isAvailable = "True";
            else
                isAvailable = "False";
            pr.println("\n			ID: "+p.getCaptainID()+"\n" +
                        "			Name: "+p.getCaptainName()+"\n" +
                        "			Available: "+isAvailable+" \n" +
                        "			Rating star: "+p.getStar()+"\n			");
            pr.println("----------------------------------------------------------------");
            display_all_Captain_info(p.getLeft(), pr);
            display_all_Captain_info(p.getRight(), pr);
        } 
    }    
    
    public void display_all_Captain_info_by_ID(int id, PrintWriter pr)
    {
        display_all_Captain_info_by_ID(root, id, pr);
    }
    
    private void display_all_Captain_info_by_ID(CaptainNode p, int id, PrintWriter pr)
    {
        CaptainNode Captain = findCaptain(id);
        if(Captain!=null)
        {
            String isAvailable = Captain.isAvailable()+"";
            if(isAvailable.equals("true"))
                isAvailable = "True";
            else
                isAvailable = "False";
            pr.println("\n			ID: "+Captain.getCaptainID()+"\n" +
                        "			Name: "+Captain.getCaptainName()+"\n" +
                        "			Available: "+isAvailable+" \n" +
                        "			Rating star: "+Captain.getStar()+"\n	");
            pr.println("----------------------------------------------------------------");
        } 
        else
        {
            pr.println(" Couldn't find any captain with ID number "+id+"\n");
            pr.println("----------------------------------------------------------------");
        }
    }
    
    public void delete(int ID) 
    {
	root = delete(root, ID);
    }
    
    public boolean isLeaf(CaptainNode p) 
    {
	return (p.getLeft()==null && p.getRight()==null);
    }
    
    public boolean hasOnlyLeftChild(CaptainNode p) 
    {
	return (p.getLeft()!=null && p.getRight()==null);
    }

    public boolean hasOnlyRightChild(CaptainNode p) 
    {
	return (p.getLeft()==null && p.getRight()!=null);
    }
	
    private CaptainNode delete(CaptainNode p, int ID) 
    {
        CaptainNode node2delete, newnode2delete, node2save, parent;
	int saveID, saveStars;
        String saveName;
        boolean saveAvailable;
		
	// Step 1: Find the node we want to delete
	node2delete = findCaptain(p, ID);
	// If node is not found (does not exist in tree), we clearly cannot delete it.
	if (node2delete == null)
            return root;	
	// Step 2: Find the parent of the node we want to delete
	parent = parent(p, node2delete);
	// Step 3: Perform Deletion based on three possibilities
	// **1** :  node2delete is a leaf node
	if (isLeaf(node2delete)) 
        {
	// if parent is null, this means that node2delete is the ONLY node in the tree
            if (parent == null)
		return null; // we return null as the updated root of the tree
		// Delete node if it is a left child
            if (ID < parent.getCaptainID())
                parent.setLeft(null);
		// Delete node if it is a right child
            else
                parent.setRight(null);
                // Finally, return the root of the tree (in case the root got updated)
            return p;
	}
		
	// **2** : node2delete has only a left child
	if (hasOnlyLeftChild(node2delete)) 
        {
            // if node2delete is the root
            if (parent == null)
		return node2delete.getLeft();
            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (ID < parent.getCaptainID())
		parent.setLeft(parent.getLeft().getLeft());
            // ELSE it is the right child of some node
            else
		parent.setRight(parent.getRight().getLeft());
            // Finally, return the root of the tree (in case the root got updated)
            return p;
	}
	// **3** :  node2delete has only a right child
	if (hasOnlyRightChild(node2delete)) 
        {
            // if node2delete is the root
            if (parent == null)
		return node2delete.getRight();
            // If node2delete is not the root,
            // it must the left OR the right child of some node	
            // IF it is the left child of some node
            if (ID < parent.getCaptainID())
		parent.setLeft(parent.getLeft().getRight());
            // ELSE it is the right child of some node
            else
                parent.setRight(parent.getRight().getRight());
            // Finally, return the root of the tree (in case the root got updated)
            return p;
	}
	// **4** :  node2delete has TWO children.
	newnode2delete = findLargest(node2delete.getLeft());
        saveID = newnode2delete.getCaptainID();
        saveName = newnode2delete.getCaptainName();
        saveStars = newnode2delete.getStar();
        saveAvailable = newnode2delete.isAvailable();
        p = delete(p, saveID);
        node2delete.setCaptainID(saveID);
        node2delete.setCaptainName(saveName);
        node2delete.setStar(saveStars);
        node2delete.setAvailable(saveAvailable);
        return p;
    }
    
    public CaptainNode parent(CaptainNode p) 
    {
	return parent(root, p);
    }
	
    private CaptainNode parent(CaptainNode root, CaptainNode p) 
    {
	// Take care of NULL cases
	if (root == null || root == p)
            return null; // because there is on parent
	// If root is the actual parent of node p
	if (root.getLeft()==p || root.getRight()==p)
            return root; // because root is the parent of p
		
	// Look for p's parent in the left side of root
	if (p.getCaptainID() < root.getCaptainID())
            return parent(root.getLeft(), p);
		
	// Look for p's parent in the right side of root
	else if (p.getCaptainID() > root.getCaptainID())
            return parent(root.getRight(), p);
	// Take care of any other cases
	else
            return null;
    }
    
    public CaptainNode findLargest()
    {
        return findLargest(root);
    }
    
    private CaptainNode findLargest(CaptainNode p)
    {
        if(p==null)
        {
            return null;
        } 
        else
        {
            if (p.getRight()== null) 
            {
                return p;
            } 
            else
            {
                return findSmallest(p.getRight());
            }
        }
    }
    
    public CaptainNode findSmallest()
    {
        return findSmallest(root);
    }
    
    private CaptainNode findSmallest(CaptainNode p)
    {
        if(p==null)
        {
            return null;
        } 
        else 
        {
            if (p.getLeft() == null) 
            {
                return p;
            }
            else
            {
                return findSmallest(p.getLeft());
            }
        }
    }
    
    
    
}
