# Zope Flash Cards v10.7 (versionCode 56) — development candidate

Built on v10.6 code55 source. Source changes include Settings submenu with Backup & Restore, Reset Leitner, Mode, Voice setting, and Practice settings; configurable Daily Practice card limit (5–100, default 10); swipe navigation in Card View using the active filter; active Library title suffix; tap logo for About; existing hardware Back behavior routes to leavePractice confirmation; filter icon active/inactive gray colors; star favorite icon drawable remains borderless after toggling; localized native meaning heading; Good button green; menu button blue; and reduced selection-button padding. Settings-related labels are translated for the 11 non-English interface languages.

Validation: ZIP integrity and layout XML parsing checked; Gradle build and device interaction tests NOT performed. Other dynamic translations and card-view swipe with nested child controls need device verification.

NOTE: Release signing key and passwords are embedded in the historical project source. Before making repository public, migrate signing secrets to GitHub Actions secrets and rotate exposed credentials as appropriate.
