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
package org.springframework.social.gitlab.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author p.hoeffling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabAccess {

    @JsonProperty("access_level")
    private long accessLevel;

    @JsonProperty("notification_level")
    private long notificationLevel;

    public long getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(long accessLevel) {
        this.accessLevel = accessLevel;
    }

    public long getNotificationLevel() {
        return notificationLevel;
    }

    public void setNotificationLevel(long notificationLevel) {
        this.notificationLevel = notificationLevel;
    }

}
