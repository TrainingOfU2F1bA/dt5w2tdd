package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    public static final String NUM_STR_1_2_3_4 = "1 2 3 4";
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() {
        randomIntGenerator = Mockito.mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void testGemerate() throws OutOfRangeAnswerException {
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn(NUM_STR_1_2_3_4);
        Assert.assertEquals(NUM_STR_1_2_3_4,answerGenerator.generate().toString());
    }
}

