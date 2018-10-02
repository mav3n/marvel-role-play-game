package com.marvel.roleplay.services;

import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.domains.Player;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.GameMenu;
import com.marvel.roleplay.helpers.RolePlayAppHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameService {

    private static Scanner scanner = new Scanner(System.in);

    private Boolean resumeGame;

    private Player player1;
    // Computer
    private Player player2;

    private static Integer DEFAULT_ENERGY_LEVEL;

    public GameService(Boolean resumeGame){
        this.resumeGame = resumeGame;
        if(Optional.ofNullable(resumeGame).orElse(Boolean.FALSE)){

        } else {
            player1 = new Player(choosePlayer(), DEFAULT_ENERGY_LEVEL, Boolean.FALSE);
            player2 = new Player(choosePlayer(), DEFAULT_ENERGY_LEVEL, Boolean.FALSE);
        }
    }

    public void startGame(){
        System.out.println("Get Ready! The game is about to begin!");
        MarvelCharacter char1 = choosePlayer();
    }


    private static MarvelCharacter choosePlayer(){
        System.out.println("Choose a player by entering the Player Id to start the game : ");
        for(MarvelCharacter character : RolePlayAppHelper.getMarvelCharactersList()){
            System.out.println("-------------------------------------------------------------------");
            if(character.getId() == 1){
                System.out.println("/t DEFAULT PLAYER");
            }
            System.out.println("/t Player Id  : " + character.getId());
            System.out.println("/t Name  : " + character.getName());
            System.out.println("/t Super Power  : " + character.getPower());
            System.out.println("/t Experience (No. of fights)  : " + character.getId());
            System.out.println("/t Opponents Energy Drained based on Move Type:");
            for(Map.Entry<FightAction, Integer> entry :  character.getActionEnergyMap().entrySet()){
                System.out.println("/t/t "+entry.getKey().name()+"  : " + entry.getValue());
            }
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("/nPlease enter a valid Player Id (otherwise default player will be selected) :");
        int selectedPlayerId = 1;
        try{
            selectedPlayerId = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Default Player selected!");
        }
        return Optional.ofNullable(RolePlayAppHelper.getMarvelCharacters()
                .get(selectedPlayerId)).orElse(RolePlayAppHelper.getMarvelCharacters().get(1));
    }

}
