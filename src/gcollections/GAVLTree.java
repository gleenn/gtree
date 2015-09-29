package gcollections;

import static gcollections.GNode.node;

public class GAVLTree {
  public static <T> GNode add(GNode node, Comparable value) {
    if(node == null) return node(value);
    int compared = value.compareTo(node.value);
    if(compared <= 0) {
      return balance(node(node.value,
          node.left == null ? node(value) : add(node.left, value),
          node.right));
    } else {
      return balance(node(node.value,
          node.left,
          node.right == null ? node(value) : add(node.right, value)));
    }
  }

  public static GNode balance(GNode node) {
    if(node.balance > 1) {
      if(node.right.balance > 0) {
        return rotateLeft(node);
      } else {
        return rotateLeft(new GNode(node.value, node.left, rotateRight(node.right)));
      }
    } else if(node.balance < -1) {
      if(node.left.balance < 0) {
        return rotateRight(node);
      } else {
        return rotateRight(new GNode(node.value, rotateLeft(node.left), node.right));
      }
    } else {
      return node;
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

  public static int size(GNode node) {
    if(node == null) return 0;
    return 1 + size(node.left) + size(node.right);
  }

  public static boolean equals(GNode nodeA, GNode nodeB) {
    if(nodeA == nodeB) return true;
    return (nodeA != null && nodeB != null) &&
        (nodeA.value.equals(nodeB.value) &&
            (nodeA.left == nodeB.left || equals(nodeA.left, nodeB.left)) &&
            (nodeA.right == nodeB.right || equals(nodeA.right, nodeB.right)));
  }

  public static GNode rotateLeft(GNode node) {
    return node(node.right.value, node(node.value, node.left, node.right.left), node.right.right);
  }

  public static GNode rotateRight(GNode node) {
    return node(node.left.value, node.left.left, node(node.value, node.left.right, node.right));
  }
}
