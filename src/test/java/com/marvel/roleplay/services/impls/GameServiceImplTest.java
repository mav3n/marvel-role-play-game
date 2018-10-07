package com.marvel.roleplay.services.impls;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.marvel.roleplay.domains.Player;
import com.marvel.roleplay.services.GameService;
import com.marvel.roleplay.utils.ConversionUtil;

public class GameServiceImplTest {

  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  private static final String GAME_START_SAVE_RESULT_FILE_PATH = "outputs/game_start_save_output.txt";
  private static final String GAME_RESUME_RESULT_FILE_PATH     = "outputs/game_resume_output.txt";

  @Test
  public void testStartNewGame() throws Exception {
    /***
     * Choose Ant-man (4th from the list) as Player and then,
     * Save and exit the game.
     */
    systemInMock.provideLines("4", "A");
    GameService gameService = new GameServiceImpl(false);
    gameService.startNewGame();
    Player opponent = gameService.getPlayers().get(1);
    String expectedOutput = ConversionUtil.convertFileContentToString(GAME_START_SAVE_RESULT_FILE_PATH);
    String actualOutput = systemOutRule.getLog();
    String replaceOpponentName = StringUtils.leftPad("Loki", opponent.getCharacter().getName().length(), " ");
    actualOutput = actualOutput.replaceAll(opponent.getCharacter().getName(), replaceOpponentName);
    assertEquals(expectedOutput, actualOutput);
  }
  /***
   * TODO - Some issue with TextFromStandardInputStream rule - test-cases passes individually but not together - Need to fix it.
   */
  @Test
  public void testResumeGame() throws Exception {
    /***
     * Resume the saved game and then,
     * Exit the game.
     */
    systemInMock.provideLines("X");
    GameService gameService = new GameServiceImpl(false);
    gameService.resumeGame();
    Player opponent = gameService.getPlayers().get(1);
    String expectedOutput = ConversionUtil.convertFileContentToString(GAME_RESUME_RESULT_FILE_PATH);
    String actualOutput = systemOutRule.getLog();
    String replaceOpponentName = StringUtils.leftPad("Loki", opponent.getCharacter().getName().length(), " ");
    actualOutput = actualOutput.replaceAll(opponent.getCharacter().getName(), replaceOpponentName);
    assertEquals(expectedOutput, actualOutput);
  }

}
