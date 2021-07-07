package com.hrz.crm.workbench.web.controller;

import com.hrz.crm.settings.entity.User;
import com.hrz.crm.utils.DateTimeUtil;
import com.hrz.crm.utils.UUIDUtil;
import com.hrz.crm.vo.PaginationVo;
import com.hrz.crm.workbench.entity.Clue;
import com.hrz.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/25/9:10
 * @Description:
 */
@RequestMapping(value = "/clue")
@Controller
public class ClueController {
    @Autowired
    private ClueServiceImpl clueService;
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
}
