import model.Code;
import model.TuringMachineException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CodeTest {


    @Test
    public void testValidCode() {
        Code code = new Code(345);
        assertEquals(345, code.getSecretCodeValue());
    }

    @Test
    public void testInvalidCodeLength() {
        assertThrows(TuringMachineException.class, () -> new Code(1234));
    }

    @Test
    public void testInvalidCodeRange() {
        assertThrows(TuringMachineException.class, () -> new Code(45));
    }

    @Test
    public void testGetCifferAtIndex() {
        Code code = new Code(456);
        assertEquals(4, code.getCifferAtIndex(0));
        assertEquals(5, code.getCifferAtIndex(1));
        assertEquals(6, code.getCifferAtIndex(2));
    }

    @Test
    public void testGetCifferAtIndexOutOfBounds() {
        Code code = new Code(456);
        assertThrows(TuringMachineException.class, () -> code.getCifferAtIndex(3));
    }

}
