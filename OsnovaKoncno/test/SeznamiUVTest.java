
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
}
