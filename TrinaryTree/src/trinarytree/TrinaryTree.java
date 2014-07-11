package trinarytree;

/**
 * A trinary tree, a tree with tree possible branches at each node. The leftmost
 * branch has values less than the node. The middle branch has values equal to
 * the node. The rightmost branch has values greater than the node.
 *
 * @author Brian Murphy
 * @param <T>
 */
public class TrinaryTree<T extends Comparable<T>>
{
    //the root of the tree
    private TrinaryNode<T> root;

    public TrinaryTree()
    {
        //intentionally left blank        
    }

    /**
     * Inserts a value into the trinary tree. Values are inserted as follows
     * Values greater than a node are inserted on the right branch of the node
     * Values less than a node are inserted on the left branch of the node
     * Values equal to a node are inserted on the middle branch Insertion starts
     * at the root and moves downward
     *
     * @param value the value to be inserted
     */
    public void insert(T value)
    {
        if (root == null)
        {
            root = new TrinaryNode<>(value);
        }
        else
        {
            root.addChild(new TrinaryNode<>(value));
        }
    }

    /**
     * Deletes the highest node with matching value from the tree. The priority
     * for the children of this node to take the place of the deleted node in
     * the tree is middle, left, right.
     *
     * @param value the value to delete
     * @return true if the value is removed, false if not
     */
    public boolean delete(T value)
    {
        if(root == null)
        {
            return false;
        }
        TrinaryNode<T> parentNode;
        //deleting the root or a node with a parent
        if (root.getValue().compareTo(value) == 0)
        {
            root = deleteRoot(root);
        }
        else
        {
            //find the parent of the node we want to delete
            parentNode = findParent(root, value);
            if(parentNode == null)
            {
                return false;
            }
            //check to see if the node we want to delete is on the left or right
            //it will never be the middle, because a middle node will always 
            //have a parent or be the root
            else if (parentNode.getLeftBranch() != null
                && parentNode.getLeftBranch().getValue().compareTo(value) == 0)
            {
                deleteLeft(parentNode);
            }
            else if (parentNode.getRightBranch() != null
                     && parentNode.getRightBranch()
                             .getValue().compareTo(value) == 0)
            {
                deleteRight(parentNode);
            }
        }
        return true;
    }

    /**
     * Deletes the a node on the parents right branch
     * @param parentNode the node whose first right child will be deleted
     */
    private void deleteRight(TrinaryNode<T> parentNode)
    {
        TrinaryNode<T> nodeToBeDeleted =  parentNode.getRightBranch();
        //If there is a middle branch, replace the node with the middle 
        if (nodeToBeDeleted.getMiddleBranch() != null)
        {
            nodeToBeDeleted.getMiddleBranch()
                    .setLeftBranch(nodeToBeDeleted.getLeftBranch());
            nodeToBeDeleted.getMiddleBranch()
                    .setRightBranch(nodeToBeDeleted.getRightBranch());
            parentNode.setRightBranch(nodeToBeDeleted.getMiddleBranch());
        }
        //if there is no middle branch, replace the node with the left
        else if (nodeToBeDeleted.getLeftBranch() != null)
        {
            if (nodeToBeDeleted.getRightBranch() != null)
            {
                nodeToBeDeleted.getLeftBranch()
                        .addChild(nodeToBeDeleted.getRightBranch());
            }
            parentNode.setRightBranch(nodeToBeDeleted.getLeftBranch());
        }
        //if there is no middle or left, replace the node with the right
        else if (nodeToBeDeleted.getRightBranch() != null)
        {
            parentNode.setRightBranch(nodeToBeDeleted.getRightBranch());
        }
        //if the node is a leaf, just delete it
        else
        {
            parentNode.setRightBranch(null);
        }
    }
    
    /**
     * Deletes the first node on the parents left branch
     * @param parentNode the node whose first left child will be deleted
     */
    private void deleteLeft(TrinaryNode<T> parentNode)
    {
        TrinaryNode<T> nodeToBeDeleted =  parentNode.getLeftBranch();
         //If there is a middle branch, replace the node with the middle 
        if (nodeToBeDeleted.getMiddleBranch() != null)
        {
            nodeToBeDeleted.getMiddleBranch()
                    .setLeftBranch(nodeToBeDeleted.getLeftBranch());
            nodeToBeDeleted.getMiddleBranch()
                    .setRightBranch(nodeToBeDeleted.getRightBranch());
            parentNode.setLeftBranch(nodeToBeDeleted.getMiddleBranch());
        }
        //if there is no middle branch, replace the node with the left
        else if (nodeToBeDeleted.getLeftBranch() != null)
        {
            if (nodeToBeDeleted.getRightBranch() != null)
            {
                nodeToBeDeleted.getLeftBranch().addChild(nodeToBeDeleted.getRightBranch());
            }
            parentNode.setLeftBranch(nodeToBeDeleted.getLeftBranch());
        }
         //if there is no middle or left, replace the node with the right
        else if (nodeToBeDeleted.getRightBranch() != null)
        {
            parentNode.setLeftBranch(nodeToBeDeleted.getRightBranch());
        }
         //if the node is a leaf, just delete it
        else
        {
            parentNode.setLeftBranch(null);
        }
    }

    /**
     * Deletes a root in the trinary tree
     * @param node the root to be deleted
     * @return the new root or null if there are no more nodes in the tree
     */
    private TrinaryNode<T> deleteRoot(TrinaryNode node)
    {
        //the middle branch is equal all the way down.  Move the middle
        //branch up and make it the node
        if (node.getMiddleBranch() != null)
        {
            node.getMiddleBranch().setLeftBranch(node.getLeftBranch());
            node.getMiddleBranch().setRightBranch(node.getRightBranch());
            node = node.getMiddleBranch();
        }
        //If there are no middle nodes, use the left branch as the new root 
        else if (node.getLeftBranch() != null)
        {
            node.getLeftBranch().addChild(node.getRightBranch());
            node = node.getLeftBranch();
        }
        //if there are no middle or left brnaches, use the right branch as
        //the new root
        else if (node.getRightBranch() != null)
        {
            node = node.getRightBranch();
        }
        //if this is a tree of one node, delete the root
        else
        {
            node = null;
        }
        return node;
    }

    /**
     * Find the parent of the Trinary node who has a value recursively. Will
     * throw a null pointer if the value is the root
     * @param iter the node currently being looked at
     * @param value the value being looked for
     * @return the Trinary node that is the parent the highest node with this
     * value
     */
    private TrinaryNode<T> findParent(TrinaryNode iter, T value)
    {
        if (iter == null)
        {
            return null;
        }

        //Check if this is the parent we are looking for.  No need to check the
        //middle, since the parent would have been found already
        if ((iter.getLeftBranch() != null && 
             iter.getLeftBranch().getValue().compareTo(value) == 0)
            || (iter.getRightBranch() != null &&
                iter.getRightBranch().getValue().compareTo(value) == 0))
        {
            return iter;
        }
        TrinaryNode<T> result;
        //Search for the parent possible branches
        if (iter.getValue().compareTo(value) < 0)
        {
            result = findParent(iter.getRightBranch(), value);
        }
        else
        {
            result = findParent(iter.getLeftBranch(), value);
        }
        return result;
    }

    /**
     * Prints the tree to standard out
     */
    public void printTree()
    {
        if(root != null)
        {
           printNode(root, "");
        }
        else
        {
            System.out.println("Empty Tree");
        }
    }

    /**
     * Recursively prints a node
     * @param t the current node
     * @param buffer the indentation to print
     */
    private void printNode(TrinaryNode<T> t, String buffer)
    {
        System.out.println(buffer + "Node " + t.getValue().toString());
        System.out.println(buffer + "Left");
        if (t.getLeftBranch() != null)
        {
            printNode(t.getLeftBranch(), buffer + "   ");
        }
        System.out.println(buffer + "Middle");
        if (t.getMiddleBranch() != null)
        {
            printNode(t.getMiddleBranch(), buffer + "   ");
        }
        System.out.println(buffer + "Right");
        if (t.getRightBranch() != null)
        {
            printNode(t.getRightBranch(), buffer + "   ");
        }
    }
}
