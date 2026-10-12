# Zope Flash Cards — project specification and historical decisions

Status: consolidated handoff from prior ChatGPT project discussions; confirm against repository source before changing code.

## Product
Android flashcard app for personal language study, with multiple learning-language libraries, native-language meanings, English explanations/examples, Leitner spaced repetition, Daily Practice, TTS, search, favorites, tags, CSV import/export and JSON backup/restore.

Package: `com.zopelab.zopeflashcards`. Developer: Zope Lab. Main distribution target: Google Play AAB.

## Language and localization
First-launch native language choices: Persian, English, Norwegian, Arabic, German, French, Spanish, Italian, Urdu, Hebrew, Turkish, Polish, Other. Native-language choice controls user-facing meaning labels, dialog text and RTL/LTR presentation (Persian, Arabic, Urdu, Hebrew are RTL). Preserve legacy Persian CSV import support. English Meaning is a separate field and remains labeled in English where specified. The UI should immediately refresh after native-language changes, including library title, counts, Good/Hard captions and add/edit field labels.

## Card data and workflows
Phrase, English meaning/explanation, native meaning, examples, tags, favorites, learning language and stable card identity. Hide empty card fields in detail. Support Add/Edit, card navigation, multi-selection, deletion, search, and filtering by favorites, tags, review result and Leitner box. Swipe navigation should respect active filters and search.

## Daily Practice
Configurable card count (requested range 5–100), show answer before feedback, Good/Hard grading, progress counters, audio, Hard-card revisit logic and leave confirmation on both toolbar back and system Back. User-requested Good button background is exactly `#93DB96`; Hard button should match shape/typography with a distinct red background.

## Interface
Responsive on devices including Samsung Galaxy S22 Ultra and S23; no clipped button labels. Main menu is a sliding drawer; Settings is a nested view within the same sliding drawer, with first item `← Menu`. Settings contains backup/restore, Leitner reset, Mode, Voice Settings and Practice Settings. Main menu Settings typography must match other entries. Main menu button matches menu item background `#7D9BC7`. Filter icon dark gray when a filter is active and light gray otherwise. The active library is shown as `<language> Cards Library` using localized caption. Tapping the app logo opens About.

## Audio and notifications
TTS speaker icon on card view and Daily Practice, optional auto-speech, speed settings and voice selection. Notification scheduling supports user-selected time/count with at least one hour between multiple daily notifications. Notification behavior needs verification on newer Android versions, particularly Galaxy S23.

## Content and import
CSV learning content is managed separately from source development. Smart CSV header mapping, active-library export, optional import from GitHub-hosted CSV files, and user file import. Photo-based cards were discussed as a potential extension: dedicated image per phrase and CSV image path; do not assume implemented.

## Important compatibility rules
Preserve existing user cards, favorites, tags, Leitner progress, preferences, old Persian-header CSV imports, and backups. Do not introduce breaking storage migrations without a tested path.
