---
stepsCompleted: [step-01-init, step-02-discovery, step-03-success, step-04-journeys, step-05-domain, step-06-innovation, step-07-project-type, step-08-scoping, step-09-functional, step-10-nonfunctional, step-11-polish, step-12-complete]
workflowComplete: true
completedAt: '2026-01-21'
inputDocuments:
  - '_bmad-output/analysis/brainstorming-session-2026-01-20.md'
workflowType: 'prd'
documentCounts:
  briefs: 0
  research: 0
  brainstorming: 1
  projectDocs: 0
classification:
  projectType: mobile_app
  platform: cross-platform (Android + iOS, Compose Multiplatform)
  domain: personal_finance_tracking
  complexity: low-medium
  projectContext: greenfield
  targetMarket: Egypt
  primaryCurrency: EGP
---

# Product Requirements Document - Flosy

**Author:** Ragaaaboelezz
**Date:** 2026-01-21

## Executive Summary

**Product:** Flosy - "Set it and forget it" salary management app

**Problem:** Young professionals watch money disappear each month with no clarity on what they can actually spend. Traditional budgeting apps demand bank connections and endless categorization.

**Solution:** Flosy shows one number - the Tank Bar - representing true spendable money after salary and fixed expenses. Users log daily spending manually (creating mindfulness), and savings automatically roll over each payday.

**Target Market:** Young professionals in Egypt (initial market)

**Core Value Proposition:**
- 2-minute setup (salary, payday, fixed expenses)
- One number that matters (Tank Bar)
- Automatic savings through rollover (no willpower required)
- Manual entry = mindfulness (feature, not bug)

**Platform:** Android + iOS via Compose Multiplatform

**Business Model:** Freemium (premium features in V2.0+)

**MVP Timeline:** 4 weeks (solo developer)

**Success Metrics:** 500+ DAU by month 3, 70% daily logging rate

## Success Criteria

### User Success

- **"Aha!" Moment:** First payday auto-reset - user opens app, sees new balance with last month's savings already rolled in. Zero effort, instant clarity.
- **Emotional Target:** Relief and pride ("I've got this"), never shame or guilt
- **Setup Success:** Complete onboarding in under 2 minutes
- **Comprehension Test:** User instantly understands Tank Bar = "what I can spend today"
- **Habit Formation:** 70% of active users log expenses daily

### Business Success

| Metric | Target | Timeframe |
|--------|--------|-----------|
| Daily Active Users | 500+ | By month 3 |
| Daily Logging Rate | 70% | Ongoing |
| Premium Conversion | Not tracked | Deferred to V2.0+ |

### Technical Success

- **Onboarding:** Complete profile setup flow in < 2 minutes
- **Reliability:** Monthly auto-reset triggers correctly on configured payday
- **Notifications:** End-of-day reminder delivered consistently
- **Data Integrity:** Savings rollover calculation accurate to the piaster
- **Offline-First:** Core functionality works without internet

### Measurable Outcomes

1. **Week 1:** User completes setup and logs first 3 expenses
2. **Week 2:** User experiences first payday reset with rollover
3. **Month 1:** User has established daily logging habit (70%+ days)
4. **Month 3:** 500+ users hitting these milestones

## Product Scope

### MVP - Minimum Viable Product

| Feature | Priority |
|---------|----------|
| Salary & payday setup | Critical |
| Fixed expenses management | Critical |
| Quick expense entry (amount + category) | Critical |
| Tank Bar visualization | Critical |
| Auto monthly reset & rollover | Critical |
| End-of-day reminder notification | Critical |
| 2-minute onboarding flow | Critical |
| Basic spending history | High |

### Growth Features (Post-MVP: V1.1 - V1.2)

- Daily spending allowance ("safe to spend today")
- Enhanced categories with icons
- Subscription tracking
- End-of-month predictions
- Spending personality insights
- Pattern alerts ("You spend more on weekends")

### Vision (Future: V2.0+)

- Shared expenses & split calculator (Premium)
- Saving streaks & achievement badges
- Savings pet/character
- Annual money story (year review)
- Multi-currency & travel mode
- Arabic language support

## User Journeys

### Persona: Raga

**Who:** Young professional in Egypt, earning 85,000 EGP/month

**Current Reality:** Money comes in, money disappears. By mid-month, Raga checks his bank balance and thinks "where did it all go?" He's not reckless - rent gets paid, bills get paid - but the money in between just... vanishes. No savings growing. No clarity. Just anxiety every time he opens his banking app.

**What he wants:** To feel in control. To know - at any moment - "can I afford this?" without mental math or guilt.

---

### Journey 1: First-Time Setup (The Promise)

**Opening Scene:**
Raga downloads Flosy after seeing it mentioned online. He's tried budgeting apps before - they all wanted him to connect bank accounts or categorize 6 months of transactions. He gave up.

**Rising Action:**
Flosy asks three things:
1. "What's your monthly salary?" → 85,000 EGP
2. "When do you get paid?" → 27th of each month
3. "What are your fixed monthly expenses?" → Rent (15,000), Utilities (2,000), Internet (500), Gym (800)

Total setup time: 1 minute 47 seconds.

**Climax:**
The Tank Bar appears. **66,700 EGP** - his actual spendable money this month, right there. One number. No charts. No judgment.

**Resolution:**
Raga thinks: "Wait, that's it? I already know what I can spend?" He screenshots the Tank Bar and sends it to a friend. For the first time, money feels simple.

---

### Journey 2: Daily Logging (The Habit)

**Opening Scene:**
9:00 PM. Raga's phone buzzes. Flosy: "How'd today go? Quick check-in?"

**Rising Action:**
He taps the notification. Three expenses today:
- Morning coffee: 75 EGP
- Lunch with colleagues: 250 EGP
- Uber home: 85 EGP

Each entry takes 2 seconds. Tap amount, tap category, done.

**Climax:**
Tank Bar updates: **61,290 EGP remaining**. He sees exactly how today's spending moved the needle.

**Resolution:**
Raga closes the app in under 30 seconds. No guilt. No complicated analysis. Just awareness. He notices he's naturally thinking twice before impulse purchases now - not because the app lectures him, but because he *sees* the impact.

---

### Journey 3: Payday Reset (The "Aha!" Moment)

**Opening Scene:**
It's the 27th. Raga wakes up, checks Flosy out of habit.

**Rising Action:**
Last month he had 8,200 EGP left in his Tank when payday hit. He didn't transfer it anywhere. Didn't do anything.

**Climax:**
Flosy shows: **New month started!**
- Salary added: +85,000 EGP
- Fixed expenses deducted: -18,300 EGP
- Last month's savings rolled over: +8,200 EGP
- **New Tank Bar: 74,900 EGP**

He has MORE spendable money than last month - not because he earned more, but because he *kept* more.

**Resolution:**
This is the moment. Raga realizes his savings are growing automatically. No willpower. No separate savings account. Just... awareness creating results. He's hooked.

---

### Journey 4: Life Changes (The Flexibility)

**Opening Scene:**
Three months in. Raga gets promoted. New salary: 95,000 EGP/month.

**Rising Action:**
He opens Flosy → Settings → Updates salary to 95,000. Updates his gym membership (upgraded to 1,200 EGP).

**Climax:**
Tank Bar recalculates instantly. He sees his new financial reality in 10 seconds.

**Resolution:**
Life changed. App adapted. No re-onboarding. No friction. Flosy grows with him.

---

### Journey Requirements Summary

| Journey | Capabilities Revealed |
|---------|----------------------|
| First-Time Setup | Onboarding flow, salary input, payday config, fixed expenses list, Tank Bar display |
| Daily Logging | Push notifications, quick expense entry, category selection, real-time Tank Bar update |
| Payday Reset | Auto salary addition, auto expense deduction, savings rollover calculation, month transition |
| Life Changes | Settings screen, editable salary/payday, editable fixed expenses, instant recalculation |

## Mobile App Specific Requirements

### Platform Requirements

| Platform | Details |
|----------|---------|
| **Android** | Min SDK 24 (Android 7.0), Target SDK 35 |
| **iOS** | iOS 15+ (aligned with Compose Multiplatform support) |
| **Framework** | Compose Multiplatform (shared UI + business logic) |
| **Languages** | Kotlin (shared), Swift (iOS wrapper only) |

### Device Permissions

| Permission | Purpose | Required |
|------------|---------|----------|
| **Notifications** | End-of-day reminder | Yes (with user prompt) |
| **Internet** | Future backend sync (not MVP) | No for MVP |
| **No sensitive permissions** | No camera, location, contacts, etc. | N/A |

**Permission Philosophy:** Minimal footprint. Flosy only asks for notifications - no invasive permissions that erode trust.

### Offline Mode

| Capability | MVP Status |
|------------|------------|
| Full app functionality offline | Yes |
| Local data persistence | Yes (Room/SQLite or DataStore) |
| Cloud sync | No (post-MVP) |
| Data export | No (post-MVP) |
| Cross-device transfer | No (post-MVP) |

**MVP Data Strategy:** 100% local storage. User data lives on device only. Backend integration planned for future version to enable cross-device sync, data backup/restore, and account-based features.

### Push Notification Strategy

| Notification | Trigger | Customizable |
|--------------|---------|--------------|
| End-of-day reminder | Daily at user-configured time (default 9 PM) | Yes - time picker |
| Payday celebration | On configured payday | No |

**Notification Tone:** Friendly, not nagging. "How'd today go?" energy, not "YOU FORGOT TO LOG!"

### Device Features

| Feature | MVP Status | Details |
|---------|------------|---------|
| **Home Screen Widget** | Yes | Quick-add expense from home screen |
| **Haptic Feedback** | Nice-to-have | On expense entry confirmation |
| **Dark Mode** | Yes | Follow system theme |

**Widget Spec (MVP):**
- Display: Current Tank Bar amount
- Action: Tap to quick-add expense
- Size: Small (2x1) widget

### Store Compliance

| Store | Considerations |
|-------|----------------|
| **Google Play** | Standard compliance, no sensitive data handling |
| **Apple App Store** | Standard compliance, no IAP for MVP (no premium yet) |

### Implementation Considerations

- **Data Layer:** Local database (Room on Android, SQLDelight for cross-platform, or Compose Multiplatform-compatible solution)
- **Notification Scheduling:** WorkManager (Android) / Background Tasks (iOS) for reliable daily reminders
- **Widget:** Glance (Android) / WidgetKit (iOS) - may require platform-specific implementation
- **Theme:** Material3 with system dark mode support

## Project Scoping & Phased Development

### MVP Strategy & Philosophy

**MVP Approach:** Problem-Solving MVP - Deliver the core "know what you can spend" value with minimal features. Validate that users will log daily before adding engagement features.

**Resource Model:** Solo developer

**Timeline Target:** 4 weeks to functional MVP

### MVP Feature Set (Phase 1)

**Core User Journeys Supported:**
- First-Time Setup (The Promise)
- Daily Logging (The Habit)
- Payday Reset (The "Aha!" Moment)
- Life Changes (The Flexibility)

**Must-Have Capabilities:**

| Feature | Risk Level | Mitigation |
|---------|------------|------------|
| Tank Bar visualization | Medium | POC first; fallback to simpler UI if needed |
| Salary & payday setup | Low | Standard form input |
| Fixed expenses management | Low | Standard CRUD list |
| Quick expense entry + categories | Low | Standard form with presets |
| Auto monthly reset & rollover | Medium | WorkManager scheduled job; savings appended after salary |
| End-of-day reminder (customizable time) | Low | WorkManager; migrate to Firebase post-MVP |
| 2-minute onboarding flow | Low | Linear wizard flow |
| Home screen widget | Medium | Platform-specific (Glance/WidgetKit) |
| Basic spending history | Low | Simple list view |
| Settings screen | Low | Standard preferences |

### Post-MVP Features

**Phase 2 - Growth (V1.1 - V1.2):**
- Daily spending allowance breakdown
- Enhanced category icons
- Subscription tracking
- End-of-month predictions
- Spending personality insights
- Pattern alerts

**Phase 3 - Expansion (V2.0+):**
- Backend integration & cloud sync
- Firebase push notifications
- Shared expenses & split calculator (Premium)
- Saving streaks & badges
- Savings pet/character
- Annual money story
- Multi-currency & travel mode
- Arabic language support

### Risk Mitigation Strategy

**Technical Risks:**

| Risk | Mitigation |
|------|------------|
| Tank Bar complexity | Build POC first; pivot to simpler visualization if needed |
| Rollover timing edge cases | Thorough testing of month-boundary scenarios |
| Widget cross-platform | Accept platform-specific implementations if needed |

**Market Risks:**

| Risk | Mitigation |
|------|------------|
| Users don't log daily | Publish MVP, study behavior, iterate on reminder UX |
| Tank Bar not intuitive | User testing before launch; iterate based on feedback |
| Churn after novelty | Plan engagement features for V1.2 based on retention data |

**Resource Risks:**

| Risk | Mitigation |
|------|------------|
| Solo developer bandwidth | Strict MVP scope; no scope creep |
| Burnout | 4-week sprints with clear milestones |
| Platform parity | Android first if iOS widget proves complex |

### Development Sequence

1. **Week 1:** Tank Bar POC + Core data model + Profile setup
2. **Week 2:** Expense entry + History + Rollover logic
3. **Week 3:** Onboarding flow + Notifications + Widget
4. **Week 4:** Polish + Testing + Store submission

## Functional Requirements

### Profile & Onboarding

- **FR1:** User can set their monthly salary amount
- **FR2:** User can set their payday (day of month)
- **FR3:** User can select their currency (EGP default)
- **FR4:** User can complete initial setup in a guided onboarding flow
- **FR5:** System calculates initial spendable balance after setup

### Fixed Expenses Management

- **FR6:** User can add a fixed monthly expense (name + amount)
- **FR7:** User can view list of all fixed expenses
- **FR8:** User can edit an existing fixed expense
- **FR9:** User can delete a fixed expense
- **FR10:** System automatically deducts fixed expenses from monthly balance

### Daily Expense Tracking

- **FR11:** User can log an expense (amount + category)
- **FR12:** User can select from predefined expense categories
- **FR13:** User can view expense history
- **FR14:** User can delete a logged expense
- **FR15:** User can edit a logged expense
- **FR16:** System updates Tank Bar immediately after expense entry

### Tank Bar & Balance Display

- **FR17:** User can view current spendable balance (Tank Bar)
- **FR18:** System displays balance as a visual indicator (bar/tank metaphor)
- **FR19:** User can see how much was spent vs. remaining at a glance
- **FR20:** System shows balance in configured currency

### Monthly Cycle & Rollover

- **FR21:** System automatically triggers monthly reset on configured payday
- **FR22:** System adds salary amount to balance on payday
- **FR23:** System deducts total fixed expenses from new month balance
- **FR24:** System carries over previous month's remaining balance (savings rollover)
- **FR25:** User can view payday reset summary (salary added, expenses deducted, rollover amount)
- **FR26:** System displays "days until payday" countdown

### Notifications & Reminders

- **FR27:** System sends daily end-of-day reminder notification
- **FR28:** User can configure reminder time (default 9 PM)
- **FR29:** User can enable/disable notifications
- **FR30:** System sends notification on payday (new month celebration)

### Home Screen Widget

- **FR31:** User can add a home screen widget
- **FR32:** Widget displays current Tank Bar balance
- **FR33:** User can tap widget to quick-add an expense

### Settings & Preferences

- **FR34:** User can edit salary amount after initial setup
- **FR35:** User can change payday date
- **FR36:** User can change notification time
- **FR37:** System supports dark mode (follows system theme)
- **FR38:** User can view app version and info

### Data Management

- **FR39:** System persists all user data locally on device
- **FR40:** System maintains data integrity across app restarts
- **FR41:** User data is retained unless app is uninstalled

## Non-Functional Requirements

### Performance

| Requirement | Target |
|-------------|--------|
| **NFR1:** App launch to usable state | < 2 seconds |
| **NFR2:** Expense entry to Tank Bar update | < 500ms (feels instant) |
| **NFR3:** Screen navigation transitions | < 300ms |
| **NFR4:** Widget data refresh | < 1 second |

**Principle:** The app should never feel sluggish. Every tap should have immediate visual feedback.

### Security

| Requirement | Specification |
|-------------|---------------|
| **NFR5:** Local data storage | Standard SQLite database (Room/SQLDelight) |
| **NFR6:** No external data transmission | MVP stores all data locally only |
| **NFR7:** App data isolation | Data accessible only to Flosy app (standard Android/iOS sandboxing) |

**Note:** No encryption required for MVP. Standard platform security is sufficient for personal tracking data.

### Reliability

| Requirement | Specification |
|-------------|---------------|
| **NFR8:** Data persistence | Zero data loss on app restart, device restart, or app update |
| **NFR9:** Monthly rollover trigger | **CRITICAL** - Must execute correctly on configured payday without fail |
| **NFR10:** Rollover edge cases | Handle month boundaries, leap years, timezone changes |
| **NFR11:** Crash recovery | App state recoverable after unexpected termination |
| **NFR12:** Calculation accuracy | All financial calculations accurate to 0.01 EGP (no rounding errors) |

**Critical Path:** The payday reset/rollover is the most critical operation. Failure to trigger or incorrect calculation is a P0 bug.

### Accessibility (Basic)

| Requirement | Specification |
|-------------|---------------|
| **NFR13:** Material3 defaults | Use standard accessibility features from Compose Material3 |
| **NFR14:** Text scaling | Support system font size preferences |
| **NFR15:** Dark mode | Follow system theme setting |

**Scope:** Standard accessibility via framework defaults. No WCAG audit required for MVP.
