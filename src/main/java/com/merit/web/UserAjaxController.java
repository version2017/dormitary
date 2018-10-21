package com.merit.web;

import com.merit.constant.Constant;
import com.merit.dto.AjaxHandleResult;
import com.merit.dto.CreateQRCodeExcution;
import com.merit.dto.SaveUserInforExcution;
import com.merit.entity.Dormitary;
import com.merit.entity.Person;
import com.merit.entity.Role;
import com.merit.enums.CreateQRCodeEnum;
import com.merit.enums.SaveUserInforEnum;
import com.merit.service.DormitaryService;
import com.merit.service.OrdinaryUserService;
import com.merit.service.PersonService;
import com.merit.service.RoleService;
import com.merit.utils.QRCodeUtil;
import com.merit.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/6/15.
 */
@Controller//类似@Service,@Component,把controller放入spring容器中
@RequestMapping("/ajax")
public class UserAjaxController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String innerErrorPage = "error";

    @Autowired
    private OrdinaryUserService ordinaryUserService;
    @Autowired
    private DormitaryService dormitaryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/saveUserInfor", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult<SaveUserInforExcution> saveUserInfor(HttpServletRequest request){
        SaveUserInforExcution saveUserInforExcution;
        String emplIdStr = request.getParameter("emplId");
        String fullName = request.getParameter("fullName");
        String sexStr = request.getParameter("sexSel");
        String phoneNum = request.getParameter("phoneNum");
        String wechatId = (String)request.getSession().getAttribute("wechatId");
        if(TextUtils.isEmpty(emplIdStr) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(sexStr) || TextUtils.isEmpty(phoneNum)){
            saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.PARAMETER_LOSS);
            return new AjaxHandleResult<SaveUserInforExcution>(false, saveUserInforExcution);
        }

        try {
            int emplId = Integer.parseInt(emplIdStr);
            Person person = personService.getPersonByEmplId(emplId);
            if(null == person){
                saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.WRONG_EMPL_ID);
                return new AjaxHandleResult<SaveUserInforExcution>(false, saveUserInforExcution);
            }
            if(!fullName.equals(person.getName())){
                saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.WRONG_EMPL_ID_OR_NAME);
                return new AjaxHandleResult<SaveUserInforExcution>(false, saveUserInforExcution);
            }

            int sex = Integer.parseInt(sexStr);
            person.setName(fullName);
            person.setSex(sex);
            person.setPhonNum(phoneNum);
            person.setWechat(wechatId);
            boolean res = personService.updatePerson(person);
            if(res){
                saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.SUCCESS);
                return new AjaxHandleResult<SaveUserInforExcution>(true, saveUserInforExcution);
            }else{
                saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.HANDLE_FAILED);
                return new AjaxHandleResult<SaveUserInforExcution>(false, saveUserInforExcution);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            saveUserInforExcution = new SaveUserInforExcution(SaveUserInforEnum.DATA_CONVERT_ERROR);
            return new AjaxHandleResult<SaveUserInforExcution>(false, saveUserInforExcution);
        }
    }

    @RequestMapping(value = "/getDormitaryList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult getDormitaryList(HttpServletRequest request){
        String placeSelected = request.getParameter("placeSelected");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(placeSelected)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "请求参数地点为空");
            return handleResult;
        }

        List<Map> dormitaries = dormitaryService.getDormitaryListWithLeaderInfor(placeSelected);
        handleResult = new AjaxHandleResult(true, dormitaries);
        return handleResult;
    }

    @RequestMapping(value = "/getSectorDormitaryList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult getSectorDormitaryList(HttpSession session){
        String wechatId = (String)session.getAttribute("wechatId");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(wechatId)){
            logger.error("Cannot get wechatId from session.");
            handleResult = new AjaxHandleResult(false, "系统无法获取您的微信好，请重新登录");
            return handleResult;
        }

        Person person = personService.getPersonByWechatId(wechatId);
        List<Map> dormitaries = dormitaryService.getSectorDormitaryListMap(person.getEmplId());
        handleResult = new AjaxHandleResult(true, dormitaries);
        return handleResult;
    }

    @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult checkIn(HttpServletRequest request, HttpSession session){
        String dormitaryIdStr = request.getParameter("dormitaryId");
        String wechatId = (String)session.getAttribute("wechatId");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(dormitaryIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "请求参数宿舍编号为空");
            return handleResult;
        }
        if(TextUtils.isEmpty(wechatId)){
            logger.error("Could't get wechatId from session");
            handleResult = new AjaxHandleResult(false, "系统无法获得您的微信号，请重新登录");
            return handleResult;
        }

        try{
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Person person = personService.getPersonByWechatId(wechatId);
            boolean res = ordinaryUserService.checkIn(dormitaryId, person.getEmplId());
            if(res){
                handleResult = new AjaxHandleResult(true, res);
            }else{
                handleResult = new AjaxHandleResult(false, "入住失败");
            }
            return handleResult;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误");
        }
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult leave(HttpServletRequest request){
        HttpSession session = request.getSession();
        String dormitaryIdStr = request.getParameter("dormitaryId");
        String wechatId = (String)session.getAttribute("wechatId");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(dormitaryIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "请求参数宿舍编号为空");
            return handleResult;
        }
        if(TextUtils.isEmpty(wechatId)){
            logger.error("Could't get wechatId from session");
            handleResult = new AjaxHandleResult(false, "系统无法获得您的微信号，请重新登录");
            return handleResult;
        }

        int dormitaryId = Integer.valueOf(dormitaryIdStr);
        Person person = personService.getPersonByWechatId(wechatId);
        boolean res = ordinaryUserService.leave(person.getEmplId());
        if(res){
            handleResult = new AjaxHandleResult(true, res);
        }else{
            handleResult = new AjaxHandleResult(false, "办理失败");
        }
        return handleResult;
    }

    @RequestMapping(value = "/createQRCode", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult createQRCode(HttpServletRequest request){
        AjaxHandleResult handleResult;
        try{
            System.out.println("进入二维码接口");
            System.out.println("二维码图片保存路径：" + Constant.FILE_SAVE_PATH);
            String dormitaryId = request.getParameter("dormitaryId");
            String dormitaryName = request.getParameter("dormitaryName");
            if(TextUtils.isEmpty(dormitaryId) || TextUtils.isEmpty(dormitaryName)){
                logger.error("Request parameter missing.");
                handleResult = new AjaxHandleResult(false, "请求参数缺失");
                return handleResult;
            }

            QRCodeUtil.savePath = Constant.FILE_SAVE_PATH;
            QRCodeUtil.suffix = Constant.QR_CODE_FORMAT;

            //判断是否已经生成过二维码，若已生成直接返回
            File file  = new File(QRCodeUtil.savePath);
            String[] fileList = file.list();
            for(String fileName : fileList){
                if(dormitaryId.equals(fileName)){
                    CreateQRCodeExcution createQRCodeExcution = new CreateQRCodeExcution(CreateQRCodeEnum.SUCCESS);
                    createQRCodeExcution.setCodeUri(Constant.QR_CODE_URI + dormitaryId + "." + QRCodeUtil.suffix);
                    handleResult = new AjaxHandleResult(true, createQRCodeExcution);
                    return handleResult;
                }
            }

            String qrData = Constant.SERVER_DOMAIN_PORT + "/dormitary/view/dormitaryDetail/" + dormitaryId;
            boolean isCreateSuccess = QRCodeUtil.createQRCode(qrData, dormitaryId);
            if(isCreateSuccess){
                CreateQRCodeExcution createQRCodeExcution = new CreateQRCodeExcution(CreateQRCodeEnum.SUCCESS);
                createQRCodeExcution.setCodeUri(Constant.QR_CODE_URI + dormitaryId + "." + QRCodeUtil.suffix);
                handleResult = new AjaxHandleResult(true, createQRCodeExcution);
            }else{
                CreateQRCodeExcution createQRCodeExcution = new CreateQRCodeExcution(CreateQRCodeEnum.CREATE_FAILED);
                handleResult = new AjaxHandleResult(false, createQRCodeExcution);
            }
            return handleResult;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            handleResult = new AjaxHandleResult(false, "系统内部错误，请联系管理员");
            return handleResult;
        }
    }

    @RequestMapping(value = "/isDormitaryManager", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult isDormitaryManager(HttpSession session){
        String wechatId = (String) session.getAttribute("wechatId");
        try{
            Person person = personService.getPersonByWechatId(wechatId);
            List<Role> roles = roleService.getRolesByEmplId(person.getEmplId());
            boolean isContainDormMana = false;
            for(Role role : roles){
                if("宿舍管理员".equals(role.getRoleName())){
                    isContainDormMana = true;
                }
            }
            if(isContainDormMana){
                return new AjaxHandleResult(true, true);
            }
            return new AjaxHandleResult(false, false);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/updateDormitaryAddress", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult updateDormitaryAddress(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");
        String dormAddress = request.getParameter("dormAddress");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(dormitaryIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "请求参数宿舍编号为空");
            return handleResult;
        }

        try{
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Dormitary dormitary = dormitaryService.getDormitaryById(dormitaryId);
            if(dormitary != null){
                dormitary.setAddress(dormAddress);
                 boolean res = dormitaryService.updateDormitary(dormitary);
                return new AjaxHandleResult(true, res);
            }else{
                return new AjaxHandleResult(false, "无法找到该宿舍");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/updateDormitary", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult updateDormitary(HttpServletRequest request){
        String dormitaryIdStr = request.getParameter("dormitaryId");
        String dormAddress = request.getParameter("dormAddress");
        String projIdStr = request.getParameter("projId");
        String bedNumStr = request.getParameter("bedNum");
        String occuNumStr = request.getParameter("occuNum");
        AjaxHandleResult handleResult;
        if(TextUtils.isEmpty(dormitaryIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "请求参数宿舍编号为空");
            return handleResult;
        }

        try{
            int dormitaryId = Integer.valueOf(dormitaryIdStr);
            Dormitary dormitary = dormitaryService.getDormitaryById(dormitaryId);
            if(dormitary != null){
                dormitary.setAddress(dormAddress);
                if(!TextUtils.isEmpty(projIdStr)){
                    int projId = Integer.valueOf(projIdStr);
                    dormitary.setProjId(projId);
                }
                dormitary.setBedNum(bedNumStr);
                dormitary.setOccuNum(occuNumStr);
                boolean res = dormitaryService.updateDormitary(dormitary);
                return new AjaxHandleResult(true, res);
            }else{
                return new AjaxHandleResult(false, "无法找到该宿舍");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new AjaxHandleResult(false, "系统内部错误，请联系管理员");
        }
    }

    @RequestMapping(value = "/kickOut", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult kickOut(HttpServletRequest request){
        AjaxHandleResult handleResult;
        String emplIdStr = request.getParameter("emplId");
        String dormitaryIdStr = request.getParameter("dormitaryId");
        if(TextUtils.isEmpty(dormitaryIdStr) || TextUtils.isEmpty(emplIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "移出失败");
            return handleResult;
        }

        int dormitaryId = Integer.valueOf(dormitaryIdStr);
        int emplId = Integer.valueOf(emplIdStr);
        boolean res = ordinaryUserService.leave(emplId);
        if(res){
            handleResult = new AjaxHandleResult(true, res);
        }else{
            handleResult = new AjaxHandleResult(false, "移出失败");
        }
        return handleResult;
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult invite(HttpServletRequest request){
        AjaxHandleResult handleResult;
        String emplIdStr = request.getParameter("emplId");
        String dormitaryIdStr = request.getParameter("dormitaryId");
        if(TextUtils.isEmpty(dormitaryIdStr) || TextUtils.isEmpty(emplIdStr)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "邀请失败");
            return handleResult;
        }

        int dormitaryId = Integer.valueOf(dormitaryIdStr);
        int emplId = Integer.valueOf(emplIdStr);
        boolean res = ordinaryUserService.invite(dormitaryId, emplId);
        if(res){
            handleResult = new AjaxHandleResult(true, res);
        }else{
            handleResult = new AjaxHandleResult(false, "移出失败");
        }
        return handleResult;
    }

    @RequestMapping(value = "/getPersonsByNameKeyWord", method = RequestMethod.POST)
    @ResponseBody
    public AjaxHandleResult getPersonsByNameKeyWord(HttpServletRequest request){
        AjaxHandleResult handleResult;
        String keyWord = request.getParameter("keyWord");
        if(TextUtils.isEmpty(keyWord)){
            logger.error("Request parameter missing.");
            handleResult = new AjaxHandleResult(false, "参数缺失，搜索失败");
            return handleResult;
        }

        List<Person> persons = personService.getPersonsByNameKeyWord(keyWord);
        handleResult = new AjaxHandleResult(true, persons);
        return handleResult;
    }
}
