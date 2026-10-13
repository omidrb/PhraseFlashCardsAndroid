# v11.1 (versionCode 60) compilation correction

Restored six text-to-speech related methods inadvertently omitted in the previous v11.1 source:
- initTts
- localeForLanguage
- startSpeakerAnimation
- stopSpeakerAnimation
- speakPhrase (both overloads)
- applyVoiceSettings

These missing declarations caused the six `cannot find symbol` errors in the uploaded GitHub Actions log.
The Android Gradle Plugin compileSdk 36 warning is separate and non-fatal.

Build validation: Not compiled in this environment. Re-run GitHub Actions `:app:assembleDebug` and `:app:bundleRelease`.
