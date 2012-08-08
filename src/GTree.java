public class GTree<T extends Comparable<? super T>> {
  public static void main(final String[] args) {
    test_contains();
    test_rightMostLeaf_and_leftMostLeaf();
    System.out.println();
  }

  public static void test_contains() {
    GTree<Integer> gtree = new GTree<Integer>();

    expectEquals(gtree.contains(1), false);

    gtree.add(1);
    expectEquals(gtree.contains(1), true);

    gtree.add(2);
    expectEquals(gtree.contains(2), true);

    gtree.remove(1);
    expectEquals(gtree.contains(1), false);
    expectEquals(gtree.contains(2), false);

    gtree.remove(2);
    expectEquals(gtree.contains(2), false);
  }

  public static void test_rightMostLeaf_and_leftMostLeaf() {
    GTree<Integer> gtree = new GTree<Integer>();

    expectEquals(gtree.leftMostLeaf(), null);
    expectEquals(gtree.rightMostLeaf(), null);

    gtree.add(1);
    expectEquals(gtree.leftMostLeaf().value, 1);
    expectEquals(gtree.rightMostLeaf().value, 1);

    gtree.add(2);
    expectEquals(gtree.leftMostLeaf().value, 1);
    expectEquals(gtree.rightMostLeaf().value, 2);
  }

  public static void expectEquals(Integer actual, Integer expected) {
    if(actual != null && expected != null && actual.equals(expected)) {
      System.out.print(".");
    } else {
      System.out.print("F");
    }
  }

  public static void expectEquals(Node actual, Node expected) {
    if(actual != null && expected != null && actual.equals(expected)) {
      System.out.print(".");
    } else {
      System.out.print("F");
    }
  }

  public static void expectEquals(boolean actual, boolean expected) {
    if(actual == expected) {
      System.out.print(".");
    } else {
      System.out.print("F");
    }
  }

  public GTree<T> add(T value) {
    add(value, root);
    return this;
  }

  private void add(T value, Node<T> node) {
    if(node == null) {
      root = new Node<T>(value, null, null);
      return;
    }

    if(value.compareTo(node.value) < 0)
      add(value, node.left);
    else
      add(value, node.right);
  }

  public T remove(T value) {
    Node<T> nodeForValue = find(value, root);
    return value;
  }

  private Node<T> find(T value, Node<T> node) {
    if(node == null) return null;

    if(value.equals(node.value)) return node;

    if(value.compareTo(node.value) < 0) {
      return find(value, node.left);
    } else {
      return find(value, node.right);
    }
  }

  private void percolateDown(Node<T> node) {

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

    if(value.equals(root.value)) return true;

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
