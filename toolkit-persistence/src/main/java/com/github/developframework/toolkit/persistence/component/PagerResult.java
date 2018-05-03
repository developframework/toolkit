package com.github.developframework.toolkit.persistence.component;

import lombok.Getter;

import java.util.List;

/**
 * @author qiuzhenhao
 */
@Getter
public class PagerResult<T> {

    /* 数据列表 */
    private List<T> list;

    /* 记录总条数 */
    private int total;

    /* 分页信息 */
    private Pager pager;

    /* 页总数 */
    private int pageTotal;

    public PagerResult(Pager pager, List<T> list, int total) {
        this.list = list;
        this.total = total;
        this.pager = pager;
        this.pageTotal = total % pager.getSize() == 0 ? total / pager.getSize() : (total / pager.getSize() + 1);
    }
}
