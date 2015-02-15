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
package org.springframework.social.gitlab.api.project;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author p.hoeffling
 */
public class ListProjectParametersBuilder {

    public static final String PARAM_SEARCH = "search";
    public static final String PARAM_SORT = "sort";
    public static final String PARAM_ORDER_BY = "order_by";
    public static final String PARAM_ARCHIVED = "archived";

    private Boolean archived;

    private OrderBy orderBy;

    private Sort sort;

    private String search;

    public ListProjectParametersBuilder withArchived() {
        this.archived = Boolean.TRUE;
        return this;
    }

    public ListProjectParametersBuilder withoutArchived() {
        this.archived = Boolean.FALSE;
        return this;
    }

    public ListProjectParametersBuilder removeArchived() {
        this.archived = null;
        return this;
    }

    public ListProjectParametersBuilder withOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public ListProjectParametersBuilder removeOrderBy() {
        this.orderBy = null;
        return this;
    }

    public ListProjectParametersBuilder withSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public ListProjectParametersBuilder removeSort() {
        this.sort = null;
        return this;
    }

    public ListProjectParametersBuilder withSearch(String search) {
        this.search = search;
        return this;
    }

    public ListProjectParametersBuilder removeSearch() {
        this.search = null;
        return this;
    }

    public MultiValueMap<String, String> build() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        if (archived != null) {
            map.add(PARAM_ARCHIVED, String.valueOf(archived));
        }
        if (orderBy != null) {
            map.add(PARAM_ORDER_BY, orderBy.getValue());
        }
        if (sort != null) {
            map.add(PARAM_SORT, sort.getValue());
        }
        if (search != null) {
            map.add(PARAM_SEARCH, search);
        }

        return map;
    }

    public enum OrderBy {

        Id("id"),
        Name("name"),
        Path("path"),
        CreatedAt("created_at"),
        UpdatedAt("updated_at"),
        LastActivityAt("last_activity_at");

        private final String value;

        OrderBy(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum Sort {

        Asc("asc"),
        Desc("desc");

        private final String value;

        Sort(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
