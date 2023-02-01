import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/example/movieRankedProject/features",
        glue = "com/example/movieRankedProject/stepDefinitions"
)
public class TestRunner {
}
