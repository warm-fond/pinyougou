package com.pinyougou.manager.controller;


/*
*
* 品牌 controller
* */

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougoou.sellergoods.service.BrandService;
import com.pinyougou.pojo.TbBrand;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController           //(@RestController=@Controller+@RequestBody)
@RequestMapping("/brand")   //@RequestMapping 地址映射
public class BrandController {

    @Reference      //选择 dubbo
    private BrandService brandService;

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<TbBrand> findAll(){


        return brandService.findAll();
    }

    /**
     * 返回全部列表——分页
     * @param page
     * @param rows
     * @return
     */

    @RequestMapping("/findPage.do")
    public PageResult findPage(int page,int rows){

        return brandService.findPage(page,rows );
    }

    /**
     * 增加 品牌
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody TbBrand brand){
        try {
            brandService.add(brand);
            return new Result(true,"增加成功" );
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"增加失败" );
        }

    }

    /**
     *  修改
     * @param brand
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败" );
        }
    }

    /**
     * 获取实体
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/delete.do")
    public Result delete(Long[] ids ){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败" );
        }
    }

    /**
     * 品牌条件查询+ 分页
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(@RequestBody TbBrand brand,int page,int rows){

        return brandService.findPage(brand,page ,rows );
    }




}
