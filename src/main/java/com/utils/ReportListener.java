package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

/**
 * This class mainly used for reporting using the Extent Reports.
 *
 * @author praveen
 */
public class ReportListener implements IReporter {

    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {

        String date = new SimpleDateFormat("dd-MM-yy HH:mm:ss:SSSSSSS").format(new Date());
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                outputDirectory + File.separator + suites.get(0).getName() + "_" + date + ".html");

        htmlReporter.config().setChartVisibilityOnOpen(true);

        // Report configurations
        htmlReporter.config()
                .setJS(
                        "$(document).ready(function() { var link = document.createElement('link');link.type = 'image/x-icon';link.rel = 'shortcut icon';link.href = 'http://www.testvagrant.com/wp-content/themes/testvagrant/images/favicon.ico';document.getElementsByTagName('head')[0].appendChild(link);  });"
                                + "$(document).ready(function() { var a = document.getElementsByClassName('brand-logo')[0]; a.innerHTML='TestVagrant'; a.href = 'https://www.testvagrant.com';});");
        htmlReporter.config().setDocumentTitle("QA Automation Report");
        htmlReporter.config().setReportName("QA Automation Report");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);

        // Put Failures on top of the report
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getFailedTests(), Status.FAIL);
            }
        }

        // Skipped
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }

        // Passed
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), Status.PASS);
            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                String testName = result.getMethod().getMethodName();
                System.out.println(result.getMethod().getMethodName());
                test = extent.createTest(testName).assignAuthor("automation");

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                if (result.getThrowable() != null) {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                    try {
                        test.addScreenCaptureFromPath(testName + ".jpeg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");

                    try {
                        test.addScreenCaptureFromPath(testName + ".jpeg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
