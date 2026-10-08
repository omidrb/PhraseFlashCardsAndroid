# GitHub CSV collections

Upload CSV files here and register each one in `catalog.json`.

Example catalog entry:

```json
{"file":"english-phrases.csv","title":"English phrases","language":"English","description":"Everyday conversation"}
```

CSV header example: `Phrase,English Meaning,Persian Meaning,Example,Usage`.

Publish to the `main` branch of `omidrb/PhraseFlashCardsAndroid`. The app loads the catalog on demand. Existing cards are skipped by case-insensitive phrase match within the same language. Keep CSV files under 4 MB.
