package com.marvel.roleplay;


import com.marvel.roleplay.domains.MarvelCharacter;
import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.GameMenu;
import com.marvel.roleplay.enums.Power;
import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.GameService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RolePlayApplication {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to the Marvel Role Play Game!/n");
		boolean successFlag = true;
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
					System.out.println("Exiting the Game!");
					System.exit(0);
				}
				default : {
					successFlag = false;
				}
			}
		} while(!successFlag);
	}

	private static boolean startGame(){
		GameService gameService = new GameService(false);
		gameService.startGame();
		return false;
	}

	private static boolean resumeGame(){
		GameService gameService = new GameService(true);
		gameService.startGame();
		return false;
	}

	private static boolean createCharacter(){
		boolean success = false;
		try{
			MarvelCharacter newCharacter = new MarvelCharacter();
			System.out.println("Enter New Character's Name : ");
			newCharacter.setName(scanner.nextLine());
			newCharacter.setPower(getValidPower());
			newCharacter.setActionEnergyMap(RolePlayAppHelper.createEnergyMap(
					getValidEnergyDrainedFromFightAction(FightAction.FRONT_KICK),
					getValidEnergyDrainedFromFightAction(FightAction.FLYING_KICK),
					getValidEnergyDrainedFromFightAction(FightAction.PUNCH),
					getValidEnergyDrainedFromFightAction(FightAction.SUPER_PUNCH),
					getValidEnergyDrainedFromFightAction(FightAction.FLIP)
					));
			newCharacter.setExperience(0);
			RolePlayAppHelper.addCharacter(newCharacter);
			System.out.println("Hurray! We have successfully added the new Character. Redirecting to the Main Menu.");
		} catch(Exception ex) {
			System.out.println("Oops! Something went wrong. Couldn't add the new Character. Redirecting to the Main Menu.");
			//success = false;
		}
		return success;
	}

	private static int getValidEnergyDrainedFromFightAction(FightAction fightAction){
		int energyDrained = 0;
		while(true){
			try {
				System.out.println("Enter the opponents energy drained (number between 1-20) by user Fight Action - " + fightAction.name() + " : ");
				energyDrained = scanner.nextInt();
				if(energyDrained>=1 && energyDrained<=20)
					break;
				else
					throw new Exception("Invalid Entry!");
			} catch (Exception ex) {
				System.out.println("/nInvalid Entry! Try Again!");
			}
		}
		return energyDrained;

	}

	private static Power getValidPower(){
		String optionSelectionMessage = "Choose New Character's Power (Enter in Caps): ( ";
		while(true) {
			try {
				System.out.print(optionSelectionMessage);
				for (Power p : Power.values()) {
					System.out.print(p.name() + "  ");
				}
				System.out.println(")");
				String selectedPower = scanner.nextLine();
				if (Arrays.stream(Power.values()).map(power -> power.name()).collect(Collectors.toList()).contains(selectedPower)) {
					return Power.valueOf(selectedPower);
				}
				throw new Exception("Invalid Power Selected!");
			} catch (Exception ex) {
				System.out.println("Invalid Option! Try Again!");
			}
		}
	}


	private static int getValidUserAction(){
		System.out.println("Choose a number from the below options to continue:");
		for(GameMenu menuOption : GameMenu.values()){
			System.out.println(menuOption.getActionCode() + " - " + menuOption.getActionText());
		}
		String optionSelectionMessage = "/nPlease enter the desired option number:";
		int selectedOption = 0;
		while(true) {
			try {
				System.out.println(optionSelectionMessage);
				selectedOption = scanner.nextInt();
				if(Arrays.stream(GameMenu.values()).map(gameMenu -> gameMenu.getActionCode()).collect(Collectors.toList())
						.contains(selectedOption)){
					return selectedOption;
				}
				throw new Exception("Invalid Option!");

			} catch (Exception e) {
				optionSelectionMessage = "/nPlease enter a valid desired option number to continue : ";
			}
		}
	}
}
