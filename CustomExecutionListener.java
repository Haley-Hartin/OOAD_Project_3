import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// https://stackoverflow.com/questions/46536753/print-test-result-in-junit

public class CustomExecutionListener extends RunListener{
	private static final Description FAILED = Description.createTestDescription("failed","failed");
	
	
	public void testRunStarted(Description description) throws Exception {
        System.out.println("Number of tests to execute: " + description.testCount() + "\n");
    }

    public void testRunFinished(Result result) throws Exception {
        System.out.println("Number of tests executed: " + result.getRunCount());
    }

    public void testStarted(Description description) throws Exception {
        System.out.println("Starting: " + description.getMethodName());
    }
    
    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
        failure.getDescription().addChild(FAILED);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Finished: " + description.getMethodName());
        if(description.getChildren().contains(FAILED)) {
        	System.out.println("Failed\n");
        }
        else {
        	System.out.println("Succeeded\n");
        }
    }
    
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) throws Exception {
        System.out.println("Ignored: " + description.getMethodName());
    }
}