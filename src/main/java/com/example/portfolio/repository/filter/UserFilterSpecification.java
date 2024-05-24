package com.example.portfolio.repository.filter;

import com.example.portfolio.model.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserFilterSpecification implements Specification<UserEntity> {
    private final String nameFilter;
    private final String emailFilter;

    public UserFilterSpecification(String nameFilter, String emailFilter) {
        this.nameFilter = nameFilter;
        this.emailFilter = emailFilter;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate nameLike = null;
        if (StringUtils.isNotEmpty(nameFilter)) {
            nameLike = criteriaBuilder.like(root.get("name"), "%" + nameFilter + "%");
        }

        Predicate emailLike = null;
        if (StringUtils.isNotEmpty(emailFilter)) {
            emailLike = criteriaBuilder.like(root.get("email"), "%" + emailFilter + "%");
        }


        if (nameLike != null && emailLike != null) {
            return criteriaBuilder.or(nameLike, emailLike);
        }
        return nameLike != null ? nameLike : emailLike;
    }
}
