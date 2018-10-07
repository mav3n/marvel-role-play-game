package com.marvel.roleplay.services.impls;

import static com.marvel.roleplay.constants.RolePlayApiConstants.CREATE_CHARACTER_SUCCESS_MSG;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.marvel.roleplay.services.CharacterService;

public class CharacterServiceImplTest {

  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Test
  public void testCreateCharacter() {
    systemInMock.provideLines("Aqua-Man", "SPEED", "5", "10", "15", "20", "9");
    CharacterService characterService = new CharacterServiceImpl();
    characterService.createCharacter();
    String actualOutput = systemOutRule.getLog();
    assertTrue(actualOutput.contains(CREATE_CHARACTER_SUCCESS_MSG));
  }

}
