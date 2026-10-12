# Codex instructions — Zope Flash Cards

This is an Android Java flashcard app. Treat GitHub as the source of truth; ChatGPT conversations are historical reference and are not automatically available in Codex.

## Before editing
1. Read README.md, docs/PROJECT_SPEC.md, docs/KNOWN_ISSUES.md, docs/MIGRATION_GUIDE.md, and current source.
2. Inspect current app/build.gradle and GitHub Actions. Never assume that a previously shared ZIP matches the branch.
3. Make focused changes; preserve unrelated behavior, user data, backup compatibility and CSV import compatibility.
4. Check existing versionCode/versionName. Increment only for an actual new distributable release, not documentation-only changes.
5. Do not edit signing credentials or keys without explicit approval; never expose secrets in logs, documentation, or commits.

## Build and review
- Prefer running `./gradlew :app:assembleDebug` or the repository's actual supported build command; use GitHub Actions if the local Android SDK is unavailable.
- Never claim that a build passed unless a build was executed and its result inspected.
- For UI changes, test on different screen sizes and with long translations.
- For localization, verify every user-visible label, menu, modal, toast, confirmation, input hint and dynamic caption in every supported UI language; check RTL.
- For Daily Practice, test both the on-screen back control and Android system Back gesture/button, including confirmation.
- For filter changes, verify the active filter constrains card navigation and Daily Practice.
- For Good/Hard styling, check runtime styles and layout XML; the Good button requirement is exactly #93DB96.
- Before PR: describe files changed, tests run, tests not run, remaining risks, and screenshots where practical.

## Project conventions
- App ID: com.zopelab.zopeflashcards
- Product name: Zope Flash Cards
- Developer identity: Zope Lab
- Main implementation historically uses MainActivity.java and activity_main.xml; inspect current architecture rather than assuming.
- System buttons: OK, Close, Apply, Clear, Save, Cancel, Add, Reset.
- User buttons: Add Card, Daily Practice, Show Answer.
- Maintain backward-compatible import/export and JSON backup/restore.
- Do not add seeded cards or change users' card data without an explicit request.
