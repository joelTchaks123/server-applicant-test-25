package com.freenow.domainvalue.search;

import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.CarDO_;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainobject.DriverDO_;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.*;

@Slf4j

public class SearchSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -9153865343320750644L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);


        for (FilterRequest filter : this.request.getFilters()) {
            Optional<String> secondFilterOptional = Optional.ofNullable(filter.getSecondFilter());

            if(secondFilterOptional.isPresent() && secondFilterOptional.get().equalsIgnoreCase("carDO")){
                Join<Object, Object> carDOJoinEntity = root.join(DriverDO_.CAR_DO);

                predicate = filter.getSecondOperator().build(carDOJoinEntity, cb, filter, predicate);
            }
            else{
                log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
                predicate = filter.getOperator().build(root, cb, filter, predicate);
            }

        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }

    public SearchSpecification(SearchRequest request) {
        this.request = request;
    }

}
