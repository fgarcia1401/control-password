package br.com.fernando.control_password;

import org.junit.Test;

import br.com.fernando.control_password.util.ValidPassword;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ControlPasswordTest {
    @Test
    public void validNumerid() throws Exception {
        assertEquals(ValidPassword.containsNumeric("1234"), true);
    }

    @Test
    public void validCharacter() throws Exception {
        assertEquals(ValidPassword.containsCharacter("43431"), false);
    }

    @Test
    public void validUppercase() throws Exception {
        assertEquals(ValidPassword.containsUppercase("dsadsaF"), true);
    }


}