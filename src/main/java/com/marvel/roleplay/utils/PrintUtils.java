package com.marvel.roleplay.utils;

import static com.marvel.roleplay.constants.RolePlayApiConstants.BOUNDARY_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_LEFT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_MIDDLE_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.CHARACTERS_RIGHT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_CONTROLS_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_CONTROL_LEFT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_CONTROL_MIDDLE_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_CONTROL_RIGHT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_ENERGY_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_LEFT_EMPTY;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_MIDDLE_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_NAME_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.GAME_STATS_RIGHT_BORDER;
import static com.marvel.roleplay.constants.RolePlayApiConstants.LONG_BOUNDARY_LINE;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER1;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER2;
import static com.marvel.roleplay.constants.RolePlayApiConstants.PLAYER_MOVES;

import java.util.List;
import java.util.Map;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.domains.Player;
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

  public static void printGameControls() {
    println(GAME_CONTROLS_LINE);
    println(GAME_CONTROL_LEFT_BORDER + String.format("%22s", "Exit Game") + GAME_CONTROL_MIDDLE_BORDER + String.format(
        "%22s", "Enter X") + GAME_CONTROL_RIGHT_BORDER);
    println(GAME_CONTROL_LEFT_BORDER + String.format("%22s", "Save & Exit Game") + GAME_CONTROL_MIDDLE_BORDER
        + String.format("%22s", "Enter A") + GAME_CONTROL_RIGHT_BORDER);
    println(PLAYER_MOVES);
    println(
        GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.PUNCH.name()) + GAME_CONTROL_MIDDLE_BORDER + String
            .format("%22s", "Enter P") + GAME_CONTROL_RIGHT_BORDER);
    println(
        GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.SUPER_PUNCH.name()) + GAME_CONTROL_MIDDLE_BORDER
            + String.format("%22s", "Enter S") + GAME_CONTROL_RIGHT_BORDER);
    println(GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.FRONT_KICK.name()) + GAME_CONTROL_MIDDLE_BORDER
        + String.format("%22s", "Enter K") + GAME_CONTROL_RIGHT_BORDER);
    println(
        GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.FLYING_KICK.name()) + GAME_CONTROL_MIDDLE_BORDER
            + String.format("%22s", "Enter F") + GAME_CONTROL_RIGHT_BORDER);
    println(GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.FLIP.name()) + GAME_CONTROL_MIDDLE_BORDER
        + String.format("%22s", "Enter L") + GAME_CONTROL_RIGHT_BORDER);
    println(GAME_CONTROL_LEFT_BORDER + String.format("%22s", FightAction.BLOCK.name() + " (Default Move)")
        + GAME_CONTROL_MIDDLE_BORDER + String.format("%22s", "Enter B or Any other") + GAME_CONTROL_RIGHT_BORDER);
    println(BOUNDARY_LINE);
  }

  public static void printGameStats(Player player1, Player player2) {
    println(GAME_STATS_LINE);
    println(GAME_STATS_LEFT_EMPTY + String.format("%20s", PLAYER1) + GAME_STATS_MIDDLE_BORDER + String.format("%24s",
        PLAYER2) + GAME_STATS_RIGHT_BORDER);
    println(BOUNDARY_LINE);
    println(GAME_STATS_NAME_BORDER + String.format("%20s", player1.getCharacter().getName()) + GAME_STATS_MIDDLE_BORDER
        + String.format("%24s", player2.getCharacter().getName()) + GAME_STATS_RIGHT_BORDER);
    println(GAME_STATS_ENERGY_BORDER + String.format("%20s", player1.getEnergyLevel()) + GAME_STATS_MIDDLE_BORDER
        + String.format("%24s", player2.getEnergyLevel()) + GAME_STATS_RIGHT_BORDER);
    printWithBoundary("Next Turn : " + (player1.getHasNextTurn() ?
        player1.getCharacter().getName() :
        player2.getCharacter().getName()));
  }

}
