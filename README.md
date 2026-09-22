# Zope Flash Cards v8.1

Changes:
- App renamed to Zope Flash Cards.
- Clean starter database: one English test card only. A new preferences namespace prevents old local cards from being carried into this version.
- Tapping a tile opens card details with Edit, Move Box, and Delete buttons directly below the details.
- Multi-selection no longer rebuilds the full grid after every tap, making selection much faster.
- Material accent colors make dialog actions distinguishable from dialog backgrounds.
- Daily Practice controls are lifted upward for easier reach.
- CSV import still supports 2-column Phrase,Meaning and 4-column Phrase,English Meaning,Persian Meaning,Example.

Visible title: Zope Flash Cards v8.1
GitHub artifact: ZopeFlashCards-v8.1-APK


## v8.2
- Added Select All / Unselect All to multi-selection mode.
- Select All applies to cards currently visible in the active language. If a search filter is active, it selects only matching cards.
- Selected cards can then be deleted together or moved to another Leitner box.


## v8.3
- Installed the approved Zope mascot/book/lightbulb launcher icon in all Android icon densities.
- Card Details now shows a much larger bold phrase.
- Detail section titles are bold with clear spacing between the phrase and meanings.
- Add/Edit Card fields have labels, larger touch areas, and vertical spacing.
- Main Library and Daily Practice use 10dp left/right page padding.
- Daily Practice has extra bottom breathing room so Show Answer / rating controls sit higher.

## v8.4
- Removed the "Card Details" heading from the card detail dialog.
- Card action buttons now use rounded blue/green/red screenshot-style buttons.
- Added the Zope app icon immediately before the app title on the main screen.
- Increased the card-detail phrase to 34sp bold and added more separation below it.

## v8.4.1
- Fixed Java compilation error in the custom rounded action-button helper.
- Replaced invalid Button.setTextAllCaps(false) with Button.setAllCaps(false).
