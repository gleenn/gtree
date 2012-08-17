import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class GTreeTest {
    @Test
    public void containsWorks() {
        GTree<Integer> gtree = new GTree<Integer>();
        assertEquals(false, gtree.contains(1));
        assertEquals(false, gtree.contains(2));
        assertEquals(false, gtree.contains(3));

        gtree.add(2);
        assertEquals(false, gtree.contains(1));
        assertEquals(true, gtree.contains(2));
        assertEquals(false, gtree.contains(3));

        gtree.add(1);
        assertEquals(true, gtree.contains(1));
        assertEquals(true, gtree.contains(2));
        assertEquals(false, gtree.contains(3));

        gtree.add(3) ;
        assertEquals(true, gtree.contains(1));
        assertEquals(true, gtree.contains(2));
        assertEquals(true, gtree.contains(3));

        gtree.remove(2);
        assertEquals(true, gtree.contains(1));
        assertEquals(false, gtree.contains(2));
        assertEquals(true, gtree.contains(3));
    }

    @Test
    public void test_rightMostLeaf_and_leftMostLeaf() {
        GTree<Integer> gtree = new GTree<Integer>();

        assertNull(gtree.leftMostLeaf());
        assertNull(gtree.rightMostLeaf());

        gtree.add(1);
        assertEquals(new Integer(1), gtree.leftMostLeaf().value);
        assertEquals(new Integer(1), gtree.rightMostLeaf().value);

        gtree.add(2);
        assertEquals(new Integer(1), gtree.leftMostLeaf().value);
        assertEquals(new Integer(2), gtree.rightMostLeaf().value);
    }

    @Test
    public void test_furthestLeaf() {
        GTree<Integer> gtree = new GTree<Integer>();
        for(int i=0; i<5; i++) gtree.add(i);

        assertEquals(gtree.furthestLeaf());
    }
}
