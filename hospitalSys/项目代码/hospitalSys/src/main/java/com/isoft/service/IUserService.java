package com.isoft.service;

import java.util.List;
import java.util.Map;

public interface IUserService {

//    插入
    int registe(String t_cname,String t_gender,String t_mobile,String t_email);
    List<Map<String, String>> findAllUser(Map map);
    int userCount(Map map);
    int deleteBenchById(String id);
    int updateUser(String t_cname,String id,String t_email,String t_mobile,String t_gender );
    int updatePerson(String t_cname,String id,String t_email,String t_mobile,String t_gender );
    List<Map<String, String>> userGenderAnalysis();
    int updatePhoto(Map map);
    List<Map<String, String>> loadHeadPhoto(Map map);
//    周

    //查询设备表单信息
    public List<Map<String,String>> deviceList(Map map);
    //查询设备表单数量，做分页
    public int deviceCount();
    //登录查询用户名和密码
    public List<Map<String,String>> login(String uname, String upwd);
    //新用户注册
    public int signup(String signupname,String signuppwd,String role);
    //老用户按email找回密码
    public List<Map<String,String>> retrieveByEmail(String email);
    //老用户按phone找回密码
    public List<Map<String,String>> retrieveByPhone(String phone);
    //按照email修改密码
    public int modifyPasswordByEmail(String email,String newPassword);
    //按照phone修改密码
    public int modifyPasswordByPhone(String phone,String newPassword);
    //添加设备
    public int addDevice(String t_equip_id,String t_agency_id,String t_device_name,String t_buy_date,String t_check_date);
    //根据id删除用户
    public int deleteUserById(String id);
    //修改设备信息
    public int updateDevice(String t_equip_id, String t_agency_id, String t_dic_code, String t_device_name,
                            String t_dtype,String t_factory,String t_dnumber,String t_buy_date,
                            String t_check_date,String t_sid,String t_left_day,String t_check_circle);
    //查询日志信息
    public List<Map<String,String>> logList(Map map);
    //查询日志数量，做分页
    public int logCount();
    //添加日志
    public int addLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content);
    //根据id删除日志
    public int deleteLogById(String id);
    //修改日志信息
    public int updateLog(String t_log_id, String id, String t_equip_id, String t_log_addTime,String t_fix_content);
    //维修设备
    public int repairDevice(String t_equip_id);
    //维修日志
    public int repairLog(String t_equip_id);


    //陈
    //查询供应商表单信息
    public List<Map<String,String>> agencyList(Map map);
    //查询供应商表单数量，做分页
    public int agencyCount();


}
