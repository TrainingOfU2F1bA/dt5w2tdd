package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.stream.IntStream;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    public static final String A3_B0 = "3A0B";
    public static final String A4_B0 = "4A0B";
    public static final String s1_2_3_4 = "1 2 3 4";
    public static final String s9_2_3_4 = "9 2 3 4";
    private Game game;
    private AnswerGenerator answerGenerator;
    private Answer answer1 = Answer.createAnswer(s1_2_3_4);
    private Answer answer2 = Answer.createAnswer(s9_2_3_4);

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(answer1);
        game=new Game(answerGenerator);
    }

    @Test
    public void testGuess() throws OutOfRangeAnswerException {
        GuessResult b = game.guess(answer1);
        Assert.assertEquals(A4_B0,b.getResult());
    }

    @Test
    public void testGuessFalse() throws OutOfRangeAnswerException {
        GuessResult b = game.guess(answer2);
        Assert.assertEquals(A3_B0, b.getResult());
    }

    @Test
    public void testCheckFail() {
        IntStream.range(1,6).boxed().map(x->answer2).forEach(game::guess);
        game.guess(answer1);
        Assert.assertEquals(GameStatus.FAIL,game.checkStatus());
    }

    @Test
    public void testCheckSuccess() {
        game.guess(answer1);
        Assert.assertEquals(GameStatus.SUCCESS,game.checkStatus());
    }

    @Test
    public void testCheckCoutinue() {
        IntStream.range(1,6).boxed().map(x->answer2).forEach(game::guess);
        Assert.assertTrue(game.checkCoutinue());
    }

}
