package com.freenow.domainvalue.search;

import com.freenow.domainobject.CarDO;
//import com.freenow.domainobject.CarDO_;
import com.freenow.domainobject.DriverDO;
//import com.freenow.domainobject.DriverDO_;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -9153865343320750644L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaSearchGeneric");
//
//        EntityManager em = emf.createEntityManager();
//
//        query = cb.createQuery(DriverDO.class);
//        Metamodel m = em.getMetamodel();
//        EntityType<DriverDO> driverMetaModel = m.entity(DriverDO.class);
//
//        Root<DriverDO> driverRoot = query.from(DriverDO.class);
//
//        Join<DriverDO, CarDO> carDOJoinEntity = driverRoot.join(driverMetaModel.getSet("carDO", CarDO.class));


//        Join<Object, Object> carDOJoinEntity = root.join(DriverDO_.CAR_DO);
//        query.where(
//                cb.equal(
//                        carDOJoinEntity.get(CarDO_.LICENSE_PLATE),
//                        "High-Performance Java Persistence"
//                )
//        );

//        CriteriaQuery<DriverDO> cq = cb.createQuery(DriverDO.class);
//        Metamodel m = em.getMetamodel();
//        EntityType<DriverDO> driverMetaModel = m.entity(DriverDO.class);
//
//        Root<DriverDO> driverRoot = cq.from(DriverDO.class);
//        Join<DriverDO, CarDO> carDOJoinEntity = driverRoot.join(driverMetaModel.getSet("carDO", CarDO.class));

        for (FilterRequest filter : this.request.getFilters()) {
//            if(filter .getSecondFilter().toString() == "carDO"){
//                Join<Object, Object> carDOJoinEntity = root.join(DriverDO_.CAR_DO);
//
//                predicate = filter.getSecondOperator().build(carDOJoinEntity, cb, filter, predicate);
//            }
//            else{
                log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
                predicate = filter.getOperator().build(root, cb, filter, predicate);
//            }

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

}
