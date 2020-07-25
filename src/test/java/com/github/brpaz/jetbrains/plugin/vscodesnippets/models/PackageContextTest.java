package com.github.brpaz.jetbrains.plugin.vscodesnippets.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PackageContextTest {

  @Test
  public void constructor_setCorrectProperties() {
    PackageContext context = new PackageContext(PackageProvider.NPM, "jest");
    assertEquals(PackageProvider.NPM, context.getProvider());
    assertEquals("jest", context.getName());
  }

  @Test
  public void testEquals() {
    PackageContext context1 = new PackageContext(PackageProvider.NPM, "jest");
    PackageContext context2 = new PackageContext(PackageProvider.NPM, "jest");

    assertTrue(context1.equals(context2));
  }
}
