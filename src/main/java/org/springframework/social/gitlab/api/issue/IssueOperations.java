/*
 * Copyright 2015 p.hoeffling.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.gitlab.api.issue;

import org.springframework.social.gitlab.api.core.PagedList;

/**
 *
 * @author j.roesler
 * @author p.hoeffling
 */
public interface IssueOperations {

    PagedList<Issue> getIssuesCreatedByCurrentUser();

    PagedList<Issue> getProjectIssues(long projectId);
    
    Issue getProjectIssue(long projectId, long issueId);

}
