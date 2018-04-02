package com.github.developframework.toolkit.persistence.component;

import lombok.Getter;

/**
 * 分页请求参数信息
 */
@Getter
public class Pager {

    public static final int DEFAULT_INDEX = 1;
    public static final int DEFAULT_SIZE = 10;

    /* 页码 */
    protected int index;
    /* 页容量 */
    protected int size;

    public Pager() {
        this(DEFAULT_INDEX, DEFAULT_SIZE);
    }

    public Pager(int index, int size) {
        this.index = index;
        this.size = size;
    }
}
