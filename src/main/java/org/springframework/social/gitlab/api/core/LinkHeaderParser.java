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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.MultiValueMap;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author p.hoeffling
 */
public class LinkHeaderParser {

    public Paging buildPaging(String linkHeader) {
        if (linkHeader == null) {
            return new Paging(null, null);
        }

        Map<String, String> linkRelations = extractLinkRelations(linkHeader);

        return new Paging(buildPagingParameters(linkRelations.get("prev")), buildPagingParameters(linkRelations.get("next")));
    }

    public Map<String, String> extractLinkRelations(String linkHeader) {
        if (linkHeader == null) {
            return Collections.<String, String>emptyMap();
        }

        Map<String, String> map = new HashMap<>();
        String[] linkHeaderEntries = StringUtils.delimitedListToStringArray(linkHeader, ", ");
        for (String linkHeaderEntry : linkHeaderEntries) {
            String relation = extractRelation(linkHeaderEntry);
            String link = extractLink(linkHeaderEntry);
            if (relation != null && link != null) {
                map.put(relation, link);
            }
        }

        return map;
    }

    public PagingParameters buildPagingParameters(String link) {
        if (link == null) {
            return null;
        }

        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUriString(link).build().getQueryParams();
        int page = 0;
        int perPage = 0;
        if (StringUtils.hasText(queryParams.getFirst("page"))) {
            page = NumberUtils.parseNumber(queryParams.getFirst("page"), Integer.class);
        }
        if (StringUtils.hasText(queryParams.getFirst("per_page"))) {
            perPage = NumberUtils.parseNumber(queryParams.getFirst("per_page"), Integer.class);
        }

        return new PagingParameters(page, perPage);
    }

    public String extractRelation(String linkHeaderEntry) {
        if (linkHeaderEntry == null) {
            return null;
        }
        String[] parts = StringUtils.delimitedListToStringArray(linkHeaderEntry, ";");
        if (parts.length != 2) {
            return null;
        }

        return parts[1].replaceFirst("rel=\"(.*)\"", "$1").trim();
    }

    public String extractLink(String linkHeaderEntry) {
        if (linkHeaderEntry == null) {
            return null;
        }
        String[] parts = StringUtils.delimitedListToStringArray(linkHeaderEntry, ";");
        if (parts.length != 2) {
            return null;
        }

        return parts[0].replaceFirst("<(.*)>", "$1").trim();
    }

}
