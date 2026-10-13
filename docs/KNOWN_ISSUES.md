# Known issues and validation backlog

This list consolidates reported problems and requests. Items described as fixed in prior ZIP releases are NOT considered verified until the current repository builds and the behavior is tested on-device.

## First priority: establish baseline
- Run debug build and AAB build using the current repository and GitHub Actions; record actual version, branch and SHA.
- Compare current code with the most recent v10.9/code58 source and release notes. A past ZIP may differ from the repository.
- Test Android Back in Daily Practice; must show leave confirmation, not exit the app.
- Test language switch updates main title, counts, Good/Hard captions, Add/Edit labels and open/next dialogs without restarting.
- Verify all dialogs/toasts/messages translated in each supported language and RTL behavior.
- Confirm Settings submenu uses same sliding drawer, starts with `← Menu`, and has consistent typography.
- Confirm library switch title says `Switch Library` rather than `Switch language`.
- Confirm Good background exactly `#93DB96` at runtime (check for tint/theme overrides).
- Confirm filter icon colors: dark gray when any filter active, light gray otherwise.
- Confirm Card View Edit button translation in every UI language.
- Check responsive button captions and selection toolbar on small screens.
- Test Samsung S23 notification delivery and permission/channel behavior.

## Engineering and security
- Public repository build configuration contains embedded signing credentials. Treat as exposed. Move signing credentials to GitHub Actions secrets, assess key rotation / Google Play upload key reset with owner, and do not expose keystore or passwords in new PRs.
- Existing Android Gradle Plugin may warn about compileSdk 36 compatibility; distinguish warnings from build failures.
- Build failures previously included incorrect Button method `setTextAllCaps`; use `setAllCaps` on Android Button.
- Run backup/restore and CSV compatibility tests after changes.

## Release acceptance
No release-ready claim without successful build, review of warnings, device or emulator smoke tests, and explicit owner approval.
