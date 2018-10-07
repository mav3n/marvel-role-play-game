package com.marvel.roleplay.services.impls;

import static com.marvel.roleplay.constants.RolePlayApiConstants.BOUNDARY_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHOOSE_PLAYER_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.DEFAULT_ENERGY_LEVEL;
import static com.marvel.roleplay.constants.RolePlayApiConstants.DEFAULT_PLAYER_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_BEGIN_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_RESUME_FAILURE_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_RESUME_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_RESUME_SUCCESS_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_SAVE_FAILURE_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_SAVE_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_SAVE_SUCCESS_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.LOST_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER1_FILE_NAME;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER2_FILE_NAME;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.REDIRECT_TO_MAIN_MENU;
import static com.marvel.roleplay.constants.RolePlayApiConstants.USER_DIR_PATH_KEY;
import static com.marvel.roleplay.constants.RolePlayApiConstants.WIN_MSG;
import static com.marvel.roleplay.helpers.RolePlayAppHelper.getMarvelCharactersList;
import static com.marvel.roleplay.utils.PrintUtils.printCharacters;
import static com.marvel.roleplay.utils.PrintUtils.printGameControls;
import static com.marvel.roleplay.utils.PrintUtils.printGameStats;
import static com.marvel.roleplay.utils.PrintUtils.printWithBoundary;
import static com.marvel.roleplay.utils.PrintUtils.println;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.domains.Player;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.GameService;

public class GameServiceImpl implements GameService {

  private static Scanner scanner = new Scanner(System.in);

  private Boolean isMultiPlayer;

  private Player player1;
  /***
   *    Evil/Opponent Player
   */
  private Player player2;

  public GameServiceImpl(Boolean isMultiPlayer) {
    this.isMultiPlayer = isMultiPlayer;
  }

  @Override
  public void startNewGame() throws Exception {
    loadNewGame();
    startGame();
  }

  @Override
  public void resumeGame() throws Exception {
    loadSavedGame();
    startGame();
  }

  private void startGame() throws Exception {
    printWithBoundary(GAME_BEGIN_MSG);
    Thread.sleep(2000);
    printGameControls();
    Thread.sleep(2000);
    printGameStats(player1, player2);
    Player computerPlayer = player2;
    boolean isComputerMove = false;
    while (player1.getEnergyLevel() > 0 && player2.getEnergyLevel() > 0) {
      Player currentPlayer = player1.getHasNextTurn() ? player1 : player2;
      Player opponentPlayer = player1.getHasNextTurn() ? player2 : player1;
      char move = getMove(currentPlayer, isComputerMove);
      int energyLoss = 0;
      switch (move) {
        case 'X': {
          return;
        }
        case 'A': {
          saveGame();
          return;
        }
        case 'P': {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.PUNCH);
          break;
        }
        case 'S': {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.SUPER_PUNCH);
          break;
        }
        case 'K': {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.FRONT_KICK);
          break;
        }
        case 'F': {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.FLYING_KICK);
          break;
        }
        case 'L': {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.FLIP);
          break;
        }
        default: {
          energyLoss = currentPlayer.getCharacter().getActionEnergyMap().get(FightAction.BLOCK);
          break;
        }
      }
      println(currentPlayer.getCharacter().getName() + " entered " + move);
      println(opponentPlayer.getCharacter().getName() + " lost " + energyLoss + " energy.");
      opponentPlayer.setEnergyLevel(Math.max(opponentPlayer.getEnergyLevel() - energyLoss, 0));
      opponentPlayer.setHasNextTurn(true);
      currentPlayer.setHasNextTurn(false);
      if (!Optional.ofNullable(isMultiPlayer).orElse(Boolean.FALSE)) {
        isComputerMove = !isComputerMove;
      }
      printGameStats(player1, player2);
      Thread.sleep(2500);
    }
    player1.addExperience();
    player2.addExperience();
    println(BOUNDARY_LINE);
    println(BOUNDARY_LINE);
    if (!isMultiPlayer) {
      println(computerPlayer.getEnergyLevel() == 0 ? WIN_MSG : LOST_MSG);
    } else {
      println("\t\t\t" + (player1.getEnergyLevel() == 0 ?
          player2.getCharacter().getName() :
          player1.getCharacter().getName()) + " WON!");
    }
    println(BOUNDARY_LINE);
    println(BOUNDARY_LINE);
    Thread.sleep(2500);
    printWithBoundary(REDIRECT_TO_MAIN_MENU);
  }

  private void saveGame() {
    printWithBoundary(GAME_SAVE_MSG);
    FileOutputStream file;
    ObjectOutputStream out;
    try {
      Thread.sleep(1000);
      file = new FileOutputStream(getFileFromResource(PLAYER1_FILE_NAME));
      out = new ObjectOutputStream(file);
      out.writeObject(player1);
      out.close();
      file.close();
      file = new FileOutputStream(getFileFromResource(PLAYER2_FILE_NAME));
      out = new ObjectOutputStream(file);
      out.writeObject(player2);
      out.close();
      file.close();
      printWithBoundary(GAME_SAVE_SUCCESS_MSG);
    } catch (IOException | InterruptedException ex) {
      printWithBoundary(GAME_SAVE_FAILURE_MSG);
    }
  }

  private void loadSavedGame() throws Exception {
    printWithBoundary(GAME_RESUME_MSG);
    FileInputStream file;
    ObjectInputStream in;
    try {
      Thread.sleep(1000);
      file = new FileInputStream(getFileFromResource(PLAYER1_FILE_NAME));
      in = new ObjectInputStream(file);
      player1 = (Player) in.readObject();
      in.close();
      file.close();
      file = new FileInputStream(getFileFromResource(PLAYER2_FILE_NAME));
      in = new ObjectInputStream(file);
      player2 = (Player) in.readObject();
      in.close();
      file.close();
      printWithBoundary(GAME_RESUME_SUCCESS_MSG);
    } catch (IOException | ClassNotFoundException | InterruptedException ex) {
      printWithBoundary(GAME_RESUME_FAILURE_MSG);
      loadNewGame();
    }
  }

  private File getFileFromResource(String fileName) {
    File file = null;
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      file = new File(classLoader.getResource(fileName).getFile());
      if (!file.exists()) {
        file = new File(System.getProperty(USER_DIR_PATH_KEY) + "/" + fileName);
      }
    } catch (Exception e) {
    }
    return file;
  }

  private void loadNewGame() throws Exception {
    player1 = new Player(choosePlayer(), DEFAULT_ENERGY_LEVEL, Boolean.TRUE);
    player2 = RolePlayAppHelper.getRandomEvilPlayer(DEFAULT_ENERGY_LEVEL);
  }

  private static char getMove(Player player, boolean isComputerMove) {
    System.out.println(player.getCharacter().getName() + " enter your move : ");
    if (isComputerMove) {
      char[] moves = new char[] {'P', 'S', 'K', 'F', 'L', 'B'};
      return moves[new Random().nextInt(moves.length)];
    } else {
      char move = 'B';
      try {
        move = scanner.nextLine().toUpperCase().charAt(0);
      } catch (Exception e) {
      }
      return move;
    }
  }

  private static MarvelCharacter choosePlayer() throws Exception {
    printWithBoundary(CHOOSE_PLAYER_MSG);
    printCharacters(getMarvelCharactersList());
    println("\n" + PLAYER_INPUT_MSG);
    int selectedPlayerId = 1;
    try {
      selectedPlayerId = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      println(DEFAULT_PLAYER_MSG);
    }
    return Optional.ofNullable(RolePlayAppHelper.getMarvelCharacters().get(selectedPlayerId))
        .orElse(RolePlayAppHelper.getMarvelCharacters().get(1));
  }

}
