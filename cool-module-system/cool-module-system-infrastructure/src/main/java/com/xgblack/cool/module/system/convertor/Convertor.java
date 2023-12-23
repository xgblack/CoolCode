package com.xgblack.cool.module.system.convertor;

import java.util.List;

/**
 * @author xg BLACK
 * @date 2023/12/23 19:28
 */

public interface Convertor<C, E, D> {
    E toEntity(C c);

    D toDataObject(E e);

    D toDataObj(C c);

    E convertEntity(D d);

    List<E> convertEntityList(List<D> list);

    C convertClientObj(D d);

    List<C> convertClientObjList(List<D> list);

    C convertClientObject(E e);

    List<C> convertClientObjectList(List<E> list);
}
