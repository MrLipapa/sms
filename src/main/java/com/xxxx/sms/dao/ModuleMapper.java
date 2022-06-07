package com.xxxx.sms.dao;

import com.xxxx.sms.base.BaseMapper;
import com.xxxx.sms.model.TreeModule;
import com.xxxx.sms.vo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper extends BaseMapper {
    //查询所有模块资源id name pId   包装在model中
    public List<TreeModule> queryAllModules(Integer roleId);

    //查询所有模块信息  --- 资源管理模块使用
    public List<Module> queryModules();

    //在添加模块资源时使用
    Module queryModuleByGradeName(@Param("grade") Integer grade, @Param("moduleName") String moduleName);

    Module queryModuleByGradeUrl(@Param("grade") Integer grade, @Param("url") String url);

    Module queryModuleById(Integer parentId);

    Module queryModuleByOptValue(String optValue);
}