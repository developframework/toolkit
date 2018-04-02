package com.github.developframework.toolkit.persistence.jpa;

import com.github.developframework.toolkit.persistence.component.Range;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuzhenhao
 */
public class ByRangeSpecification<T, Y extends Comparable<? super Y>> implements Specification<T> {

    private final List<Range<Y>> ranges;

    public ByRangeSpecification(List<Range<Y>> ranges) {
        this.ranges = ranges;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        for (Range<Y> range : ranges) {
            predicates.add(buildRangePredicate(range, root, cb));
        }
        return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate buildRangePredicate(Range<Y> range, Root<T> root, CriteriaBuilder builder) {
        if (range.isBetween()) {
            return builder.between(root.get(range.getFieldName()), range.getFrom(), range.getTo());
        } else if (range.isOnlyFrom()) {
            return builder.greaterThanOrEqualTo(root.get(range.getFieldName()), range.getFrom());
        } else if (range.isOnlyTo()) {
            return builder.lessThanOrEqualTo(root.get(range.getFieldName()), range.getTo());
        }
        return null;
    }
}
