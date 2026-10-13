# v11.1 workflow correction

- Corrected release AAB versionCode validation from 59 to 60.
- Added explicit APK existence verification after `gradle assembleDebug` in the same job.
- Added diagnostic listing of APK outputs if expected file is missing.

Note: The reported `Run test -s app/build/outputs/apk/debug/app-debug.apk` command does not appear in this source package workflow. It may be from another workflow or a previously committed workflow version. Check the Actions run commit and workflow file.

No Android build was run in this environment.
