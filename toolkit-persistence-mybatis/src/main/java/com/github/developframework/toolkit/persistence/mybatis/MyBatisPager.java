package com.github.developframework.toolkit.persistence.mybatis;

import com.github.developframework.toolkit.persistence.component.Pager;

/**
 * Mybatis分页请求参数信息
 */
public class MyBatisPager extends Pager {

    public MyBatisPager() {
        super();
    }

    public MyBatisPager(int index, int size) {
        super(index, size);
    }

    /**
     * 生成 LIMIT 语句
     * @return
     */
    public String limitSQL() {
        return String.format("LIMIT %d, %d", (index - 1) * size, size);
    }
}
