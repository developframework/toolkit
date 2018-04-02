package com.github.developframework.toolkit.persistence.jpa;

import com.github.developframework.toolkit.base.component.Range;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiuzhenhao
 */
@NoRepositoryBean
public interface ToolkitRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    <Y extends Comparable<? super Y>> T findOneExampleWithRange(Example<T> example, List<Range<Y>> ranges);

    <Y extends Comparable<? super Y>> long countByExampleWithRange(Example<T> example, List<Range<Y>> ranges);

    <Y extends Comparable<? super Y>> Page<T> findByExampleWithRange(Example<T> example, List<Range<Y>> ranges, Pageable pageable);
}
