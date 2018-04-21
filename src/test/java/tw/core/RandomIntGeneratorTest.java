package tw.core;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    private RandomIntGenerator randomIntGenerator;

    @Before
    public void setUp() {
        randomIntGenerator = new RandomIntGenerator();
    }

    @Test
    public void testGenerateNums() {
        InputValidator inputValidator = new InputValidator();
        Boolean validate = inputValidator.validate(randomIntGenerator.generateNums(9, 4));
        Assert.assertEquals(true,validate);
    }
}