package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.Company;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maxim on 10.11.2014.
 */
@Repository
@Transactional
public class CompanyRepositoryImpl extends BasePagingAndSortingRepositoryImpl<Company, Long> implements CompanyRepository {

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = getSession().createCriteria(Company.class);
        criteria.add(Restrictions.eq("name", name));
        return (Company)criteria.uniqueResult();
    }
}
