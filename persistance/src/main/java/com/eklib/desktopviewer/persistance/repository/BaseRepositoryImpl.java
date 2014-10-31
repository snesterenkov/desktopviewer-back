package com.eklib.desktopviewer.persistance.repository;


import com.eklib.desktopviewer.persistance.model.BaseEntity;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Transactional
public abstract class BaseRepositoryImpl<E extends BaseEntity, I extends Serializable> implements BaseRepository<E, I> {

    private Class<E> persistentClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource(name = "entityManagerFactory")
    protected EntityManagerFactory entityManagerFactory;

    public BaseRepositoryImpl() {
        this.persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
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
    public Class<E> getPersistentClass() {
        return persistentClass;
    }
}
