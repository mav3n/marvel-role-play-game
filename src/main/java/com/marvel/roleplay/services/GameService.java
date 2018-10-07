package com.marvel.roleplay.services;

import java.util.List;

import com.marvel.roleplay.domains.Player;

public interface GameService {

  void startNewGame() throws Exception;

  void resumeGame() throws Exception;

  List<Player> getPlayers();

}
