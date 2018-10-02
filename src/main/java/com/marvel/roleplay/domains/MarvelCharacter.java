package com.marvel.roleplay.domains;

import com.marvel.roleplay.enums.FightAction;
import com.marvel.roleplay.enums.Power;

import java.util.HashMap;
import java.util.Map;

public class MarvelCharacter {

    private Integer id;
    private String name;
    private Power power;
    /***
     *      ActionEnergyMap contains the Energy Drop int value against any FightAction like KICK, PUNCH etc.
     */
    private Map<FightAction, Integer> actionEnergyMap = new HashMap<>();
    /***
     *      Experience tells how many match this character has played.
     */
    private Integer experience;

    public MarvelCharacter(Integer id, String name, Power power, Map<FightAction, Integer> actionEnergyMap, Integer experience) {
        this.name = name;
        this.power = power;
        this.actionEnergyMap = actionEnergyMap;
        this.experience = experience;
    }

    public MarvelCharacter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Map<FightAction, Integer> getActionEnergyMap() {
        return actionEnergyMap;
    }

    public void setActionEnergyMap(Map<FightAction, Integer> actionEnergyMap) {
        this.actionEnergyMap = actionEnergyMap;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
