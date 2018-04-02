package com.github.developframework.toolkit.persistence.jpa;

import com.github.developframework.toolkit.base.component.Range;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * @author qiuzhenhao
 */
public class ToolkitRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ToolkitRepository<T, ID> {

    private final EntityManager entityManager;

    public ToolkitRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <Y extends Comparable<? super Y>> T findOneExampleWithRange(Example<T> example, List<Range<Y>> ranges) {
        return (T) findOne(compositeSpecifications(example, ranges));
    }

    @Override
    public <Y extends Comparable<? super Y>> long countByExampleWithRange(Example<T> example, List<Range<Y>> ranges) {
        return count(compositeSpecifications(example, ranges));
    }

    @Override
    public <Y extends Comparable<? super Y>> Page<T> findByExampleWithRange(Example<T> example, List<Range<Y>> ranges, Pageable pageable) {
        return findAll(compositeSpecifications(example, ranges), pageable);
    }

    private <Y extends Comparable<? super Y>> Specifications compositeSpecifications(Example<T> example, List<Range<Y>> ranges) {
        Specification<T> byExample = new ByExampleSpecification<>(example);
        Specification<T> byRanges = new ByRangeSpecification<>(ranges);
        return where(byExample).and(byRanges);
    }
}
