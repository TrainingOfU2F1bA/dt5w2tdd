package tw.core.generator;

import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() {
        randomIntGenerator = Mockito.mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void testGemerate() throws OutOfRangeAnswerException {
        Mockito.when(randomIntGenerator.generateNums(9,4)).thenReturn("1 2 3 4");
        Assert.assertEquals("1 2 3 4",answerGenerator.generate().toString());
    }
}

