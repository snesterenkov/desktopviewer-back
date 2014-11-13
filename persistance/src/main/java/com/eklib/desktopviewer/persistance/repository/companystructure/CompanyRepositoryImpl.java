package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maxim on 10.11.2014.
 */
@Repository
@Transactional
public class CompanyRepositoryImpl extends BasePagingAndSortingRepositoryImpl<CompanyEntity, Long> implements CompanyRepository {

    @Override
    public CompanyEntity getCompanyByName(String name) {
        Criteria criteria = getSession().createCriteria(CompanyEntity.class);
        criteria.add(Restrictions.eq("name", name));
        return (CompanyEntity)criteria.uniqueResult();
    }
}
