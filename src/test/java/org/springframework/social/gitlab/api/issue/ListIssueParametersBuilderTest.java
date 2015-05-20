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

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.social.gitlab.api.issue.ListIssueParametersBuilder.*;

/**
 *
 * @author p.hoeffling
 */
public class ListIssueParametersBuilderTest {

    private ListIssueParametersBuilder builder;

    @Before
    public void setUp() {
        this.builder = new ListIssueParametersBuilder();
    }

    @Test
    public void testMapIsEmptyAfterConstruction() {
        MultiValueMap<String, String> params = builder.build();
        assertThat(params.keySet(), is(empty()));
    }

    @Test
    public void testWithState() {
        MultiValueMap<String, String> params = builder
                .withStateOpened()
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_STATE));
        assertThat(params.get(PARAM_STATE), contains(State.Opened.getValue()));
    }

    @Test
    public void testRemoveState() {
        MultiValueMap<String, String> params = builder
                .withStateOpened()
                .removeState()
                .build();

        assertThat(params.keySet(), is(empty()));
    }

    public void testWithOrderBy() {
        MultiValueMap<String, String> params = builder
                .withOrderByCreateAt()
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_ORDER_BY));
        assertThat(params.get(PARAM_ORDER_BY), contains(OrderBy.CreatedAt.getValue()));

    }

    @Test
    public void testRemoveOrderBy() {
        MultiValueMap<String, String> params = builder
                .withOrderByCreateAt()
                .removeOderBy()
                .build();

        assertThat(params.keySet(), is(empty()));
    }

    @Test
    public void testWithSort() {
        MultiValueMap<String, String> params = builder
                .withSortAsc()
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_SORT));
        assertThat(params.get(PARAM_SORT), contains(Sort.Asc.getValue()));

    }

    @Test
    public void testRemoveSort() {
        MultiValueMap<String, String> params = builder
                .withSortAsc()
                .removeSort()
                .build();

        assertThat(params.keySet(), is(empty()));
    }

    @Test
    public void testWithLabel() {
        MultiValueMap<String, String> params = builder
                .withLabels("test")
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), contains("test"));

    }

    @Test
    public void testWithLabelsFromParams() {
        MultiValueMap<String, String> params = builder
                .withLabels("foo", "bar")
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), anyOf(contains("foo,bar"), contains("bar,foo")));
    }

    @Test
    public void testWithLabelsFromSet() {
        Set<String> labels = new HashSet<>();
        labels.add("foo");
        labels.add("bar");
        
        MultiValueMap<String, String> params = builder
                .withLabels(labels)
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), anyOf(contains("foo,bar"), contains("bar,foo")));
    }

    @Test
    public void testWithLabelsReplace() {
        MultiValueMap<String, String> params = builder
                .withLabels("foo", "bar")
                .withLabels("lorem", "ipsum")
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), not(anyOf(contains("foo,bar"), contains("bar,foo"))));
        assertThat(params.get(PARAM_LABELS), anyOf(contains("lorem,ipsum"), contains("ipsum,lorem")));

    }

    @Test
    public void testAndWithLabelsFromParams() {
        MultiValueMap<String, String> params = builder
                .withLabels("foo")
                .andWithLabels("bar")
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), anyOf(contains("foo,bar"), contains("bar,foo")));

    }

    @Test
    public void testAndWithLabelsFromSet() {
        Set<String> labels = new HashSet<>();
        labels.add("bar");

        MultiValueMap<String, String> params = builder
                .withLabels("foo")
                .andWithLabels(labels)
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(PARAM_LABELS));
        assertThat(params.get(PARAM_LABELS), anyOf(contains("foo,bar"), contains("bar,foo")));

    }

    @Test
    public void testRemoveLabels() {
        MultiValueMap<String, String> params = builder
                .withLabels("foo", "bar")
                .removeLabels()
                .build();

        assertThat(params.keySet(), is(empty()));

    }
}
