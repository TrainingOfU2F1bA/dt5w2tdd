package tw.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private Game game;
    private AnswerGenerator answerGenerator;
    private GameController gameController;
    private ByteArrayOutputStream out=new ByteArrayOutputStream(2048);

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(out));
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game=new Game(answerGenerator);
        GameView gameView=new GameView();
        gameController= new GameController(game,gameView);
    }

    @Test
    public void testBeginGame() throws IOException {
        gameController.beginGame();
        Assert.assertTrue( out.toString().contains("------Guess Number Game, You have 6 chances to guess!  ------"));
    }

    @Test
    public void testPlay() throws IOException {
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.input()).thenReturn(Answer.createAnswer("2 3 4 5"),Answer.createAnswer("1 2 3 4"));
        gameController.play(command);
        Assert.assertTrue(out.toString().contains(
        "Guess Result: 0A3B\r\n" +
                "Guess History:\r\n" +
                "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                "Guess Result: 4A0B\r\n" +
                "Guess History:\r\n" +
                "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n" +
                "Game Status: success"));
    }

    @Test
    public void testPlayAndCantCoutinue() throws IOException {
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.input()).thenReturn(Answer.createAnswer("2 3 4 5"),Answer.createAnswer("2 3 4 5"),
                Answer.createAnswer("2 3 4 5"),Answer.createAnswer("2 3 4 5"),Answer.createAnswer("2 3 4 5"),Answer.createAnswer("1 2 3 4"));
        gameController.play(command);
        Assert.assertTrue(out.toString().contains(
                "Guess Result: 0A3B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "Guess Result: 0A3B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "Guess Result: 0A3B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "Guess Result: 0A3B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "Guess Result: 0A3B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "Guess Result: 4A0B\r\n" +
                        "Guess History:\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n" +
                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n" +
                        "Game Status: fail"));
    }
}