package com.github.developframework.toolkit.persistence.component;

import com.github.developframework.toolkit.persistence.exception.RangeException;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

import static com.github.developframework.toolkit.base.Sugar.useElseDefault;

/**
 * 时间日期范围
 * @author qiuzhenhao
 */
public class DateRange extends Range<Date> {

    public static final DateRange parse(String fromDateStr, String toDateStr, String pattern) {
        Date from;
        try {
            from = Sugar.useElseDefault(DateUtils.parseDate(fromDateStr, pattern), null);
        } catch (ParseException e) {
            throw new RangeException(String.format("\"%s\" invalid.", fromDateStr));
        }
        Date to;
        try {
            to = Sugar.useElseDefault(DateUtils.parseDate(toDateStr, pattern), null);
        } catch (ParseException e) {
            throw new RangeException(String.format("\"%s\" invalid.", fromDateStr));
        }
        return new DateRange(from, to);
    }

    private DateRange(Date from, Date to) {
        super(from, to);
    }
}
