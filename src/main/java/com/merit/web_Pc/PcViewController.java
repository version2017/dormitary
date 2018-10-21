package com.merit.web_Pc;

import com.merit.service.DormitaryService;
import com.merit.service.PersonService;
import com.merit.service.ProjectService;
import com.merit.service.SectorService;
import com.merit.utils.dataobject.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/7/10.
 */
@Controller//类似@Service,@Component,把controller放入spring容器中
@RequestMapping("/pcView")
public class PcViewController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String innerErrorPage = "error";

    @Autowired
    private DormitaryService dormitaryService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String test(Model model){
        return "pc_adminLogin";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        List<String> places = dormitaryService.getDormitaryPlaces();
        model.addAttribute("places", places);
        PageInfo<Map> pageInfo = dormitaryService.getDormitaryAndProjectByPage(1);
        model.addAttribute("pageInfo", pageInfo);
        return "pc_index1";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index2(Model model){
        PageInfo<Map> pageInfo = sectorService.getSectorByPage(1);
        model.addAttribute("pageInfo", pageInfo);
        return "pc_index2";
    }

    @RequestMapping(value = "/index3", method = RequestMethod.GET)
    public String index3(Model model){
        PageInfo<Map> pageInfo = projectService.queryProjectByPage(1);
        model.addAttribute("pageInfo", pageInfo);
        return "pc_index3";
    }

    @RequestMapping(value = "/index4", method = RequestMethod.GET)
    public String index4(Model model){
        PageInfo<Map> pageInfo = personService.queryPersonMapByPage(1);
        model.addAttribute("pageInfo", pageInfo);
        return "pc_index4";
    }
}
