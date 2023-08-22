import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// --== CS400 File Header Information ==--
// Name: Anais Corona Perez
// Email: coronaperez@wisc.edu
// Team: DC
// TA: Daniel Finer
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

/**
 * Tester for RedBlackTree.java
 * Verifies its functionality
 * @author anais
 *
 */
public class TestRedBlackTree {
	
	/*
	 * Tests recoloring without rotations in right subtree
	 */
	@Test
	public void testRightSubtreeRecolor() {
		//create RB Tree with starting values
		RedBlackTree<Integer>instance = new RedBlackTree<Integer>();
		
		//test root, should be black
		instance.insert(11);
		assertEquals(true, instance.root.isBlack());
		//add other values
		instance.insert(15);
		instance.insert(8);
		
		//placing 20 into the tree should recolor its parent 15 to black
		assertEquals(false, instance.root.rightChild.isBlack());
		instance.insert(20);
		assertEquals(true, instance.root.rightChild.isBlack());
		assertEquals(20, instance.root.rightChild.rightChild.data);
		
		instance.insert(13);
		
		//placing 12 into the tree should recolor its parent 13 to black
		//and uncle 20 to black and grandparent 15 to red
		instance.insert(12);
		assertEquals(true, instance.root.rightChild.leftChild.isBlack());
		assertEquals(true, instance.root.rightChild.rightChild.isBlack());
		assertEquals(false, instance.root.rightChild.isBlack());
			
	}
	
	/**
	 * Tests recoloring without rotations in left subtree
	 */
	@Test
	public void testLeftSubtreeRecolor() {
		RedBlackTree<Integer>instance = new RedBlackTree<Integer>();
		instance.insert(11);
		assertEquals(true, instance.root.isBlack());
		//add other values
		instance.insert(15);
		instance.insert(8);
		
		//placing 9 into the tree should recolor its parent 8 to black
		assertEquals(false, instance.root.leftChild.isBlack());
		instance.insert(9);
		assertEquals(true, instance.root.leftChild.isBlack());
		
		instance.insert(6);
		
		//placing 7 should recolor its parent 6 and uncle 9 to black
		//and grandparent 8 to red
		instance.insert(7);
		assertEquals(true, instance.root.leftChild.leftChild.isBlack());
		assertEquals(true, instance.root.leftChild.rightChild.isBlack());
		assertEquals(false, instance.root.leftChild.isBlack());		
	}
	
	/**
	 * Tests rotation and colors after rotation in right subtree
	 */
	@Test
	public void testRightSubtreeRotation() {
		RedBlackTree<Integer>instance = new RedBlackTree<Integer>();
		instance.insert(11);
		instance.insert(16);
		instance.insert(8);	
		instance.insert(20);
		instance.insert(14);
		
		//additional values to prep tree for rotation
		instance.insert(13);
		assertEquals(false, instance.root.rightChild.leftChild.leftChild.isBlack());
		instance.insert(25);
		assertEquals(false, instance.root.rightChild.rightChild.rightChild.isBlack());
		
		//test right rotation and recolor
		instance.insert(12);
		assertEquals(13, instance.root.rightChild.leftChild.data);
		assertEquals(true, instance.root.rightChild.leftChild.isBlack());
				
		//test left rotation and recolor
		instance.insert(27);
		assertEquals(25, instance.root.rightChild.rightChild.data);
		assertEquals(true, instance.root.rightChild.rightChild.isBlack());
	}
	
	/**
	 * Tests rotation and colors after rotation in left subtree
	 */
	@Test
	public void testLeftSubtreeRotation() {
		RedBlackTree<Integer>instance = new RedBlackTree<Integer>();
		instance.insert(20);
		instance.insert(23);
		instance.insert(8);	
		instance.insert(7);
		instance.insert(10);
		
		//additional values to prep tree for rotation
		instance.insert(6);
		assertEquals(false, instance.root.leftChild.leftChild.leftChild.isBlack());
		instance.insert(11);
		assertEquals(false, instance.root.leftChild.rightChild.rightChild.isBlack());
		
		//test right rotation and recolor
		instance.insert(5);
		assertEquals(6, instance.root.leftChild.leftChild.data);
		assertEquals(true, instance.root.leftChild.leftChild.isBlack());
		
		instance.insert(12);
		assertEquals(11, instance.root.leftChild.rightChild.data);
		assertEquals(true, instance.root.leftChild.rightChild.isBlack());
	}
}
