package com.pinyougoou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

/**
 * 品牌接口
 *
 */
public interface BrandService {

    //查询所有品牌
    public List<TbBrand> findAll();

    /**
     * 查询分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 增加 品牌数据
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 修改    品牌列表
     * @param brand
     */
    public void update(TbBrand brand);

    /**
     * 根据id获取实体
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 品牌条件查询 -----查询出结果很多需要用分页进行展示
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);



}
