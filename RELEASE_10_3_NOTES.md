# Zope Flash Cards v10.3 (versionCode 52) — development candidate

Based on v10.2 code 51 workflow-repair source.

Changes: translated primary UI labels for eleven native languages (English fallback for Other), RTL alignment; CSV translation-header/script mismatch warnings and per-card metadata-based hiding without deleting translations; Daily Practice horizontal swipe (forward requires prior feedback, backwards allowed); search focus and keyboard dismissal; styled import chooser; server name without GitHub; scrollable rounded server items; language-first labels; A–Z native-language choices.

**Limitations:** Full translation of every dialog, message, dynamic label and notification has not been completed. CSV language identification is heuristic and cannot reliably distinguish Latin-script languages, Persian vs Arabic vs Urdu, or unknown translations. Native-language selection styling needs on-device visual verification. This is not a verified production release.

Build with GitHub Actions: AAB + debug APK. Run on Samsung devices and check import, backups, notification, swipes, search, RTL, all native-language labels. Never submit to Play until Android compilation and on-device tests succeed.
