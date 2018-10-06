package com.marvel.roleplay.utils;

import static com.marvel.roleplay.constants.RolePlayApiConstants.BOUNDARY_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_LEFT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_MIDDLE_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_RIGHT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.LONG_BOUNDARY_LINE;

import java.util.List;
import java.util.Map;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.enums.FightAction;

public class PrintUtils {

  public static void println(String string) {
    System.out.println(string);
  }

  public static void print(String string) {
    System.out.print(string);
  }

  public static void printWithBoundary(String string) {
    System.out.println(BOUNDARY_LINE);
    System.out.println(string);
    System.out.println(BOUNDARY_LINE);
  }

  public static void printCharacters(List<MarvelCharacter> characterList) throws Exception {
    println(LONG_BOUNDARY_LINE);
    println(
        CHARACTERS_LEFT_BORDER + String.format("%9s", "Player Id") + CHARACTERS_MIDDLE_BORDER + String.format("%18s",
            "Name") + CHARACTERS_MIDDLE_BORDER + String.format("%11s", "Super Power") + CHARACTERS_MIDDLE_BORDER
            + String.format("%10s", "Experience") + CHARACTERS_MIDDLE_BORDER + String.format("%92s",
            "Opponents Energy Consumption based on Move Type") + CHARACTERS_RIGHT_BORDER);
    println(LONG_BOUNDARY_LINE);
    for (MarvelCharacter character : characterList) {
      String energyMap = "[ ";
      for (Map.Entry<FightAction, Integer> entry : character.getActionEnergyMap().entrySet()) {
        energyMap += entry.getKey().name() + " = " + entry.getValue() + ", ";
      }
      energyMap = energyMap.substring(0, energyMap.length() - 2) + " ]";
      println(
          CHARACTERS_LEFT_BORDER + String.format("%9s", character.getId()) + CHARACTERS_MIDDLE_BORDER + String.format(
              "%18s", character.getName()) + CHARACTERS_MIDDLE_BORDER + String.format("%11s", character.getPower())
              + CHARACTERS_MIDDLE_BORDER + String.format("%10s", character.getExperience() + " Fights")
              + CHARACTERS_MIDDLE_BORDER + String.format("%92s", energyMap) + CHARACTERS_RIGHT_BORDER);
      println(LONG_BOUNDARY_LINE);
      Thread.sleep(500);
    }
  }
}
