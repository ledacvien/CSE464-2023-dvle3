import org.example.MyGraph;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyGraphTest {
    private String filePath = "C:\\Users\\levie\\OneDrive\\Documents\\aFall2023\\CSE464\\Project1";
    private String inputPath = "input.dot";
    private String outputText = "output.txt";
    private String exptectFile1 = "expected1.txt";

    private MyGraph graph;
    @Before
    public void setUp()
    {
        graph = new MyGraph();
    }

    @Test
    public void feature1Test() throws IOException {
        graph.parseGraph(inputPath);
        graph.outputGraph(outputText);
        String expected = Files.readString(Paths.get(exptectFile1));
        String output = Files.readString(Paths.get(outputText));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void feature2Test()
    {

    }

    @Test
    public void feature3Test()
    {

    }

    @Test
    public void toStringTest()
    {

    }

    @Test
    public void outputGraphTest()
    {

    }

    @Test
    public void addNodeTest()
    {

    }

    @Test
    public void addEdgeTest()
    {

    }

    @Test
    public void outputDOTGraph()
    {

    }

    @Test
    public void outputGraphicTest()
    {

    }

    @After
    public void tearDown()
    {
        System.out.println("This is tear down");
    }
}
