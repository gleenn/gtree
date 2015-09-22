package gcollections;

import static gcollections.GNode.node;

public class GAVLTree {
  public static GNode add(GNode node, Comparable value) {
    if(node == null) return node(value);
    int compared = value.compareTo(node.value);
    if(compared <= 0) {
      return node(node.value,
          (node.left == null) ? node(value) : add(node.left, value),
          node.right);
    } else {
      return node(node.value,
          node.left,
          (node.right == null) ? node(value) : add(node.right, value));
    }
  }

  public static boolean contains(GNode node, Comparable value) {
    if(node == null) return false;
    int compared = value.compareTo(node.value);
    if(compared == 0) return true;
    if(compared < 0) return contains(node.left, value);
    if(compared > 0) return contains(node.right, value);
    return false;
  }

  public static boolean equals(GNode nodeA, GNode nodeB) {
    if(nodeA == nodeB) return true;
    return (nodeA != null && nodeB != null) &&
        (nodeA.value.equals(nodeB.value) &&
        (nodeA.left == nodeB.left || equals(nodeA.left, nodeB.left)) &&
        (nodeA.right == nodeB.right || equals(nodeA.right, nodeB.right)));
  }

  public static GNode rotateLeft(GNode node) {
    return node(node.right.value, node(node.value, node.left, null), node.right.right);
  }

  public static GNode rotateRight(GNode node) {
    return node(node.left.value, node.left.left, node(node.value, null, node.right));
  }
}
