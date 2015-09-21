package gcollections;

import junit.framework.TestCase;

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

  public void testAdd() throws Exception {
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
}
