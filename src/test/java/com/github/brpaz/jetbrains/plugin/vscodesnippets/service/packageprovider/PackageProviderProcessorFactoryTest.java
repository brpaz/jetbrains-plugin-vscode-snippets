package com.github.brpaz.jetbrains.plugin.vscodesnippets.service.packageprovider;

import static org.junit.Assert.*;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.PackageProvider;
import org.junit.Test;

public class PackageProviderProcessorFactoryTest {

  @Test
  public void createFromProvider_validProvider() {
    PackageProviderProcessorFactory factory = new PackageProviderProcessorFactory();

    PackageProviderProcessor processor = factory.createFromProvider(PackageProvider.NPM);
    assertEquals(PackageProvider.NPM, processor.getProvider());
  }
}
