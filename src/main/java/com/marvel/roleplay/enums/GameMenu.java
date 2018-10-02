package com.marvel.roleplay.enums;

public enum GameMenu {

    START(1, "Start Game"),
    RESUME(2, "Resume Last Game"),
    CREATE_CHARACTER(3, "Create a Character"),
    EXIT(4, "Exit the Game");

    private final int actionCode;
    private final String actionText;

    GameMenu(int actionCode, String actionText){
        this.actionCode = actionCode;
        this.actionText = actionText;
    }

    public int getActionCode(){
        return actionCode;
    }

    public String getActionText(){
        return actionText;
    }

}
