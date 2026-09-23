# Zope Flash Cards v8.7

Android flash-card app for personal language learning.

## v8.7 changes
- Voice setting menu: 0.5x / 0.75x / 1x and up to four installed TTS voices for the active language.
- Speaker-icon playback in card details and Daily Practice; Daily Practice still auto-plays each new phrase.
- Usage removed from card display, editor, CSV import/export, and Daily Practice.
- Leitner box label removed from card tiles/details (Leitner scheduling remains active internally and management remains in Menu).
- Smarter CSV import maps Phrase, English Meaning, Persian Meaning, and Example from headers/content and ignores Usage columns.
- High-contrast black Close buttons.
- Short haptic feedback for button/icon-button taps.
- Wider left/right safe margins.
- Main logo doubled and a large startup logo overlay added.
- Version 8.7 / versionCode 19.

## APK update signing
This source includes a fixed signing keystore (`app/zope-update.jks`) so builds made from v8.7 onward can update each other without deleting app data, provided the same keystore remains unchanged.

IMPORTANT: an APK can update an already-installed Android app only if both APKs use the same package ID and signing certificate. The old v8.6 GitHub debug APK was signed by the temporary GitHub runner debug key. That private key is not available in this source, so the first move from that particular v8.6 APK to this new fixed-key build may still require uninstalling v8.6. Before doing that, export your cards. After v8.7 is installed, keep this keystore for all future releases so subsequent updates preserve app data.

## Build
Run the included GitHub Actions workflow (`Build Android APK`). The artifact will be named `ZopeFlashCards-v8.7-APK`.
