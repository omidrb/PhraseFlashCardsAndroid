# Zope Flash Cards v10.1

Zope Flash Cards is an Android language-learning flashcard application for creating, importing, reviewing, and practicing vocabulary and phrases. It combines multilingual card management, Daily Practice, Leitner-style spaced repetition, text-to-speech, favorites, filtering, notifications, CSV exchange, and full backup/restore.

## Release information

- App name: **Zope Flash Cards**
- Version: **10.0**
- Version code: **47**
- Android package / application ID: **`com.zopelab.zopeflashcards`**
- Minimum SDK: **24**
- Compile SDK: **36**
- Target SDK: **36**
- Developer identity: **Zope Lab**
- Distribution build: signed Android App Bundle (`.aab`) for Google Play

## Main features

### Flashcard library
- Create, edit, view, and delete cards.
- Organize cards by learning language.
- Search the active language library.
- Mark cards as favorites.
- Add and use tags.
- Multi-select cards for management operations.
- Favorites are prioritized in the library.

### Daily Practice and spaced repetition
- Daily Practice presents a focused set of cards for review.
- Reveal the answer with **Show Answer** before rating a card.
- Mark reviewed cards as **Good** or **Hard**.
- Leitner-style box/progress data is maintained for spaced-repetition scheduling.
- Good/Hard progress is shown during practice.
- Filters can limit practice/library cards by favorites, tags, Good/Hard result, and Leitner box.

### Text-to-speech
- Listen to card phrases using Android text-to-speech.
- Speaker playback is available in Card View and Daily Practice.
- Daily Practice can automatically speak a new phrase.
- Voice settings include playback-speed choices and available installed voices for the active learning language.

## Native-language support

On first launch, the app asks the user to choose a native language. Supported choices are:

- Persian
- English
- Norwegian
- Arabic
- German
- French
- Spanish
- Italian
- Urdu
- Hebrew
- Turkish
- Polish
- Other

The former fixed **Persian Meaning** field is displayed dynamically according to this selection, for example **Persian Meaning**, **Norwegian Meaning**, or **Arabic Meaning**.

The selected native-language meaning is used consistently in Card View, Add/Edit Card, Daily Practice, CSV import/export, and backup/restore.

Text direction is automatically adapted for the native language. Persian, Arabic, Urdu, and Hebrew use right-to-left presentation; the other supported languages use left-to-right presentation.

For data compatibility, the internal legacy card field is retained while the user-facing label and exported meaning-column name are dynamic. Older CSV files using Persian/Farsi meaning headers remain import-compatible.

## CSV import and export

- Export cards for the active learning language to CSV.
- The native-meaning CSV column uses the selected native-language name.
- Import performs flexible header mapping for phrase, English meaning, native meaning, and example fields.
- Legacy CSV headers such as **Persian Meaning**, **Farsi**, and Persian-language headers remain supported.

## Backup and restore

A full JSON backup can preserve app preferences and learning data, including languages, cards, Leitner/progress state, favorites, tags, filters, voice/theme/notification settings, and native-language configuration.

New backups explicitly include the selected `nativeLanguage` and `nativeMeaningField`. Restore also supports the existing stored preferences used by earlier releases.

## Notifications

- Optional reminders for Hard cards.
- Configurable notification time and number of notifications per day.
- Multiple notifications are spaced one hour apart.
- Notification slots are scheduled independently and re-armed for the following day.
- Tapping a card notification opens the relevant card in Zope Flash Cards.
- Boot handling restores scheduled reminders after device restart.

## Appearance

- Light, Dark, and System theme modes.
- Responsive controls with auto-sizing where needed for different Android screen sizes.
- Card/detail and Daily Practice views share consistent controls and speaker styling.

## Privacy policy

The application includes **Menu → Privacy Policy**, which opens the public Zope Flash Cards privacy policy:

https://omidrb.github.io/PhraseFlashCardsAndroid/privacy-policy.html

The privacy-policy HTML file is also included in this repository for GitHub Pages publishing.

## Google Play build

The GitHub Actions workflow is located at:

`.github/workflows/build-apk.yml`

Despite the historical filename, the workflow builds the **Google Play Android App Bundle**, not an APK.

Workflow configuration:

- Java: 17 (Temurin)
- Gradle: 8.9
- Build command: `gradle clean bundleRelease --stacktrace`
- Output: `app/build/outputs/bundle/release/app-release.aab`
- GitHub artifact: `ZopeFlashCards-v10.1-GooglePlay-AAB`

The workflow also verifies that the source package and Gradle application ID are consistently `com.zopelab.zopeflashcards` and removes stale legacy `com.phrasecards` Java sources before building.

## Signing

The project is configured with the existing Zope release signing configuration in `app/build.gradle`. Preserve the signing key and credentials securely for future updates. Google Play updates must use the expected signing/upload identity and a version code greater than the previously uploaded release.

## Repository

GitHub repository:

https://github.com/omidrb/PhraseFlashCardsAndroid

Copyright / project identity: **Zope Lab**.


## Import from GitHub (v10.1)

Open Menu → Import CSV → Import from Server (GitHub). Collections are read from `server-csv/catalog.json` in the public GitHub `main` branch. Select a collection to download and import. Matching phrases already stored for the same language are skipped (case-insensitive). Local device CSV import remains available. See `server-csv/README.md` to publish collections. An empty catalog is included by default; upload CSVs and add catalog entries to populate the list.


### Google Play release code

This v10.1 source uses **versionCode 49** (48 was present in the previous source archive, but Google Play rejected the uploaded bundle). The workflow verifies the version code and package in the **built AAB** before uploading the artifact. If Google Play has already accepted versionCode 49, increase it again before uploading.

## Experimental v10.2 source changes
This is a partial, uncompiled update, versionCode 50. Server import dialog uses a scrollable rounded collection list with language-first headings; import source chooser is styled and no longer says GitHub; native language selection is alphabetized; CSV header mismatches in server imports suppress native translations. Full UI localization, local CSV mismatch validation, and swipe navigation are not yet implemented. Do not publish without completing and testing those requirements.
