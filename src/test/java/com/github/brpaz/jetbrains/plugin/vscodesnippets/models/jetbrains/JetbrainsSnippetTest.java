package com.github.brpaz.jetbrains.plugin.vscodesnippets.models.jetbrains;

import static org.junit.Assert.assertEquals;

import com.github.brpaz.jetbrains.plugin.vscodesnippets.models.vscode.VSCodeSnippet;
import com.google.gson.Gson;
import org.junit.Test;

public class JetbrainsSnippetTest {

  private VSCodeSnippet createFromJson(String json) {
    return new Gson().fromJson(json, VSCodeSnippet.class);
  }

  @Test
  public void fromVSCodeSnippet() {
    VSCodeSnippet vsCodeSnippet =
        createFromJson(
            "{\n"
                + "\t\"label\": \"jest-afterall\",\n"
                + "\t\"body\": [\n"
                + "\t\t\"afterAll(() => {\\n\\t$0\\n});\"\n"
                + "\t],\n"
                + "\t\"description\": \"afterAll function is called once after all specs\",\n"
                + "\t\"prefix\": [\n"
                + "\t\t\"jest afterall\",\n"
                + "\t\t\"aa\"\n"
                + "\t],\n"
                + "\t\"scope\": \"javascript,javascriptreact,typescript,typescriptreact\",\n"
                + "\t\"context\": {\n"
                + "\t\t\"patterns\": [\n"
                + "\t\t\t\"**/*/*.spec.{ts,js}\"\n"
                + "\t\t],\n"
                + "\t\t\"package\": {\n"
                + "\t\t\t\"provider\": \"npm\",\n"
                + "\t\t\t\"name\": \"jest\"\n"
                + "\t\t}\n"
                + "\t}\n"
                + "}");

    JetbrainsSnippet mappedSnippet = JetbrainsSnippet.fromVSCodeSnippet(vsCodeSnippet);
    assertEquals("jest-afterall", mappedSnippet.getLabel());
    assertEquals(
        "afterAll function is called once after all specs", mappedSnippet.getDescription());
    assertEquals("jest", mappedSnippet.getContext().getPackageProvider().getName());
    // assertEquals(PackageProvider.NPM,
    // mappedSnippet.getContext().getPackageProvider().getProvider());
    assertEquals(4, mappedSnippet.getContext().getLanguages().length);
    assertEquals("**/*/*.spec.{ts,js}", mappedSnippet.getContext().getFilePatterns().get(0));
    assertEquals("afterAll(() => {\n" + "\t$0\n" + "});", mappedSnippet.getBody());
  }
}
