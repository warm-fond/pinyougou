package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougoou.sellergoods.service.BrandService;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.pinyougou.mapper.TbBrandMapper;

import java.util.List;
/*
*  需求：完成品牌管理的后端代码，在浏览器可查询品牌的数据（json格式）
* */
@Service        //选择dubbo的注解  al：远程调用
public class BrandServiceImpl implements BrandService {
   @Autowired     //这是本地调用，用@Autowired  所以不用@reference 引用注解
   private TbBrandMapper brandMapper;
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
        //null值，表示空参（无参），=selectByExample（）;

    }

    /**
     * 品牌分页
     * @param pageNum 当前页面
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        //pageNum,和 pageSize 是前台传入的参数：当前页面和每页记录数
            //PageHelper为MyBatis分页插件的对象
        PageHelper.startPage(pageNum,pageSize );

        //dao层查询数据库，返回类型强转为Page类型
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);

        return new PageResult(page.getTotal(),page.getResult() ) ;
        // page.getResult() 底层return this
    }

    // 增加 数据 品牌列表
    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    //修改
    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    //根据id获取实体
    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    //批量删除
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }

    }

    /**
     * 品牌条件查询 -----查询出结果很多需要用分页进行展示
     * @param brand     传入个品牌对象，通过对象获取name和firstChar
     * @param pageNum   当前页码
     * @param pageSize  每页记录数
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        //前台传入当前页码，及每页记录数
        PageHelper.startPage(pageNum,pageSize );

        TbBrandExample example = new TbBrandExample();

       TbBrandExample.Criteria criteria = example.createCriteria();

       if(brand!=null){
           if(brand.getName()!=null&&brand.getName().length()>0){
               criteria.andNameLike("%"+brand.getName()+"%");//模糊查询
           }
           if(brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
               criteria.andFirstCharEqualTo(brand.getFirstChar());//首字母匹配
           }

       }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);

        return new PageResult(page.getTotal(),page.getResult() );
    }
}
