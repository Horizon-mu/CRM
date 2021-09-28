package com.hrz.crm.workbench.web.controller;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.settings.service.impl.UserServiceImpl;
import com.hrz.crm.utils.DateTimeUtil;
import com.hrz.crm.utils.UUIDUtil;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Activity;
import com.hrz.crm.workbench.entity.Clue;
import com.hrz.crm.workbench.entity.Tran;
import com.hrz.crm.workbench.service.impl.ActivityServiceImpl;
import com.hrz.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




//线索处理
@RequestMapping(value = "/clue")
@Controller
public class ClueController {
    @Autowired
    private ClueServiceImpl clueService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ActivityServiceImpl activityService;

    //线索添加操作
    @PostMapping(value = "/saveClue.do")
    @ResponseBody
    public boolean doSave(Clue clue,HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        clue.setCreateBy(user.getName());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        clue.setId(UUIDUtil.getUUID());
        boolean flag = true;
        flag = clueService.doSave(clue);
        return flag;
    }

    //线索页面，搜索框的查询按钮，线索页面加载后，获取线索列表
    @PostMapping(value = "/pageList.do")
    @ResponseBody
    public PaginationVo doPageList(HttpServletRequest request){
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        int no = Integer.valueOf(pageNo);
        int size = Integer.valueOf(pageSize);
        int skipCount = (no - 1)*size;
        Map<String,Object> map = new HashMap<>();
        map.put("fullname",request.getParameter("fullname"));
        map.put("company",request.getParameter("company"));
        map.put("mphone",request.getParameter("mphone"));
        map.put("source",request.getParameter("source"));
        map.put("owner",request.getParameter("owner"));
        map.put("phone",request.getParameter("phone"));
        map.put("state",request.getParameter("state"));
        map.put("skipCount",skipCount);
        map.put("pageSize",size);
        return clueService.doPageList(map);
    }

    //提前渲染线索的修改模态窗口
    @RequestMapping("/preEditClue.do")
    @ResponseBody
    public Map preEditClue(String id){
        Map<String,Object> map = new HashMap<>();
        Clue clue =  clueService.getClueById(id);
        List<User> uList = userService.getUserList();
        map.put("clue",clue);
        map.put("uList",uList);
        return map;
    }

    //渲染线索详细页面
    @RequestMapping("/detail.do")
    public String doCluePreViewDetail(String id,HttpServletRequest request){
        Clue clue = clueService.getClueById(id);
        request.setAttribute("clue",clue);
        return "forward:/workbench/clue/detail.jsp";
    }

    //详情页面加载完成后，查询该条线索关联的市场活动，三表联查

    @RequestMapping("/getActivityByClueId.do")
    @ResponseBody
    public List<Activity> getActivityByClueId(String id){
        List<Activity> list = activityService.getActivityByClueId(id);
        return list;
    }
    //修改线索信息

    /*@RequestMapping("/editClue.do")
    @ResponseBody

    public Map editClue(){

    }*/

    //解除绑定

    @RequestMapping("/unBund.do")
    @ResponseBody
    public void unBundle(String id){

    }

    //关联市场活动模态窗口的搜索框查询(根据名称模糊查询+排除掉已经关联指定线索的列表)
    @RequestMapping("/getActivityListByNameAndNotByClueId.do")
    @ResponseBody
    public List<Activity> getActivityList(String aName,String clueId){
        Map<String,String> map = new HashMap<>();
        map.put("aName",aName);
        map.put("clueId",clueId);
        List<Activity> activityList = activityService.getActivityListByNameAndNotInClueId(map);
        return activityList;
    }
    //删除线索

    @RequestMapping("/delCluesById.do")
    @ResponseBody
    public boolean doDelete(String[] id){
        return true;
    }

    //执行线索转换的操作
    @RequestMapping("/convert.do")
    @ResponseBody
    public ModelAndView convert(String flag, String clueId, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        Tran tran = null;
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        //接收是否需要创建的标记
        if ("a".equals(flag)){
            tran = new Tran();
            //接收交易表单中的参数
            tran.setId(UUIDUtil.getUUID());
            tran.setCreateTime(DateTimeUtil.getSysTime());
            tran.setMoney(request.getParameter("money"));
            tran.setName(request.getParameter("name"));
            tran.setExpectedDate(request.getParameter("expectedDate"));
            tran.setStage(request.getParameter("stage"));
            tran.setActivityId(request.getParameter("activityId"));
            tran.setCreateBy(createBy);
        }
        boolean returnFlag = clueService.convert(clueId,tran,createBy);
        if (returnFlag){
            mv.setViewName("redirect:/workbench/clue/index.jsp");
        }
        return mv;
    }
}
