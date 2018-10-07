package com.marvel.roleplay.services.impls;

import static com.marvel.roleplay.constants.RolePlayApiConstants.CREATE_CHARACTER_FAILURE_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CREATE_CHARACTER_SUCCESS_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.ENERGY_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.INVALID_ENTRY_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.INVALID_POWER_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.NAME_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.POWER_INPUT_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.TRY_AGAIN_MSG;
import static com.marvel.roleplay.utils.PrintUtils.print;
import static com.marvel.roleplay.utils.PrintUtils.printWithBoundary;
import static com.marvel.roleplay.utils.PrintUtils.println;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.Power;
import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.CharacterService;

public class CharacterServiceImpl implements CharacterService {

  private static Scanner scanner = new Scanner(System.in);

  @Override
  public void createCharacter() {
    try {
      MarvelCharacter newCharacter = new MarvelCharacter();
      print(NAME_INPUT_MSG);
      String name = scanner.nextLine();
      newCharacter.setName(name);
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
  }

  private int getValidEnergyDrainedFromFightAction(FightAction fightAction) {
    int energyDrained = 0;
    while (true) {
      try {
        println(ENERGY_INPUT_MSG + fightAction.name() + " :");
        energyDrained = Integer.parseInt(scanner.nextLine());
        if (energyDrained >= 1 && energyDrained <= 20)
          break;
        else
          throw new Exception(INVALID_ENTRY_MSG);
      } catch (Exception ex) {
        println(TRY_AGAIN_MSG);
      }
    }
    return energyDrained;
  }

  private Power getValidPower() {
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
        throw new Exception(INVALID_POWER_MSG);
      } catch (Exception ex) {
        System.out.println(TRY_AGAIN_MSG);
      }
    }
  }

}
