package com.marvel.roleplay.helpers;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.domains.Player;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.Power;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RolePlayAppHelper {

    public static Map<Integer, MarvelCharacter> marvelCharacters = new HashMap<>();
    public static Map<Integer, MarvelCharacter> evilMarvelCharacters = new HashMap<>();

    public static void addCharacter(MarvelCharacter character){
        character.setId(marvelCharacters.size() + 1);
        marvelCharacters.put(marvelCharacters.size() + 1, character);
    }

    public static Map<Integer, MarvelCharacter> getMarvelCharacters(){
        return marvelCharacters;
    }

    public static List<MarvelCharacter> getMarvelCharactersList(){
        return marvelCharacters.values().stream().sorted(Comparator.comparing(MarvelCharacter::getId))
                .collect(Collectors.toList());
    }

    public static Player getRandomEvilPlayer(){
        int size = evilMarvelCharacters.size();
        //Integer id = evilMarvelCharacters.keySet().stream().
        return new Player(evilMarvelCharacters.get());
    }

    public static Map<FightAction, Integer> createEnergyMap(int frontKick, int flyingKick, int punch, int superPunch, int flip){
        Map<FightAction, Integer> energyMap = new HashMap<>();
        energyMap.put(FightAction.FRONT_KICK, frontKick);
        energyMap.put(FightAction.FLYING_KICK, flyingKick);
        energyMap.put(FightAction.PUNCH, punch);
        energyMap.put(FightAction.SUPER_PUNCH, superPunch);
        energyMap.put(FightAction.FLIP, flip);
        return energyMap;
    }

    // region Private Methods

    private static void getDefaultEvilMarvelCharacter(){
        return new MarvelCharacter(101, "Thanos", Power.STRENGTH, createEnergyMap(5, 10, 6, ))
    }

    private static void loadDefaultCharacters(){
        marvelCharacters.put(1, new MarvelCharacter(1, "Ant Man", Power.SHRINK, createEnergyMap(4,6, 4, 7, 6), 25));
        marvelCharacters.put(2, new MarvelCharacter(2, "Iron Man", Power.MIND, createEnergyMap(5,10, 4, 10, 8), 53));
        marvelCharacters.put(3, new MarvelCharacter(3, "Hulk", Power.STRENGTH, createEnergyMap(8,10, 12, 20, 10), 43));
        marvelCharacters.put(4, new MarvelCharacter(4, "Dead Pool", Power.AGILITY, createEnergyMap(4,7, 4, 8, 8), 23));
        marvelCharacters.put(5, new MarvelCharacter(5, "Thor", Power.STAMINA, createEnergyMap(6,9, 8, 15, 10), 36));
        marvelCharacters.put(6, new MarvelCharacter(6, "Captain Marvel", Power.MIND, createEnergyMap(5,10, 5, 10, 8), 5));
        marvelCharacters.put(7, new MarvelCharacter(7, "Captain America", Power.STRENGTH, createEnergyMap(7,12, 7, 12, 7), 28));
        marvelCharacters.put(8, new MarvelCharacter(8, "Black Panther", Power.SPEED, createEnergyMap(8,12, 6, 10, 10), 15));
        marvelCharacters.put(9, new MarvelCharacter(9, "Wasp", Power.SHRINK, createEnergyMap(4,7, 4, 7, 7), 8));
        marvelCharacters.put(10, new MarvelCharacter(10, "Spider Man", Power.DURABILITY, createEnergyMap(4,6, 4, 8, 6), 5));


        evilMarvelCharacters.put(101, new MarvelCharacter(101, "Thanos", Power.STRENGTH, createEnergyMap(5, 10, 12, 20, 10), 89));
        evilMarvelCharacters.put(102, new MarvelCharacter(102, "Loki", Power.MIND, createEnergyMap(4, 6, 4, 7, 5), 39));
        evilMarvelCharacters.put(103, new MarvelCharacter(103, "Ultron", Power.STAMINA, createEnergyMap(6, 12, 10, 15, 8), 49));
        evilMarvelCharacters.put(104, new MarvelCharacter(104, "Red Skull", Power.DURABILITY, createEnergyMap(5, 8, 6, 10, 6), 59));
        evilMarvelCharacters.put(105, new MarvelCharacter(105, "Mandarin", Power.SPEED, createEnergyMap(5, 10, 7, 12, 5), 69));
    }

    // endregion
}
