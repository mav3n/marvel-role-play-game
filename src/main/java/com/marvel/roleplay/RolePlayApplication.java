package com.marvel.roleplay;

import static com.marvel.roleplay.constants.RolePlayApiConstants.EXIT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.INVALID_OPTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.NEWLINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.OPTION_SELECTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.SEPARATOR;
import static com.marvel.roleplay.constants.RolePlayApiConstants.USER_ACTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.VALID_OPTION_SELECTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.WELCOME_MSG;
import static com.marvel.roleplay.utils.PrintUtils.print;
import static com.marvel.roleplay.utils.PrintUtils.printWithBoundary;
import static com.marvel.roleplay.utils.PrintUtils.println;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.marvel.roleplay.enums.GameMenu;
import com.marvel.roleplay.services.CharacterService;
import com.marvel.roleplay.services.ExploreService;
import com.marvel.roleplay.services.GameService;
import com.marvel.roleplay.services.impls.CharacterServiceImpl;
import com.marvel.roleplay.services.impls.ExploreServiceImpl;
import com.marvel.roleplay.services.impls.GameServiceImpl;

public class RolePlayApplication {

  private static Scanner scanner = new Scanner(System.in);

  private static GameService      gameService      = new GameServiceImpl(false);
  private static CharacterService characterService = new CharacterServiceImpl();
  private static ExploreService   exploreService   = new ExploreServiceImpl();

  public static void main(String[] args) throws Exception {
    printWithBoundary(WELCOME_MSG);
    do {
      int selectedOption = getValidUserAction();
      switch (selectedOption) {
        case 1: {
          gameService.startNewGame();
          break;
        }
        case 2: {
          gameService.resumeGame();
          break;
        }
        case 3: {
          characterService.createCharacter();
          break;
        }
        case 4: {
          exploreService.exploreMarvel();
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

  private static int getValidUserAction() {
    println(USER_ACTION_MSG);
    for (GameMenu menuOption : GameMenu.values()) {
      println(menuOption.getActionCode() + SEPARATOR + menuOption.getActionText());
    }
    String optionSelectionMessage = NEWLINE + OPTION_SELECTION_MSG;
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
        throw new Exception(INVALID_OPTION_MSG);

      } catch (Exception e) {
        optionSelectionMessage = NEWLINE + VALID_OPTION_SELECTION_MSG;
      }
    }
  }
}
