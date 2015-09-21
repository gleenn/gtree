package gcollections;

public class DistTree {

//  public T furthestLeaf(GNode<T> root) {
//    return furthestLeaf(root, 0).node.value;
//  }
//
//  DistNode furthestLeaf(GNode<T> node, int distance) {
//      DistNode thisDistNode = new DistNode(distance, node);
//      DistNode leftDist = node.left == null ? thisDistNode : furthestLeaf(node.left, distance + 1);
//      DistNode rightDist = node.right == null ? thisDistNode : furthestLeaf(node.right, distance + 1);
//
//      if(leftDist.dist > rightDist.dist) {
//        return leftDist;
//      } else {
//        return rightDist;
//      }
//    }
//
//    class DistNode {
//      public int dist;
//      public GNode<T> node;
//
//      public DistNode(int dist, GNode<T> node) {
//        this.dist = dist;
//        this.node = node;
//      }
//    }
}
