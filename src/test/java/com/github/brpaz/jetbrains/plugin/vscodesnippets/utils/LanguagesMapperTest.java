package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils;

import static org.junit.Assert.*;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains.JetbrainsLanguage;
import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import org.junit.Test;

public class LanguagesMapperTest {

  @Test
  public void toJetbrains_withKnownLanguage() {
    JetbrainsLanguage result = LanguagesMapper.toJetbrains(VSCodeLanguage.PHP);
    assertEquals(JetbrainsLanguage.PHP, result);
  }

  @Test
  public void toJetbrains_withUnknownLanguage_returnsNone() {
    JetbrainsLanguage result = LanguagesMapper.toJetbrains(VSCodeLanguage.ABAP);
    assertEquals(JetbrainsLanguage.NONE, result);
  }
}
