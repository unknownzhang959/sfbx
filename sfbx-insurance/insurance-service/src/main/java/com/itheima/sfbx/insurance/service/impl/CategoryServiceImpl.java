package com.itheima.sfbx.insurance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.itheima.sfbx.file.feign.FileBusinessFeign;
import com.itheima.sfbx.framework.commons.constant.basic.SuperConstant;
import com.itheima.sfbx.framework.commons.constant.category.CategoryConstant;
import com.itheima.sfbx.framework.commons.constant.insure.InsureConstant;
import com.itheima.sfbx.framework.commons.dto.basic.TreeItemVO;
import com.itheima.sfbx.framework.commons.dto.basic.TreeVO;
import com.itheima.sfbx.framework.commons.exception.ProjectException;
import com.itheima.sfbx.framework.commons.utils.BeanConv;
import com.itheima.sfbx.framework.commons.utils.EmptyUtil;
import com.itheima.sfbx.framework.commons.utils.ExceptionsUtil;
import com.itheima.sfbx.framework.commons.utils.NoProcessing;
import com.itheima.sfbx.insurance.constant.CategoryCacheConstant;
import com.itheima.sfbx.insurance.dto.CategoryCoefficentVO;
import com.itheima.sfbx.insurance.dto.CategoryConditionVO;
import com.itheima.sfbx.insurance.dto.CategorySafeguardVO;
import com.itheima.sfbx.insurance.dto.CategoryVO;
import com.itheima.sfbx.insurance.enums.CategoryEnum;
import com.itheima.sfbx.insurance.mapper.CategoryMapper;
import com.itheima.sfbx.insurance.pojo.Category;
import com.itheima.sfbx.insurance.pojo.CategoryCoefficent;
import com.itheima.sfbx.insurance.pojo.CategoryCondition;
import com.itheima.sfbx.insurance.pojo.CategorySafeguard;
import com.itheima.sfbx.insurance.service.ICategoryCoefficentService;
import com.itheima.sfbx.insurance.service.ICategoryConditionService;
import com.itheima.sfbx.insurance.service.ICategorySafeguardService;
import com.itheima.sfbx.insurance.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description：保险分类服务实现类
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    ICategoryConditionService categoryConditionService;

    @Autowired
    ICategoryCoefficentService categoryCoefficentService;

    @Autowired
    ICategorySafeguardService categorySafeguardService;

    /***
     * @description 保险分类多条件组合
     * @param categoryVO 保险分类
     * @return QueryWrapper查询条件
     */
    private QueryWrapper<Category> queryWrapper(CategoryVO categoryVO) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        //父分类编号查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getParentCategoryNo())) {
            queryWrapper.lambda().eq(Category::getParentCategoryNo, categoryVO.getParentCategoryNo());
        }
        //分类编号查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getCategoryNo())) {
            queryWrapper.lambda().eq(Category::getCategoryNo, categoryVO.getCategoryNo());
        }
        //分类名称查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getCategoryName())) {
            queryWrapper.lambda().eq(Category::getCategoryName, categoryVO.getCategoryName());
        }
        //图标查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getIcon())) {
            queryWrapper.lambda().eq(Category::getIcon, categoryVO.getIcon());
        }
        //是否叶子节点（0是 否1）查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getLeafNode())) {
            queryWrapper.lambda().eq(Category::getLeafNode, categoryVO.getLeafNode());
        }
        //是否显示在首页（0是 否1）查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getShowIndex())) {
            queryWrapper.lambda().eq(Category::getShowIndex, categoryVO.getShowIndex());
        }
        //校验规则：0医疗 1重疾 2意外 3养老 4储蓄 5旅游 6宠物 7定寿
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getCheckRule())) {
            queryWrapper.lambda().eq(Category::getCheckRule, categoryVO.getCheckRule());
        }
        //分类类型：0推荐分类  1产品分类
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getCategoryType())) {
            queryWrapper.lambda().eq(Category::getCategoryType, categoryVO.getCategoryType());
        }
        //排序查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getSortNo())) {
            queryWrapper.lambda().eq(Category::getSortNo, categoryVO.getSortNo());
        }
        //分类补充说明查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getRemake())) {
            queryWrapper.lambda().eq(Category::getRemake, categoryVO.getRemake());
        }
        //校验规则：0医疗 1重疾 2意外 3养老 4储蓄 5旅游 6宠物 7定寿
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getCheckRule())) {
            queryWrapper.lambda().eq(Category::getCheckRule, categoryVO.getCheckRule());
        }
        //状态查询
        if (!EmptyUtil.isNullOrEmpty(categoryVO.getDataState())) {
            queryWrapper.lambda().eq(Category::getDataState, categoryVO.getDataState());
        }
        //按排序字段升序
        queryWrapper.lambda().orderByAsc(Category::getSortNo);
        return queryWrapper;
    }

//    @Override
//    @Cacheable(value = CategoryCacheConstant.PAGE,key ="#pageNum+'-'+#pageSize+'-'+#categoryVO.hashCode()")
//    public Page<CategoryVO> findPage(CategoryVO categoryVO, int pageNum, int pageSize) {
//        try {
//            //构建分页对象
//            Page<Category> CategoryPage = new Page<>(pageNum,pageSize);
//            //构建查询条件
//            QueryWrapper<Category> queryWrapper = queryWrapper(categoryVO);
//            //执行分页查询
//            Page<CategoryVO> categoryVOPage = BeanConv
//                .toPage(page(CategoryPage, queryWrapper), CategoryVO.class);
//            if (!EmptyUtil.isNullOrEmpty(categoryVOPage.getRecords())){
//                //构建分类编号Set
//                Set<String> categoryNoSet = categoryVOPage.getRecords().stream().map(CategoryVO::getCategoryNo).collect(Collectors.toSet());
//                //查询分类筛选项
//                List<CategoryConditionVO> categoryConditionVOs = categoryConditionService.findListInCategoryNo(categoryNoSet);
//                //查询分类保障项
//                List<CategorySafeguardVO> categorySafeguardVOs = categorySafeguardService.findListInCategoryNo(categoryNoSet);
//                //查询分类系数项
//                List<CategoryCoefficentVO> categoryCoefficentVOs = categoryCoefficentService.findListInCategoryNo(categoryNoSet);
//                //组合子属性
//                categoryVOPage.getRecords().forEach(n->{
//                    List<CategoryConditionVO> categoryConditionVOsHandler = Lists.newArrayList();
//                    List<CategorySafeguardVO> categorySafeguardVOsHandler = Lists.newArrayList();
//                    List<CategoryCoefficentVO> categoryCoefficentVOsHandler = Lists.newArrayList();
//                    categoryConditionVOs.forEach(y->{
//                        if (n.getCategoryNo().equals(y.getCategoryNo())) {
//                            categoryConditionVOsHandler.add(y);
//                        }
//                    });
//                    categorySafeguardVOs.forEach(z->{
//                        if (n.getCategoryNo().equals(z.getCategoryNo())) {
//                            categorySafeguardVOsHandler.add(z);
//                        }
//                    });
//                    categoryCoefficentVOs.forEach(e->{
//                        if (n.getCategoryNo().equals(e.getCategoryNo())) {
//                            categoryCoefficentVOsHandler.add(e);
//                        }
//                    });
//                    n.setCategoryConditionVOs(categoryConditionVOsHandler);
//                    n.setCategorySafeguardVOs(categorySafeguardVOsHandler);
//                    n.setCategoryCoefficentVOs(categoryCoefficentVOsHandler);
//                });
//            }
//            //返回结果
//            return categoryVOPage;
//        }catch (Exception e){
//            log.error("保险分类分页查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
//            throw new ProjectException(CategoryEnum.PAGE_FAIL);
//        }
//    }

    @Override
    public Page<CategoryVO> findPage(CategoryVO categoryVO, int pageNum, int pageSize) {
        //1、创建分类查询条件对象；
        QueryWrapper<Category> queryWrapper = queryWrapper(categoryVO);
        //2、分页查询分类列表；page中获取分类列表
        Page<Category> page = new Page<>(pageNum, pageSize);
        Page<Category> pageData = page(page, queryWrapper);
        if (EmptyUtil.isNullOrEmpty(pageData.getRecords())) {
            return null;
        }
        Page<CategoryVO> pagevo = BeanConv.toPage(pageData, CategoryVO.class);
        //2.1、获取当前的分类列表中的所有分类id；放置到一个集合set
        Set<String> categoryIds = pagevo.getRecords().stream().map(CategoryVO::getCategoryNo).collect(Collectors.toSet());
        //处理分类保障项
        //①、根据分类no集合查询这些分类对应的分类保障项 ---》1次数据库查询
        List<CategorySafeguardVO> categorySafeguardVOs = categorySafeguardService.findListInCategoryNo(categoryIds);
        //②、将上述的分类保障项根据分类no进行分组放置到一个map中；map<分类no，分类保障项vo列表>
        Map<String, List<CategorySafeguardVO>> categorySafeguardVOsMap = categorySafeguardVOs.stream().collect(Collectors.groupingBy(CategorySafeguardVO::getCategoryNo));
        //③、遍历分类列表；逐个根据分类no从上面的map中获取到 分类保障项vo列表 设置到分类中
        pagevo.getRecords().forEach(n -> {
            n.setCategorySafeguardVOs(categorySafeguardVOsMap.get(n.getCategoryNo()));
        });

        //处理分类系数项
        //①、根据分类no集合查询这些分类对应的分类系数项 ---》1次数据库查询
        List<CategoryCoefficentVO> categoryCoefficentVOs = categoryCoefficentService.findListInCategoryNo(categoryIds);
        //②、将上述的分类系数项根据分类no进行分组放置到一个map中；map<分类no，分类保障项vo列表>
        Map<String, List<CategoryCoefficentVO>> categoryCoefficentVOsMap = categoryCoefficentVOs.stream().collect(Collectors.groupingBy(CategoryCoefficentVO::getCategoryNo));
        //③、遍历分类列表；逐个根据分类no从上面的map中获取到 分类系数项vo列表 设置到分类中
        pagevo.getRecords().forEach(n -> {
            n.setCategoryCoefficentVOs(categoryCoefficentVOsMap.get(n.getCategoryNo()));
        });

        //处理分类筛选项
        //①、根据分类no集合查询这些分类对应的分类筛选项 ---》1次数据库查询
        List<CategoryConditionVO> categoryConditionVOs = categoryConditionService.findListInCategoryNo(categoryIds);
        //②、将上述的分类筛选项根据分类no进行分组放置到一个map中；map<分类no，分类筛选项vo列表>
        Map<String, List<CategoryConditionVO>> categoryConditionVOsMap = categoryConditionVOs.stream().collect(Collectors.groupingBy(CategoryConditionVO::getCategoryNo));
        //③、遍历分类列表；逐个根据分类no从上面的map中获取到 分类筛选项vo列表 设置到分类中
        pagevo.getRecords().forEach(n -> {
            n.setCategoryConditionVOs(categoryConditionVOsMap.get(n.getCategoryNo()));
        });

        //3、返回分页对象Page
        return pagevo;
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = CategoryCacheConstant.PAGE, allEntries = true),
            @CacheEvict(value = CategoryCacheConstant.LIST, allEntries = true)},
            put = {@CachePut(value = CategoryCacheConstant.BASIC, key = "#result.id")})
    public CategoryVO save(CategoryVO categoryVO) {
        try {
            //转换CategoryVO为Category
            Category category = BeanConv.toBean(categoryVO, Category.class);
            category.setCategoryNo(this.createCategoryNo(category.getParentCategoryNo()));
            boolean flag = save(category);
            if (!flag) {
                throw new RuntimeException("保存保险分类失败");
            }
            //转换返回对象CategoryVO
            return BeanConv.toBean(category, CategoryVO.class);
        } catch (Exception e) {
            log.error("保存保险分类异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(CategoryEnum.SAVE_FAIL);
        }
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = CategoryCacheConstant.PAGE, allEntries = true),
            @CacheEvict(value = CategoryCacheConstant.LIST, allEntries = true),
            @CacheEvict(value = CategoryCacheConstant.BASIC, key = "#categoryVO.id")})
    public Boolean update(CategoryVO categoryVO) {
        try {
            //- 更新分类数据
            Category category = BeanConv.toBean(categoryVO, Category.class);
            boolean flag = updateById(category);
            //- 根据分类编号删除保障项
            categorySafeguardService.deleteByCategoryNo(category.getCategoryNo());
            //2.2、保存分类保障项：保存每个分类保障项需要设置分类编号
            List<CategorySafeguard> categorySafeguardList = categoryVO.getCategorySafeguardVOs().stream().map(tmpCategorySafeguardVO -> {
                tmpCategorySafeguardVO.setCategoryNo(category.getCategoryNo());
                return BeanConv.toBean(tmpCategorySafeguardVO, CategorySafeguard.class);
            }).collect(Collectors.toList());
            categorySafeguardService.saveBatch(categorySafeguardList);

            //- 根据分类编号删除系数项
            categoryCoefficentService.deleteByCategoryNo(category.getCategoryNo());
            //- 根据前端传递的系数项列表（没有分类编号），保存到对应的分类系数项表
            List<CategoryCoefficent> categoryCoefficentList = categoryVO.getCategoryCoefficentVOs().stream().map(tmpCategoryCoefficentVO -> {
                tmpCategoryCoefficentVO.setCategoryNo(category.getCategoryNo());
                return BeanConv.toBean(tmpCategoryCoefficentVO, CategoryCoefficent.class);
            }).collect(Collectors.toList());
            categoryCoefficentService.saveBatch(categoryCoefficentList);

            //- 根据分类编号删除筛选项
            categoryConditionService.deleteByCategoryNo(category.getCategoryNo());
            //- 根据前端传递的筛选项列表（没有分类编号），保存到对应的分类筛选项表
            List<CategoryCondition> categoryConditionList = categoryVO.getCategoryConditionVOs().stream().map(tmpCategoryConditionVO -> {
                tmpCategoryConditionVO.setCategoryNo(category.getCategoryNo());
                return BeanConv.toBean(tmpCategoryConditionVO, CategoryCondition.class);
            }).collect(Collectors.toList());
            categoryConditionService.saveBatch(categoryConditionList);
            return flag;
        } catch (Exception e) {
            log.error("修改保险分类异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(CategoryEnum.UPDATE_FAIL);
        }
    }

    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = CategoryCacheConstant.PAGE, allEntries = true),
            @CacheEvict(value = CategoryCacheConstant.LIST, allEntries = true),
            @CacheEvict(value = CategoryCacheConstant.BASIC, allEntries = true)})
    public Boolean delete(String[] checkedIds) {
        try {
            List<Long> idsLong = Arrays.asList(checkedIds).stream().map(Long::new).collect(Collectors.toList());
            List<Category> categories = listByIds(idsLong);
            //删除关联的分类保障项、分类筛选项、分类系数项信息
            if (EmptyUtil.isNullOrEmpty(categories)) {
                List<String> categoryNos = categories.stream().map(Category::getCategoryNo).collect(Collectors.toList());
                categoryConditionService.deleteByCategoryNos(categoryNos);
                categoryCoefficentService.deleteByCategoryNos(categoryNos);
                categorySafeguardService.deleteByCategoryNos(categoryNos);
            }
            boolean flag = removeByIds(idsLong);
            if (!flag) {
                throw new RuntimeException("删除保险分类失败");
            }
            return flag;
        } catch (Exception e) {
            log.error("删除保险分类异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(CategoryEnum.DEL_FAIL);
        }
    }

    @Override
    @Cacheable(value = CategoryCacheConstant.LIST, key = "#categoryVO.hashCode()")
    public List<CategoryVO> findList(CategoryVO categoryVO) {
        try {
            //构建查询条件
            QueryWrapper<Category> queryWrapper = queryWrapper(categoryVO);
            List<Category> categorys = list(queryWrapper);
            List<Category> categorysHandler = Lists.newArrayList();
            //显示指定节点层
            if (!EmptyUtil.isNullOrEmpty(categoryVO.getNodeFloors())) {
                categorys.forEach(n -> {
                    if (categoryVO.getNodeFloors().contains(Long.valueOf(NoProcessing.processString(n.getCategoryNo()).length() / 3 - 1))) {
                        categorysHandler.add(n);
                    }
                });
            } else {
                categorysHandler.addAll(categorys);
            }
            //执行列表查询
            List<CategoryVO> categoryVOs = BeanConv.toBeanList(categorysHandler, CategoryVO.class);
            if (!EmptyUtil.isNullOrEmpty(categoryVOs)) {
                Set<String> categoryNoSet = categoryVOs.stream().map(CategoryVO::getCategoryNo).collect(Collectors.toSet());
                //查询分类筛选项
                List<CategoryConditionVO> categoryConditionVOs = categoryConditionService.findListInCategoryNo(categoryNoSet);
                //查询分类保障项
                List<CategorySafeguardVO> categorySafeguardVOs = categorySafeguardService.findListInCategoryNo(categoryNoSet);
                //查询分类系数项
                List<CategoryCoefficentVO> categoryCoefficentVOs = categoryCoefficentService.findListInCategoryNo(categoryNoSet);
                //组合表单
                categoryVOs.forEach(n -> {
                    List<CategoryConditionVO> categoryConditionVOsHandler = Lists.newArrayList();
                    List<CategorySafeguardVO> categorySafeguardVOsHandler = Lists.newArrayList();
                    List<CategoryCoefficentVO> categoryCoefficentVOsHandler = Lists.newArrayList();
                    categoryConditionVOs.forEach(y -> {
                        if (n.getCategoryNo().equals(y.getCategoryNo())) {
                            categoryConditionVOsHandler.add(y);
                        }
                    });
                    categorySafeguardVOs.forEach(z -> {
                        if (n.getCategoryNo().equals(z.getCategoryNo())) {
                            categorySafeguardVOsHandler.add(z);
                        }
                    });
                    categoryCoefficentVOs.forEach(e -> {
                        if (n.getCategoryNo().equals(e.getCategoryNo())) {
                            categoryCoefficentVOsHandler.add(e);
                        }
                    });
                    n.setCategoryConditionVOs(categoryConditionVOsHandler);
                    n.setCategorySafeguardVOs(categorySafeguardVOsHandler);
                    n.setCategoryCoefficentVOs(categoryCoefficentVOsHandler);
                });
            }
            return categoryVOs;
        } catch (Exception e) {
            log.error("保险分类列表查询异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(CategoryEnum.LIST_FAIL);
        }
    }


    @Override
    @Cacheable(value = CategoryCacheConstant.TREE, key = "#parentCategoryNo+'-'+#categoryType+'-'+#checkedCategoryNos")
    public TreeVO categoryTreeVO(String parentCategoryNo, String categoryType, String[] checkedCategoryNos) {
        try {
            //起始节点确定
            String parentCategoryNoHandler = EmptyUtil.isNullOrEmpty(parentCategoryNo) ?
                    SuperConstant.ROOT_PARENT_ID : parentCategoryNo;
            //起始节点子节点
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(Category::getDataState, SuperConstant.DATA_STATE_0)
                    .likeRight(Category::getParentCategoryNo, NoProcessing.processString(parentCategoryNoHandler))
                    .orderByAsc(Category::getCategoryNo);
            if (!EmptyUtil.isNullOrEmpty(categoryType)) {
                queryWrapper.lambda().eq(Category::getCategoryType, categoryType);
            }
            List<Category> categoryList = list(queryWrapper);
            if (EmptyUtil.isNullOrEmpty(categoryList)) {
                throw new RuntimeException("分类信息未定义！");
            }
            //构建展开节点
            List<String> expandedIds = new ArrayList<>();
            //构建选中节点
            List<String> checkedCategoryNosHandler = !EmptyUtil.isNullOrEmpty(checkedCategoryNos) ?
                    Lists.newArrayList(checkedCategoryNos) : null;
            //构建List<TreeItemVO>对象
            List<TreeItemVO> treeItemVOs = categoryList.stream()
                    .map(category -> {
                        //构建当前节点
                        TreeItemVO treeItemVO = TreeItemVO.builder()
                                .id(category.getCategoryNo())
                                .parentId(category.getParentCategoryNo())
                                .label(category.getCategoryName())
                                .build();
                        //当前节点是否选择
                        if (!EmptyUtil.isNullOrEmpty(checkedCategoryNosHandler)
                                && checkedCategoryNosHandler.contains(category.getCategoryNo())) {
                            treeItemVO.setIsChecked(true);
                        } else {
                            treeItemVO.setIsChecked(false);
                        }
                        //当前节点展开处理
                        if (NoProcessing.processString(category.getCategoryNo()).length() / 3 == 3) {
                            expandedIds.add(category.getCategoryNo());
                        }
                        return treeItemVO;
                    }).collect(Collectors.toList());
            //数据进行分组（key-->parentId:val-->List<TreeItemVO>）
            Map<String, List<TreeItemVO>> parentParentIdMap = treeItemVOs.stream()
                    .collect(Collectors.groupingBy(TreeItemVO::getParentId));
            //遍历回填子节点
            treeItemVOs.forEach(treeItemVO -> {
                //根据父编号到map中查找是否包含子菜单，如果包含则设置为当前菜单的子菜单
                List<TreeItemVO> menuVos = parentParentIdMap.get(treeItemVO.getId());
                if (!EmptyUtil.isNullOrEmpty(menuVos)) {
                    treeItemVO.setChildren(menuVos);
                }
            });
            //构建treeItemVOResult
            List<TreeItemVO> treeItemVOResult = parentParentIdMap.get(parentCategoryNoHandler);
            //返回TreeVO
            return TreeVO.builder()
                    .items(treeItemVOResult)
                    .checkedIds(checkedCategoryNosHandler)
                    .expandedIds(expandedIds)
                    .build();
        } catch (Exception e) {
            log.error("查询分类表TREE异常：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(CategoryEnum.TREE_FAIL);
        }
    }

    @Override
    public String createCategoryNo(String parentCategoryNo) {
        CategoryVO categoryVO = CategoryVO.builder()
                .parentCategoryNo(parentCategoryNo)
                .build();
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Category::getParentCategoryNo, parentCategoryNo);
        List<Category> categoryList = list(queryWrapper);
        //无下属节点则创建下属节点
        if (EmptyUtil.isNullOrEmpty(categoryList)) {
            return NoProcessing.createNo(parentCategoryNo, false);
            //有下属节点则累加下属节点
        } else {
            Long categoryNo = categoryList.stream()
                    .map(category -> {
                        return Long.valueOf(category.getCategoryNo());
                    })
                    .max(Comparator.comparing(i -> i)).get();
            return NoProcessing.createNo(String.valueOf(categoryNo), true);
        }
    }

    /**
     * 人种榜险种榜分类
     *
     * @param type
     */
    @Override
    public List<CategoryVO> categoryCheckRule(String type) {
        //判断是否是险种榜
        if (InsureConstant.checkIsInsureType(type)) {
            List<CategoryVO> res = new ArrayList<>();
            //获取所有的险种榜
            List<String> allCheckRule = InsureConstant.getAllCheckRule();
            allCheckRule.forEach(e -> {
                CategoryVO categoryVO = CategoryVO.builder().categoryNo(e).categoryName(InsureConstant.getRuleNameById(e)).build();
                res.add(categoryVO);
            });
            return res;
        } else {
            //说明是人种榜
            List<String> categoryNames = Arrays.asList("成年人", "少儿", "老人");
            LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Category::getCategoryName, categoryNames);
            queryWrapper.eq(Category::getDataState, SuperConstant.DATA_STATE_0);
            List<CategoryVO> res = BeanConv.toBeanList(list(queryWrapper), CategoryVO.class);
            return res;
        }
    }
}
