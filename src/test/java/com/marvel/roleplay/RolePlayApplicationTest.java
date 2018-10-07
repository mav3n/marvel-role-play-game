package com.marvel.roleplay;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class RolePlayApplicationTest {

  @Rule
  public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Test
  public void testValidStartGameMenuSelectionAndSavingGame() throws Exception {
    systemInMock.provideLines("1", "6", "S", "A", "5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }

  @Test
  public void testValidResumeGameMenuSelection() throws Exception {
    systemInMock.provideLines("2", "P", "X", "5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }

  @Test
  public void testValidCreateACharacterMenuSelection() throws Exception {
    systemInMock.provideLines("3", "Super-Man", "STRENGTH", "5", "10", "15", "20", "15", "4", "5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }

  @Test
  public void testValidExploreMenuSelection() throws Exception {
    systemInMock.provideLines("4", "5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }

  @Test
  public void testValidExitMenuSelection() throws Exception {
    systemInMock.provideLines("5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }

  @Test
  public void testInValidMenuSelection() throws Exception {
    systemInMock.provideLines("6", "5");
    exit.expectSystemExit();
    RolePlayApplication.main(null);
  }
}
