package trinarytree;

/**
 *
 * @author Murph
 */
public class TestDriver
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       
        TrinaryTree<Integer> justRoot = new TrinaryTree<>();
        justRoot.insert(10);
        justRoot.printTree();
        justRoot.delete(10);
        justRoot.printTree();
        System.out.println(justRoot.delete(10));
        justRoot.insert(10);
        System.out.println(justRoot.delete(11));
        
        TrinaryTree<Integer> tree = new TrinaryTree<>();
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(9);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(2);
        tree.insert(2);
        tree.delete(9);
        tree.insert(9);
        tree.insert(9);
        tree.insert(9);
        tree.insert(7);
        tree.insert(7);
        tree.delete(9);
        tree.delete(7);
        tree.insert(4);
        tree.delete(6);

        tree.printTree();

    }
    
}
