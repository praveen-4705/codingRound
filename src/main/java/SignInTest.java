import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest{
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        getDriver().findElement(By.linkText("Your trips")).click();
        getDriver().findElement(By.id("SignIn")).click();

        getDriver().findElement(By.id("signInButton")).click();

        String errors1 = getDriver().findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        getDriver().quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
