---
layout: page
title: Rayasam Harshini's Project Portfolio Page
---
## Project: ForgetfulNUS

ForgetfulNUS is a desktop glossary app for students taking German 1 (LAG1201) and German 2 (LAG2201) in NUS to practise and test their vocabulary. 
This app is optimised for use via a Command Line Interface (CLI). If you can type fast, ForgetfulNUS can get your German revision done quickly and effectively.

Given below are my contributions to the project.

* **New Feature**: Added the ability to save quiz scores.
  * What it does: allows the user to view scores for previous rounds 
  of testing, along with a list of German words tested in each round. Scores are saved to local
  storage and can be accessed even after exiting and reopening the application.
  * Justification: This feature improves the product significantly because a user can keep
  track of their progress when quizzing. Not only can users view previous scores
  to check if they are making progress, they can also see the words tested to identify potential
  weak areas.
  * Highlights: This enhancement required significant additions to the Storage component to save scoring
   information in a separate .json file. Given that scores are saved in a similar way to flashcards in the glossary,
   this enhancement was challenging as it required analysis of code to extract several class methods into interfaces to minimise code duplication.
   Modifications were also made to the Model component to allow words tested to be tracked along with the scores.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=rayasamhr&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=rayasamhr&tabRepo=AY2021S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management - Incomplete**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features- Incomplete**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation - Incomplete**:
  * User Guide:
    * Added documentation for the feature `scores [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `scores` feature.

* **Community - I**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
