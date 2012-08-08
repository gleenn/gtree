public class GTree<T extends Comparable<? super T>> {
  public static void main(final String[] args) {
    GTree<Integer> gtree = new GTree<Integer>();
    expectEquals(gtree.contains(4), false);
    gtree.add(4);
    expectEquals(gtree.contains(4), true);
    System.out.println();
  }

  public static void expectEquals(int actual, int expected) {
    if(actual == expected) {
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

  public boolean contains(T value) {
    return contains(value, root);
  }

  private boolean contains(T value, Node<T> node) {
    if(node == null) {
      return false;
    }

    if(value.compareTo(node.value) < 0)
      return contains(value, node.left);
    else
      return contains(value, node.right);
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
  }
}
