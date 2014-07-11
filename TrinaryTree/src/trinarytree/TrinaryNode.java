package trinarytree;

/**
 * A node on a trinary tree
 * @author Brian Murphy
 * @param <T> trinary nodes contain a comparable object of type T
 */
public class TrinaryNode<T extends Comparable<T>>
                implements Comparable<TrinaryNode<T>> 
{

    //the left node, those less than this node
    private TrinaryNode<T> lessThan;
    //the middle node, those equal to this node
    private TrinaryNode<T> equalTo;
    //the right node, those greater than this node
    private TrinaryNode<T> moreThan; 
    //the value of this node
    private T value;

    private TrinaryNode()
    {
        //intentionaly left blank        
    }
    
    /**
     * Force each node to be created with a value.
     * @param value the value of this node
     */
    public TrinaryNode(T value)
    {
        this.value = value;
    }
    /**
     * Gets left branch, values less than this node
     * @return the left branch
     */
    public TrinaryNode getLeftBranch()
    {
        return lessThan;
    }

    /**
     * Sets the left branch
     * @param lessThan the node to set value to
     */
    public void setLeftBranch(TrinaryNode lessThan)
    {
        this.lessThan = lessThan;
    }

    /**
     * Gets the middle branch, nodes equal to this node
     * @return the middle branch
     */
    public TrinaryNode getMiddleBranch()
    {
        return equalTo;
    }

    /**
     * Sets the middle branch
     * @param equalTo the node to set as the middle branch
     */
    public void setMiddleBranch(TrinaryNode equalTo)
    {
        this.equalTo = equalTo;
    }

    /**
     * Gets the right branch, values greater than this node
     * @return the right branch
     */
    public TrinaryNode getRightBranch()
    {
        return moreThan;
    }

    /**
     * Set the right branch
     * @param moreThan the node to set the right branch to
     */
    public void setRightBranch(TrinaryNode moreThan)
    {
        this.moreThan = moreThan;
    }
    
    /**
     * Returns the value of this node
     * @return the value of this node
     */
    public T getValue()
    {
        return value; 
    }
    
    /**
     * Recursively adds a child to the correct branch
     * @param child the node that is being added
     */
    public void addChild(TrinaryNode<T> child)
    {
       //If the nodes are equal
       if(this.compareTo(child) == 0)
       {
           //If there are no nodes already in the middle branch, add it there
           if(equalTo == null)
           {
               equalTo = child;
           }
           else
           {
               //If there is already a node in the middle, make the new addition
               //a child of that node
               equalTo.addChild(child);
           }
       }
       //If the node is less than this node
       else if(this.compareTo(child)> 0)
       {    
          //if there is no nodes on the left, add it there
           if(lessThan == null)
           {
               lessThan = child;
           }
           else
           {
               lessThan.addChild(child);
           }
       
       }
       //the node must be greater than this node
       else
       {
           if(moreThan == null)
           {
               moreThan = child;               
           }
           else
           {
               moreThan.addChild(child);
           }
           
       }
       
    }  
    
    @Override
    public int compareTo(TrinaryNode<T> node)
    {
       return value.compareTo(node.getValue());
    }

    
}
