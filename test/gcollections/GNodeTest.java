package gcollections;

import junit.framework.TestCase;

import static gcollections.GNode.node;

public class GNodeTest extends TestCase {
  public static void testGetBalance() throws Exception {
    assertEquals(0, node(1).balance);
    assertEquals(-1, node(1, node(-1), null).balance);
    assertEquals(-2, node(1, node(-1, node(-2), null), null).balance);
    assertEquals(1, node(1, null, node(2)).balance);
    assertEquals(2, node(1, null, node(2, null, node(3))).balance);
  }
}
