package com.xxxx.sms.controller;
import com.xxxx.sms.annotation.RequirePermission;
import com.xxxx.sms.base.BaseController;
import com.xxxx.sms.base.ResultInfo;
import com.xxxx.sms.query.SaleChanceQuery;
import com.xxxx.sms.service.SaleChanceService;
import com.xxxx.sms.utils.AssertUtil;
import com.xxxx.sms.utils.CookieUtil;
import com.xxxx.sms.utils.LoginUserUtil;
import com.xxxx.sms.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;
    /**
     * 2.0
     * 多条件分页查询数据
     * @param saleChanceQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    @RequirePermission(code = "101001")
    public Map<String, Object> queryByParams(SaleChanceQuery saleChanceQuery){

        return saleChanceService.queryByParams(saleChanceQuery);
    }

    /**
     * 打开信息管理页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "saleChance/sale_chance";
    }

    /**
     * 查询所有信息
     */
    @PostMapping("queryAllClass")
    @ResponseBody
    public List<Map<String,Object>> queryClass(){
        return saleChanceService.queryClass();
    }
    /**
     * 添加数据
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest request, SaleChance saleChance){
        // 获取用户名字
        saleChanceService.addSaleChance(saleChance);
        return success();
    }
    /**
     * 添加数据
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return success();
    }
    /**
     * 打开学生信息管理修改/添加的页面
     * @return
     */
    @RequestMapping("toAddUpdatePage")
    public String toAddUpdatePage(Integer id,HttpServletRequest request){
        //如果是修改操作那么需要将修改的数据映射在页面中
        if(id != null){
            SaleChance saleChance= (SaleChance) saleChanceService.selectByPrimaryKey(id);
            String claName=saleChanceService.queryClassName(Integer.parseInt(saleChance.getUserClassId()));
            AssertUtil.isTrue(saleChance == null,"数据异常，请重试");
            AssertUtil.isTrue(claName == null,"数据异常，请重试");
            request.setAttribute("saleChance",saleChance);
            request.setAttribute("claName",claName);
        }
        return "saleChance/add_update";
    }


    /**
     * 删除数据
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance (Integer[] ids) {
        // 删除数据
        saleChanceService.deleteBatch(ids);
        return success("学生信息管理数据数删除成功！");
    }

}
