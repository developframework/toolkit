package com.github.developframework.toolkit.persistence.component;

import com.github.developframework.toolkit.persistence.exception.RangeException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 范围封装
 * @author qiuzhenhao
 */
@Data
@NoArgsConstructor
public class Range<Y extends Comparable<? super Y>> implements Serializable{

    private String fieldName;

    private Y from;

    private Y to;

    public Range(Y from, Y to) {
        this.from = from;
        this.to = to;

        if(from != null && to != null && from.compareTo(to) > 0 ) {
            throw new RangeException("The \"from\" value must less than or equal to \"to\" value.");
        } else if(from == null && to == null) {
            throw new RangeException("The \"from\" value and \"to\" value can't be all null.");
        }
    }

    public boolean isBetween() {
        return from != null && to != null;
    }

    public boolean isOnlyFrom() {
        return from != null && to == null;
    }

    public boolean isOnlyTo() {
        return from == null && to != null;
    }
}
