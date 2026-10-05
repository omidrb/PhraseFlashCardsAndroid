# Zope Flash Cards v8.8

Android flash-card app for personal language learning.

## v8.8 changes
- Voice setting menu: 0.5x / 0.75x / 1x and up to four installed TTS voices for the active language.
- Speaker-icon playback in card details and Daily Practice; Daily Practice still auto-plays each new phrase.
- Usage removed from card display, editor, CSV import/export, and Daily Practice.
- Leitner box label removed from card tiles/details (Leitner scheduling remains active internally and management remains in Menu).
- Smarter CSV import maps Phrase, English Meaning, Persian Meaning, and Example from headers/content and ignores Usage columns.
- High-contrast black Close buttons.
- Short haptic feedback for button/icon-button taps.
- Wider left/right safe margins.
- Main logo doubled and a large startup logo overlay added.
- Version 8.8 / versionCode 19.

## APK update signing
This source includes a fixed signing keystore (`app/zope-update.jks`) so builds made from v8.8 onward can update each other without deleting app data, provided the same keystore remains unchanged.

IMPORTANT: an APK can update an already-installed Android app only if both APKs use the same package ID and signing certificate. The old v8.6 GitHub debug APK was signed by the temporary GitHub runner debug key. That private key is not available in this source, so the first move from that particular v8.6 APK to this new fixed-key build may still require uninstalling v8.6. Before doing that, export your cards. After v8.8 is installed, keep this keystore for all future releases so subsequent updates preserve app data.

## Build
Run the included GitHub Actions workflow (`Build Android APK`). The artifact will be named `ZopeFlashCards-v8.8-APK`.


## v8.8 UI changes
- Sliding left navigation menu
- 30dp side margins across main and Daily Practice views
- High-contrast black dialog action buttons
- Colored Good/Hard labels below progress bar

## v9.9 native-language update
- First launch asks for the user's native language and stores the selection.
- The former Persian meaning field is presented as the selected native-language meaning in Card View, Add/Edit Card, and Daily Practice.
- RTL is applied for Persian/Farsi, Arabic, Urdu and Hebrew; other native languages use LTR.
- CSV export names the native meaning column from the selection; import recognizes the selected header and legacy Persian/Farsi headers.
- New JSON backups expose the selected native-language field name and restore both new and legacy backup formats.
- Daily Practice speaker button uses the same style helper as Card View.
- Version: 9.9 (45), package: com.zopelab.zopeflashcards.
