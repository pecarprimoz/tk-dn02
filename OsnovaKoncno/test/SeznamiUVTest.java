
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class SeznamiUVTest {

    SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
        
    }

    @After
    public void tearDown() {
    }
    @Test
    public void testExit(){
        //I guess it works ?
        assertEquals("Goodbye.", uv.processInput("exit"));
    }
    @Test
    public void testNotVaildRequest(){
        //I guess it works ?
        assertEquals("Invalid command.", uv.processInput("asdoiauisdgusiuadghu"));
    }
    @Test
    public void testNotVaildArguments(){
        //I guess it works ?
        assertEquals("Invalid argument", uv.processInput("add 1 2 3 4 5 6"));
    }
    @Test
    public void testOneAdd(){
        assertEquals("OK", uv.processInput("add 65 A A 3.3"));
    }
    @Test
    public void testAddMultipleSameId(){
        assertEquals("OK", uv.processInput("add 66 A A 3.3"));
        assertEquals("Student already exists.",uv.processInput("add 66 C F 3.4"));
    }
    @Test
    public void testAddMultipleSameName(){
        assertEquals("OK", uv.processInput("add 66 A A 3.3"));
        assertEquals("Student already exists.",uv.processInput("add 64 A A 3.4"));
    }
    @Test
    public void testAddMultiple(){
        //I guess it works ?
        assertEquals("OK", uv.processInput("add 66 A A 3.3"));
        assertEquals("OK", uv.processInput("add 65 A B 3.3"));
        assertEquals("OK", uv.processInput("add 64 A C 3.3"));
        assertEquals("OK", uv.processInput("add 63 A D 3.3"));
        assertEquals("OK", uv.processInput("add 62 A E 3.3"));
        assertEquals("OK", uv.processInput("add 61 A F 3.3"));
    }
    @Test
    public void testRemoveOne(){
        assertEquals("OK", uv.processInput("add 6 A B 3.3"));
        assertEquals("OK", uv.processInput("remove 6"));
    }
    @Test
    public void testRemoveSecondOne(){
        assertEquals("OK", uv.processInput("add 6 A B 3.3"));
        assertEquals("OK", uv.processInput("add 7 C C 3.4"));
        assertEquals("OK", uv.processInput("remove 7"));
    }
    @Test
    public void testRemoveRandomElem(){
        assertEquals("OK", uv.processInput("add 66 A A 3.3"));
        assertEquals("OK", uv.processInput("add 65 A B 3.3"));
        assertEquals("OK", uv.processInput("add 64 A C 3.3"));
        assertEquals("OK", uv.processInput("add 63 A D 3.3"));
        assertEquals("OK", uv.processInput("add 62 A E 3.3"));
        assertEquals("OK", uv.processInput("add 61 A F 3.3"));
        assertEquals("OK", uv.processInput("remove 63"));
    }
    @Test
    public void testNoarguments(){
        assertEquals("Error: enter command", uv.processInput(""));
    }
    @Test
    public void testDeleteWrongType(){
        assertEquals("OK", uv.processInput("add 66 A A 3.3"));
        assertEquals("Invalid input data.", uv.processInput("remove hans"));
    }
    @Test
    public void testDoesNotExistType(){
        assertEquals("OK", uv.processInput("add 63 A A 3.3"));
        assertEquals("Error: Student does not exist", uv.processInput("remove 3"));
    }
    @Test
    public void testDoesExistSearch(){
        assertEquals("OK", uv.processInput("add 63 A A 3.3"));
        assertEquals("63 | A, A | 3.3", uv.processInput("search 63"));
    }
    @Test
    public void testDoesNotExistSearch(){
        assertEquals("OK", uv.processInput("add 63 A A 3.3"));
        assertEquals("Student does not exist.", uv.processInput("search 64"));
    }
    @Test
    public void testDoesExistSearchWrong(){
        assertEquals("OK", uv.processInput("add 63 A A 3.3"));
        assertEquals("Invalid input data.", uv.processInput("search asdfvasdfasdf"));
    }
    @Test
    public void testSavePossible(){
        assertEquals("OK", uv.processInput("add 63 A A 3.3"));
        assertEquals("OK", uv.processInput("save test46.bin"));
    }
    @Test
    public void testRestorePossible(){
        assertEquals("OK", uv.processInput("restore test46.bin"));
    }
    
    @Test
    public void testNothing(){
        assertEquals("Error: enter command", uv.processInput(""));
    }
    
    
    
}
