package com.merit.web_Pc;

import com.merit.dto.AjaxHandleResult;
import com.merit.dto.LoginExcution;
import com.merit.entity.*;
import com.merit.enums.LoginEnum;
import com.merit.service.*;
import com.merit.utils.TextUtils;
import com.merit.utils.dataobject.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by R on 2018/7/10.
 */
@Controller//类似@Service,@Component,把controller放入spring容器中
@RequestMapping("/managerAjax")
public class ManagerAjaxController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String innerErrorPage = "error";

    @Autowired
    private ManagerService managerService;
    @Autowired
    private DormitaryService dormitaryService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<LoginExcution> login(HttpServletRequest request, HttpSession session){
        LoginExcution loginExcution;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            loginExcution = new LoginExcution(LoginEnum.USERNAME_OR_PASSWORD_IS_EMPTY);
            return new AjaxHandleResult<LoginExcution>(false, loginExcution);
        }

        boolean isUsernameExist = managerService.isUsernameExist(username);
        if(!isUsernameExist){
            loginExcution = new LoginExcution(LoginEnum.UNKNOWN_USERNAME);
            return new AjaxHandleResult<LoginExcution>(false, loginExcution);
        }
        boolean isPasswordCorrect = managerService.isPasswordCorrect(username, password);
        if(isPasswordCorrect){
            session.setAttribute("adminAccount", username);
            loginExcution = new LoginExcution(LoginEnum.SUCCESS);
            return new AjaxHandleResult<LoginExcution>(true, loginExcution);
        }else{
            loginExcution = new LoginExcution(LoginEnum.PASSWORD_ERROR);
            return new AjaxHandleResult<LoginExcution>(false, loginExcution);
        }
    }

    @RequestMapping(value = "/alterPassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult alterPassword(HttpServletRequest request, HttpSession session){
        String username = (String)session.getAttribute("adminAccount");
        String oldPassword = request.getParameter("oldPassword");
        String password1 = request.getParameter("newPassword1");
        String password2 = request.getParameter("newPassword2");
        if(TextUtils.isEmpty(username)){
            return new AjaxHandleResult(false, "无法从session中获得账号");
        }
        if(TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)){
            return new AjaxHandleResult(false, "后台未接收到新密码");
        }

        boolean isPasswordCorrect = managerService.isPasswordCorrect(username, oldPassword);
        if(!isPasswordCorrect){
            return new AjaxHandleResult(false, "原密码错误");
        }
        boolean isAlterSuccess = managerService.alterPassword(username, password1);
        if(isAlterSuccess){
            return new AjaxHandleResult(true, null);
        }else{
            return new AjaxHandleResult(false, "密码修改失败");
        }
    }

    @RequestMapping(value = "/getDormitaries", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<PageInfo> getDormitaries(HttpServletRequest request){
        String dormitaryName = request.getParameter("dormitaryName");
        String location = request.getParameter("location");
        String pageNumStr = request.getParameter("pageNum");

        PageInfo<Map> pageInfo = new PageInfo<Map>();
        try {
            int pageNum = Integer.valueOf(pageNumStr);
            List<Dormitary> dormitaries = new ArrayList<Dormitary>();
            if(TextUtils.isEmpty(dormitaryName) && TextUtils.isEmpty(location)){
                pageInfo = dormitaryService.getDormitaryAndProjectByPage(pageNum);
            }
            if(!TextUtils.isEmpty(dormitaryName) && TextUtils.isEmpty(location)){
                pageInfo = dormitaryService.getDormitaryAndProjectByNameAndPage(dormitaryName, pageNum);
            }
            if(TextUtils.isEmpty(dormitaryName) && !TextUtils.isEmpty(location)){
                pageInfo = dormitaryService.getDormitaryAndProjectByLocationAndPage(location, pageNum);
            }
            if(!TextUtils.isEmpty(dormitaryName) && !TextUtils.isEmpty(location)){
                pageInfo = dormitaryService.getDormitayAndProjectByNameAndLocationAndPage(dormitaryName, location, pageNum);
            }
            return new AjaxHandleResult<PageInfo>(true, pageInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getDormitaryDetail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Map> getDormitaryDetail(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");

        try {
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Map dormitary = dormitaryService.getDormitaryAndProjectById(dormitaryId);
            if(null == dormitary){
                return new AjaxHandleResult(false, "无法获得该宿舍信息");
            }
            return new AjaxHandleResult<Map>(true, dormitary);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getCheckInListOfDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getCheckInListOfDormitary(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");

        try {
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            List<Person> personList = dormitaryService.getPersonsInTheDormitary(dormitaryId);
            return new AjaxHandleResult<List>(true, personList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/addDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> addDormitary(HttpServletRequest request){
        String dormName = request.getParameter("dormName");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String dormAddress = request.getParameter("dormAddress");
        String projIdStr = request.getParameter("projId");
        String emplIdStr = request.getParameter("emplId");
        String bedNumStr = request.getParameter("bedNum");
        String rentTime = request.getParameter("rentTime");
        String retireTime = request.getParameter("retireTime");
        String remarks = request.getParameter("remarks");

        try{
            Dormitary dormitary = new Dormitary();
            dormitary.setDormName(dormName);
            dormitary.setAddress(dormAddress);
            dormitary.setProvince(province);
            dormitary.setCity(city);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(!TextUtils.isEmpty(rentTime)){
                dormitary.setRentDate(sdf.parse(rentTime));
            }
            if(!TextUtils.isEmpty(retireTime)){
                dormitary.setRetireTime(sdf.parse((retireTime)));
            }
            if(!TextUtils.isEmpty(projIdStr)){
                int projId = Integer.valueOf(projIdStr);
                dormitary.setProjId(projId);
            }
            if(!TextUtils.isEmpty(emplIdStr)){
                int emplId = Integer.valueOf(emplIdStr);
                dormitary.setEmplIdRef(emplId);
            }
            dormitary.setBedNum(bedNumStr);
            dormitary.setRemarks(remarks);
            boolean isAddSucc = dormitaryService.addDormitary(dormitary);
            if(isAddSucc)
                return new AjaxHandleResult<Boolean>(true, isAddSucc);
            else
                return new AjaxHandleResult<Boolean>(false, isAddSucc);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult<Boolean>(false, false);
        }
    }

    @RequestMapping(value = "/updateOrAddDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> updateOrAddDormitary(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String dormitaryName = request.getParameter("dormitaryName");
        String bedNum = request.getParameter("bedNum");
        String occuNum = request.getParameter("occuNum");
        String rentTime = request.getParameter("rentTime");
        String retireTime = request.getParameter("retireTime");
        String isRetire = request.getParameter("isRetire");
        String remarks = request.getParameter("remarks");
        String projId = request.getParameter("projId");
        String emplIdRef = request.getParameter("emplIdRef");


        try {
            //如果没有id传过来表示是新增操作
            if(TextUtils.isEmpty(dormitaryIdStr)){
                Dormitary dormitary = new Dormitary();
                if(!TextUtils.isEmpty(province))
                    dormitary.setProvince(province);
                if(!TextUtils.isEmpty(city))
                    dormitary.setCity(city);
                if(!TextUtils.isEmpty(address))
                    dormitary.setAddress(address);
                if(!TextUtils.isEmpty(dormitaryName))
                    dormitary.setDormName(dormitaryName);
                if(!TextUtils.isEmpty(bedNum))
                    dormitary.setBedNum(bedNum);
                if(!TextUtils.isEmpty(occuNum))
                    dormitary.setOccuNum(occuNum);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(!TextUtils.isEmpty(rentTime))
                    dormitary.setRentDate(sdf.parse(rentTime));
                if(!TextUtils.isEmpty(retireTime))
                    dormitary.setRetireTime(sdf.parse(retireTime));
                if(!TextUtils.isEmpty(isRetire))
                    dormitary.setIsRetire(new Integer(isRetire));
                if(!TextUtils.isEmpty(remarks))
                    dormitary.setRemarks(remarks);
                if(!TextUtils.isEmpty(projId))
                    dormitary.setProjId(Integer.valueOf(projId));
                if(!TextUtils.isEmpty(emplIdRef)){
                    dormitary.setEmplIdRef(Integer.valueOf(emplIdRef));
                }
                boolean res = dormitaryService.addDormitary(dormitary);
                if(res)
                    return new AjaxHandleResult<Boolean>(true, res);
                else
                    return new AjaxHandleResult(false, "新增失败！");
            }

            //如有id传过来表示是更新操作
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Dormitary dormitary = dormitaryService.getDormitaryById(dormitaryId);
            if(!TextUtils.isEmpty(province))
                dormitary.setProvince(province);
            if(!TextUtils.isEmpty(city))
                dormitary.setCity(city);
            if(!TextUtils.isEmpty(address))
                dormitary.setAddress(address);
            if(!TextUtils.isEmpty(dormitaryName))
                dormitary.setDormName(dormitaryName);
            if(!TextUtils.isEmpty(bedNum))
                dormitary.setBedNum(bedNum);
            if(!TextUtils.isEmpty(occuNum))
                dormitary.setOccuNum(occuNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(!TextUtils.isEmpty(rentTime))
                dormitary.setRentDate(sdf.parse(rentTime));
            if(!TextUtils.isEmpty(retireTime))
                dormitary.setRetireTime(sdf.parse(retireTime));
            if(!TextUtils.isEmpty(isRetire))
                dormitary.setIsRetire(new Integer(isRetire));
            if(!TextUtils.isEmpty(remarks))
                dormitary.setRemarks(remarks);
            if(!TextUtils.isEmpty(projId))
                dormitary.setProjId(Integer.valueOf(projId));
            if(!TextUtils.isEmpty(emplIdRef)){
                dormitary.setEmplIdRef(Integer.valueOf(emplIdRef));
            }

            boolean res = dormitaryService.updateDormitary(dormitary);
            if(res){
                return new AjaxHandleResult<Boolean>(true, res);
            }else{
                return new AjaxHandleResult(false, "更新失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/deleteDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Integer> deleteDormitary(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");

        try {
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            int res = dormitaryService.deleteDormitary(dormitaryId);
            if(res == 1){
                return new AjaxHandleResult<Integer>(true, res);
            }else{
                return new AjaxHandleResult(false, "删除失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getSectorsByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<PageInfo> getSectorsByPage(HttpServletRequest request){
        String sectName = request.getParameter("sectName");
        String pageNumStr = request.getParameter("pageNum");
        if(TextUtils.isEmpty(pageNumStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        PageInfo<Map> pageInfo = new PageInfo<Map>();
        try {
            int pageNum = Integer.valueOf(pageNumStr);
            if(TextUtils.isEmpty(sectName)){
                pageInfo = sectorService.getSectorByPage(pageNum);
                return new AjaxHandleResult<PageInfo>(true, pageInfo);
            }
            pageInfo = sectorService.getSectorByNameAndPage(sectName, pageNum);
            return new AjaxHandleResult<PageInfo>(true, pageInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getAllSectors", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getAllSectors(HttpServletRequest request){
        List<Map> sectors = sectorService.getAllSectorsMap();
        return new AjaxHandleResult<List>(true, sectors);
    }

    @RequestMapping(value = "/getSectorDetail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Map> getSectorDetail(HttpServletRequest request){
        String sectorIdStr = request.getParameter("sectorId");

        try {
            int sectorId = Integer.valueOf(sectorIdStr);
            Map sector = sectorService.getSectorMapById(sectorId);
            if(null == sector){
                return new AjaxHandleResult(false, "无法获得该部门信息");
            }
            return new AjaxHandleResult<Map>(true, sector);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getSectorManagers", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getManagers(HttpServletRequest request){
        List<Person> sectorManagers = personService.getAllSectorManagers();
        return new AjaxHandleResult<List>(true, sectorManagers);
    }

    @RequestMapping(value = "/updateSector", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> updateSector(HttpServletRequest request){
        String sectorIdStr = request.getParameter("sectorId");
        String sectorName = request.getParameter("sectorName");
        String sectorManagerPersonIdStr = request.getParameter("sectorManagerPersonId");
        if(TextUtils.isEmpty(sectorIdStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        try{
            int sectorId = Integer.valueOf(sectorIdStr);
            Sector sector = sectorService.getSectorById(sectorId);
            if(!TextUtils.isEmpty(sectorManagerPersonIdStr)){
                int sectorManagerPersonId = Integer.valueOf(sectorManagerPersonIdStr);
                sector.setSectMana(sectorManagerPersonId);
            }else{
                sector.setSectMana(0);
            }
            sector.setSectName(sectorName);
            boolean isUpdateSuccess = sectorService.updateSector(sector);
            if(isUpdateSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "更新失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/addSector", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> addSector(HttpServletRequest request){
        String sectorName = request.getParameter("sectorName");
        String sectorManagerPersonIdStr = request.getParameter("sectorManagerPersonId");
        if(TextUtils.isEmpty(sectorName))
            return new AjaxHandleResult(false, "请求参数缺失");

        try{
            int sectorManagerPersonId = 0;
            if(!TextUtils.isEmpty(sectorManagerPersonIdStr))
                sectorManagerPersonId = Integer.valueOf(sectorManagerPersonIdStr);
            Sector sector = new Sector();
            sector.setSectName(sectorName);
            sector.setSectMana(sectorManagerPersonId);

            boolean isAddSuccess = sectorService.addSector(sector);
            if(isAddSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "新增失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/deleteSector", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> deleteSector(HttpServletRequest request){
        String sectorIdStr = request.getParameter("sectorId");

        try {
            int sectorId = Integer.valueOf(sectorIdStr);
            boolean res = sectorService.delSector(sectorId);
            if(res){
                return new AjaxHandleResult<Boolean>(true, res);
            }else{
                return new AjaxHandleResult(false, "删除失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getProjectManagers", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getProjectManagers(HttpServletRequest request){
        List<Person> projectManagers = personService.getAllProjectManagers();
        return new AjaxHandleResult<List>(true, projectManagers);
    }

    @RequestMapping(value = "/getProjectsByPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<PageInfo> getProjectsByPage(HttpServletRequest request){
        String projectName = request.getParameter("projectName");
        String pageNumStr = request.getParameter("pageNum");
        if(TextUtils.isEmpty(pageNumStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        PageInfo<Map> pageInfo = new PageInfo<Map>();
        if(!TextUtils.isEmpty(projectName)){
            int pageNum = Integer.valueOf(pageNumStr);
            pageInfo = projectService.getProjectByNameAndPage(projectName, pageNum);
            return new AjaxHandleResult<PageInfo>(true, pageInfo);
        }
        try {
            int pageNum = Integer.valueOf(pageNumStr);
            pageInfo = projectService.queryProjectsByPage(pageNum);
            return new AjaxHandleResult<PageInfo>(true, pageInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getProjectsByKeyWord", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getProjectsByKeyWord(HttpServletRequest request){
        String keyWord = request.getParameter("keyWord");
        List<Project> projectList;
        if(TextUtils.isEmpty(keyWord)){
            projectList = projectService.getAllProject();
        }else{
            projectList = projectService.getProjectByKeyWord(keyWord);
        }
        return new AjaxHandleResult<List>(true, projectList);
    }

    @RequestMapping(value = "/getProjectDetail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Map> getProjectDetail(HttpServletRequest request){
        String projectIdStr = request.getParameter("projectId");

        try {
            int projectId = Integer.valueOf(projectIdStr);
            Map project = projectService.getProjectMapById(projectId);
            if(null == project){
                return new AjaxHandleResult(false, "无法获得该项目信息");
            }
            return new AjaxHandleResult<Map>(true, project);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> addProject(HttpServletRequest request){
        String projectName = request.getParameter("projectName");
        String projectManagerPersonIdStr = request.getParameter("projectManagerPersonId");
        String sectorIdStr = request.getParameter("sectorId");
        if(TextUtils.isEmpty(projectName) || TextUtils.isEmpty(projectManagerPersonIdStr) || TextUtils.isEmpty(sectorIdStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        try{
            int projectManagerPersonId = Integer.valueOf(projectManagerPersonIdStr);
            int sectorId = Integer.valueOf(sectorIdStr);
            Project project = new Project();
            project.setProjName(projectName);
            project.setProjMana(projectManagerPersonId);
            project.setSectId(sectorId );

            boolean isAddSuccess = projectService.addProject(project);
            if(isAddSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "新增失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> updateProject(HttpServletRequest request){
        String projectIdStr = request.getParameter("projectId");
        String projectName = request.getParameter("projectName");
        String projectManagerPersonIdStr = request.getParameter("projectManagerPersonId");
        String sectorIdStr = request.getParameter("sectorId");
        if(TextUtils.isEmpty(projectIdStr) || TextUtils.isEmpty(projectName) || TextUtils.isEmpty(projectManagerPersonIdStr) || TextUtils.isEmpty(sectorIdStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        try{
            int projectId = Integer.valueOf(projectIdStr);
            int projectManagerPersonId = Integer.valueOf(projectManagerPersonIdStr);
            int sectorId = Integer.valueOf(sectorIdStr);
            Project project = projectService.getProjectById(projectId);
            project.setProjName(projectName);
            project.setProjMana(projectManagerPersonId);
            project.setSectId(sectorId);
            boolean isUpdateSuccess = projectService.updateProject(project);
            if(isUpdateSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "更新失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> deleteProject(HttpServletRequest request){
        String projIdStr = request.getParameter("projectId");

        try {
            int projId = Integer.valueOf(projIdStr);
            boolean res = projectService.deleteProject(projId);
            if(res){
                return new AjaxHandleResult<Boolean>(true, res);
            }else{
                return new AjaxHandleResult(false, "删除失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getPersonsByParamAndPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<PageInfo> getPersonsByParamAndPage(HttpServletRequest request){
        String pageNumStr = request.getParameter("pageNum");
        String emplIdStr = request.getParameter("emplId");
        String personName = request.getParameter("personName");
        String role = request.getParameter("role");
        if(TextUtils.isEmpty(pageNumStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        PageInfo<Map> pageInfo = new PageInfo<Map>();
        try {
            Map params = new HashMap(4);
            int pageNum = Integer.valueOf(pageNumStr);
            params.put("pageNum", pageNum);
            int emplId;
            if(!TextUtils.isEmpty(emplIdStr)){
                emplId = Integer.valueOf(emplIdStr);
                params.put("emplId", emplId);
            }
            if(!TextUtils.isEmpty(personName)){
                params.put("name", personName);
            }
            if(!TextUtils.isEmpty(role)){
                params.put("role", role);
            }

            pageInfo = personService.queryPersonMapByParams(params);
            return new AjaxHandleResult<PageInfo>(true, pageInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getPersonDetail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Map> getPersonDetail(HttpServletRequest request){
        String emplIdStr = request.getParameter("emplId");

        try {
            int emplId = Integer.valueOf(emplIdStr);
            Map person = personService.getPersonMapByEmplId(emplId);
            if(null == person){
                return new AjaxHandleResult(false, "无法获得该人员信息");
            }
            return new AjaxHandleResult<Map>(true, person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/getDormManasByName", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> getDormManasByName(HttpServletRequest request){
        String persName = request.getParameter("persName");

        try {
            List<Map>  personList;
            if(TextUtils.isEmpty(persName)){
                personList = personService.getAllDormMana();
            }else{
                personList = personService.getDormManasByName(persName);
            }
            if(0 == personList.size()){
                return new AjaxHandleResult(false, "无法获得人员信息");
            }
            return new AjaxHandleResult<List>(true, personList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> addPerson(HttpServletRequest request){
        String employIdStr = request.getParameter("employId");
        String personName = request.getParameter("personName");
        String sexStr = request.getParameter("sex");
        String phoneNum = request.getParameter("phoneNum");
        String sectorIdStr = request.getParameter("sector");
        String manaDormitaryStr = request.getParameter("manaDormitary");
        String roleName = request.getParameter("role");
        if(TextUtils.isEmpty(employIdStr))
            return new AjaxHandleResult(false, "请求参数缺失");

        try{
            int employId = Integer.valueOf(employIdStr);
            Person person = personService.getPersonByEmplId(employId);
            if(null != person)
                return new AjaxHandleResult(false, "该工号已存在");
            int sex = Integer.valueOf(sexStr);
            int sectorId = Integer.valueOf(sectorIdStr);
            person = new Person();
            person.setEmplId(employId);
            person.setName(personName);
            person.setSex(sex);
            person.setPhonNum(phoneNum);
            person.setSectId(sectorId);

            boolean isAddSuccess = personService.addPerson(person);
            if(!TextUtils.isEmpty(roleName) && !"普通员工".equals(roleName)){
                int emplId = Integer.valueOf(employIdStr);
                roleService.setPersonRole(emplId, roleName);
            }
            if(isAddSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "新增失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> updatePerson(HttpServletRequest request){
        String emplIdStr = request.getParameter("emplId");
        String personName = request.getParameter("personName");
        String phoneNum = request.getParameter("phoneNum");
        String sexStr = request.getParameter("sex");
        String sectorIdStr = request.getParameter("sectorId");
        String roleName = request.getParameter("role");
        String manaDormitaryStr = request.getParameter("manaDormitary");
        if(TextUtils.isEmpty(emplIdStr) || TextUtils.isEmpty(personName))
            return new AjaxHandleResult(false, "请求参数缺失，请完善信息");

        try{
            int emplId = Integer.valueOf(emplIdStr);
            int sex = Integer.valueOf(sexStr);
            int sectorId = Integer.valueOf(sectorIdStr);

            Person person = personService.getPersonByEmplId(emplId);
            person.setName(personName);
            person.setPhonNum(phoneNum);
            person.setSex(sex);
            person.setSectId(sectorId);
            boolean isUpdateSuccess = personService.updatePerson(person);
            if(!TextUtils.isEmpty(roleName)){
                if(!"普通员工".equals(roleName)){
                    List<Role> roles = roleService.getRolesByEmplId(emplId);
                    if(0 != roles.size()){
                        boolean isContain = false;
                        for(Role role : roles){
                            if(roleName.equals(role.getRoleName())){
                                isContain = true;
                            }
                        }
                        if(!isContain){
                            roleService.setPersonRole(emplId, roleName);
                        }
                    }else{
                        roleService.setPersonRole(emplId, roleName);
                    }
                }else{
                    roleService.deleteRoleByEmplId(emplId);
                }
            }
            if(isUpdateSuccess)
                return new AjaxHandleResult(true, true);
            else
                return new AjaxHandleResult(false, "更新失败");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部出错，请联系管理员");
        }
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<Boolean> deletePerson(HttpServletRequest request){
        String emplIdStr = request.getParameter("emplId");

        try {
            int emplId = Integer.valueOf(emplIdStr);
            roleService.deleteRoleByEmplId(emplId);
            boolean res = personService.deletePerson(emplId);
            if(res){
                return new AjaxHandleResult<Boolean>(true, res);
            }else{
                return new AjaxHandleResult(false, "删除失败！");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/listAllDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> listAllDormitary(HttpServletRequest request){
        List<Dormitary> dormitaries = dormitaryService.listAllDormitary();
        return new AjaxHandleResult<List>(true, dormitaries);
    }

    @RequestMapping(value = "/listAllRoles", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<List> listAllRoles(HttpServletRequest request){
        List<Role> roles = roleService.listAllRoles();
        return new AjaxHandleResult<List>(true, roles);
    }
}
