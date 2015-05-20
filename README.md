# spring-social-gitlab

Spring Social provider module for [Gitlab API](http://doc.gitlab.com/ce/api/README.html)

Since version 7.7 Gitlab implements OAuth application features.

## Project state

[![Build Status](https://travis-ci.org/burning-duck/spring-social-gitlab.svg?branch=master)](https://travis-ci.org/burning-duck/spring-social-gitlab)

First goal is to provide a read only implementation of the users API.
Admin an modifying operations may follow.

## API Implementation

- [ ] [Projects](http://doc.gitlab.com/ce/api/projects.html)
  - [x] list projects
  - [x] list projects with params
  - [x] list owned projects
  - [x] list owned projects with params
  - [x] get single project
  - [x] get project events
  - [ ] create project
  - [ ] edit project
  - [ ] fork project
  - [ ] remove project
  - [ ] search projects by name
  - [ ] members
    - [x] list team members
    - [x] list team members with parms
    - [x] get single team member
    - [ ] add team member
    - [ ] edit team member
    - [ ] remove team member
  - [ ] hooks
    - [x] list hooks
    - [x] get single hook
    - [ ] add hook
    - [ ] edit hook
    - [ ] delete hook
  - [ ] branches
    - [x] list branches
    - [x] get single branch
    - [ ] protect single branch
    - [ ] unprotect single branch
- [ ] [Issues](http://doc.gitlab.com/ce/api/issues.html)
    - [x] list issues created by current user
    - [ ] list issues created by current user, paginated, filtered, ordered
    - [x] list issues by project
    - [ ] list issues by project, paginated, filtered, ordered
    - [x] get a single issue
    - [ ] create a new issue
    - [ ] edit an existing issue

  
