package mainclass;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {

        TestNG runner = new TestNG();

        List<String> suiteFiles = new ArrayList<String>();
        suiteFiles.add(args[0]);
        runner.setTestSuites(suiteFiles);
        runner.run();
    }
}
