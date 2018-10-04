package com.marvel.roleplay;

import static com.marvel.roleplay.constants.RolePlayApiConstants.CREATE_CHARACTER_FAILURE_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CREATE_CHARACTER_SUCCESS_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.ENERGY_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXIT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_END_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_MARVEL_STUDIOS;
import static com.marvel.roleplay.constants.RolePlayApiConstants.INVALID_ENTRY_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.NAME_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.OPTION_SELECTION_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.POWER_INPUT_MSG;
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

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.GameMenu;
import com.marvel.roleplay.enums.Power;
import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.GameService;

public class RolePlayApplication {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    printWithBoundary(WELCOME_MSG);
    boolean successFlag;
    do {
      int selectedOption = getValidUserAction();
      switch (selectedOption) {
        case 1: {
          successFlag = startGame();
          break;
        }
        case 2: {
          successFlag = resumeGame();
          break;
        }
        case 3: {
          successFlag = createCharacter();
          break;
        }
        case 4: {
          successFlag = exploreMarvel();
          break;
        }
        case 5: {
          printWithBoundary(EXIT_MSG);
          System.exit(0);
        }
        default: {
          successFlag = false;
        }
      }
    } while (!successFlag);
  }

  private static boolean startGame() throws Exception {
    GameService gameService = new GameService(false, false);
    gameService.startGame();
    return false;
  }

  private static boolean resumeGame() throws Exception {
    GameService gameService = new GameService(true, false);
    gameService.startGame();
    return false;
  }

  private static boolean createCharacter() {
    boolean success = false;
    try {
      MarvelCharacter newCharacter = new MarvelCharacter();
      print(NAME_INPUT_MSG);
      newCharacter.setName(scanner.nextLine());
      newCharacter.setPower(getValidPower());
      newCharacter.setActionEnergyMap(
          RolePlayAppHelper.createEnergyMap(getValidEnergyDrainedFromFightAction(FightAction.FRONT_KICK),
              getValidEnergyDrainedFromFightAction(FightAction.FLYING_KICK),
              getValidEnergyDrainedFromFightAction(FightAction.PUNCH),
              getValidEnergyDrainedFromFightAction(FightAction.SUPER_PUNCH),
              getValidEnergyDrainedFromFightAction(FightAction.FLIP)));
      newCharacter.setExperience(0);
      RolePlayAppHelper.addCharacter(newCharacter);
      printWithBoundary(CREATE_CHARACTER_SUCCESS_MSG);
    } catch (Exception ex) {
      printWithBoundary(CREATE_CHARACTER_FAILURE_MSG);
    }
    return success;
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

  private static int getValidEnergyDrainedFromFightAction(FightAction fightAction) {
    int energyDrained = 0;
    while (true) {
      try {
        println(ENERGY_INPUT_MSG + fightAction.name() + " : ");
        energyDrained = Integer.parseInt(scanner.nextLine());
        if (energyDrained >= 1 && energyDrained <= 20)
          break;
        else
          throw new Exception("Invalid Entry.");
      } catch (Exception ex) {
        println(INVALID_ENTRY_MSG);
      }
    }
    return energyDrained;

  }

  private static Power getValidPower() {
    String optionSelectionMessage = POWER_INPUT_MSG + " ( ";
    while (true) {
      try {
        print(optionSelectionMessage);
        for (Power p : Power.values()) {
          print(p.name() + ", ");
        }
        println("\b\b)");
        String selectedPower = scanner.nextLine();
        if (Arrays.stream(Power.values())
            .map(power -> power.name())
            .collect(Collectors.toList())
            .contains(selectedPower)) {
          return Power.valueOf(selectedPower);
        }
        throw new Exception("Invalid Power Selected.");
      } catch (Exception ex) {
        System.out.println(INVALID_ENTRY_MSG);
      }
    }
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
