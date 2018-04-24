package tw.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    public static final String GUESS_NUMBER_GAME_YOU_HAVE_6_CHANCES_TO_GUESS = "------Guess Number Game, You have 6 chances to guess!  ------";
    public static final String INPUT_STR_2_3_4_5 = "2 3 4 5";
    public static final String INPUT_STR_1_2_3_4 = "1 2 3 4";
    public static final String RESULT_1 = "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 4A0B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" + "Game Status: success";
    public static final String RESULT_2 = "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 0A3B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "Guess Result: 4A0B\n" + "Guess History:\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\n" + "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" + "Game Status: fail";
    private Game game;
    private AnswerGenerator answerGenerator;
    private GameController gameController;
    private ByteArrayOutputStream out=new ByteArrayOutputStream(2048);

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        System.setOut(new PrintStream(out));
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(Answer.createAnswer(INPUT_STR_1_2_3_4));
        game=new Game(answerGenerator);
        gameController= new GameController(game, new GameView());
    }

    @Test
    public void testBeginGame() throws IOException {
        gameController.beginGame();
        Assert.assertTrue( out.toString().contains(GUESS_NUMBER_GAME_YOU_HAVE_6_CHANCES_TO_GUESS));
    }

    @Test
    public void testPlay() throws IOException {
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.input()).thenReturn(Answer.createAnswer(INPUT_STR_2_3_4_5),Answer.createAnswer(INPUT_STR_1_2_3_4));
        gameController.play(command);
        Assert.assertTrue(out.toString().contains(RESULT_1));
    }

    @Test
    public void testPlayAndCantCoutinue() throws IOException {
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.input()).thenReturn(Answer.createAnswer(INPUT_STR_2_3_4_5),Answer.createAnswer(INPUT_STR_2_3_4_5),Answer.createAnswer(INPUT_STR_2_3_4_5),
                Answer.createAnswer(INPUT_STR_2_3_4_5),Answer.createAnswer(INPUT_STR_2_3_4_5),Answer.createAnswer(INPUT_STR_1_2_3_4));
        gameController.play(command);
        Assert.assertTrue(out.toString().contains(RESULT_2));
    }
}