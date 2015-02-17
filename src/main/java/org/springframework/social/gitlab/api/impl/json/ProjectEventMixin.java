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
package org.springframework.social.gitlab.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class ProjectEventMixin extends GitlabObjectMixin {

    @JsonProperty("project_id")
    long projectId;

    @JsonProperty("title")
    String title;

    @JsonProperty("action_name")
    String actionName;

    @JsonProperty("target_id")
    long targetId;

    @JsonProperty("target_type")
    String targetType;

    @JsonProperty("author_id")
    long authorId;

    @JsonProperty("author_username")
    String authorUsername;

    @JsonProperty("target_title")
    String targetTitle;

    @JsonProperty("data")
    Map<String, Object> data;

}
