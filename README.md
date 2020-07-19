# VS Code Snippets Jetbrains Plugin

> Use your VS Code Snippets directly in Jetbrains IDEs. Compatible with [Contextual Snippets Extenstion](https://github.com/brpaz/vscode-contextual-snips)

[![JetBrains IntelliJ Plugins](https://img.shields.io/jetbrains/plugin/v/com.github.brpaz.jetbrains.plugin.vscodesnippets?style=for-the-badge)](https://plugins.jetbrains.com/plugin/)
[![CI Status](https://img.shields.io/github/workflow/status/brpaz/jetbrains-plugin-vscode-snippets/CI?color=orange&label=actions&logo=github&logoColor=orange&style=for-the-badge)](https://github.com/brpaz/jetbrains-plugin-vscode-snippets)
[![Codecov](https://img.shields.io/codecov/c/github/brpaz/jetbrains-plugin-vscode-snippets?style=for-the-badge)](https://codecov.io/gh/brpaz/jetbrains-plugin-vscode-snippets)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

## Install

* On Your Jetbrains IDE, go to "Settings" -> "Plugins" and search for "VS Code Snippets". Then press "install" and restart the IDE.

## Usage

![demo](demo.gif)

⚠️ Snippets not included in the plugin.

First you need to have some snippets in VS Code format.  The structure is the same as any other VS Code snippet with some changes:

* Each snippet must have the "scope" attribute, so the plugin can identify the file type. 

    The scope field is a comma-separated list of the languages that snippet is associated with in VS Code. 
    You can find a basic list of the "language identifiers" [here](https://code.visualstudio.com/docs/languages/identifiers)

* The snippet can contain an additional "context" field, that you can use, to restrict even more when the snippet is displayed. (like files matching a specific pattern).

You can find examples of all of this in [this](https://github.com/brpaz/vscode-snippets) repository.

Then, on your Jetbrains IDE, go to "Settings" -> "Editor" -> "VS Code Snippets" and add the path to the directory containing your snippet files.

![settings](images/settings.png) 

You can configure multiple paths. After that your snippets should start appearing on the IDE autocomplete.
 
 
## TODO

* Improve handling of variables in templates
* Optionally read the "scope" from the filename so the default VS Code snippets can be used.
* Add more tests


## Show your support

If this project have been useful for you, I would be grateful to have your support.

Give a ⭐️ to the project, and if you feel more generous you can [Sponsor](https://github.com/sponsors/brpaz) me on GitHub.

Alternatively, if you prefer a one time donation, you can:

<a href="https://www.buymeacoffee.com/Z1Bu6asGV" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>


## Author

👤 **Bruno Paz**

* Github: [@brpaz](https://github.com/brpaz)


## 📝 License

Copyright © 2020 [Bruno Paz](https://github.com/brpaz).

This project is [MIT](https://opensource.org/licenses/MIT) licensed.
