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
import org.springframework.util.MultiValueMap;

/**
 *
 * @author j.roesler
 * @author p.hoeffling
 */
public interface IssueOperations {

    /**
     * Get a paged list of issues created by the current user.
     * 
     * @return A pgaed list of issues.
     */
    PagedList<Issue> getIssuesCreatedByCurrentUser();

    /**
     * Get a paged list of issues and append the given parammeters.
     * 
     * This method allows access to query params as needed.
     * If you just wat to pass in a set of supported/known parameters lokk at the builder based version of this method.
     * 
     * @param parameters  The map of parameters send to the server.
     * @return A pgaed list of issues.
     */
    PagedList<Issue> getIssuesCreatedByCurrentUser(MultiValueMap<String, String> parameters);
    
    /**
     * Get a paged list of issues filtered and ordered.
     * 
     * All parameters defined in the builder are supported by the current API implementation.
     * Use this interface for syntactic sugar and/or if you want to be sure the params are supported by the current API.
     * 
     * @param parametersBuilder The builder to apply.
     * @return A paged list of issues.
     */
    PagedList<Issue> getIssuesCreatedByCurrentUser(ListIssueParametersBuilder parametersBuilder);

    /**
     * Get a paged list of issues from the current project.
     * 
     * @param projectId The project id.
     * @return A paged list of issues.
     */
    PagedList<Issue> getProjectIssues(long projectId);
    
    /**
     * Get all issues of a project.
     * 
     * This method allows access to query params as needed.
     * If you just wat to pass in a set of supported/known parameters lokk at the builder based version of this method.
     * 
     * @param projectId The project id.
     * @param parameters The map of parameters send to the server.
     * @return A paged list of issues.
     */
    PagedList<Issue> getProjectIssues(long projectId, MultiValueMap<String, String> parameters);

    /**
     * Get all issues of a project.
     * 
     * All parameters defined in the builder are supported by the current API implementation.
     * Use this interface for syntactic sugar and/or if you want to be sure the params are supported by the current API.
     * 
     * @param projectId The project id.
     * @param parametersBuilder The builder to apply.
     * @return A paged list of issues.
     */
    PagedList<Issue> getProjectIssues(long projectId, ListIssueParametersBuilder parametersBuilder);
    
    /**
     * Get a single issue of a project.
     * 
     * @param projectId The project id.
     * @param issueId The issue id.
     * @return Issue
     */
    Issue getProjectIssue(long projectId, long issueId);

}
