package com.isoft.dao.impl;

import com.isoft.dao.IUserDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
    @Autowired
    SqlSessionFactory sqlSessionFactory;


    @Override
    public int registe(String t_cname,String t_gender,String t_mobile,String t_email) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement = "com.isoft.mapping.userMapper.insertUser";
        Map map = new HashMap();
        map.put("t_cname", t_cname);
        map.put("t_gender", t_gender);
        map.put("t_mobile", t_mobile);
        map.put("t_email", t_email);
//        System.out.println(map);
        int i = sqlSession.insert(statement, map);
        return i;
    }

    @Override
    public List<Map<String, String>> findAllUser(Map map) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.findAllUser";
        List<Map<String, String>> list = sqlSession.selectList(statement,map);
//        System.out.println(list);
        return list;
    }

    @Override
    public int userCount(Map map) {

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement = "com.isoft.mapping.userMapper.findCountUser";
        int i = sqlSession.selectOne(statement,map);

        return i;
    }

    @Override
    public int deleteBenchById(String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement = "com.isoft.mapping.userMapper.deleteBenchById";
        int i = sqlSession.delete(statement,id);
        return i;
    }

    @Override
    public int updateUser(String t_cname,String id,String t_email,String t_mobile,String t_gender ) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.updateUser";
        Map map = new HashMap();
        map.put("t_mobile",t_mobile);
        map.put("t_cname", t_cname);
        map.put("t_gender",t_gender);
        map.put("id",id);
        map.put("t_email", t_email);
       int i = sqlSession.update(statement,map);
        System.out.println(map);
        return i;
    }

    @Override
    public int updatePerson(String t_cname,String id,String t_email,String t_mobile,String t_gender ) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.updatePerson";
        Map map = new HashMap();
        map.put("t_mobile",t_mobile);
        map.put("t_cname", t_cname);
        map.put("t_gender",t_gender);
        map.put("id",id);
        map.put("t_email", t_email);
        int i = sqlSession.update(statement,map);
        System.out.println(map);
        return i;
    }

    @Override
    public List<Map<String, String>> userGenderAnalysis() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.userGenderAnalysis";

        List<Map<String, String>> list = sqlSession.selectList(statement);
        System.out.println(list);
        return list;
    }

    @Override
    public int updatePhoto(Map map) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.updatePhoto";
        int i = sqlSession.update(statement,map);
        System.out.println(map);
        return i;
    }

    @Override
    public List<Map<String, String>> loadHeadPhoto(Map map) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        System.out.println(map+"dao");

        String statement  = "com.isoft.mapping.userMapper.loadHeadPhoto";
        List<Map<String, String>> list =sqlSession.selectList(statement,map);

        return list;
    }

    //    周
    //查询设备表单信息
    @Override
    public List<Map<String, String>> deviceList(Map map) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findDeviceAll";
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        return list;
    }
    //查询设备表单数量，做分页
    @Override
    public int deviceCount() {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findCountDevice";
        int count=sqlSession.selectOne(statement);
        return count;
    }
    //登录查询用户名和密码
    @Override
    public List<Map<String, String>> login(String uname, String upwd) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findUserByUnameAndUpwd";
        Map map=new HashMap();
        map.put("uname",uname);
        map.put("upwd",upwd);
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        return list;
    }

    //新用户注册
    @Override
    public int signup(String signupname,String signuppwd,String role){
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.signup";
        Map map=new HashMap();
        map.put("signupname",signupname);
        map.put("signuppwd",signuppwd);
        map.put("role",role);
        int i=sqlSession.insert(statement,map);
        return i;
    }

    //老用户按email找回密码
    @Override
    public List<Map<String,String>> retrieveByEmail(String email) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findEmail";
        Map map=new HashMap();
        map.put("email",email);
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        return list;
    }
    //老用户按phone找回密码
    @Override
    public List<Map<String,String>> retrieveByPhone(String phone) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findPhone";
        Map map=new HashMap();
        map.put("phone",phone);
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        return list;
    }
    //按照email修改密码
    @Override
    public int modifyPasswordByEmail(String email,String newPassword) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.modifyPasswordByEmail";
        Map map=new HashMap();
        map.put("email",email);
        map.put("newPassword",newPassword);
        int i = sqlSession.update(statement,map);
        return i;
    }
    //按照email修改密码
    @Override
    public int modifyPasswordByPhone(String phone,String newPassword) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.modifyPasswordByPhone";
        Map map=new HashMap();
        map.put("phone",phone);
        map.put("newPassword",newPassword);
        int i = sqlSession.update(statement,map);
        return i;
    }
    //添加设备
    @Override
    public int addDevice(String t_equip_id,String t_agency_id,String t_device_name,String t_buy_date,String t_check_date) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement = "com.isoft.mapping.userMapper.insertDevice";
        Map map = new HashMap();
        map.put("t_equip_id", t_equip_id);
        map.put("t_agency_id", t_agency_id);
        map.put("t_device_name", t_device_name);
        map.put("t_buy_date", t_buy_date);
        map.put("t_check_date", t_check_date);
        int i = sqlSession.insert(statement, map);
        return i;
    }
    //根据id删除用户
    @Override
    public int deleteUserById(String id) {
        try{
            SqlSession sqlSession=sqlSessionFactory.openSession(true);
            String statement="com.isoft.mapping.userMapper.deleteUserById";
            int i=sqlSession.delete(statement,id);
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //修改设备信息
    @Override
    public int updateDevice(String t_equip_id, String t_agency_id, String t_dic_code,String t_device_name,
                            String t_dtype, String t_factory,String t_dnumber, String t_buy_date,
                            String t_check_date, String t_sid, String t_left_day, String t_check_circle) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.updateDevice";
        Map map = new HashMap();
        map.put("t_equip_id",t_equip_id);
        map.put("t_agency_id", t_agency_id);
        map.put("t_dic_code",t_dic_code);
        map.put("t_device_name", t_device_name);
        map.put("t_dtype",t_dtype);
        map.put("t_factory", t_factory);
        map.put("t_dnumber",t_dnumber);
        map.put("t_buy_date", t_buy_date);
        map.put("t_check_date",t_check_date);
        map.put("t_sid", t_sid);
        map.put("t_left_day",t_left_day);
        map.put("t_check_circle", t_check_circle);
        int i = sqlSession.update(statement,map);
        return i;
    }
    //查询日志信息
    @Override
    public List<Map<String, String>> logList(Map map) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findLogAll";
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        return list;
    }
    //查询日志表单数量，做分页
    @Override
    public int logCount() {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findCountLog";
        int count=sqlSession.selectOne(statement);
        return count;
    }
    //添加日志
    @Override
    public int addLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement = "com.isoft.mapping.userMapper.insertLog";
        Map map = new HashMap();
        map.put("t_log_id", t_log_id);
        map.put("id", id);
        map.put("t_equip_id", t_equip_id);
        map.put("t_log_addTime", t_log_addTime);
        map.put("t_fix_content", t_fix_content);
        int i = sqlSession.insert(statement, map);
        return i;
    }
    //根据id删除日志
    @Override
    public int deleteLogById(String id) {
        try{
            SqlSession sqlSession=sqlSessionFactory.openSession(true);
            String statement="com.isoft.mapping.userMapper.deleteLogById";
            int i=sqlSession.delete(statement,id);
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //修改日志信息
    @Override
    public int updateLog(String t_log_id, String id, String t_equip_id, String t_log_addTime, String t_fix_content) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.updateLog";
        Map map = new HashMap();
        map.put("t_log_id",t_log_id);
        map.put("id", id);
        map.put("t_equip_id",t_equip_id);
        map.put("t_log_addTime", t_log_addTime);
        map.put("t_fix_content",t_fix_content);
        int i = sqlSession.update(statement,map);
        return i;
    }
    //维修设备
    @Override
    public int repairDevice(String t_equip_id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.repairDevice";
        Map map=new HashMap();
        map.put("t_equip_id",t_equip_id);
        int i=sqlSession.update(statement,map);
        return i;
    }
    //维修日志
    @Override
    public int repairLog(String t_equip_id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        String statement  = "com.isoft.mapping.userMapper.repairLog";
        Map map=new HashMap();
        map.put("t_equip_id",t_equip_id);
        int i=sqlSession.update(statement,map);
        return i;
    }

    //    陈
//查询设备表单信息
    @Override
    public List<Map<String, String>> agencyList(Map map) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        System.out.println("agency ser");
        String statement="com.isoft.mapping.userMapper.findAgencyAll";
        List<Map<String,String>> list = sqlSession.selectList(statement,map);
        System.out.println("dao"+list);
        return list;
    }
    //查询设备表单数量，做分页
    @Override
    public int agencyCount() {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        String statement="com.isoft.mapping.userMapper.findCountAgency";
        int count=sqlSession.selectOne(statement);
        return count;
    }


}
