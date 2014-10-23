package com.eklib.desktopviewer.persistance.repository;


import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Transactional
public abstract class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> implements BaseRepository<T, ID> {

    private Class<T> persistentClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource(name = "entityManagerFactory")
    protected EntityManagerFactory entityManagerFactory;

    public BaseRepositoryImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * GET session
     * @return session hibernate
     */
    public Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    /**
     * get classe persistante
     * @return classe persistante
     */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
}
