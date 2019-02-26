package com.wbg.sums.service;

import com.wbg.sums.entity.Permissions;

import java.util.List;

public interface PermissionsMapper {
    /**
     * 根据id删除操作
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加操作
     *
     * @param Permissions
     * @return
     */
    int insert(Permissions Permissions);

    /**
     * 根据id查询操作
     *
     * @param id
     * @return
     */
    Permissions selectByPrimaryKey(Integer id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Permissions> selectAll();

    /**
     * 根据id全部修改操作
     *
     * @param Permissions
     * @return
     */
    int updateByPrimaryKey(Permissions Permissions);
}
