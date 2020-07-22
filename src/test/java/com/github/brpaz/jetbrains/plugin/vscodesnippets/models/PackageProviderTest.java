package com.github.brpaz.jetbrains.plugin.vscodesnippets.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PackageProviderTest {

  @Test
  public void fromLabel_returnsProviderEnumIfExists() {
    assertEquals(PackageProvider.NPM, PackageProvider.fromLabel("npm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void fromLabel_throwsExceptionForNotExistingEnum() {
    PackageProvider.fromLabel("xpto");
  }
}
