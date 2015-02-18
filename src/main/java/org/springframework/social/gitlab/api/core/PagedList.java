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

import java.util.List;

/**
 *
 * @author p.hoeffling
 * @param <T>
 */
public class PagedList<T> {

    private final List<T> data;

    private final Paging paging;

    public PagedList(List<T> data, Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    public List<T> getData() {
        return data;
    }

    public Paging getPaging() {
        return paging;
    }

}
