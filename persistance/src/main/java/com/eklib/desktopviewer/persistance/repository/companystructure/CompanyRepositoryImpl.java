package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.companystructure.DepartmentEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepositoryImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 10.11.2014.
 */
@Repository
@Transactional
public class CompanyRepositoryImpl extends BasePagingAndSortingRepositoryImpl<CompanyEntity, Long> implements CompanyRepository {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public CompanyEntity getCompanyByName(String name) {
        Criteria criteria = getSession().createCriteria(CompanyEntity.class);
        criteria.add(Restrictions.eq("name", name));
        return (CompanyEntity)criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CompanyEntity> findByUser(String client) {
        Criteria criteria = getSession().createCriteria(CompanyEntity.class);
        criteria.createAlias("owner", "ow", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("ow.login", client));
        return criteria.list();
    }

    @Override
    public void delete(CompanyEntity entity) {
        super.delete(entity);
    }

    @Override
    public boolean changeStatus(CompanyEntity company, StatusEnum newStatus) {
        if(!company.getStatus().equals(newStatus)){
            if(newStatus.equals(StatusEnum.OPEN)){
                company.setStatus(newStatus);
                update(company);
                return true;
            } else {
                for(DepartmentEntity department :company.getDepartments()){
                    departmentRepository.changeStatus(department, newStatus);
                }
                company.setStatus(newStatus);
                update(company);
                return true;
            }
        }
        return false;
    }
}
