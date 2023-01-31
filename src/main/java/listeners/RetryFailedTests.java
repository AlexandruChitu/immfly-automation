package Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " with status " +
                    getResultStatus(result.getStatus()) + " for the " + retryCount);

            retryCount++;
            return true;
        }
        return false;
    }

    public String getResultStatus(int status) {
        String resultStatus = null;

        if (status == 1)
            resultStatus = "SUCCESS";
        if (status == 2)
            resultStatus = "FAILURE";
        if (status == 3)
            resultStatus = "SKIP";

        return resultStatus;
    }
}
