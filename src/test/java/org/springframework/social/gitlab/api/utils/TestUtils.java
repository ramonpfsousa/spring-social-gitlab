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
package org.springframework.social.gitlab.api.utils;

import java.util.Date;
import static org.hamcrest.Matchers.is;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import static org.junit.Assert.assertThat;

/**
 *
 * @author p.hoeffling
 */
public class TestUtils {

    public static void verifyUtcDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        DateTime datetime = new DateTime(date).withZone(DateTimeZone.UTC);
        assertThat("year", datetime.getYear(), is(year));
		assertThat("month", datetime.getMonthOfYear(), is(month));
		assertThat("day", datetime.getDayOfMonth(), is(day));
		assertThat("hour", datetime.getHourOfDay(), is(hour));
		assertThat("minute", datetime.getMinuteOfHour(), is(minute));
		assertThat("second", datetime.getSecondOfMinute(), is(second));
    }

}
