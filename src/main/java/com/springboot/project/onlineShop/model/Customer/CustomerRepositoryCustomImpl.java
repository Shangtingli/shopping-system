package com.springboot.project.onlineShop.model.Customer;

import com.springboot.project.onlineShop.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getCustomerByUserName(String userName) {
        User user = null;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        ParameterExpression<String> emailIdParameter = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(userRoot)
                .where(criteriaBuilder.equal(userRoot.get("emailId"), emailIdParameter));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        query.setParameter(emailIdParameter, userName);
        List<User> listOfUsers = query.getResultList();
        if (listOfUsers.size() > 0){
            return listOfUsers.get(0).getCustomer();
        }
        return null;
    }
}
