package com.marvel.roleplay.services.impls;

import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_END_MSG;
import static com.marvel.roleplay.constants.RolePlayApiConstants.EXPLORE_MARVEL_STUDIOS;
import static com.marvel.roleplay.constants.RolePlayApiConstants.SUPER_HEROES;
import static com.marvel.roleplay.constants.RolePlayApiConstants.SUPER_VILLAINS;
import static com.marvel.roleplay.utils.PrintUtils.printCharacters;
import static com.marvel.roleplay.utils.PrintUtils.printWithBoundary;

import com.marvel.roleplay.helpers.RolePlayAppHelper;
import com.marvel.roleplay.services.ExploreService;

public class ExploreServiceImpl implements ExploreService {

  @Override
  public void exploreMarvel() throws Exception {
    printWithBoundary(EXPLORE_MARVEL_STUDIOS);
    Thread.sleep(1500);
    printWithBoundary(SUPER_HEROES);
    printCharacters(RolePlayAppHelper.getMarvelCharactersList());
    Thread.sleep(1500);
    printWithBoundary(SUPER_VILLAINS);
    printCharacters(RolePlayAppHelper.getEvilMarvelCharactersList());
    Thread.sleep(1500);
    printWithBoundary(EXPLORE_END_MSG);
    Thread.sleep(2000);
  }
}
