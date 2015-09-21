package gcollections;

public class GTree<T extends Comparable<? super T>> {
  private GNode<T> root;

  public GTree<T> add(T value) {
    if(root == null) {
      root = new GNode<T>(value, null, null);
    } else {
      add(value, root);
    }
    return this;
  }

  void add(T value, GNode<T> node) {
    if(value.compareTo(node.value) < 0) {
      if(node.left == null)
        node.left = new GNode<T>(value, null, null);
      else
        add(value, node.left);
    } else {
      if(node.right == null) {
        node.right = new GNode<T>(value, null, null);
      } else {
        add(value, node.right);
      }
    }
  }

  public GNode<T> leftMostLeaf() {
    return leftMostLeaf(root);
  }

  GNode<T> leftMostLeaf(GNode<T> node) {
    if(node == null) return null;
    if(node.left == null) return node;
    return leftMostLeaf(node.left);
  }

  GNode<T> rightMostLeaf() {
    return rightMostLeaf(root);
  }

  GNode<T> rightMostLeaf(GNode<T> node) {
    if(node == null) return null;
    if(node.right == null) return node;
    return rightMostLeaf(node.right);
  }

  public boolean contains(T value) {
    return contains(value, root);
  }

  boolean contains(T value, GNode<T> node) {
    if(node == null) return false;

    if(value.equals(node.value)) return true;

    if(value.compareTo(node.value) < 0) {
      return contains(value, node.left);
    } else {
      return contains(value, node.right);
    }
  }
}
