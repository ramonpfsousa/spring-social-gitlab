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
     * @return 
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
     * Get all issues of a project.
     * 
     * @param projectId
     * @return 
     */
    PagedList<Issue> getProjectIssues(long projectId);
    
    /**
     * Get a single issue of a project.
     * 
     * @param projectId
     * @param issueId
     * @return 
     */
    Issue getProjectIssue(long projectId, long issueId);

    
    

}
