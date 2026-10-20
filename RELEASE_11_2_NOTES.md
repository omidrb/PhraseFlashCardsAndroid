# Zope Flash Cards v11.2 — versionCode 61

- Server catalog `Language` controls card speech language independently of library name.
- Server catalog `Library` determines import destination.
- Server catalog `Translation` controls meaning title on Card View and Daily Practice and directs CSV translation-column detection.
- Import confirmation displays all three catalog values and supports choosing an existing library or creating a new library. Existing libraries retain append/new/cancel flow.
- Default library renamed to `Default`; legacy English library and custom cards migrate once. Seed cards are normalized each launch.
- Daily Practice hides the empty English-meaning section when a server CSV provides only the catalog translation.

**Build status:** Source package only. Android build and device testing have not been run in this environment.
