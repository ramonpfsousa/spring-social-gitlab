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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * Parameter builder for issues.
 *
 * @author p.hoeffling
 */
public class ListIssueParametersBuilder {

    public static final String PARAM_STATE = "state";
    public static final String PARAM_LABELS = "labels";
    public static final String PARAM_ORDER_BY = "order_by";
    public static final String PARAM_SORT = "sort";

    private State state;

    private final Set<String> labels;

    private OrderBy orderBy;

    private Sort sort;

    public ListIssueParametersBuilder() {
        this.labels = new HashSet<>();
    }

    /**
     * Remove the state from this filter.
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder removeState() {
        this.state = null;
        return this;
    }

    /**
     * Set the state filter to ALL
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withStateAll() {
        this.state = null;
        return this;
    }

    /**
     * Set the state filter to "opened".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withStateOpened() {
        this.state = State.Opened;
        return this;
    }

    /**
     * Set the state filter to "closed".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withStateClosed() {
        this.state = State.Closed;
        return this;
    }

    /**
     * Remove all labels from the builder.
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder removeLabels() {
        this.labels.clear();
        return this;
    }

    /**
     * Removes a single label from the filter.
     *
     * @param removeLabel The label to remove.
     * @return This builder.
     */
    public ListIssueParametersBuilder removeLabel(String removeLabel) {
        this.labels.remove(removeLabel);
        return this;
    }

    /**
     * Set the labels for this filter, removing all labels added before.
     *
     * @param setLabels A set of labels to filter.
     * @return This builder.
     */
    public ListIssueParametersBuilder withLabels(Set<String> setLabels) {
        this.labels.clear();
        if (setLabels != null) {
            this.labels.addAll(setLabels);
        }
        return this;
    }

    /**
     * Set the labels for this filter, removing all labels added before.
     *
     * @param setLabels Label params to filter.
     * @return This builder.
     */
    public ListIssueParametersBuilder withLabels(String... setLabels) {
        this.labels.clear();
        if (setLabels != null) {
            Collections.addAll(labels, setLabels);
        }
        return this;
    }

    /**
     * Adds a set of labels to this filter.
     *
     * @param addLabels The set of labels to add.
     * @return This builder.
     */
    public ListIssueParametersBuilder andWithLabels(Set<String> addLabels) {
        if (addLabels != null) {
            this.labels.addAll(addLabels);
        }
        return this;
    }

    /**
     * Adds a set of labels to this filter.
     *
     * @param addLabels The set of labels to add.
     * @return This builder.
     */
    public ListIssueParametersBuilder andWithLabels(String... addLabels) {
        if (addLabels != null) {
            Collections.addAll(this.labels, addLabels);
        }
        return this;
    }

    /**
     * Remove the ordering from the filter.
     *
     * @return This builer.
     */
    public ListIssueParametersBuilder removeOderBy() {
        this.orderBy = null;
        return this;
    }

    /**
     * Set the order to "created_at".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withOrderByCreateAt() {
        this.orderBy = OrderBy.CreatedAt;
        return this;
    }

    /**
     * Set the order to "updated_at".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withOrderByUpdatedAt() {
        this.orderBy = OrderBy.UpdatedAt;
        return this;
    }

    /**
     * Remove the sorting from the filter.
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder removeSort() {
        this.sort = null;
        return this;
    }

    /**
     * Set the sorting to "asc".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withSortAsc() {
        this.sort = Sort.Asc;
        return this;
    }

    /**
     * Set the sorting to "desc".
     *
     * @return This builder.
     */
    public ListIssueParametersBuilder withSortDesc() {
        this.sort = Sort.Desc;
        return this;
    }

    /**
     * Build a map with parameters defined in this builder.
     *
     * @return A new map, never null.
     */
    public MultiValueMap<String, String> build() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        if (state != null) {
            map.set(PARAM_STATE, state.getValue());
        }
        if (!labels.isEmpty()) {
            map.set(PARAM_LABELS, StringUtils.collectionToCommaDelimitedString(labels));
        }
        if (orderBy != null) {
            map.set(PARAM_ORDER_BY, orderBy.getValue());
        }
        if (sort != null) {
            map.set(PARAM_SORT, sort.getValue());
        }

        return map;
    }

    public enum State {

        Opened("opened"),
        Closed("closed");

        private final String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum OrderBy {

        CreatedAt("created_at"),
        UpdatedAt("updated_at");

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
