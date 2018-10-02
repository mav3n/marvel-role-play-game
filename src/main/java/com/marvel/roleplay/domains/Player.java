package com.marvel.roleplay.domains;

public class Player {

    private MarvelCharacter character;
    private Integer energyLevel;
    private Boolean hasNextTurn;

    public Player(MarvelCharacter character, Integer energyLevel, Boolean hasNextTurn) {
        this.character = character;
        this.energyLevel = energyLevel;
        this.hasNextTurn = hasNextTurn;
    }

    public Player() {
    }

    public MarvelCharacter getCharacter() {
        return character;
    }

    public void setCharacter(MarvelCharacter character) {
        this.character = character;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Boolean getHasNextTurn() {
        return hasNextTurn;
    }

    public void setHasNextTurn(Boolean hasNextTurn) {
        this.hasNextTurn = hasNextTurn;
    }
}
