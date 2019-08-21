

package com.isoft.action;

import com.isoft.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserAction {
    @Autowired
    UserServiceImpl userServiceImpl;


    @RequestMapping(value = "/registe.do", method = RequestMethod.POST)
    @ResponseBody
    public int registe(String t_cname, String t_gender, String t_mobile, String t_email) {
        System.out.println(t_cname + t_gender + "regist");

        int i = userServiceImpl.registe(t_cname, t_gender, t_mobile, t_email);
        return i;
    }

    @RequestMapping(value = "/findAllUser.do")
    @ResponseBody
    public Map<String, Object> findAllUser(String t_cname, int page, int limit) {
//       System.out.println(t_cname);
//        System.out.println("action");
        Map map = new HashMap();
        if (t_cname != null)
            map.put("t_cname", t_cname);
        map.put("recordIndex", (page - 1) * limit);
        map.put("pagesize", limit);
        int count = userServiceImpl.userCount(map);
        List<Map<String, String>> list = userServiceImpl.findAllUser(map);
        map.clear();
        map.put("msg", "");
        map.put("code", 0);
        map.put("data", list);
        map.put("count", count);
        return map;

    }

    //    查询头像
    @RequestMapping(value = "/loadHeadPhoto.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadHeadPhoto(HttpSession session) {

        String uname = session.getAttribute("uname").toString();
//        System.out.println(uname+"");
        Map map = new HashMap();
        map.put("uname", uname);
        List<Map<String, String>> list = userServiceImpl.loadHeadPhoto(map);
        map.clear();
        map.put("msg", "");
        map.put("code", 0);
        map.put("data", list);
        map.put("count", 1);
        System.out.println("actmap" + map);
        return map;

    }

    @RequestMapping(value = "/uploadPhoto.do", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadPhoto(MultipartFile file, HttpSession session) throws IOException {
        System.out.println("action--uploadPhoro");
        System.out.println("upload" + file.getOriginalFilename());
        ArrayList allowPhotoTypelist = new ArrayList();
        allowPhotoTypelist.add(".jpg");
        allowPhotoTypelist.add(".png");
        allowPhotoTypelist.add(".bmp");
        allowPhotoTypelist.add(".jpeg");
        allowPhotoTypelist.add(".gif");
        Map map = new HashMap();
        String fileName = file.getOriginalFilename();
        String exName = fileName.substring(fileName.lastIndexOf("."));

        if (allowPhotoTypelist.contains(exName)) {
            String realPath = session.getServletContext().getRealPath("images");
            File target = new File(realPath);
            if (!target.exists())
                target.mkdir();
            String newFileName = new Date().getTime() + exName;
            target = new File(realPath, newFileName);
            file.transferTo(target);
            map.put("uname", session.getAttribute("uname").toString());
            map.put("photo", "images/" + newFileName);
            userServiceImpl.updatePhoto(map);
            map.clear();
            map.put("code", 0);
        } else
            map.put("code", 2);

        return map;
    }

    @RequestMapping(value = "/manPercent.do")
    @ResponseBody
    public double manPercent() {
        List<Map<String, String>> list = userServiceImpl.userGenderAnalysis();
        int count = 0;
        int man = 0;
        for (Map map1 : list) {
            if (map1.get("t_gender").toString().equals("男")) {
                man = Integer.parseInt(map1.get("count").toString());
            }
            count += Integer.parseInt(map1.get("count").toString());
        }
        return (double) man / count;

    }

    @RequestMapping(value = "/userGenderAnalysis.do")
    @ResponseBody
    public Map<String, String> userGenderAnalysis() {
        List<Map<String, String>> list = userServiceImpl.userGenderAnalysis();

        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        for (Map map1 : list) {
            list1.add(map1.get("t_gender"));
            list2.add(map1.get("count"));
        }

        Map map = new HashMap();
        map.put("t_gender", list1);
        map.put("count", list2);
        return map;

    }

    @RequestMapping(value = "/deleteBenchById.do")
    @ResponseBody
    public String deleteBenchById(String id) {
        System.out.println(id + "delete");
        int i = 0;
        String[] arr = id.split(",");
        for (String str : arr) {
            i = userServiceImpl.deleteBenchById(str);
        }
        if (i > 0)
            return "success";
        else
            return "fault";
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(String t_cname, String id, String t_email, String t_mobile,String t_gender) {

        int i = userServiceImpl.updateUser(t_cname, id, t_email, t_mobile,t_gender);
        if (i > 0)
            return "success";
        else
            return "fault";
    }
    @RequestMapping(value = "/updatePerson.do", method = RequestMethod.POST)
    @ResponseBody
    public String updatePerson(String t_cname, String id, String t_email, String t_mobile,String t_gender) {

        int i = userServiceImpl.updateUser(t_cname, id, t_email, t_mobile,t_gender);
        if (i > 0)
            return "admin.html";
        else
            return "fault";
    }


    //    周
//查询设备表单信息
    @RequestMapping(value = "/deviceList.do")
    @ResponseBody
    public Map<String, Object> searchDeviceList(String t_device_name, int page, int limit) {
        Map map = new HashMap();
        map.put("t_device_name", t_device_name);
        map.put("recordIndex", (page - 1) * limit);
        map.put("pagesize", limit);
        List<Map<String, String>> list = userServiceImpl.deviceList(map);
        int count = userServiceImpl.deviceCount();
        map.clear();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }

    //登录查询用户名和密码
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("uname") String uname, String upwd, HttpSession session) {
        List<Map<String, String>> list = userServiceImpl.login(uname, upwd);
        if (list.size() > 0) {
            session.setAttribute("uname", uname);
            return "success";
        } else {
            return "fails";
        }
    }

    //新用户注册
    @RequestMapping(value = "/signup.do")
    @ResponseBody
    public String signup(String signupname, String signuppwd, String role) {
        int i = userServiceImpl.signup(signupname, signuppwd, role);
        if (i > 0) {
            return "success";
        } else {
            return "fails";
        }
    }
    //老用户按email找回密码
    @RequestMapping(value = "/retrieveByEmail.do")
    @ResponseBody
    public String retrieveByEmail(@RequestParam("email") String email) {
        List<Map<String, String>> list = userServiceImpl.retrieveByEmail(email);
        if (list.size() > 0) {
            return "success";
        } else
            return "false";
    }
    //老用户按phone找回密码
    @RequestMapping(value = "/retrieveByPhone.do")
    @ResponseBody
    public String retrieveByPhone(@RequestParam("phone") String phone) {
        List<Map<String, String>> list = userServiceImpl.retrieveByPhone(phone);
        if (list.size() > 0) {
            return "success";
        } else
            return "false";
    }
    //按照email修改密码
    @RequestMapping(value = "/modifyPasswordByEmail.do")
    @ResponseBody
    public String modifyPasswordByEmail(String email,String newPassword){
        int i=userServiceImpl.modifyPasswordByEmail(email,newPassword);
        if (i > 0) {
            return "success";
        } else
            return "false";
    }
    //按照phone修改密码
    @RequestMapping(value = "/modifyPasswordByPhone.do")
    @ResponseBody
    public String modifyPasswordByPhone(String phone,String newPassword){
        int i=userServiceImpl.modifyPasswordByPhone(phone,newPassword);
        if (i > 0) {
            return "success";
        } else
            return "false";
    }
    //添加设备
    @RequestMapping(value = "/addDevice.do", method = RequestMethod.POST)
    @ResponseBody
    public String addDevice(String t_equip_id, String t_agency_id, String t_device_name, String t_buy_date, String t_check_date) {
        int i = userServiceImpl.addDevice(t_equip_id, t_agency_id, t_device_name, t_buy_date, t_check_date);
        if(i>0){
            return "添加设备成功！";
        }else{
            return "添加失败！";
        }
    }
    //根据id删除用户
    @RequestMapping(value = "/deleteUserById.do")
    @ResponseBody
    public String deleteUserById(String id) {
        String[] arr=id.split(",");
        int i=0;
        for(String str:arr){
            i = userServiceImpl.deleteUserById(str);
        }
        if (i > 0) {
            return "success";
        } else {
            return "fails";
        }
    }
    //更新设备
    @RequestMapping(value = "/modifyDevice.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateDevice(String t_equip_id, String t_agency_id, String t_dic_code, String t_device_name,
                               String t_dtype,String t_factory,String t_dnumber,String t_buy_date,
                               String t_check_date,String t_sid,String t_left_day,String t_check_circle) {
        int i = userServiceImpl.updateDevice(t_equip_id, t_agency_id, t_dic_code, t_device_name, t_dtype, t_factory,
                t_dnumber, t_buy_date, t_check_date, t_sid,t_left_day,t_check_circle);
        if (i > 0)
            return "更新成功!";
        else
            return "更新失败!";
    }
    //查询日志信息
    @RequestMapping(value = "/logList.do")
    @ResponseBody
    public Map<String, Object> searchLogList(String t_log_id, int page, int limit) {
        Map map = new HashMap();
        map.put("t_log_id", t_log_id);
        map.put("recordIndex", (page - 1) * limit);
        map.put("pagesize", limit);
        List<Map<String, String>> list = userServiceImpl.logList(map);
        int count = userServiceImpl.logCount();
        map.clear();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //添加日志
    @RequestMapping(value = "/addLog.do")
    @ResponseBody
    public String addLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content) {
        int i = userServiceImpl.addLog(t_log_id, id, t_equip_id, t_log_addTime, t_fix_content);
        if(i>0){
            return "success";
        }else{
            return "添加失败";
        }
    }
    //根据id删除日志
    @RequestMapping(value = "/deleteLogById.do")
    @ResponseBody
    public String deleteLogById(String id) {
        String[] arr=id.split(",");
        int i=0;
        for(String str:arr){
            i = userServiceImpl.deleteLogById(str);
        }
        if (i > 0) {
            return "success";
        } else {
            return "fails";
        }
    }
    //更新日志
    @RequestMapping(value = "/modifyLog.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateLog(String t_log_id, String id, String t_equip_id, String t_log_addTime,String t_fix_content) {
        int i = userServiceImpl.updateLog(t_log_id, id, t_equip_id, t_log_addTime, t_fix_content);
        if (i > 0)
            return "更新日志成功!";
        else
            return "更新失败!";
    }
    //维修设备
    @RequestMapping(value = "/repairDevice.do")
    @ResponseBody
    public String repairDevice(String t_equip_id) {
        int i = userServiceImpl.repairDevice(t_equip_id);
        if (i > 0)
            return "success";
        else
            return "fault";
    }
    //维修添加日志
    @RequestMapping(value = "/repairLog.do")
    @ResponseBody
    public String repairLog(String t_equip_id) {
        int i = userServiceImpl.repairLog(t_equip_id);
        if(i>0){
            return "success";
        }else{
            return "添加失败";
        }
    }



    //    陈
//查询供应商表单信息
    @RequestMapping(value = "/agencyList.do")
    @ResponseBody
    public Map<String, Object> searchAgencyList(String t_agency_name, int page, int limit) {
        System.out.println(t_agency_name + "action");
        System.out.println("agency action");
        Map map = new HashMap();
        map.put("t_agency_name", t_agency_name);
        map.put("recordIndex", (page - 1) * limit);
        map.put("pagesize", limit);
        List<Map<String, String>> list = userServiceImpl.agencyList(map);
        int count = userServiceImpl.agencyCount();
        map.clear();
        map.put("code", "");
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", list);
        return map;
    }

    }

