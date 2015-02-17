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
package org.springframework.social.gitlab.api;

import java.util.Map;

/**
 *
 * @author p.hoeffling
 */
public class ProjectEvent {

    private long projectId;

    private String title;

    private String actionName;

    private long targetId;

    private String targetType;

    private long authorId;

    private String authorUsername;

    private String targetTitle;

    private Map<String, Object> data;

    public long getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getActionName() {
        return actionName;
    }

    public long getTargetId() {
        return targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
