package com.concordia.service.impl;

import com.concordia.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    @Autowired
    protected abstract JpaRepository getJpaRepository();

    /**
     * 保存单个实体
     *
     * @param entity
     * @return
     */
    @Override
    public <S extends T> S save(S entity) {
        return (S)getJpaRepository().save(entity);
    }

    /**
     * 保存所有实体
     *
     * @param entitys
     * @return
     */
    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entitys) {
        return getJpaRepository().saveAll(entitys);
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    @Override
    public Optional<T> findById(ID id) {
        return getJpaRepository().findById(id);
    }

    /**
     * 查询当前id的实体是否存在
     *
     * @param id
     * @return
     */
    @Override
    public boolean existsById(ID id) {
        return getJpaRepository().existsById(id);
    }

    /**
     * 获取所有实体
     *
     * @return
     */
    @Override
    public Iterable<T> findAll() {
        return getJpaRepository().findAll();
    }

    /**
     * 根据ids，获取所有实体
     *
     * @param ids
     * @return
     */
    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return getJpaRepository().findAllById(ids);
    }

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return getJpaRepository().findAll(pageable);
    }

    /**
     * @return 获得总数据条数
     */
    @Override
    public Long count() {
        return getJpaRepository().count();
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void deleteById(ID id) {
        getJpaRepository().deleteById(id);
    }

    /**
     * 根据entity删除
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        getJpaRepository().delete(entity);
    }

    /**
     * 根据entities删除所有
     *
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        getJpaRepository().deleteAll(entities);
    }
}
