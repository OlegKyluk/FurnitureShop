package com.furniturestore.specification;

import com.furniturestore.entity.Furniture;
import com.furniturestore.dto.filter.FurnitureFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FurnitureSpecification implements Specification<Furniture> {

    private final FurnitureFilter filter;

    private static final Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

    private final List<Predicate> predicates = new ArrayList<>();


    public FurnitureSpecification(FurnitureFilter filter) {
        this.filter = filter;
        if (REG.matcher(filter.getMax()).matches()) {
            filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
        }
        if (REG.matcher(filter.getMin()).matches()) {
            filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
        }

    }

    private void findByPrice(Root<Furniture> root,CriteriaQuery<?>query,CriteriaBuilder cb){
        if (filter.getMaxValue() != null){
            predicates.add(cb.le(root.get("price"),filter.getMaxValue()));
        }
        if (filter.getMinValue() != null){
            predicates.add(cb.ge(root.get("price"),filter.getMinValue()));
        }

    }

    private void findByColor(Root<Furniture> root, CriteriaQuery<?> query) {
        if (!filter.getColorId().isEmpty()) {
            predicates.add(root.get("color").in(filter.getColorId()));
        }
    }

    private void findByDenomination(Root<Furniture> root, CriteriaQuery<?> query) {
        if (!filter.getDenominationId().isEmpty()) {
            predicates.add(root.get("denomination").in(filter.getDenominationId()));
        }
    }

    private void findByTypeFurniture(Root<Furniture> root,CriteriaQuery<?> query){
        if (!filter.getTypeFurnitureId().isEmpty()){
            predicates.add(root.get("typeFurniture").in(filter.getTypeFurnitureId()));
        }
    }
    private void findByCollectionName(Root<Furniture> root,CriteriaQuery<?> query){
        if (!filter.getCollectionNameId().isEmpty()){
            predicates.add(root.get("collectionName").in(filter.getCollectionNameId()));
        }
    }

    private void fetch(Root<Furniture> root, CriteriaQuery<?> query) {
        if (!query.getResultType().equals(Long.class)) {
            query.distinct(true);
            root.fetch("color");
            root.fetch("denomination");
            root.fetch("typeFurniture");
            root.fetch("collectionName");
        }
    }

    @Override
    public Predicate toPredicate(Root<Furniture> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        fetch(root, query);
        findByPrice(root, query, cb);
        findByColor(root, query);
        findByDenomination(root, query);
        findByTypeFurniture(root, query);
        findByCollectionName(root, query);
        if (predicates.isEmpty())
            return null;
        Predicate[] array = new Predicate[predicates.size()];
        array = predicates.toArray(array);
        return cb.and(array);
    }

}
