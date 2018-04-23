package tw.core;

import org.junit.Assert;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    public static final String s1_2_3_4 = "1 2 3 4";
    public static final String s1_32_4_2 = "1 32 4 2";
    public static final String NUM = "4";
    public static final int[] EXPECTEDS = {4, 0};
    private Answer answer = Answer.createAnswer(s1_2_3_4);

    @Test
    public void testToString() {
        Assert.assertEquals(s1_2_3_4, answer.toString());
    }

    @Test
    public void testGetIndexOfNum() {
        Assert.assertEquals(3, answer.getIndexOfNum(NUM));
    }

    @Test
    public void testCheck() {
        Assert.assertArrayEquals(EXPECTEDS, answer.check(answer).getValue());
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void testValidate() throws OutOfRangeAnswerException {
        Answer.createAnswer(s1_32_4_2).validate();
    }
}