package com.github.brpaz.jetbrains.plugin.vscodesnippets.service;

import static org.junit.Assert.*;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeLanguage;
import org.junit.Test;

public class VSCodeLanguageTest {

  @Test
  public void fromLabel_success() {
    VSCodeLanguage result = VSCodeLanguage.fromLabel("php");
    assertEquals(result, VSCodeLanguage.PHP);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fromLabel_notExistingLanguage_throwsException() {
    VSCodeLanguage.fromLabel("bananas");
  }
}
