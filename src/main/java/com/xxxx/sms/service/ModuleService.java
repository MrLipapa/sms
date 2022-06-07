package com.xxxx.sms.service;

import com.xxxx.sms.base.BaseService;
import com.xxxx.sms.dao.ModuleMapper;
import com.xxxx.sms.dao.PermissionMapper;
import com.xxxx.sms.dao.RoleMapper;
import com.xxxx.sms.model.TreeModule;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleService extends BaseService {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    //查询所有模块资源id name pId   包装在model中
    public List<TreeModule> queryAllModules(Integer roleId) {
        //校验参数和目标角色是否存在
        AssertUtil.isTrue(roleId == null || roleMapper.selectByPrimaryKey(roleId) == null, "角色不存在");
        //查询当前角色拥有的权限
        List<Integer> mIds = permissionMapper.selectPermissionByRoleId(roleId);
        //查询所有的模块
        List<TreeModule> treeModules = moduleMapper.queryAllModules(roleId);
        //遍历所有模块，并标记当前角色拥有的模块
        for (TreeModule treeModule : treeModules) {
            //获取遍历对象的模块id
            Integer id = treeModule.getId();
            //判断当前角色拥有的模块权限mIds是否包含当前遍历对象的模块id
            if (mIds.contains(id)) { //当前方法判断某个数据是否存在于集合当中
                treeModule.setChecked(true);
                treeModule.setOpen(true);
            }
        }
        return treeModules;
    }

    //查询所有模块信息  --- 资源管理模块使用
    public Map<String, Object> queryModules() {
        Map<String, Object> map = new HashMap<>();
        List<Module> modules = moduleMapper.queryModules();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", modules);
        map.put("count", modules.size());
        return map;
    }

    /**
     * 添加资源模块
     *      参数校验
     */
    public void moduleAdd(Module module){
        //资源名称不为空且唯一
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"资源名称不存在");
        Module dbModule = moduleMapper.queryModuleByGradeName(module.getGrade(), module.getModuleName());
        AssertUtil.isTrue(dbModule != null,"模块名已存在");

        //层级不为空且必须为012
        AssertUtil.isTrue(module.getGrade() == null,"层级不能为空");
        AssertUtil.isTrue(!(module.getGrade()==0 || module.getGrade()==1 || module.getGrade()==2),"层级有误");

        //二级菜单url，非空，同级唯一
        //???????????
        if(module.getGrade() == 1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"模块地址不能为空");
            dbModule = moduleMapper.queryModuleByGradeUrl(module.getGrade(),module.getUrl());
            AssertUtil.isTrue(dbModule != null,"地址已存在，请重新输入");
        }

        //父级菜单  二级|三级：非空 | 必须存在
        //????????????
        if(module.getGrade() == 1 || module.getGrade() == 2){
            AssertUtil.isTrue(module.getParentId() == null,"父ID不能为空");
            dbModule = moduleMapper.queryModuleById(module.getParentId());
            AssertUtil.isTrue(dbModule == null,"父ID不存在");
        }

        //权限码  非空  唯一
        AssertUtil.isTrue(module.getOptValue() == null,"权限码不能为空");
        dbModule = moduleMapper.queryModuleByOptValue(module.getOptValue());
        AssertUtil.isTrue(dbModule != null,"权限码已存在");

        //设置默认值
        module.setIsValid(1);
        module.setCreateDate(new Date());
        module.setUpdateDate(new Date());

        //执行添加操作，并进行判断
        AssertUtil.isTrue(moduleMapper.insertSelective(module) < 1,"模块添加失败");
    }

    /**
     * 资源模块修改
     *     1.参数校验
     * * id 非空 记录存在
     * * 模块名-module_name
     * * 非空 同一层级下模块名唯一
     * * url
     * * 二级菜单 非空 不可重复
     * * 上级菜单-parent_id
     * * 二级|三级菜单 parent_id 非空 必须存在
     * * 层级-grade
     * * 非空 0|1|2
     * * 权限码 optValue
     * * 非空 不可重复
     * * 2.参数默认值设置
     * * update_date
     * * 3.执行更新 判断结果
     * */
    public void moduleUpdate(Module module){
       //id非空判断，并在数据库中存在
        AssertUtil.isTrue(module.getId() == null,"待删除资源id不能为空");
        Module dbModule = (Module) moduleMapper.selectByPrimaryKey(module.getId());

        //记录在数据库中必须存在
        AssertUtil.isTrue(dbModule == null,"系统异常");

        //资源名称不为空且唯一
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()),"资源名称不存在");
        dbModule = moduleMapper.queryModuleByGradeName(module.getGrade(), module.getModuleName());
        AssertUtil.isTrue(dbModule != null && !(dbModule.getId().equals(module.getId())),"模块名已存在");

        //层级不为空且必须为012
        AssertUtil.isTrue(module.getGrade() == null,"层级不能为空");
        AssertUtil.isTrue(!(module.getGrade()==0 || module.getGrade()==1 || module.getGrade()==2),"层级有误");

        // 二级菜单URL：非空，同级唯一
        if(module.getGrade() == 1){
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()),"模块地址不能为空");
            dbModule = moduleMapper.queryModuleByGradeUrl(module.getGrade(),module.getUrl());
            AssertUtil.isTrue(dbModule != null && !(module.getId().equals(dbModule.getId())),"地址已存在，请重新输入");
        }

        //父级菜单  二级|三级：非空 | 必须存在
        if(module.getGrade() == 1 || module.getGrade() == 2){
            AssertUtil.isTrue(module.getParentId() == null,"父ID不能为空");
            dbModule = moduleMapper.queryModuleById(module.getParentId());
            AssertUtil.isTrue(dbModule == null && !(module.getId().equals(dbModule.getId())),"父ID不存在");
        }

        //权限码  非空  唯一
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()),"权限码不能为空");
        dbModule = moduleMapper.queryModuleByOptValue(module.getOptValue());
        AssertUtil.isTrue(dbModule != null && !(module.getId().equals(dbModule.getId())),"权限码已存在");

        //默认值
        module.setUpdateDate(new Date());

        //执行修改操作，并进行判断
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1,"模块修改失败");
    }
}
