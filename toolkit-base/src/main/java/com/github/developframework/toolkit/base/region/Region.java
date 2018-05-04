package com.github.developframework.toolkit.base.region;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qiuzhenhao
 */
@Getter
@Setter
public abstract class Region implements Serializable {

    protected String code;

    protected String name;

    @Override
    public String toString() {
        return name;
    }
}
