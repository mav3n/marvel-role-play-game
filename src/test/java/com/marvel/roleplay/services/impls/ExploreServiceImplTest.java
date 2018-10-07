package com.marvel.roleplay.services.impls;

import com.marvel.roleplay.services.ExploreService;
import com.marvel.roleplay.utils.ConversionUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ExploreServiceImplTest {

  @Rule
  public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

  @Rule
  public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

  private ExploreService exploreService = new ExploreServiceImpl();

  private static final String EXPLORE_MARVEL_RESULT_FILE_PATH = "outputs/explore_output.txt";

  @Test
  public void testExploreMarvel() throws Exception {
    exploreService.exploreMarvel();
    assertEquals(ConversionUtil.convertFileContentToString(EXPLORE_MARVEL_RESULT_FILE_PATH), systemOutRule.getLog());
  }

}
