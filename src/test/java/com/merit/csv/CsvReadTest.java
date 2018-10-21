package com.merit.csv;

import com.csvreader.CsvReader;
import com.merit.entity.Person;
import com.merit.entity.Project;
import com.merit.entity.Sector;
import com.merit.service.PersonService;
import com.merit.service.ProjectService;
import com.merit.service.RoleService;
import com.merit.service.SectorService;
import com.merit.utils.TextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by R on 2018/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class CsvReadTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SectorService sectorService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleService roleService;

    @Test
    public void readSectorInfoAndSaveToDatabase(){
        String filePath = "C:\\Users\\R\\Desktop\\部门信息.csv";
        try{
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            csvReader.readRecord();
            while (csvReader.readRecord()){
                String rowData = csvReader.getRawRecord();
                String[] cols = rowData.split(",", -1);
                Sector sector = new Sector();
                sector.setSectId(Integer.valueOf(cols[0]));
                sector.setSectName(cols[1]);
                sector.setPersNum(cols[2]);
                if(!TextUtils.isEmpty(cols[3])){
                    sector.setSectMana(Integer.valueOf(cols[3]));
                }else{
                    sector.setSectMana(0);
                }
                boolean res = sectorService.addSector(sector);
                if(res)
                    System.out.println(sector);
            }
        }catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void readProjectInfoAndSaveToDatabase(){
        String filePath = "C:\\Users\\R\\Desktop\\项目信息.csv";
        try{
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            csvReader.readRecord();
            while (csvReader.readRecord()){
                String rowData = csvReader.getRawRecord();
                String[] cols = rowData.split(",", -1);
                Project project = new Project();
                project.setProjName(cols[0]);
                project.setProjId(Integer.valueOf(cols[1]));
                if(!TextUtils.isEmpty(cols[2])){
                    Sector sector = sectorService.getSectorByName(cols[2]);
                    if(null != sector){
                        project.setSectId(sector.getSectId());
                    }

                }
                if(!TextUtils.isEmpty(cols[4]))
                project.setProjMana(Integer.valueOf(cols[4]));
                boolean res = projectService.addProject(project);
                if(res)
                    System.out.println(project);
            }
        }catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void readPersonInfoAndSaveToDatabase(){
        String filePath = "C:\\Users\\R\\Desktop\\人员信息.csv";
        try{
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            csvReader.readRecord();
            while (csvReader.readRecord()){
                String rowData = csvReader.getRawRecord();
                String[] cols = rowData.split(",", -1);
                Person person = new Person();
                person.setEmplId(Integer.valueOf(cols[0]));
                if(!TextUtils.isEmpty(cols[1])){
                    Sector sector = sectorService.getSectorByName(cols[1]);
                    if(null != sector){
                        person.setSectId(sector.getSectId());
                    }

                }
                person.setName(cols[2]);
                if("nodata".equals(cols[3])){
                    person.setSex(0);
                }else if("男".equals(cols[3])){
                    person.setSex(1);
                }else if("女".equals(cols[3])){
                    person.setSex(0);
                }
                person.setPhonNum(cols[4]);
                boolean res = personService.addPerson(person);
                if(res)
                    System.out.println(person);
            }
        }catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void setSectorManaAndProjMana(){
        //设置部门经理
        List<Map> sectorList = sectorService.getAllSectorsMap();
        for(Map sector : sectorList){
            int emplId;
            if(null ==  sector.get("SECT_MANA")){
                continue;
            }
            emplId  = (Integer)sector.get("SECT_MANA");
            Person person = personService.getPersonByEmplId(emplId);
            if(null != person){
                roleService.setPersonRole(person.getEmplId(), "部门经理");
            }
            System.out.println(sector);
        }
        //设置项目经理
        List<Project> projectList = projectService.getAllProject();
        for(Project project : projectList){
            int emplId = Integer.valueOf(project.getProjMana());
            Person person = personService.getPersonByEmplId(emplId);
            if(null != person){
                roleService.setPersonRole(person.getEmplId(), "项目经理");
            }
        }
    }

    @Test//从表格中更新人员的部门信息
    public void sss(){
        String filePath = "C:\\Users\\R\\Desktop\\人员信息.csv";
        try{
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            csvReader.readRecord();
            while (csvReader.readRecord()){
                String rowData = csvReader.getRawRecord();
                String[] cols = rowData.split(",", -1);

                int emplId = Integer.valueOf(cols[0]);
                String sectorName = cols[1];
                int sectId = sectorService.getSectorByName(sectorName).getSectId();
                Person person = personService.getPersonByEmplId(emplId);
                person.setSectId(sectId);
                personService.updatePerson(person);
            }
        }catch (IOException e){
            logger.error(e.getMessage(), e);
        }
    }
}
