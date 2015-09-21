package gcollections;

class GNode<T extends Comparable<? super T>> implements Comparable<GNode<T>> {
  public final T value;
  public GNode<T> left;
  public GNode<T> right;

  public GNode(T value, GNode<T> left, GNode<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    GNode<?> node = (GNode<?>) o;

    return value.equals(node.value) &&
        !(left != null ? !left.equals(node.left) : node.left != null) &&
        !(right != null ? !right.equals(node.right) : node.right != null);

  }

  @Override
  public int hashCode() {
    int result = value.hashCode();
    result = 31 * result + (left != null ? left.hashCode() : 0);
    result = 31 * result + (right != null ? right.hashCode() : 0);
    return result;
  }

  public int compareTo(GNode<T> node) {
    return value.compareTo(node.value);
  }
}
