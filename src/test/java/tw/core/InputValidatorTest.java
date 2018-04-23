package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import java.util.Arrays;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    public static final String NUM_STR_1_2_3_4 = "1 2 3 4";
    public static final String NUM_STR_1_2_3 = "1 2 3 ";
    public static final String NUM_STR_1_2_3_43 = "1 2 3 43";
    private InputValidator inputValidator;
    private String[] array = new String[]{NUM_STR_1_2_3_4, NUM_STR_1_2_3, NUM_STR_1_2_3_43};

    @Before
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testValidate() {
        Object[] objects = Arrays.stream(array).map(inputValidator::validate).toArray();
        Assert.assertArrayEquals(new Boolean[]{true,false,false},objects);
    }

}
