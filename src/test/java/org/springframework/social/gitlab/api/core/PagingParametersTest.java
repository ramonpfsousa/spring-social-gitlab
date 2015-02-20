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
package org.springframework.social.gitlab.api.core;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author p.hoeffling
 */
public class PagingParametersTest {

    private PagingParameters empty;
    private PagingParameters first;
    private PagingParameters next;
    private PagingParameters last;

    @Before
    public void setUp() {
        empty = new PagingParameters(0, 0);
        first = new PagingParameters(1, 20);
        next = new PagingParameters(2, 20);
        last = new PagingParameters(2, 20);
    }

    @Test
    public void testGetPage() {
        assertThat(empty.getPage(), is(0));
        assertThat(first.getPage(), is(1));
        assertThat(next.getPage(), is(2));
        assertThat(last.getPage(), is(2));
    }

    @Test
    public void testGetPerPage() {
        assertThat(empty.getPerPage(), is(0));
        assertThat(first.getPerPage(), is(20));
    }

    @Test
    public void testHashCode() {
        assertThat(empty.hashCode(), is(not(first.hashCode())));
        assertThat(next.hashCode(), is(last.hashCode()));
    }

    @Test
    public void testEquals() {
        assertThat(empty, is(not(equalTo(first))));
        assertThat(next, is(equalTo(last)));
    }

    @Test
    public void testToMap() {
        assertThat(empty.toMap(), not(hasKey("page")));
        assertThat(empty.toMap(), not(hasKey("per_page")));

        assertThat(first.toMap(), hasKey("page"));
        assertThat(first.toMap(), hasKey("per_page"));

        assertThat(first.toMap().getFirst("page"), is("1"));
        assertThat(first.toMap().getFirst("per_page"), is("20"));
    }

}
