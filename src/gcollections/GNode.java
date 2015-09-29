package gcollections;

class GNode implements Comparable<GNode> {
  public final Comparable value;
  public final GNode left;
  public final GNode right;
  public final int balance;

  public static GNode node(Comparable value, GNode left, GNode right, int balance) {
    return new GNode(value, left, right, balance);
  }

  public static GNode node(Comparable value, GNode left, GNode right) {
    return new GNode(value, left, right);
  }

  public static GNode node(Comparable value) {
    return new GNode(value);
  }

  public static GNode node() {
    return new GNode(null);
  }

  public GNode(Comparable value, GNode left, GNode right, int balance) {
    this.value = value;
    this.left = left;
    this.right = right;
    this.balance = balance;
  }

  public GNode(Comparable value, GNode left, GNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
    this.balance = getBalance(left, right);
  }

  public GNode(Comparable value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.balance = 0;
  }

  int getBalance(GNode left, GNode right) {
    return height(right) - height(left);
  }

  int height(GNode node) {
    if(node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    GNode gNode = (GNode) o;

    if(!value.equals(gNode.value)) return false;
    if(left != null ? !left.equals(gNode.left) : gNode.left != null) return false;
    return !(right != null ? !right.equals(gNode.right) : gNode.right != null);
  }

  @Override
  public int hashCode() {
    int result = value.hashCode();
    result = 31 * result + (left != null ? left.hashCode() : 0);
    result = 31 * result + (right != null ? right.hashCode() : 0);
    return result;
  }

  public int compareTo(GNode o) {
    return value.compareTo(o.value);
  }

  public String toString() {
    if(value == null) return "()";
    if(left == null && right == null) return "("+value+")";
    return "(" + value + ", " + left + ", " + right + ")";
  }
}
