# Algile Test Metrics — QA Reference

**Purpose:** Define what to measure, how to calculate it, and how metrics map to Agile testing quadrants.

**Related doc:** [Algile Testing Quadrants — QA Reference](./Algile-Testing-Quadrants-QA.md)

**Audience:** QA engineers, test leads, product owners, developers  
**Version:** 1.0 | **Date:** 2026-05-31

---

## 1. Principles

Test metrics show whether quality activities are effective—not whether QA is “busy.” Use them to **improve** (trends, root cause), not to punish individuals.

| Do | Avoid |
|----|--------|
| Tie metrics to business risk and release goals | Raw bug counts as a performance score |
| Track trends over sprints/releases | Single-day snapshots without context |
| Segment by quadrant, severity, environment | Mixing unit failures with production incidents |
| Review in retros with actions | Metrics with no owner or threshold |

**Cadence:** Sprint — team health; Release — readiness; Quarterly — process and automation investment.

---

## 2. Metrics by Quadrant

### Q1 — Technology · Support the team

| Metric | Formula / source | Typical target (template) | Why it matters |
|--------|------------------|---------------------------|----------------|
| **CI pass rate** | `(green builds / total builds) × 100` | ≥ 95% on main branch | Stable pipeline for fast feedback |
| **Build fix time (MTTR)** | Avg time from red → green on main | < 4 hours (team-defined) | Reduces dev blocking |
| **Unit test pass rate** | Passing unit tests / total in CI | 100% on merge | Baseline code health |
| **Code coverage (changed code)** | Tool report on PR diff | Team policy (e.g. ≥ 80% on new code) | Guides gaps, not a quality proof |
| **Flaky test rate** | Flaky runs / total automated runs | Trending down; < 2% | Trust in automation |
| **PR validation time** | Commit → CI complete | Within SLA (e.g. < 15 min) | Feedback loop speed |

### Q2 — Business · Support the team

| Metric | Formula / source | Typical target (template) | Why it matters |
|--------|------------------|---------------------------|----------------|
| **AC coverage** | AC with ≥1 test / total AC in sprint | 100% for committed stories | Nothing ships untested |
| **Automation coverage (regression)** | Automated cases / planned regression set | Increase per release; no drop | Sustainable regression |
| **Test pass rate (functional)** | Passed / executed (excl. blocked) | ≥ 98% at release gate | Release confidence |
| **Defect detection in test** | Bugs found in QA env before prod | Higher in-test vs escaped | Shift-left effectiveness |
| **Escaped defects** | Prod bugs / total bugs (or per story) | Trend down; track Sev-1/2 | Real quality signal |
| **Defect leakage ratio** | Escaped / (escaped + pre-prod found) | < 10% (context-dependent) | Process gap indicator |
| **Test execution progress** | Executed / planned for release | 100% before sign-off | Completeness |

### Q3 — Business · Critique the product

| Metric | Formula / source | Typical target (template) | Why it matters |
|--------|------------------|---------------------------|----------------|
| **Exploratory yield** | Valid bugs / session hour | Track trend; compare charters | Session efficiency |
| **Exploratory coverage** | Charters completed / planned | 100% for release scope | Risk areas visited |
| **UAT pass rate** | Accepted scenarios / total UAT | 100% or documented waivers | Business approval |
| **UAT defects** | Defects found in UAT | Count + severity mix | Late requirement gaps |
| **Defect severity mix (pre-prod)** | % Critical/High/Medium/Low | Fewer Critical at RC | Risk before ship |
| **Reopen rate** | Reopened bugs / closed bugs | < 5% | Fix quality |
| **Defect age** | Days open by severity | Sev-1: 0 days at release | Stale risk |

### Q4 — Technology · Critique the product

| Metric | Formula / source | Typical target (template) | Why it matters |
|--------|------------------|---------------------------|----------------|
| **p95 / p99 latency** | APM or load tool vs NFR doc | ≤ NFR (e.g. p95 < 500 ms) | User experience under load |
| **Throughput** | Requests/sec at target load | Meets capacity plan | Scalability |
| **Error rate under load** | 5xx or failed tx / total | < 0.1% (service-defined) | Stability |
| **Security — critical/high open** | Scanner + pen test backlog | 0 critical at release | Exposure |
| **Availability / uptime (stage)** | Monitoring during soak | ≥ 99.9% (example) | Reliability |
| **Recovery time (RTO)** | DR drill measured time | ≤ policy | Operability |

---

## 3. Cross-cutting Process Metrics

| Metric | Formula | Use |
|--------|---------|-----|
| **Test effectiveness** | Bugs found in testing / (bugs in testing + escaped) | Higher = better test design |
| **Defect density** | Defects / KLOC or per story point | Compare modules/releases |
| **Defect removal efficiency (DRE)** | Defects found pre-release / total defects (incl. prod) | Phase effectiveness |
| **Automation ROI** | Hours saved (manual − auto run) / maintenance cost | Invest in stable suites |
| **Environment availability** | Uptime of test/stage envs | Blocker for all quadrants |
| **Test debt** | Skipped/deferred tests + open automation gaps | Backlog for hardening |

---

## 4. Severity and SLAs (template)

| Severity | Definition (example) | Fix before release? | Metric |
|----------|----------------------|------------------------|--------|
| **Sev-1** | Production down / data loss | Yes — no ship | Count = 0 at gate |
| **Sev-2** | Major feature broken, no workaround | Yes — or waiver | Count = 0 at gate |
| **Sev-3** | Workaround exists | Waivable with PO | Trend only |
| **Sev-4** | Cosmetic / minor | Backlog | Trend only |

---

## 5. Sprint / Release Scorecard

Copy per sprint or release:

| Metric | Q1 | Q2 | Q3 | Q4 | Actual | Target | Trend (↑/↓/→) |
|--------|----|----|----|-----|--------|--------|----------------|
| CI pass rate % | ✓ | | | | | ≥ 95% | |
| AC coverage % | | ✓ | | | | 100% | |
| Regression pass % | | ✓ | | | | ≥ 98% | |
| Escaped defects (Sev-1/2) | | ✓ | ✓ | | | 0 | |
| Exploratory sessions done | | | ✓ | | | planned | |
| UAT sign-off | | | ✓ | | | Yes | |
| p95 latency vs NFR | | | | ✓ | | met | |
| Open critical security | | | | ✓ | | 0 | |

**Sprint:** _____________ **Release:** _____________ **Reported by:** _____________ **Date:** _____________

---

## 6. Data Sources

| Tool area | Examples | Metrics fed |
|-----------|----------|-------------|
| CI/CD | Jenkins, GitHub Actions, GitLab CI | Q1 pass rate, duration, flaky |
| Test management | TestRail, Zephyr, Xray | Q2–Q3 execution, coverage |
| Defect tracking | Jira, Azure DevOps | All quadrants, leakage, age |
| APM / load | Grafana, k6, JMeter | Q4 latency, errors |
| Security | SonarQube, Snyk, OWASP ZAP | Q4 vulnerabilities |
| Coverage | JaCoCo, Istanbul | Q1 changed-code coverage |

---

## 7. Review Ritual

1. **Daily (stand-up):** Blocked tests, env down, Sev-1/2 only.
2. **Sprint review:** Scorecard trends; escaped defects; automation added.
3. **Retro:** One metric that improved, one that worsened, one action.
4. **Release post-mortem:** Escaped defects by quadrant; update targets if needed.

---

## Appendix A — Formula Cheat Sheet

| Metric | Formula |
|--------|---------|
| CI pass rate | `(green builds ÷ total builds) × 100` |
| AC coverage | `(AC with tests ÷ total AC) × 100` |
| Test pass rate | `(passed ÷ (passed + failed)) × 100` — exclude blocked/not run |
| Escaped defects | Count of bugs found in **production** (first detection) |
| Defect leakage | `escaped ÷ (escaped + found in QA/UAT/stage)` |
| Exploratory yield | `valid bugs found ÷ exploratory hours` |
| DRE | `pre-release defects ÷ (pre-release + post-release defects) × 100` |
| Flaky test rate | `flaky failures ÷ total automated runs × 100` |

---

*Customize targets and tool names for your project. Align NFR thresholds with your architecture and security policies.*
