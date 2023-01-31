package Listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    // This belongs to ISuiteListener and will execute before the Suite start
    @Override
    public void onStart(ISuite suite) {

        Reporter.log("About to begin executing Suite " + suite.getName(), true);
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    @Override
    public void onFinish(ISuite suite) {

        Reporter.log("About to end executing Suite " + suite.getName(), true);
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    @Override
    public void onStart(ITestContext testContext) {

        Reporter.log("About to begin executing Test " + testContext.getName(), true);
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    @Override
    public void onFinish(ITestContext testContext) {

        Reporter.log("Completed executing test " + testContext.getName(), true);
    }

    // This belongs to ITestListener and will execute only when the test is pass
    @Override
    public void onTestSuccess(ITestResult testResult) {

        // This is calling the printTestResults method
        printTestResults(testResult);
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    @Override
    public void onTestFailure(ITestResult testResult) {
        printTestResults(testResult);
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    @Override
    public void onTestStart(ITestResult testResult) {
        Reporter.log("Test: " +  testResult.getMethod().getMethodName() + " is starting", true);
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    @Override
    public void onTestSkipped(ITestResult testResult) {
        printTestResults(testResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        printTestResults(testResult);
    }

    private void printTestResults(ITestResult testResult) {

        Reporter.log("Test Method resides in " + testResult.getTestClass().getName(), true);

        if (testResult.getParameters().length != 0) {

            String params = null;

            for (Object parameter : testResult.getParameters()) {

                params += parameter.toString() + ",";

            }

            Reporter.log("Test Method had the following parameters : " + params, true);
        }

        String status = null;

        switch(testResult.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;

            case ITestResult.FAILURE:
                status = "Failed";
                break;

            case ITestResult.SKIP:
                status = "Skipped";
                break;
            default:
                System.out.println("Unknown status");
                break;
        }
        Reporter.log("Run Test: " +  testResult.getMethod().getMethodName() + " with status -> " + status, true);
        assert status != null;
        if (status.contains("Failed")) {
        }
    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
    @Override
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

        Reporter.log(textMsg, true);

    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
    @Override
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

        String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

        Reporter.log(textMsg, true);

    }

    // This will return method names to the calling function
    private String returnMethodName(ITestNGMethod method) {

        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }
}
