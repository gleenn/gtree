public class GTree<T extends Comparable<? super T>> {
  public GTree<T> add(T value) {
    if(root == null) {
        root = new Node<T>(value, null, null);
    } else {
        add(value, root);
    }
    return this;
  }

  private void add(T value, Node<T> node) {
    if(value.compareTo(node.value) < 0) {
      if(node.left == null)
        node.left = new Node<T>(value, null, null);
      else
        add(value, node.left);
    } else {
      if(node.right == null) {
        node.right = new Node<T>(value, null, null);
      } else {
        add(value, node.right);
      }
    }
  }

  public T remove(T value) {
    Node<T> parent = findParent(value, root, null);
    if(parent == null) throw new RuntimeException("Not Found");
      if(value.compareTo(parent.value) < 0) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    return value;
  }

  public T furthestLeaf() {
      return furthestLeaf(root);
  }

  private T furthestLeaf(Node<T> node) {
      return null;
  }

  private Node<T> findParent(T value, Node<T> node, Node<T> parent) {
    if(node == null) return parent;

    if(value.equals(node.value)) return node;

    if(value.compareTo(node.value) < 0) {
      return findParent(value, node.left, node);
    } else {
      return findParent(value, node.right, node);
    }
  }

  public Node<T> leftMostLeaf() {
    return leftMostLeaf(root);
  }

  private Node<T> leftMostLeaf(Node<T> node) {
    if(node == null) return null;
    if(node.left == null) return node;
    return null;
  }

  public Node<T> rightMostLeaf() {
    return rightMostLeaf(root);
  }

  private Node<T> rightMostLeaf(Node<T> node) {
    if(node == null) return null;
    if(node.right == null) return node;
    return null;
  }

  public boolean contains(T value) {
    return contains(value, root);
  }

  private boolean contains(T value, Node<T> node) {
    if(node == null) return false;

    if(value.equals(node.value)) return true;

    if(value.compareTo(node.value) < 0) {
      return contains(value, node.left);
    } else {
      return contains(value, node.right);
    }
  }

  Node<T> root;

  static class Node<T> {
    public final T value;
    public Node<T> left;
    public Node<T> right;

    public Node(T value, Node<T> left, Node<T> right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public boolean equals(Node<T> node) {
      if(node == null) return false;
      return value.equals(node.value) &&
             left.equals(node.left) &&
             right.equals(node.right);
    }
  }
}

