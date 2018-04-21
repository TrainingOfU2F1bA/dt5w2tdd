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

    private Game game;
    private AnswerGenerator answerGenerator;

    @Before
    public void setUp() throws Exception {
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game=new Game(answerGenerator);
    }

    @Test
    public void testGuess() throws OutOfRangeAnswerException {
        GuessResult b = game.guess(Answer.createAnswer("1 2 3 4"));
        Assert.assertEquals("4A0B",b.getResult());
    }

    @Test
    public void testGuessFalse() throws OutOfRangeAnswerException {
        GuessResult b = game.guess(Answer.createAnswer("9 2 3 4"));
        Assert.assertEquals("3A0B", b.getResult());
    }

    @Test
    public void testCheckStatus() {
        Answer answer = Answer.createAnswer("9 2 3 4");
        IntStream.range(1,5).boxed().map(x->answer).forEach(game::guess);
        Assert.assertEquals(GameStatus.CONTINUE,game.checkStatus());
        game.guess(Answer.createAnswer("1 2 3 4"));
        Assert.assertEquals(GameStatus.SUCCESS,game.checkStatus());
        game.guess(Answer.createAnswer("1 2 3 4"));
        Assert.assertEquals(GameStatus.FAIL,game.checkStatus());
    }

    @Test
    public void testCheckCoutinue() {
        Answer answer = Answer.createAnswer("9 2 3 4");
        IntStream.range(1,6).boxed().map(x->answer).forEach(game::guess);
        Assert.assertTrue(game.checkCoutinue());
        game.guess(Answer.createAnswer("1 2 3 4"));
        Assert.assertFalse(game.checkCoutinue());
    }

}
