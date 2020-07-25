package com.github.brpaz.jetbrains.plugin.vscodesnippets.completion;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Test;

public class VSCodeCompletionProviderTest extends BasePlatformTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Test
  public void testCompletion() {
    myFixture.configureByText("hello.yml", "test");
    myFixture.completeBasic();
    /*List<String> strings = myFixture.getLookupElementStrings();
    assertTrue(strings.containsAll(Arrays.asList("key with spaces", "language", "message", "tab", "website")));
    assertEquals(5, strings.size());*/
  }
}
