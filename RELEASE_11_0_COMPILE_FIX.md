# v11.0 / versionCode 59 compilation fix

Fixed missing `filteredOnly` CheckBox declaration in `practiceSettings()` that caused Java compilation failure at MainActivity.java:353. The checkbox now appears in Practice Settings and loads the persisted preference. No version increment because v11.0 has not built successfully. Full Android compilation remains unverified.
