import model.Problem;
import model.util.Loader;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {

    @Test
    void testGetInstance() {
        Loader loader1 = Loader.getInstance();
        Loader loader2 = Loader.getInstance();
        assertSame(loader1, loader2, "Instances should be the same");
    }

    @Test
    void testGetSpecificProblem() {
        Loader loader = Loader.getInstance();
        Problem problem = loader.getSpecificProblem(0); // Assuming index 0 exists
        assertNotNull(problem, "Problem should not be null");
        // Add more assertions based on the expected values of the specific problem
    }

    @Test
    void testGetProblems() {
        Loader loader = Loader.getInstance();
        List<Problem> problems = loader.getProblems();
        assertNotNull(problems, "List of problems should not be null");
        assertFalse(problems.isEmpty(), "List of problems should not be empty");
        // Add more assertions based on the expected values in the list of problems
    }
}
