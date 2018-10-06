package com.marvel.roleplay;

import static com.marvel.roleplay.constants.RolePlayApiConstants.EXIT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_END_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_MARVEL_STUDIOS;
import static com.marvel.roleplay.constants.RolePlayApiConstants.OPTION_SELECTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.SUPER_HEROES;
import static com.marvel.roleplay.constants.RolePlayApiConstants.SUPER_VILLAINS;
import static com.marvel.roleplay.constants.RolePlayApiConstants.USER_ACTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.VALID_OPTION_SELECTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.WELCOME_MSG;
import static com.marvel.roleplay.utils.PrintUtils.print;
import static com.marvel.roleplay.utils.PrintUtils.printCharacters;
import static com.marvel.roleplay.utils.PrintUtils.printWithBoundary;
import static com.marvel.roleplay.utils.PrintUtils.println;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.marvel.roleplay.enums.GameMenu;
import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.CharacterService;
import com.marvel.roleplay.services.GameService;
import com.marvel.roleplay.services.impls.CharacterServiceImpl;
import com.marvel.roleplay.services.impls.GameServiceImpl;

public class RolePlayApplication {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    printWithBoundary(WELCOME_MSG);
    do {
      int selectedOption = getValidUserAction();
      switch (selectedOption) {
        case 1: {
          startGame();
          break;
        }
        case 2: {
          resumeGame();
          break;
        }
        case 3: {
          createCharacter();
          break;
        }
        case 4: {
          exploreMarvel();
          break;
        }
        case 5: {
          printWithBoundary(EXIT_MSG);
          System.exit(0);
        }
        default: {
          continue;
        }
      }
    } while (true);
  }

  private static void startGame() throws Exception {
    GameService gameService = new GameServiceImpl(false);
    gameService.startNewGame();
  }

  private static void resumeGame() throws Exception {
    GameService gameService = new GameServiceImpl(false);
    gameService.resumeGame();
  }

  private static void createCharacter() {
    CharacterService characterService = new CharacterServiceImpl();
    characterService.createCharacter();
  }

  private static boolean exploreMarvel() throws Exception {
    printWithBoundary(EXPLORE_MARVEL_STUDIOS);
    Thread.sleep(2000);
    printWithBoundary(SUPER_HEROES);
    printCharacters(RolePlayAppHelper.getMarvelCharactersList());
    Thread.sleep(2000);
    printWithBoundary(SUPER_VILLAINS);
    printCharacters(RolePlayAppHelper.getEvilMarvelCharactersList());
    Thread.sleep(2000);
    printWithBoundary(EXPLORE_END_MSG);
    Thread.sleep(2000);
    return false;
  }

  private static int getValidUserAction() {
    println(USER_ACTION_MSG);
    for (GameMenu menuOption : GameMenu.values()) {
      println(menuOption.getActionCode() + " - " + menuOption.getActionText());
    }
    String optionSelectionMessage = "\n" + OPTION_SELECTION_MSG;
    int selectedOption = 0;
    while (true) {
      try {
        print(optionSelectionMessage);
        selectedOption = Integer.parseInt(scanner.nextLine());
        if (Arrays.stream(GameMenu.values())
            .map(gameMenu -> gameMenu.getActionCode())
            .collect(Collectors.toList())
            .contains(selectedOption)) {
          return selectedOption;
        }
        throw new Exception("Invalid Option!");

      } catch (Exception e) {
        optionSelectionMessage = "\n" + VALID_OPTION_SELECTION_MSG;
      }
    }
  }
}
