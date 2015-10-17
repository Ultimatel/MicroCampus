package org.smile.microcampus.Utils;

import java.util.Comparator;

/**
 * Created by Ben on 2015/10/16.
 */
public class DateComparator implements Comparator<DateText> {
    @Override
    public int compare(DateText lhs, DateText rhs) {
        return rhs.getDate().compareTo(lhs.getDate());
    }
}
