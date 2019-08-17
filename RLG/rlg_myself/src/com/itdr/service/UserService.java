package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

//业务层调用数据层
public class UserService {
    private UserDao ud = new UserDao();
    //获取用户列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        //设置默认值
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        //调用数据层
        List<Users> li = ud.selectAll(pageSize,pageNum);

        ResponseCode rs = ResponseCode.successRS(li);

        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = null;
        if (username == null || username.equals("")||password == null || password.equals("")){
            rs = ResponseCode.defeatedRS(1,"参数为空 ");
            return rs;
        }

        //查找是否有这样一个用户
        Users u = ud.selectOne(username,password);

        //如果用户不存在
        if (u == null){
            rs = ResponseCode.defeatedRS(1,"账号或密码错误 ");
            return rs;
        }

        //如果用户存在 ，判断用户权限（是否是管理员）
        if (u.getType() != 1){//管理员是1
            rs = ResponseCode.defeatedRS(2,"没有操作权限 ");
            return rs;
        }

        //如果用户存在 ，又有操作权限
        rs = ResponseCode.successRS(u);
        return rs;
    }

    //用户禁用
    public ResponseCode selectOne(String uids) {
        ResponseCode rs = new ResponseCode();

        if (uids == null || uids.equals("")){
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer uid = null;
        try {//因为上面即使传进来一个abc，上面的判断也不会认为有错，在转型的时候就会报错，所以try/catch
             uid = Integer.parseInt(uids);
        }catch (Exception e){
            rs = ResponseCode.defeatedRS(105,"输入非法参数");
            return rs;
        }

        //查找是否有这样一个用户
        Users u = ud.selectOne(uid);


        //如果用户不存在
        if (u == null){
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);
            return rs;
        }

        //判断用户禁用情况
        if (u.getStats() == 1){// 如果等于1说明已经被禁用了
            rs = ResponseCode.defeatedRS(Const.USER_DISABLE_CODE,Const.USER_DISABLE_MSG);
            return rs;
        }

        //如果用户存在 ，又没有被禁用的时候 就禁用用户
        int row = ud.updateByUid(uid);//uid传进来应该是一个 Integer的值,最终返回一个int类型的值
        if (row <= 0){//如果小于等于0，代表禁用用户的过程失败了
            rs = ResponseCode.defeatedRS(106,"用户禁用更新失败");
            return rs;
        }

        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }


}
