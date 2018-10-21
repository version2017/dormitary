package com.merit.web;

import com.merit.constant.Constant;
import com.merit.entity.*;
import com.merit.service.*;
import com.merit.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/13.
 */
@Controller//类似@Service,@Component,把controller放入spring容器中
@RequestMapping("/view")//url:/模块/资源/{id}/细分
public class ViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String innerErrorPage = "error";

    @Autowired
    private OrdinaryUserService ordinaryUserService;
    @Autowired
    private DormitaryService dormitaryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model){
        System.out.println("Into Test Page");
        return "direct";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }

    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
    public String getOpenId(Model model){
        return "getOpenId";
    }

    @RequestMapping(value = "/listChoose", method = RequestMethod.GET)
    public String listChoose(Model model){
        return "listChoose";
    }

    @RequestMapping(value = "/listChoose2", method = RequestMethod.GET)
    public String listChoose2(Model model){
        return "listChoose2";
    }

    @RequestMapping(value = "/isFirstTimeLogin", method = RequestMethod.GET)
    public String isFirstTimeLogin(Model model, @RequestParam("wechatId") String wechatId, HttpServletRequest request){
        if(TextUtils.isEmpty(wechatId)){
            model.addAttribute("errorMessage", "无法获得您的微信号，请重新进入");
            model.addAttribute("maintainerName", Constant.MAINTAINER_NAME);
            model.addAttribute("maintainerPhone", Constant.MAINTAINER_PHONE);
            model.addAttribute("maintaineEmail", Constant.MAINTAINER_EMAIL);
            return innerErrorPage;
        }
        HttpSession session = request.getSession();
        session.setAttribute("wechatId", wechatId);

        if(!TextUtils.isEmpty(wechatId)){
            Person person = personService.getPersonByWechatId(wechatId);
            if(null == person){
                return "redirect:/view/fillInformation";
            }else{
                return "redirect:/view/identification";
            }
        }else{
            model.addAttribute("errorMessage", "无法获得微信认证userId，请重新进入");
            model.addAttribute("maintainerName", Constant.MAINTAINER_NAME);
            model.addAttribute("maintainerPhone", Constant.MAINTAINER_PHONE);
            model.addAttribute("maintaineEmail", Constant.MAINTAINER_EMAIL);
            return innerErrorPage;
        }
    }

    @RequestMapping(value = "/fillInformation", method = RequestMethod.GET)
    public String fillInformation(HttpServletRequest request, HttpSession session, Model model){
        return "fillInformation";
    }

    @RequestMapping(value = "/identification", method = RequestMethod.GET)
    public String identification(Model model,HttpSession session){
        //若sesion中有宿舍编号，则说明是扫码进入的。不进行身份认证，直接进入相应的宿舍
        if(null != session.getAttribute("dormitaryId")){
            String dormtaryId = (String)session.getAttribute("dormitaryId");
            return "redirect:/view/dormitaryDetail/" + dormtaryId;
        }

        String wechatId = (String)session.getAttribute("wechatId");
        Person person = personService.getPersonByWechatId(wechatId);
        //如果已经入住宿舍，将宿舍编号加入model，下一个页面有用
        if(person.getDormId() != 0){
            model.addAttribute("myDormId", person.getDormId());
        }
        List<Role> roles = roleService.getRolesByEmplId(person.getEmplId());
        boolean isContainProjMana = false;
        boolean isContainSectMana = false;
        for(Role role : roles){
            if("项目经理".equals(role.getRoleName())){
                isContainProjMana = true;
            }
            if("部门经理".equals(role.getRoleName())){
                isContainSectMana = true;
            }
        }
        if(0 != roles.size() && isContainProjMana){
            return "listChoose_projMana";
        }else if(0 != roles.size() && isContainSectMana){
            return "listChoose_sectMana";
        }
        return "listChoose_normal";
    }

    @RequestMapping(value = "/dormitaryList", method = RequestMethod.GET)
    public String dormitaryList(Model model, HttpServletRequest request, @RequestParam(value = "province", required = false, defaultValue = "")String province){
        if(!TextUtils.isEmpty(province)){
            model.addAttribute("province", province);
        }
        List<String> places = dormitaryService.getDormitaryPlaces();
        model.addAttribute("places", places);
        return "dormitaryList";
    }

    @RequestMapping(value = "/dormitaryDetail/{dormitaryId}", method = RequestMethod.GET)
    public String dormitaryDetail(Model model,HttpServletRequest request, @PathVariable("dormitaryId") String dormitaryIdStr){
        HttpSession session = request.getSession();
        //将扫码时记录的宿舍编号移出session，防止页面跳转出错。也就是说进入一个宿舍，那么扫码记录的宿舍编号就失效了
        session.removeAttribute("dormitaryId");

        String wechatId = (String)session.getAttribute("wechatId");
        if(TextUtils.isEmpty(dormitaryIdStr)){
            model.addAttribute("errorMessage", "系统内部错误，请尝试重新进入或刷新页面");
            model.addAttribute("maintainerName", Constant.MAINTAINER_NAME);
            model.addAttribute("maintainerPhone", Constant.MAINTAINER_PHONE);
            model.addAttribute("maintaineEmail", Constant.MAINTAINER_EMAIL);
            return innerErrorPage;
        }
        if(TextUtils.isEmpty(wechatId)){
            //若session中没有微信号，则先将宿舍编号存入request，再重定向至首页，重新获取微信号
            session.setAttribute("dormitaryId", dormitaryIdStr);
            return "redirect:/view/index";
        }

        try{
            //向页面传递宿舍信息
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Map dormitary = dormitaryService.getDormitaryAndProjectById(dormitaryId);
            model.addAttribute("dormitary", dormitary);

            //向页面传递已入住人员信息
            List<Person> persons = dormitaryService.getPersonsInTheDormitary(dormitaryId);
            if(persons.size() == 0){
                model.addAttribute("persons", "");
            }else{
                model.addAttribute("persons", persons);
            }

            //向页面传递已入住人员的姓名合集
            List<String> names = dormitaryService.getPersonNamesInTheDormitary(dormitaryId);
            if(names.size() > 0){
                StringBuilder nameSb = new StringBuilder();
                for(String name : names){
                    nameSb.append(name);
                    nameSb.append("、");
                }
                nameSb.deleteCharAt(nameSb.length()-1);
                model.addAttribute("names", nameSb.toString());
            }

            //判断当前用户是否已经入住该宿舍
            Person person = personService.getPersonByWechatId(wechatId);
            boolean isCheckIn = ordinaryUserService.isCheckIn(dormitaryId, person.getEmplId());
            model.addAttribute("isCheckIn", isCheckIn);
            //判断该用户是否为当前宿舍的管理员
            boolean isManagerOfDormitary;
            List<Role> roles = roleService.getRolesByEmplId(person.getEmplId());
            boolean isContainDormMana = false;
            for(Role role : roles){
                if("宿舍管理员".equals(role.getRoleName())){
                    isContainDormMana = true;
                }
            }
            if(0 == roles.size() || null == dormitary.get("EMPL_ID_REF")){
                isManagerOfDormitary = false;
            }else if(isContainDormMana && (Integer)dormitary.get("EMPL_ID_REF") == person.getEmplId()){
                isManagerOfDormitary = true;
            }else{
                isManagerOfDormitary = false;
            }
            model.addAttribute("isManagerOfDormitary", isManagerOfDormitary);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
//            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("errorMessage", "系统内部错误，请尝试重新进入或刷新页面");
            model.addAttribute("maintainerName", Constant.MAINTAINER_NAME);
            model.addAttribute("maintainerPhone", Constant.MAINTAINER_PHONE);
            model.addAttribute("maintaineEmail", Constant.MAINTAINER_EMAIL);
            return innerErrorPage;
        }
        return "dormitaryDetail";
    }

    @RequestMapping(value = "/list_projMana", method = RequestMethod.GET)
    public String list_projMana(Model model, HttpSession session){
        String wechatId = (String)session.getAttribute("wechatId");
        if(TextUtils.isEmpty(wechatId)){
            return "index";
        }

        Person person = personService.getPersonByWechatId(wechatId);
        List<Project> projects = projectService.getProjectByManaEmplId(person.getEmplId());
        List<Map> dormitaries = new ArrayList<Map>();
        for(Project project : projects){
            Map dormitary = dormitaryService.getDormitaryAndManaByProjId(project.getProjId());
            if(null != dormitary){
                dormitaries.add(dormitary);
            }
        }
        model.addAttribute("dormitaries", dormitaries);
        return "list_projMana";
    }

    @RequestMapping(value = "/list_sectMana", method = RequestMethod.GET)
    public String list_sectMana(Model model, HttpSession session){
        String wechatId = (String)session.getAttribute("wechatId");
        if(TextUtils.isEmpty(wechatId)){
            return "index";
        }

        Person person = personService.getPersonByWechatId(wechatId);
        Sector sector = sectorService.getSectorByManaEmplId(person.getEmplId());
        List<Project> projects = projectService.getProjectBySectId(sector.getSectId());
        List<Map> dormitaries = new ArrayList<Map>();
        for(Project project : projects){
            Map dormitary = dormitaryService.getDormitaryAndManaByProjId(project.getProjId());
            if(null != dormitary){
                dormitaries.add(dormitary);
            }
        }
        model.addAttribute("dormitaries", dormitaries);
        return "list_sectMana";
    }
}
