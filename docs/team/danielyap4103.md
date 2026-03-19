---
layout: page
title: Daniel Yap's Project Portfolio Page
---

### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:
1. Student ID field
* Added a new StudentId model class with validation (format: A + 7 digits + letter, e.g. A0123456X).
* Integrated Student ID into the add and edit commands.
* Updated Person model, storage, and UI to support Student ID.
* Fixed PersonCard display bug when Student ID was missing.
2. Telegram handle field
* Replaced the Address field with a new TeleHandle model class.
* Added validation for Telegram handles (@ + 5–32 characters: letters, numbers, underscores).
* Integrated into add and edit commands and PersonCard UI.
3. TeleHandle case-insensitivity
* Made TeleHandle case-insensitive throughout the app.
* Normalized Telegram handles to lowercase for storage and comparison.
* Aligned with Telegram’s behavior where usernames are case-insensitive.
4. Developer Guide
* Added Non-Functional Requirements section.
* Added Glossary section.
5. Project documentation
* Added team photo and updated personal information.
