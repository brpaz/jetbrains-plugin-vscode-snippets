package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
  public void toJetbrains_withUnknownLanguage_returnsNull() {
    JetbrainsLanguage result = LanguagesMapper.toJetbrains(VSCodeLanguage.ABAP);
    assertNull(result);
  }
}
