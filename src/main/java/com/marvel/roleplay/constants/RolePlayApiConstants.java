package com.marvel.roleplay.constants;

public interface RolePlayApiConstants {

  String LONG_BOUNDARY_LINE           =
      "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
  String BOUNDARY_LINE                = "-----------------------------------------------------------------------------";
  String GAME_STATS_LINE              = "--------------------------------- GAME STATS --------------------------------";
  String GAME_CONTROLS_LINE           = "------------------------------- GAME CONTROLS -------------------------------";
  String PLAYER_MOVES                 = "-------------------------------  PLAYER MOVES -------------------------------";
  String WELCOME_MSG                  = "Welcome to the Marvel Role Play Game!";
  String EXIT_MSG                     = "Exiting the Game!";
  String REDIRECT_TO_MAIN_MENU        = "Redirecting to the Main Menu . . .";
  String CREATE_CHARACTER_SUCCESS_MSG = "Hurray! Successfully added the new Character.\n" + REDIRECT_TO_MAIN_MENU;
  String CREATE_CHARACTER_FAILURE_MSG = "Oops! Couldn't add the new Character.\n" + REDIRECT_TO_MAIN_MENU;
  String INVALID_ENTRY_MSG            = "Invalid Entry! Try Again!";
  String NAME_INPUT_MSG               = "Enter New Character's Name : ";
  String ENERGY_INPUT_MSG             =
      "Enter the opponents energy drained (number between 1-20) by user Fight Action - ";
  String POWER_INPUT_MSG              = "Choose New Character's Power (Enter in Caps) : ";
  String USER_ACTION_MSG              = "Choose a number from the below options to continue:";
  String OPTION_SELECTION_MSG         = "Please enter the desired option number: ";
  String VALID_OPTION_SELECTION_MSG   = "Please enter a valid desired option number to continue : ";
  String GAME_BEGIN_MSG               = "Get Ready! The game is about to begin!";
  String WIN_MSG                      = "\t\t\tHurray! You WON!";
  String LOST_MSG                     = "\t\t\tSorry! You LOST!";
  String PLAYER1_FILE_NAME            = "player1.ser";
  String PLAYER2_FILE_NAME            = "player2.ser";
  String GAME_SAVE_MSG                = "Saving your game . . .";
  String GAME_RESUME_MSG              = "Resuming your game . . .";
  String GAME_SAVE_SUCCESS_MSG        =
      "We have saved your game! Come back and resume anytime :)\n" + REDIRECT_TO_MAIN_MENU;
  String GAME_SAVE_FAILURE_MSG        = "Sorry! We couldn't save your game :(\n" + REDIRECT_TO_MAIN_MENU;
  String GAME_RESUME_SUCCESS_MSG      = "Hurray! We have resumed your game :)";
  String GAME_RESUME_FAILURE_MSG      = "Sorry! We couldn't resume your game :( \n Starting a new Game . . .";
  String PLAYER1                      = "Player 1 (YOU)";
  String PLAYER2                      = "PLAYER 2 (YOUR OPPONENT)";
  String CHOOSE_PLAYER_MSG            = "Choose a player by entering the Player Id to start the game : ";
  String PLAYER_INPUT_MSG             =
      "Please enter a valid Player Id (otherwise first player will be selected by default) :";
  String DEFAULT_PLAYER_MSG           = "Default Player selected!";
  String EXPLORE_MARVEL_STUDIOS       = "EXPLORE MARVEL STUDIO'S WORLD";
  String SUPER_HEROES                 = "Introducing Marvel Super Heroes . . .";
  String SUPER_VILLAINS               = "Introducing Marvel Super Villains . . . ";
  String EXPLORE_END_MSG              =
      "Now, its time to become a Super Hero and defeat the Villains to Save the world!";

  Integer DEFAULT_ENERGY_LEVEL = 100;
}
