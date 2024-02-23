package com.xgblack.cool.framework.common.convertor;

import com.mybatisflex.core.paginate.Page;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;

import java.util.List;

/**
 * 公共对象转换接口
 *
 * <p>自下而上 convertXXXXX  自上而下 toXXXXX
 * <p>CO和DO直接转换使用ClientObj,CO和Entity使用简写CO
 * <p>DTO往下层对象转换需要在实现类自己定义
 *
 * <p>C 客户端对象CO, E 领域实体Entity , D 数据库对象DO
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface Convertor<C, E, D> {
    /**
     * clientObject转为领域实体
     * CO -> Domain Entity
     * @param clientObject
     * @return
     */
    E toEntity(C clientObject);

    /**
     * 领域实体 转 DO
     * @param e
     * @return
     */
    D toDataObject(E e);

    /**
     * clientObject转为DO
     * @param c
     * @return
     */
    D toDataObj(C c);

    /**
     * DO 转 领域实体
     * @param d
     * @return
     */
    E convertDO2Entity(D d);

    /**
     * 数据库对象list 转 领域实体list
     * @param list
     * @return
     */
    List<E> convertDO2EntityList(List<D> list);

    /**
     * DO 分页 转 领域实体 分页
     * @param page
     * @return
     */
    PageResult<E> convertDO2EntityPageResult(PageResult<D> page);

    /**
     * DO 分页(数据库分页对象) 转 领域实体 分页
     * @param page
     * @return
     */
    PageResult<E> convertDO2EntityPage(Page<D> page);

    /**
     * DO 转 CO
     * @param d
     * @return
     */
    C convertDO2ClientObj(D d);

    /**
     * DO list 转 CO list
     * @param list
     * @return
     */
    List<C> convertDO2ClientObjList(List<D> list);

    /**
     * DO 分页 转 CO 分页
     * @param page
     * @return
     */
    PageResult<C> convertDO2ClientObjPageResult(PageResult<D> page);

    /**
     * 领域实体 转 CO
     * @param e
     * @return
     */
    C convertEntity2CO(E e);

    /**
     * 领域实体 list 转 CO list
     * @param list
     * @return
     */
    List<C> convertEntity2COList(List<E> list);

    /**
     * 领域实体 分页 转 CO 分页
     * @param page
     * @return
     */
    PageResult<C> convertEntity2COPageResult(PageResult<E> page);

    //toEntity
}
