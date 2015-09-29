package gcollections;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static gcollections.GNode.node;

public class GAVLTreeTest extends TestCase {
  public void testEquals() throws Exception {
    GNode singleNode = node(1);
    assertTrue("matches self", GAVLTree.equals(singleNode, singleNode));
    assertTrue("matches different with same values", GAVLTree.equals(singleNode, node(1)));
    assertTrue("matches independent of order", GAVLTree.equals(node(1), singleNode));
    assertFalse("doesn't match when values different", GAVLTree.equals(node(2), node(1)));
    assertFalse("doesn't match when children don't match", GAVLTree.equals(new GNode(1, node(2), null), node(1)));
    assertFalse("doesn't match when children don't match", GAVLTree.equals(node(1), new GNode(1, null, node(2))));
    assertTrue("does match when whole tree matches exactly", GAVLTree.equals(node(2, node(3), node(4)), node(2, node(3), node(4))));
  }

  public void testContains() throws Exception {
    GNode root = node(1);

    assertTrue(GAVLTree.contains(root, 1));

    root = GAVLTree.add(root, 2);
    root = GAVLTree.add(root, 3);
    root = GAVLTree.add(root, 4);
    root = GAVLTree.add(root, -1);

    assertTrue("tree should contain added nodes", GAVLTree.contains(root, 2));
    assertTrue("tree should contain added nodes", GAVLTree.contains(root, 3));
    assertTrue("tree should contain added nodes", GAVLTree.contains(root, 4));
    assertTrue("tree should contain added nodes", GAVLTree.contains(root, -1));
  }

  public void testSize_worksWithALotOfValues() {
    Random random = new Random();
    List<Integer> vals = new ArrayList<Integer>();
    for(int i=0; i< 1000; i++) vals.add(random.nextInt());

    GNode node = node(vals.get(0)); // smell, why can't I have an empty node...
    for(int i = 1; i < vals.size(); i++) node = GAVLTree.add(node, vals.get(i));

    assertEquals("tree should be of same size", vals.size(), GAVLTree.size(node));
    for(Integer val : vals) {
      assertTrue("tree should contain all values", GAVLTree.contains(node, val));
    }
  }

  public void testAdd_balancesTreeProperly() {
    GNode root = node(3);
    assertTrue("balances correctly - " + root, GAVLTree.equals(root, node(3)));
    root = GAVLTree.add(root, 4);
    assertTrue("balances correctly - " + root, GAVLTree.equals(root, node(3, null, node(4))));
    root = GAVLTree.add(root, 5);
    //        4
    //      3   5

    assertTrue("balances correctly - " + root, GAVLTree.equals(root, node(4, node(3), node(5))));
    root = GAVLTree.add(root, 2);
    //        4
    //      3   5
    //    2
    assertTrue("balances correctly - " + root, GAVLTree.equals(root, node(4, node(3, node(2), null), node(5))));
    root = GAVLTree.add(root, 1);
    //        4
    //      3   5
    //    2
    //  1

    //        4
    //      2   5
    //    1   3

    assertTrue("balances correctly - " + root, GAVLTree.equals(root, node(4, node(2, node(1), node(3)), node(5)))); // not sure if this is right

  }

  public void testRotateLeft() throws Exception {
    GNode tree = node(1, node(-1), node(2, null, node(3)));
    GNode rotatedTree = GAVLTree.rotateLeft(tree);
    assertTrue("rotation should work", GAVLTree.equals(rotatedTree, node(2, node(1, node(-1), null), node(3))));
  }

  public void testRotateRight() throws Exception {
    GNode tree = node(1, node(-1, node(-2, node(-3), null), null), null);
    GNode rotatedTree = GAVLTree.rotateRight(tree);
    assertTrue("rotation should work", GAVLTree.equals(rotatedTree, node(-1, node(-2, node(-3), null), node(1))));
  }

  public void testBalance() throws Exception {
    GNode balanced = node(2, node(1), node(3));

    GNode nodeRR = GAVLTree.balance(node(1, null, node(2, null, node(3))));
    assertTrue("balances RR bias - " + nodeRR, GAVLTree.equals(nodeRR, balanced));

    GNode nodeRL = GAVLTree.balance(node(1, null, node(3, node(2), null)));
    assertTrue("balances RL bias - " + nodeRL, GAVLTree.equals(nodeRL, balanced));

    GNode nodeLL = GAVLTree.balance(node(3, node(2, node(1), null), null));
    assertTrue("balances LL bias - " + nodeLL, GAVLTree.equals(nodeLL, balanced));

    GNode nodeLR = GAVLTree.balance(node(3, node(1, null, node(2)), null));
    assertTrue("balances LR bias - " + nodeLR, GAVLTree.equals(nodeLR, balanced));

    // the following is bad methinks
    /*
    GNode deepBalanced = node(4, node(2, node(1), node(3)), node(6, node(5), node(7)));

    GNode deepNodeRR = GAVLTree.balance(node(4, node(2), node(6, node(5), node(7))));
    assertTrue("balances RR bias - " + deepNodeRR, GAVLTree.equals(nodeRR, deepBalanced));

    GNode deepNodeRL = GAVLTree.balance(node(1, null, node(3, node(2), null)));
    assertTrue("balances RL bias - " + deepNodeRL, GAVLTree.equals(nodeRL, deepBalanced));

    GNode deepNodeLL = GAVLTree.balance(node(3, node(2, node(1), null), null));
    assertTrue("balances LL bias - " + deepNodeLL, GAVLTree.equals(nodeLL, deepBalanced));

    GNode deepNodeLR = GAVLTree.balance(node(3, node(1, null, node(2)), null));
    assertTrue("balances LR bias - " + deepNodeLR, GAVLTree.equals(nodeLR, deepBalanced));
    */
  }
}
