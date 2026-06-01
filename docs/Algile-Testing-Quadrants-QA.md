# Algile Testing Quadrants — QA Reference

**Purpose:** Guide QA planning, execution, and collaboration across the four Agile testing quadrants (Brian Marick; Lisa Crispin & Janet Gregory).

**Audience:** QA engineers, test leads, product owners, developers  
**Version:** 1.0 | **Date:** 2026-05-31

**Related doc:** [Algile Test Metrics — QA Reference](./Algile-Test-Metrics-QA.md)

---

## 1. Overview

The **Agile Testing Quadrants** classify tests by two axes:

| Axis | Meaning |
|------|---------|
| **Facing** | Who the test primarily speaks to — **business** (users, PO) or **technology** (developers, ops) |
| **Intent** | **Support the team** (build the right thing, fast feedback) or **critique the product** (find risks before/after release) |

Quadrants are **not a sequence**. All four are used continuously within each iteration or release train.

```
                    SUPPORT THE TEAM          CRITIQUE THE PRODUCT
                 ┌─────────────────────┬─────────────────────────┐
  BUSINESS-      │  Q2: Business       │  Q3: Business           │
  FACING         │  (examples, stories)│  (exploratory, UAT)     │
                 ├─────────────────────┼─────────────────────────┤
  TECHNOLOGY-    │  Q1: Technology     │  Q4: Technology         │
  FACING         │  (unit, API, CI)    │  (perf, security, ops)  │
                 └─────────────────────┴─────────────────────────┘
```

---

## 2. Quadrant Summary for QA

| Quadrant | Primary QA role | Typical timing | Main outcome |
|----------|-------------------|----------------|--------------|
| **Q1** | Collaborate on testability; review coverage in CI | Every commit / PR | Fast defect detection at code level |
| **Q2** | Define acceptance criteria; automate key paths | Story refinement → sprint | Shared understanding of “done” |
| **Q3** | Lead exploratory, usability, scenario testing | Mid-sprint → pre-release | User-value and workflow risks found |
| **Q4** | Plan non-functional tests; interpret results | Hardening, release candidates | Production-readiness (NFR) validated |

---

## 3. Quadrant Details

### Q1 — Technology-facing · Supports the team

**Goal:** Give developers immediate feedback that code behaves as intended.

| Item | Guidance |
|------|----------|
| **Examples** | Unit tests, component tests, contract tests, static analysis, lint |
| **Owner** | Developers (QA advises on boundaries, data, mocks) |
| **QA activities** | Pair on edge cases; ensure test IDs in automation; verify CI gates |
| **Entry criteria** | Story has clear technical scope; APIs/components identified |
| **Exit criteria** | CI green; agreed coverage on changed modules; no blocker defects |

**QA checklist — Q1**

- [ ] Acceptance criteria translated into testable units where needed
- [ ] Test data and environments documented for automation
- [ ] Failed builds triaged with dev within SLA (e.g. same day)
- [ ] Regression pack updated when public contracts change

---

### Q2 — Business-facing · Supports the team

**Goal:** Confirm the team is building the **right** behavior from a business perspective.

| Item | Guidance |
|------|----------|
| **Examples** | Acceptance tests, BDD scenarios (Given/When/Then), API functional tests, story-level UI checks |
| **Owner** | Whole team (QA often drives automation and data) |
| **QA activities** | Refine scenarios with PO; automate stable flows; maintain test data |
| **Entry criteria** | Story “ready” with testable AC; environments available |
| **Exit criteria** | All AC scenarios pass; known gaps logged and accepted |

**QA checklist — Q2**

- [ ] Every AC has at least one automated or repeatable manual check
- [ ] Positive, negative, and boundary cases covered per story
- [ ] Test data reset strategy defined
- [ ] Defects linked to story/AC; no silent “works on my machine”

---

### Q3 — Business-facing · Critiques the product

**Goal:** Explore what **specs miss** — usability, workflows, integration surprises, real-user behavior.

| Item | Guidance |
|------|----------|
| **Examples** | Exploratory sessions, usability review, end-to-end user journeys, UAT, beta feedback, accessibility spot-checks |
| **Owner** | QA (with PO, UX, stakeholders as needed) |
| **QA activities** | Charter-based exploration; session notes; risk-based depth |
| **Entry criteria** | Q2 baseline passing; build deployable to test/stage |
| **Exit criteria** | Session goals met or risks logged; no open Sev-1/2 without mitigation |

**QA checklist — Q3**

- [ ] Exploratory charter: mission, scope, time box, risks (see template below)
- [ ] End-to-end journeys exercised across roles/devices where applicable
- [ ] Usability/accessibility issues captured with severity and screenshots
- [ ] UAT sign-off criteria agreed with PO before release

**Exploratory session template**

| Field | Value |
|-------|--------|
| Charter title | |
| Build / version | |
| Tester | |
| Duration | |
| Mission | What we are trying to learn |
| Scope | In / out |
| Risks targeted | |
| Findings | Bug IDs, questions, observations |
| Debrief | Continue / stop / need Q4? |

---

### Q4 — Technology-facing · Critiques the product

**Goal:** Validate **non-functional** quality before production stress.

| Item | Guidance |
|------|----------|
| **Examples** | Performance, load, stress, soak, security (SAST/DAST/pen test), failover, backup/restore, compatibility, chaos/resilience |
| **Owner** | QA + DevOps/SRE + security (shared) |
| **QA activities** | Define NFR test plan; run tools; analyze bottlenecks and CVEs |
| **Entry criteria** | Stable RC build; environments mirror prod topology where possible |
| **Exit criteria** | NFR thresholds met or waivers approved by stakeholders |

**QA checklist — Q4**

- [ ] NFR targets documented (latency p95, throughput, error rate, RTO/RPO)
- [ ] Performance baseline compared to previous release
- [ ] Security scan results triaged (critical/high within policy)
- [ ] DR/failover drill executed or scheduled per release policy

---

## 4. How Quadrants Fit the Sprint

| Sprint phase | Primary quadrants | QA focus |
|--------------|-------------------|----------|
| Refinement | Q2 (draft), Q3 (risks) | Clarify AC; flag testability and NFR gaps |
| Development | Q1, Q2 | CI health; automate AC; peer review tests |
| Test / stabilize | Q2, Q3, Q4 | Regression; exploration; NFR on RC |
| Release | Q3 (UAT), Q4 (final gates) | Sign-off; release notes; known issues |

**Rule of thumb:** Q1 and Q2 run **all sprint**; Q3 intensifies as features stabilize; Q4 peaks on **release candidates**, not only at the end of a multi-month project.

---

## 5. Roles and Collaboration

| Role | Q1 | Q2 | Q3 | Q4 |
|------|----|----|----|-----|
| Developer | Lead | Contribute | Support fixes | Implement fixes, tuning |
| QA | Advise | Lead automation & AC tests | Lead | Lead planning & execution |
| PO | — | Approve AC | UAT decisions | Accept NFR waivers |
| DevOps / SRE | CI/CD | Environments | Stage parity | Perf, monitoring, DR |

**Anti-patterns to avoid**

- Treating quadrants as waterfall phases (Q1 → Q2 → Q3 → Q4 only once)
- QA owning only Q3 while ignoring Q2 automation
- Running Q4 performance tests only in production
- No Q3 exploration because “automation passed”

---

## 6. Defect and Risk Mapping

When logging defects, tag the quadrant to improve retrospectives:

| Tag | Example defect types |
|-----|----------------------|
| `Q1` | Null pointer, wrong algorithm, broken unit assumption |
| `Q2` | AC mismatch, wrong calculation per spec, API contract break |
| `Q3` | Confusing UX, broken journey, missing validation message |
| `Q4` | Timeout under load, memory leak, XSS, failed failover |

---

## 7. Test Documentation Minimum Set

| Artifact | Quadrant | Owner |
|----------|----------|--------|
| Test plan (per release or epic) | All | QA lead |
| AC / BDD scenarios | Q2 | PO + QA |
| Automated regression suite | Q1, Q2 | Dev + QA |
| Exploratory charters & notes | Q3 | QA |
| NFR test plan & results | Q4 | QA + DevOps |
| Traceability matrix (story → tests) | Q2, Q3 | QA |
| UAT sign-off record | Q3 | PO |

---

## 8. Release Readiness Gate (QA Sign-off)

Use before promoting to production:

| Gate | Quadrant | Pass? |
|------|----------|-------|
| CI/unit & component tests green | Q1 | ☐ |
| All committed stories meet AC (automated + manual) | Q2 | ☐ |
| Exploratory / UAT complete; no open Sev-1/2 | Q3 | ☐ |
| NFR & security thresholds met or waived | Q4 | ☐ |
| Known issues published and accepted | All | ☐ |

**Sign-off**

| Field | Value |
|-------|--------|
| Release / version | |
| QA lead | |
| Date | |
| Comments | |

---

## 9. Test Metrics

Full definitions, formulas, scorecard, and review cadence are in a separate document:

**[Algile Test Metrics — QA Reference](./Algile-Test-Metrics-QA.md)**

Quick map:

| Quadrant | Example metrics |
|----------|-----------------|
| Q1 | CI pass rate, flaky test rate, PR validation time |
| Q2 | AC coverage, regression pass %, escaped defects |
| Q3 | Exploratory yield, UAT pass rate, defect age |
| Q4 | p95 latency, error rate under load, open critical security |

---

## 10. References

- Brian Marick — Agile testing quadrants (original model)
- Lisa Crispin & Janet Gregory — *Agile Testing: A Practical Guide for Testers and Agile Teams*
- ISTQB / team-specific NFR and security policies (replace with your org links)

---

## Appendix A — Quick Reference Card

| | **Support team** | **Critique product** |
|---|------------------|----------------------|
| **Business** | **Q2** — AC, BDD, functional | **Q3** — Exploratory, UAT, UX |
| **Technology** | **Q1** — Unit, component, CI | **Q4** — Perf, security, resilience |

**QA mantra:** Automate what repeats (Q1–Q2); explore what surprises (Q3); measure what hurts in prod (Q4).

---

*Customize sections 6–8 with your project names, tools (e.g. Jira, TestRail, Jenkins), and NFR thresholds.*
