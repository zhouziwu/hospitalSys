package com.isoft.service.impl;


import com.isoft.dao.impl.UserDAOImpl;
import com.isoft.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.String;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserDAOImpl userDAO;



    @Override
    public int registe(String t_cname,String t_gender,String t_mobile,String t_email) {
//        System.out.println("registe ser");
        int i = userDAO.registe(t_cname, t_gender,t_mobile,t_email);
        return i;
    }

    @Override
    public List<Map<String, String>> findAllUser(Map map) {
//        System.out.println("ser");
        return userDAO.findAllUser(map);
    }

    @Override
    public int userCount(Map map) {


        return userDAO.userCount(map);
    }

    @Override
    public int deleteBenchById(String id) {
        return userDAO.deleteBenchById(id);
    }

    @Override
    public int updateUser(String t_cname,String id,String t_email,String t_mobile ,String t_gender) {
        return userDAO.updateUser(t_cname,id,t_email,t_mobile,t_gender);
    }

    @Override
    public int updatePerson(String t_cname, String id, String t_email, String t_mobile, String t_gender) {
        return userDAO.updatePerson(t_cname,id,t_email,t_mobile,t_gender);
    }

    @Override
    public List<Map<String, String>> userGenderAnalysis() {
        return userDAO.userGenderAnalysis();
    }

    @Override
    public int updatePhoto(Map map) {
        return userDAO.updatePhoto(map);
    }

    @Override
    public List<Map<String, String>> loadHeadPhoto(Map map) {
        return userDAO.loadHeadPhoto(map);
    }

//    周

    //查询设备表单信息
    @Override
    public List<Map<String, String>> deviceList(Map map) {
        return userDAO.deviceList(map);
    }
    //查询设备表单数量，做分页
    @Override
    public int deviceCount() {
        return userDAO.deviceCount();
    }
    //登录查询用户名和密码
    @Override
    public List<Map<String, String>> login(String uname, String upwd) {
        List<Map<String,String>> list=userDAO.login(uname,upwd);
        return list;
    }
    //新用户注册
    @Override
    public int signup(String signupname,String signuppwd,String role){
        int i=userDAO.signup(signupname,signuppwd,role);
        return i;
    }
    //老用户按email找回密码
    @Override
    public List<Map<String, String>> retrieveByEmail(String email) {
        return userDAO.retrieveByEmail(email);
    }
    //老用户按phone找回密码
    @Override
    public List<Map<String, String>> retrieveByPhone(String phone) {
        return userDAO.retrieveByPhone(phone);
    }
    //按照email修改密码
    @Override
    public int modifyPasswordByEmail(String email, String newPassword) {
        return userDAO.modifyPasswordByEmail(email,newPassword);
    }
    //按照phone修改密码
    @Override
    public int modifyPasswordByPhone(String phone, String newPassword) {
        return userDAO.modifyPasswordByPhone(phone,newPassword);
    }
    //添加设备
    @Override
    public int addDevice(String t_equip_id,String t_agency_id,String t_device_name,String t_buy_date,String t_check_date) {
        int i = userDAO.addDevice(t_equip_id, t_agency_id,t_device_name,t_buy_date,t_check_date);
        return i;
    }
    //根据id删除用户
    @Override
    public int deleteUserById(String id) {
        return userDAO.deleteUserById(id);
    }
    //更新设备信息
    @Override
    public int updateDevice(String t_equip_id, String t_agency_id, String t_dic_code, String t_device_name,
                            String t_dtype, String t_factory, String t_dnumber, String t_buy_date,
                            String t_check_date, String t_sid, String t_left_day, String t_check_circle) {
        return userDAO.updateDevice(t_equip_id, t_agency_id, t_dic_code, t_device_name, t_dtype, t_factory,
                t_dnumber, t_buy_date, t_check_date, t_sid,t_left_day,t_check_circle);
    }
    //查询日志信息
    @Override
    public List<Map<String, String>> logList(Map map) {
        return userDAO.logList(map);
    }
    //查询日志表单数量，做分页
    @Override
    public int logCount() {
        return userDAO.logCount();
    }
    //添加日志
    @Override
    public int addLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content) {
        return userDAO.addLog(t_log_id, id, t_equip_id, t_log_addTime, t_fix_content);
    }
    //根据id删除日志
    @Override
    public int deleteLogById(String id) {
        return userDAO.deleteLogById(id);
    }
    //修改日志信息
    @Override
    public int updateLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content) {
        return userDAO.updateLog(t_log_id, id, t_equip_id, t_log_addTime, t_fix_content);
    }
    //维修设备
    @Override
    public int repairDevice(String t_equip_id) {
        return userDAO.repairDevice(t_equip_id);
    }
    //维修添加日志
    @Override
    public int repairLog(String t_equip_id) {
        return userDAO.repairLog(t_equip_id);
    }






    //    陈

    //查询供应商表单信息
    @Override
    public List<Map<String, String>> agencyList(Map map) {
        return userDAO.agencyList(map);
    }
    //查询供应商表单数量，做分页
    @Override
    public int agencyCount() {
        return userDAO.agencyCount();
    }

}
