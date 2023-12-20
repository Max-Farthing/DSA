package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree.BinaryTreeNode;


/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 *
 */
public class LinkedBinaryTreeTest {
	/** sample tree for testing **/
    private LinkedBinaryTree<String> tree;
    /** position one of tree **/
    private Position<String> one;
    /** position two of tree **/
    private Position<String> two;
    /** position three of tree **/
    private Position<String> three;
    /** position four of tree **/
    private Position<String> four;
    /** position five of tree **/
    private Position<String> five;
    /** position six of tree **/
    private Position<String> six;
    /** position seven of tree **/
    private Position<String> seven;
    /** position eight of tree **/
    private Position<String> eight;
    /** position nine of tree **/
    private Position<String> nine;
    /** position ten of tree **/
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        tree.set(seven, "twelve");
        assertEquals(seven.getElement(), "twelve");
        tree.set(one, "forty");
        assertEquals(one.getElement(), "forty");
        tree.set(four, "one");
        assertEquals(four.getElement(), "one");
        tree.set(ten, "three");
        assertEquals(ten.getElement(), "three");
        tree.set(six, "two");
        assertEquals(six.getElement(), "two");
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);

        node1.setLeft(node2);
        node1.setRight(node3);
        assertEquals(2, tree.numChildren(node1));
        
        node2.setLeft(node6);
        node2.setRight(node10);
        assertEquals(2, tree.numChildren(node2));
        
        node3.setLeft(node4);
        assertEquals(2, tree.numChildren(node4));
        
        node4.setLeft(node8);
        node4.setRight(node9);
        assertEquals(2, tree.numChildren(node4));
        
        node10.setLeft(node7);
        node10.setRight(node5);
        assertEquals(2, tree.numChildren(node10));
        
        assertEquals(0, tree.numChildren(node7));
        assertEquals(0, tree.numChildren(node5));
        assertEquals(0, tree.numChildren(node8));
        assertEquals(0, tree.numChildren(node9));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);    
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        assertEquals(node1, node2.getParent());
        assertEquals(node1, node3.getParent());
        
        node2.setLeft(node6);
        node2.setRight(node10);
        assertEquals(node2, node6.getParent());
        assertEquals(node2, node10.getParent());
        
        node3.setLeft(node4);
        assertEquals(node3, node4.getParent());
        
        node4.setLeft(node8);
        node4.setRight(node9);
        assertEquals(node4, node8.getParent());
        assertEquals(node4, node9.getParent());

        
        node10.setLeft(node7);
        node10.setRight(node5);
        assertEquals(node10, node7.getParent());
        assertEquals(node10, node5.getParent());

    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);    
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);
        assertEquals(node6, tree.sibling(node10));
        assertEquals(node10, tree.sibling(node6));

        
        node3.setLeft(node4);
        assertEquals(node2, tree.sibling(node3));
        assertEquals(node3, tree.sibling(node2));
        
        node4.setLeft(node8);
        node4.setRight(node9);
        assertEquals(node9, tree.sibling(node8));
        assertEquals(node8, tree.sibling(node9));

        node10.setLeft(node7);
        node10.setRight(node5);
        assertEquals(node5, tree.sibling(node7));
        assertEquals(node7, tree.sibling(node5));

    }   

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);    
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);

        
        node3.setLeft(node4);
        
        node4.setLeft(node8);
        node4.setRight(node9);

        node10.setLeft(node7);
        node10.setRight(node5);
        
        assertTrue(tree.isInternal(node1));
        assertTrue(tree.isInternal(node2));
        assertTrue(tree.isInternal(node10));
        assertTrue(tree.isInternal(node3));
        assertTrue(tree.isInternal(node4));
        
        assertFalse(tree.isInternal(node5));
        assertFalse(tree.isInternal(node6));
        assertFalse(tree.isInternal(node7));
        assertFalse(tree.isInternal(node8));
        assertFalse(tree.isInternal(node9));

    }
    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);    
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);

        
        node3.setLeft(node4);
        
        node4.setLeft(node8);
        node4.setRight(node9);

        node10.setLeft(node7);
        node10.setRight(node5);    
        
        assertTrue(tree.isLeaf(node5));
        assertTrue(tree.isLeaf(node6));
        assertTrue(tree.isLeaf(node7));
        assertTrue(tree.isLeaf(node8));
        assertTrue(tree.isLeaf(node9));
        
        assertFalse(tree.isLeaf(node1));
        assertFalse(tree.isLeaf(node2));
        assertFalse(tree.isLeaf(node3));
        assertFalse(tree.isLeaf(node4));
        assertFalse(tree.isLeaf(node10));

    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);    
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);

        
        node3.setLeft(node4);
        
        node4.setLeft(node8);
        node4.setRight(node9);

        node10.setLeft(node7);
        node10.setRight(node5);
        
        assertTrue(tree.isRoot(node1));
        
        assertFalse(tree.isRoot(node2));
        assertFalse(tree.isRoot(node3));
        assertFalse(tree.isRoot(node4));
        assertFalse(tree.isRoot(node5));
        assertFalse(tree.isRoot(node6));
        assertFalse(tree.isRoot(node7));
        assertFalse(tree.isRoot(node8));
        assertFalse(tree.isRoot(node9));
        assertFalse(tree.isRoot(node10));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterable<Position<String>> preOrderTraversal = tree.preOrder();

        String[] expectedOrder = {"one", "two", "six", "ten", "seven", "five", "three", "four", "eight", "nine"};

        String[] obtainedOrder = new String[expectedOrder.length];
        int index = 0;
        for (Position<String> position : preOrderTraversal) {
            obtainedOrder[index++] = position.getElement();
        }

        assertArrayEquals(expectedOrder, obtainedOrder);
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterable<Position<String>> postOrderTraversal = tree.postOrder();

        String[] expectedOrder = {"six", "seven", "five", "ten", "two", "eight", "nine", "four", "three", "one"};

        String[] obtainedOrder = new String[expectedOrder.length];
        int index = 0;
        for (Position<String> position : postOrderTraversal) {
            obtainedOrder[index++] = position.getElement();
        }

        assertArrayEquals(expectedOrder, obtainedOrder);
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterable<Position<String>> inOrderTraversal = tree.inOrder();

        String[] expectedOrder = {"six", "two", "seven", "ten", "five", "one", "eight", "four", "nine", "three"};

        String[] obtainedOrder = new String[expectedOrder.length];
        int index = 0;
        for (Position<String> position : inOrderTraversal) {
            obtainedOrder[index++] = position.getElement();
        }

        assertArrayEquals(expectedOrder, obtainedOrder);
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	assertTrue(tree.isEmpty());
    	createTree();
    	assertFalse(tree.isEmpty());
    }
    
    /** 
     * Tests the level order traversal of the iterator
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterable<Position<String>> levelOrderTraversal = tree.levelOrder();

        String[] expectedOrder = {"one", "two", "three", "six", "ten", "four", "seven", "five", "eight", "nine"};

        String[] obtainedOrder = new String[expectedOrder.length];
        int index = 0;
        for (Position<String> position : levelOrderTraversal) {
            obtainedOrder[index++] = position.getElement();
        }
        

        assertArrayEquals(expectedOrder, obtainedOrder);
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
   	 BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);
        //create some new nodes for testing
        Position<String> eleven = tree.addLeft(node9, "eleven"); //11 to the left of 9
        BinaryTreeNode<String> node11 = tree.validate(eleven);
        Position<String> twelve = tree.addLeft(node7, "twelve"); //12 to the left of 7
        BinaryTreeNode<String> node12 = tree.validate(twelve);
        Position<String> thirteen = tree.addLeft(node6, "thirteen"); //13 to the left of 6
        BinaryTreeNode<String> node13 = tree.validate(thirteen);
        
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);

        
        node3.setLeft(node4);
        
        node4.setLeft(node8);
        node4.setRight(node9);

        node10.setLeft(node7);
        node10.setRight(node5); //setting up original tree
        
        node9.setLeft(node11); //testing new nodes starting here 
        assertEquals(node11, node9.getLeft());
        assertEquals(node9, node11.getParent());
        
        node7.setLeft(node12);
        assertEquals(node12, node7.getLeft());
        assertEquals(node7, node12.getParent());
        
        node6.setLeft(node13);
        assertEquals(node13, node6.getLeft());
        assertEquals(node6, node13.getParent());
        
        assertThrows(IllegalArgumentException.class, () -> tree.addLeft(node1, "thirteen"));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	 createTree();
    	 BinaryTreeNode<String> node1 = tree.validate(one);
         BinaryTreeNode<String> node2 = tree.validate(two);
         BinaryTreeNode<String> node3 = tree.validate(three);
         BinaryTreeNode<String> node4 = tree.validate(four);
         BinaryTreeNode<String> node5 = tree.validate(five);
         BinaryTreeNode<String> node6 = tree.validate(six);
         BinaryTreeNode<String> node7 = tree.validate(seven);
         BinaryTreeNode<String> node8 = tree.validate(eight);
         BinaryTreeNode<String> node9 = tree.validate(nine);
         BinaryTreeNode<String> node10 = tree.validate(ten);
         //create some new nodes for testing
         Position<String> eleven = tree.addRight(node9, "eleven"); //11 to the right of 9
         BinaryTreeNode<String> node11 = tree.validate(eleven);
         Position<String> twelve = tree.addRight(node7, "twelve"); //12 to the right of 7
         BinaryTreeNode<String> node12 = tree.validate(twelve);
         Position<String> thirteen = tree.addRight(node6, "thirteen"); //13 to the right of 6
         BinaryTreeNode<String> node13 = tree.validate(thirteen);
         
     
         node1.setLeft(node2);
         node1.setRight(node3);
         
         node2.setLeft(node6);
         node2.setRight(node10);

         
         node3.setLeft(node4);
         
         node4.setLeft(node8);
         node4.setRight(node9);

         node10.setLeft(node7);
         node10.setRight(node5); //setting up original tree
         
         node9.setRight(node11); //testing new nodes starting here 
         assertEquals(node11, node9.getRight());
         assertEquals(node9, node11.getParent());
         
         node7.setRight(node12);
         assertEquals(node12, node7.getRight());
         assertEquals(node7, node12.getParent());
         
         node6.setRight(node13);
         assertEquals(node13, node6.getRight());
         assertEquals(node6, node13.getParent());
         
         assertThrows(IllegalArgumentException.class, () -> tree.addRight(node1, "thirteen"));

    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        BinaryTreeNode<String> node1 = tree.validate(one);
        BinaryTreeNode<String> node2 = tree.validate(two);
        BinaryTreeNode<String> node3 = tree.validate(three);
        BinaryTreeNode<String> node4 = tree.validate(four);
        BinaryTreeNode<String> node5 = tree.validate(five);
        BinaryTreeNode<String> node6 = tree.validate(six);
        BinaryTreeNode<String> node7 = tree.validate(seven);
        BinaryTreeNode<String> node8 = tree.validate(eight);
        BinaryTreeNode<String> node9 = tree.validate(nine);
        BinaryTreeNode<String> node10 = tree.validate(ten);
    
    
        node1.setLeft(node2);
        node1.setRight(node3);
        
        node2.setLeft(node6);
        node2.setRight(node10);

        
        node3.setLeft(node4);
        
        node4.setLeft(node8);
        node4.setRight(node9);

        node10.setLeft(node7);
        node10.setRight(node5);
        
        assertEquals(10, tree.size());
        assertEquals("five", tree.remove(node5));
        assertEquals(9, tree.size());
        assertNull(node10.getRight()); //testing removing leaf
        
        assertEquals("ten", tree.remove(node10)); //testing removing inner node
        assertEquals(8, tree.size());
        assertEquals(node7, node2.getRight());
        assertEquals(node7, tree.sibling(node6));
        assertEquals(node6, tree.sibling(node7)); //testing shifting of moving nodes

        assertThrows(IllegalArgumentException.class, () -> tree.remove(node1));
        
    } 
    
    /**
     * testing toString method in abstract tree
     */
    @Test
    public void testToString() {
    	tree = new LinkedBinaryTree<String>();

        Position<String> root = tree.addRoot("Root");
        Position<String> left = tree.addLeft(root, "Left");
        Position<String> right = tree.addRight(root, "Right");
        tree.addLeft(left, "LeftLeft");
        tree.addRight(left, "LeftRight");
        tree.addLeft(right, "RightLeft");
        tree.addRight(right, "RightRight");
        
        String expected = "LinkedBinaryTree[\n" +
                "Root\n" +
                " Left\n" +
                "  LeftLeft\n" +
                "  LeftRight\n" +
                " Right\n" +
                "  RightLeft\n" +
                "  RightRight\n" +
                "]";
        
        assertEquals(expected, tree.toString());
    }
    
}
