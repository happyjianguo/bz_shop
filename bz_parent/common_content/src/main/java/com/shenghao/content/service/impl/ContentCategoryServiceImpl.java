package com.shenghao.content.service.impl;

import com.shenghao.content.service.ContentCategoryService;
import com.shenghao.mapper.TbContentCategoryMapper;
import com.shenghao.pojo.TbContentCategory;
import com.shenghao.pojo.TbContentCategoryExample;
import com.shenghao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容分类业务层
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    /**
     * 根据父节点id查询子节点
     * @param parentId
     * @return
     */
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        return list;
    }

    /**
     * 添加内容分类节点
     * @param tbContentCategory
     * @return
     */
    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        //补齐数据
        Date date = new Date();
        tbContentCategory.setUpdated(date);
        tbContentCategory.setCreated(date);
        tbContentCategory.setIsParent(false);//新添加的节点永远是子节点
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);

        //插入数据
        Integer result = this.tbContentCategoryMapper.insert(tbContentCategory);

        //判断当前插入节点的父结点是否是叶子节点，是则改为是父结点
        TbContentCategory parent = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if(!parent.getIsParent()){
            parent.setIsParent(true);
            parent.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(parent);
        }
        return result;
    }

    @Override
    public Integer deleteContentCategoryById(Long categoryId) {
        //查询当前节点
        TbContentCategory currentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);

        //删除当前节点的子节点
        Integer status = this.deleteNode(currentCategory);

        //查询当前节点的父结点
        TbContentCategory parentCatrgory = this.tbContentCategoryMapper.selectByPrimaryKey(currentCategory.getParentId());

        //查看当前节点是否有兄弟节点，决定是否修改父结点的状态
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentCatrgory.getId());

        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        if(list.size() == 0){
            parentCatrgory.setIsParent(false);
            parentCatrgory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(parentCatrgory);
        }
        return 200;
    }

    /**
     * 删除当前节点与子节点
     * @param currentCategory
     * @return
     */
    private Integer deleteNode(TbContentCategory currentCategory) {
        if(currentCategory.getIsParent()){
            //是父结点
            //查询当前节点所有的子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(currentCategory.getId());
            List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
            for(TbContentCategory children : list){
                this.deleteNode(children);//递归删除子节点，递归结束后把自己删除
                this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
            }
        }else{
            //是叶子节点
            this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
        }
        return 200;
    }

    /**
     * 修改当前内容分类
     * @param tbContentCategory
     * @return
     */
    @Override
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        return this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }
}
