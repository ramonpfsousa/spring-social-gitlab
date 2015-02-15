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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.util.MultiValueMap;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author p.hoeffling
 */
public class ListProjectParametersBuilderTest {

    private ListProjectParametersBuilder builder;

    @Before
    public void setUp() {
        builder = new ListProjectParametersBuilder();
    }

    @Test
    public void testWithArchived() {
        MultiValueMap<String, String> params = builder
                .withArchived()
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(ListProjectParametersBuilder.PARAM_ARCHIVED));
        assertThat(params.get(ListProjectParametersBuilder.PARAM_ARCHIVED), contains(String.valueOf(true)));
    }

    @Test
    public void testWithoutArchived() {
        MultiValueMap<String, String> params = builder
                .withoutArchived()
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(ListProjectParametersBuilder.PARAM_ARCHIVED));
        assertThat(params.get(ListProjectParametersBuilder.PARAM_ARCHIVED), contains(String.valueOf(false)));
    }

    @Test
    public void testRemoveArchived() {
        MultiValueMap<String, String> params = builder
                .withArchived()
                .removeArchived()
                .build();

        assertThat(params.keySet(), hasSize(0));
        assertThat(params, not(hasKey(ListProjectParametersBuilder.PARAM_ARCHIVED)));
    }

    @Test
    public void testWithOrderBy() {
        MultiValueMap<String, String> params = builder
                .withOrderBy(ListProjectParametersBuilder.OrderBy.Id)
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(ListProjectParametersBuilder.PARAM_ORDER_BY));
        assertThat(params.get(ListProjectParametersBuilder.PARAM_ORDER_BY), contains(ListProjectParametersBuilder.OrderBy.Id.getValue()));
    }

    @Test
    public void testRemoveOrderBy() {
        MultiValueMap<String, String> params = builder
                .withOrderBy(ListProjectParametersBuilder.OrderBy.Id)
                .removeOrderBy()
                .build();

        assertThat(params.keySet(), hasSize(0));
        assertThat(params, not(hasKey(ListProjectParametersBuilder.PARAM_ORDER_BY)));
    }

    @Test
    public void testWithSort() {
        MultiValueMap<String, String> params = builder
                .withSort(ListProjectParametersBuilder.Sort.Asc)
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(ListProjectParametersBuilder.PARAM_SORT));
        assertThat(params.get(ListProjectParametersBuilder.PARAM_SORT), contains(ListProjectParametersBuilder.Sort.Asc.getValue()));
    }

    @Test
    public void testRemoveSort() {
        MultiValueMap<String, String> params = builder
                .withSort(ListProjectParametersBuilder.Sort.Asc)
                .removeSort()
                .build();

        assertThat(params.keySet(), hasSize(0));
        assertThat(params, not(hasKey(ListProjectParametersBuilder.PARAM_SORT)));
    }

    @Test
    public void testWithSearch() {
        MultiValueMap<String, String> params = builder
                .withSearch("foo")
                .build();

        assertThat(params.keySet(), hasSize(1));
        assertThat(params, hasKey(ListProjectParametersBuilder.PARAM_SEARCH));
        assertThat(params.get(ListProjectParametersBuilder.PARAM_SEARCH), contains("foo"));
    }

    @Test
    public void testRemoveSearch() {
        MultiValueMap<String, String> params = builder
                .withSearch("foo")
                .removeSearch()
                .build();

        assertThat(params.keySet(), hasSize(0));
        assertThat(params, not(hasKey(ListProjectParametersBuilder.PARAM_SEARCH)));
    }

}
