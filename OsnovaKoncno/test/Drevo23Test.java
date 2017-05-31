import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author primoz
 */
public class Drevo23Test {
    Drevo23<Integer> dv;
    //Drevo23<Studenti> st; figure this out
    
    @Before
    public void setUp() {
        dv = new Drevo23<Integer>(new PrimerjajPoTipu<Integer>());
    }
    
    @Test
    public void init23Tree() {
        //System.out.println(dv.get_root_node());
    }
    @Test
    public void addOne23(){
        dv.add(5);
        assertEquals(5, (int) dv.get_root_node().left);
    }
    @Test
    public void addTwoRight23(){
        dv.add(5);
        dv.add(6);
        assertEquals(6, (int) dv.get_root_node().right);
    }
    @Test
    public void addTwoLeft23(){
        dv.add(5);
        dv.add(4);
        assertEquals(4, (int) dv.get_root_node().left);
    }
    
    @Test(expected=java.lang.IllegalArgumentException.class)
    public void elementAlreadyIn23(){
        dv.add(5);
        dv.add(5);
    }
    @Test
    public void addThreeRight23(){
        dv.add(2);
        dv.add(3);
        dv.add(4);
    }
    @Test
    public void addThreeRight231(){
        dv.add(2);
        dv.add(3);
        dv.add(1);
        assertEquals(1, (int) dv.get_root_node().left_child.left);
        assertEquals(2, (int) dv.get_root_node().left);
        assertEquals(3, (int) dv.get_root_node().right_child.left);
    }
    @Test
    public void addThreeLeft234(){
        dv.add(3);
        dv.add(2);
        dv.add(4);
        assertEquals(2, (int) dv.get_root_node().left_child.left);
        assertEquals(3, (int) dv.get_root_node().left);
        assertEquals(4, (int) dv.get_root_node().right_child.left);
    }
    @Test
    public void addThreeLeft123(){
        dv.add(3);
        dv.add(2);
        dv.add(1);
        assertEquals(1, (int) dv.get_root_node().left_child.left);
        assertEquals(2, (int) dv.get_root_node().left);
        assertEquals(3, (int) dv.get_root_node().right_child.left);
    }
    
    @Test
    public void addFourRight(){
        dv.add(3);
        dv.add(2);
        dv.add(1);
        dv.add(5);
        assertEquals(1, (int) dv.get_root_node().left_child.left);
        assertEquals(2, (int) dv.get_root_node().left);
        assertEquals(3, (int) dv.get_root_node().right_child.left);
        assertEquals(5, (int) dv.get_root_node().right_child.right);
    }
    
    @Test
    public void addLeftRight(){
        dv.add(4);
        dv.add(3);
        dv.add(2);
        dv.add(1);
        assertEquals(2, (int) dv.get_root_node().left_child.right);
        assertEquals(3, (int) dv.get_root_node().left);
        assertEquals(4, (int) dv.get_root_node().right_child.left);
        assertEquals(1, (int) dv.get_root_node().left_child.left);
    }
    @Test
    public void testGetFirst(){
        dv.add(4);
        dv.add(3);
        dv.add(2);
        dv.add(1);
        assertEquals(3,(int)  dv.getFirst());
    }
    
    @Test
    public void testGetFirstMiddle(){
        dv.add(4);
        dv.add(3);
        dv.add(1);
        assertEquals(3,(int)dv.getFirst());
    }
    @Test
    public void testExistsTrueOne(){
        dv.add(4);
        assertTrue(dv.exists(4));
    }
    @Test
    public void testExistsTrueTwo(){
        dv.add(4);
        dv.add(5);
        assertTrue(dv.exists(5));
    }
    @Test
    public void testExistsTrueThree(){
        dv.add(4);
        dv.add(3);
        dv.add(1);
        assertTrue(dv.exists(4));
    }
    @Test
    public void testExistsTrueFour(){
        dv.add(4);
        dv.add(3);
        dv.add(2);
        dv.add(1);
        assertTrue(dv.exists(2));
    }
    @Test
    public void testAlotElemsEisMid(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(12);
        assertEquals(12,(int) dv.root_node.left);
        assertEquals(19,(int) dv.root_node.right);
        assertEquals(4,(int) dv.root_node.left_child.left);
        assertEquals(17,(int) dv.root_node.mid_child.left);
        assertEquals(20,(int) dv.root_node.right_child.left);
    }
    @Test
    public void testAlotElemsLeftisMid(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(1);
        assertEquals(4,(int) dv.root_node.left);
        assertEquals(19,(int) dv.root_node.right);
        assertEquals(1,(int) dv.root_node.left_child.left);
        assertEquals(17,(int) dv.root_node.mid_child.left);
        assertEquals(20,(int) dv.root_node.right_child.left);
    }
    @Test
    public void testAlotElemsRightisMid(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(18);
        assertEquals(17,(int) dv.root_node.left);
        assertEquals(19,(int) dv.root_node.right);
        assertEquals(4,(int) dv.root_node.left_child.left);
        assertEquals(18,(int) dv.root_node.mid_child.left);
        assertEquals(20,(int) dv.root_node.right_child.left);
    }
    @Test
    public void testBigTreeMiddleLeft(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(12);
        dv.add(15);
        dv.add(13);//naredi splittanje v zgo
        assertEquals(15,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(12,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(13,(int) dv.root_node.left_child.right_child.left);
        assertEquals(19,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(17,(int) dv.root_node.right_child.left_child.left);
        assertEquals(20,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testBigTreeMiddleE(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(12);
        dv.add(15);
        dv.add(16);//naredi splittanje v zgo
        assertEquals(16,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(12,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(15,(int) dv.root_node.left_child.right_child.left);
        assertEquals(19,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(17,(int) dv.root_node.right_child.left_child.left);
        assertEquals(20,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testBigTreeMiddleRight(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(17);
        dv.add(12);
        dv.add(15);
        dv.add(18);//naredi splittanje v zgo
        assertEquals(17,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(12,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(15,(int) dv.root_node.left_child.right_child.left);
        assertEquals(19,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(18,(int) dv.root_node.right_child.left_child.left);
        assertEquals(20,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testSplitRightMediRight(){
        dv.add(19);
        dv.add(4);
        dv.add(20);
        dv.add(21);
        dv.add(22);
        assertEquals(19,(int) dv.root_node.left);
        assertEquals(21,(int) dv.root_node.right);
        assertEquals(4,(int) dv.root_node.left_child.left);
        assertEquals(20,(int) dv.root_node.mid_child.left);
        assertEquals(22,(int) dv.root_node.right_child.left);
    }
    @Test
    public void testSplitRightMediLeft(){
        dv.add(18);
        dv.add(4);
        dv.add(20);
        dv.add(21);
        dv.add(19);
        assertEquals(18,(int) dv.root_node.left);
        assertEquals(20,(int) dv.root_node.right);
        assertEquals(4,(int) dv.root_node.left_child.left);
        assertEquals(19,(int) dv.root_node.mid_child.left);
        assertEquals(21,(int) dv.root_node.right_child.left);
    }
    @Test
    public void testSplitSpecialRight(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(16);
        dv.add(17);
        dv.add(18);
        assertEquals(10,(int) dv.root_node.left);
        assertEquals(16,(int) dv.root_node.right);
        assertEquals(4,(int) dv.root_node.left_child.left);
        assertEquals(15,(int) dv.root_node.mid_child.left);
        assertEquals(17,(int) dv.root_node.right_child.left);
        assertEquals(18,(int) dv.root_node.right_child.right);
    }
    @Test
    public void testSplitSpecialRightRightisMedi(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(16);
        dv.add(17);
        dv.add(18);
        dv.add(19);
        assertEquals(16,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(10,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(15,(int) dv.root_node.left_child.right_child.left);
        assertEquals(18,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(17,(int) dv.root_node.right_child.left_child.left);
        assertEquals(19,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testSplitSpecialRightLeftisMedi(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(18);
        dv.add(20);
        dv.add(21);
        dv.add(19);
        assertEquals(18,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(10,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(15,(int) dv.root_node.left_child.right_child.left);
        assertEquals(20,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(19,(int) dv.root_node.right_child.left_child.left);
        assertEquals(21,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testSplitSpecialRightEisMedi(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(16);
        dv.add(17);
        dv.add(19);
        dv.add(18);
        assertEquals(16,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(10,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(15,(int) dv.root_node.left_child.right_child.left);
        assertEquals(18,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(17,(int) dv.root_node.right_child.left_child.left);
        assertEquals(19,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testAddElemDepth(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(16);
        dv.add(17);
        dv.add(19);
        dv.add(18);
        dv.add(12);
    }
    @Test
    public void testAddElemNewDepth(){
        dv.add(10);
        dv.add(4);
        dv.add(15);
        dv.add(16);
        dv.add(17);
        dv.add(19);
        dv.add(18);
        dv.add(1);
        dv.add(3);
    }
    @Test
    public void testSplitSpecialLeftLeftisMedi(){
        dv.add(10);
        dv.add(15);
        dv.add(20);
        dv.add(9);
        dv.add(8);
        dv.add(6);
        dv.add(5);
        assertEquals(9,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(6,(int) dv.root_node.left_child.left);
        assertEquals(5,(int) dv.root_node.left_child.left_child.left);
        assertEquals(8,(int) dv.root_node.left_child.right_child.left);
        assertEquals(15,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(10,(int) dv.root_node.right_child.left_child.left);
        assertEquals(20,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testSplitSpecialLeftEisMedi(){
        dv.add(10);
        dv.add(15);
        dv.add(20);
        dv.add(9);
        dv.add(8);
        dv.add(6);
        dv.add(7);
        assertEquals(9,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(7,(int) dv.root_node.left_child.left);
        assertEquals(6,(int) dv.root_node.left_child.left_child.left);
        assertEquals(8,(int) dv.root_node.left_child.right_child.left);
        assertEquals(15,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(10,(int) dv.root_node.right_child.left_child.left);
        assertEquals(20,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testSplitSpecialLeftRightisMedi(){
        dv.add(20);
        dv.add(30);
        dv.add(10);
        dv.add(5);
        dv.add(9);
        dv.add(4);
        dv.add(6);
        assertEquals(9,(int) dv.root_node.left);
        assertEquals(null, dv.root_node.right);
        assertEquals(5,(int) dv.root_node.left_child.left);
        assertEquals(4,(int) dv.root_node.left_child.left_child.left);
        assertEquals(6,(int) dv.root_node.left_child.right_child.left);
        assertEquals(20,(int) dv.root_node.right_child.left);
        assertEquals(null, dv.root_node.mid_child);
        assertEquals(10,(int) dv.root_node.right_child.left_child.left);
        assertEquals(30,(int) dv.root_node.right_child.right_child.left);
    }
    @Test
    public void testDepthBigTree(){
        dv.add(20);
        dv.add(30);
        dv.add(10);
        dv.add(5);
        dv.add(9);
        dv.add(4);
        dv.add(6);
        assertEquals(3, dv.depth());
    }
    @Test
    public void testDepthSmallerTree(){
        dv.add(20);
        dv.add(30);
        dv.add(10);
        dv.add(5);
        dv.add(9);
        dv.add(4);
        assertEquals(2, dv.depth());
    }
    @Test
    public void testDepthTinyTree(){
        dv.add(20);
        dv.add(30);
        dv.add(10);
        assertEquals(2, dv.depth());
    }
    @Test
    public void testDepthNoChildrenTree(){
        dv.add(20);
        assertEquals(1, dv.depth());
    }
    @Test
    public void testIsEmptyTrue(){
        assertTrue(dv.isEmpty());
    }
    @Test
    public void testIsntEmptyTrue(){
        dv.add(10);
        assertFalse(dv.isEmpty());
    }
    @Test
    public void testDsntExist(){
        assertFalse(dv.exists(42));
    }
    @Test
    public void testNotIntreeExist(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        assertFalse(dv.exists(42));
    }
    @Test
    public void testInTreeRightBigExist(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        dv.add(40);
        dv.add(50);
        dv.add(60);
        assertTrue(dv.exists(60));
    }
    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testNoGetFirst(){
        dv.getFirst();
    }
    @Test
    public void testInBigTreeSize(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        dv.add(40);
        dv.add(50);
        dv.add(60);
        dv.add(70);
        assertEquals(7, dv.size());
    }
    @Test
    public void testInSmallTreeSize(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        assertEquals(3, dv.size());
    }
    @Test
    public void testInOneTreeSize(){
        dv.add(10);
        assertEquals(1, dv.size());
    }
    @Test
    public void testInNoneTreeSize(){
        assertEquals(0, dv.size());
    }
    @Test
    public void testInBigTwoTreeSize(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        dv.add(40);
        dv.add(50);
        dv.add(60);
        assertEquals(6, dv.size());
    }
    
    public void testNothingToRemove(){
        assertEquals(null,dv.remove(2));
    }
    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testDoesNotExistRemove(){
        dv.add(1);
        dv.add(3);
        dv.remove(2);
    }
    @Test
    public void testDoesExistDOneLeft(){
        dv.add(1);
        dv.add(2);
        assertEquals(1,(int) dv.remove(1));
    }
    @Test
    public void testDoesExistDOneRight(){
        dv.add(1);
        dv.add(2);
        assertEquals(2,(int) dv.remove(2));
    }
    @Test
    public void testDoesExistDTwoRight(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        assertEquals(1, (int) dv.remove(1));
    }
    @Test
    public void testDoesExistDTwoLeft(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        assertEquals(3, (int) dv.remove(3));
    }
    
    @Test
    public void testDoesExistDTwoRightMore(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        dv.add(4);
        assertEquals(3, (int) dv.remove(3));
    }
    @Test
    public void testDoesExistDTwoLeftMore(){
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(1);
        assertEquals(1, (int) dv.remove(1));
    }
    @Test
    public void testDoesExistDTwoRightMoreFix(){
        
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(1);
        assertEquals(4, (int) dv.remove(4));
        assertEquals(2, (int) dv.getFirst());
    }
    @Test
    public void testDoesExistDTwoLeftMoreFix(){ 
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(5);
        assertEquals(2, (int) dv.remove(2));
        assertEquals(4, (int) dv.getFirst());
    }
    @Test
    public void testRemoveFromBigTree(){ 
        dv.add(1);
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(5);
        dv.add(6);
        assertEquals(3, (int) dv.remove(3));
        assertEquals(2, (int) dv.getFirst());
    }
    @Test
    public void testRemoveFromBiggerTree(){ 
        dv.add(1);
        dv.add(4);
        dv.add(5);
        dv.add(7);
        dv.add(8);
        dv.add(9);
        dv.add(6);
        assertEquals(6, (int) dv.remove(6));
        assertEquals(4, (int) dv.getFirst());
    }
    @Test
    public void testRemoveFromBiggerTreeLeft(){ 
        dv.add(1);
        dv.add(4);
        dv.add(5);
        dv.add(7);
        dv.add(8);
        dv.add(9);
        dv.add(6);
        assertEquals(5, (int) dv.remove(5));
        assertEquals(4, (int) dv.getFirst());
    }
    @Test(expected=java.util.NoSuchElementException.class)
    public void testRemoveFirstNoElems(){
        dv.removeFirst();
    }
    @Test
    public void testRemoveFirstOneElems(){
        dv.add(1);
        assertEquals(1,(int) dv.removeFirst());
    }
    @Test
    public void testRemoveFirstTwoElems(){
        dv.add(1);
        dv.add(2);
        assertEquals(1,(int) dv.removeFirst());
    }
    @Test
    public void testRemoveFirstBigTree(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(5);
        dv.add(6);
        assertEquals(2, (int) dv.removeFirst());
        assertEquals(4, (int) dv.getFirst());
    }
    @Test
    public void removeMultipleElems(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        dv.add(4);
        dv.add(5);
        dv.add(6);
        assertEquals(6, (int) dv.remove(6));
        assertEquals(3, (int) dv.remove(3));
        assertEquals(4, (int) dv.getFirst());
    }
    @Test
    public void testAsListBig(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        dv.add(40);
        dv.add(50);
        dv.add(60);
        dv.add(70);
        assertEquals(Arrays.asList(40,20,10,30,60,50,70), dv.asList());
    }
    @Test
    public void testAsListEmpty(){
        assertEquals(Arrays.asList(), dv.asList());
    }
    @Test
    public void asListUpper(){
        dv.add(10);
        dv.add(20);
        assertEquals(Arrays.asList(10,20), dv.asList());
    }
    @Test
    public void asListMiddle(){
        dv.add(10);
        dv.add(20);
        dv.add(30);
        dv.add(5);
        dv.add(1);
        assertEquals(Arrays.asList(5,20,1,30,10), dv.asList());
    }
    @Test
    public void removeisNull(){
        assertEquals(null, dv.remove(1));
    }
    @Test
    public void testReset(){
        dv.add(1);
        dv.add(2);
        dv.add(3);
        dv.reset(5);
        assertEquals(5,(int) dv.getFirst());
    }
    
}
