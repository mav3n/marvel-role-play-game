package com.marvel.roleplay.utils;

import static com.marvel.roleplay.constants.RolePlayApiConstants.BOUNDARY_LINE;
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
        "|   " + String.format("%9s", "Player Id") + "   |  " + String.format("%18s", "Name") + "   |  " + String.format(
            "%11s", "Super Power") + "   |  " + String.format("%10s", "Experience") + "   |  " + String.format("%92s",
            "Opponents Energy Consumption based on Move Type") + "   |  ");
    println(LONG_BOUNDARY_LINE);
    for (MarvelCharacter character : characterList) {
      String energyMap = "[ ";
      for (Map.Entry<FightAction, Integer> entry : character.getActionEnergyMap().entrySet()) {
        energyMap += entry.getKey().name() + " = " + entry.getValue() + ", ";
      }
      energyMap = energyMap.substring(0, energyMap.length() - 2) + " ]";
      println("|   " + String.format("%9s", character.getId()) + "   |  " + String.format("%18s", character.getName())
          + "   |  " + String.format("%11s", character.getPower()) + "   |  " + String.format("%10s",
          character.getExperience() + " Fights") + "   |  " + String.format("%92s", energyMap) + "   |  ");
      println(LONG_BOUNDARY_LINE);
      Thread.sleep(500);
    }
  }
}
