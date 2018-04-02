package com.github.developframework.toolkit.persistence.jpa;

import com.github.developframework.toolkit.base.component.Pager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * JPA分页请求参数信息
 */
public class JPAPager extends Pager{

    public JPAPager() {
        super();
    }

    public JPAPager(int index, int size) {
        super(index, size);
    }

    /**
     * 获得Pageable
     * @return
     */
    public Pageable getPageable() {
        return new PageRequest(index - 1, size);
    }

    /**
     * 获得Pageable（带Sort）
     * @param sort
     * @return
     */
    public Pageable getPageable(Sort sort) {
        return new PageRequest(index, size, sort);
    }
}
