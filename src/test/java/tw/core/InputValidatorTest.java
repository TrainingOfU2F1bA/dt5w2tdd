package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    private InputValidator inputValidator;

    @Before
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testValidate() {
        Boolean validate = inputValidator.validate("1 2 3 4");
        Assert.assertEquals(true,validate);
        validate=inputValidator.validate("1 2 3 ");
        Assert.assertEquals(false,validate);
        validate=inputValidator.validate("1 2 3 43");
        Assert.assertEquals(false,validate);

    }

}
