# ChatGPT → Codex migration guide

## What is migrated
The existing GitHub repository remains the canonical Android source. This PR adds Codex-readable documentation and instructions. No Android code, user data or historical ChatGPT conversations are moved or deleted by this PR.

## Historical conversations
Previous Zope Flash Cards chats stay in ChatGPT; this migration does not delete, archive or modify them. Codex does not automatically inherit those conversations. For fuller historical coverage, export relevant chats through ChatGPT data export or copy important decisions into versioned docs. Keep exports PRIVATE: they may contain personal details, logs or credentials. Do not commit full chat exports to this public repository.

## Getting started in Codex
1. Open Codex in ChatGPT or the Codex app.
2. Connect/select GitHub repository `omidrb/PhraseFlashCardsAndroid`.
3. After this documentation PR is reviewed and merged, open a task on the `main` branch.
4. Ask: 'Read AGENTS.md and docs/*.md. Inspect the current source, run the debug build, and report baseline failures. Do not change files yet.'
5. Next ask for one focused fix on a new branch and a PR with build evidence.
6. Review the PR, run on Samsung devices, then merge when accepted.

## Suggested initial Codex task
'Read AGENTS.md, docs/PROJECT_SPEC.md and docs/KNOWN_ISSUES.md. Establish a reproducible baseline for the current app: inspect version and build workflow, run assembleDebug, check whether signing secrets are exposed, and report which UI/translation issues remain. Do not change app code until I approve a plan.'

## Scope and safety
This migration is documentation-only. Keep original ChatGPT conversations intact. No automatic Codex workspace creation or conversation-history import is performed. Never upload private chat archives, keystores or passwords into a public GitHub repo.
