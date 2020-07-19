package com.github.brpaz.jetbrains.plugin.vscodesnippets.utils;

import org.junit.Assert;
import org.junit.Test;

public class PlaceholderProcessorTest {

  @Test
  public void process_numericPlaceholder() {
    String body = "afterAll(() => {\\n\\t${0}\\n});";

    Assert.assertEquals("afterAll(() => {\\n\\t$VAR0$\\n});", PlaceholderProcessor.process(body));
  }

  @Test
  public void process_labeledPlaceholder() {
    String body = "${3:Application}Module";

    Assert.assertEquals("ApplicationModule", PlaceholderProcessor.process(body));
  }
}
