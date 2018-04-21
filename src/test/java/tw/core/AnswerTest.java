package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    private Answer answer;

    @Before
    public void setUp() {
        answer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    public void name() {
        String s = answer.toString();
        Assert.assertEquals("1 2 3 4",s);
    }

    @Test
    public void testGetIndexOfNum() {
        int indexOfNum = answer.getIndexOfNum("4");
        Assert.assertEquals(3,indexOfNum);
    }

    @Test
    public void testCheck() {
        Record check = answer.check(Answer.createAnswer("1 2 3 4"));
        Assert.assertArrayEquals(new int[]{4,0},check.getValue());
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void testValidate() throws OutOfRangeAnswerException {
        answer=Answer.createAnswer("1 32 4 2");
        answer.validate();
    }
}