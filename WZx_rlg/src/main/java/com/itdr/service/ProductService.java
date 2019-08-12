package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Product;
import com.itdr.pojo.Users;

import java.util.List;


public class ProductService {
    ProductDao pd = new ProductDao();

    //搜索商品
    public ResponseCode search_product(String productName) {
        ResponseCode rs = new ResponseCode();

        if (productName == null || productName.equals("")){
            rs.setStatus(108);
            rs.setMag("请输入您要查询的商品名！");
            return rs;
        }

        List<Product> li = pd.search_product(productName);

        if (li.size() == 0){
            rs.setStatus(Const.PRODUCT_SEARCH_CODE);
            rs.setMag(Const.PRODUCT_SEARCH_MSG);
            return  rs;
        }

        rs = ResponseCode.successRS(li);
        return rs;
    }

    //商品详情
    public ResponseCode product_detail(String productId) {
        ResponseCode rs = new ResponseCode();

        if (productId == null || productId.equals("")){
            rs.setStatus(108);
            rs.setMag("请输入您要查询的商品id！");
            return rs;
        }

        Product product = pd.product_detail(productId);

        if (product == null){
            rs.setStatus(Const.PRODUCT_SEARCH_CODE);
            rs.setMag(Const.PRODUCT_SEARCH_MSG);
            return  rs;
        }

        rs.setStatus(0);
        rs.setData(product);
        rs.setMag("您已成功查询到该商品！");
        return rs;
    }

    //增加/更新商品
    public ResponseCode add_product(String productName, String parentId, String status) {

        ResponseCode rs = new ResponseCode();
        //设置默认值
        if (parentId == null || parentId.equals("")){
            parentId = "0";
        }
        if (productName == null || productName.equals("")){
            rs.setStatus(0);
            rs.setMag("请输入您要添加的商品名称");
            return rs;
        }

        List<Product> li = pd.add_product(parentId,productName,status);
        //如果商品分类存在
        if (li.size() != 0){
            rs.setStatus(Const.PRODUCT_HAVE_CODE);
            rs.setMag(Const.PRODUCT_HAVE_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setMag("商品添加成功");
        return rs;
    }

    //商品上下架，修改商品状态
    public ResponseCode set_sale_status(String productId) {
        ResponseCode rs = new ResponseCode();

        if (productId == null || productId.equals("")){
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }
        //字符串转数值
        Integer pid = null;
        try {//因为上面即使传进来一个abc，上面的判断也不会认为有错，在转型的时候就会报错，所以try/catch
            pid = Integer.parseInt(productId);
        }catch (Exception e){
            rs = ResponseCode.defeatedRS(109,"输入非法参数");
            return rs;
        }
        //查找是否有这样一个用户
        Product product = pd.product_detail(productId);
        //如果用户不存在
        if (product == null){
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);//消息码有问题
            return rs;
        }



        int row = pd.set_sale_status(productId);//uid传进来应该是一个 Integer的值,最终返回一个int类型的值
        if (row <= 0){//如果小于等于0，代表禁用用户的过程失败了
            rs = ResponseCode.defeatedRS(106,"商品下架失败");
            return rs;
        }

        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }

    //商品上架
    public ResponseCode set_sale_statusSJ(String productId) {
        ResponseCode rs = new ResponseCode();

        if (productId == null || productId.equals("")){
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }
        //字符串转数值
        Integer pid = null;
        try {//因为上面即使传进来一个abc，上面的判断也不会认为有错，在转型的时候就会报错，所以try/catch
            pid = Integer.parseInt(productId);
        }catch (Exception e){
            rs = ResponseCode.defeatedRS(109,"输入非法参数");
            return rs;
        }
        //查找是否有这样一个用户
        Product product = pd.product_detail(productId);
        //如果用户不存在
        if (product == null){
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);//消息码有问题
            return rs;
        }
        int row = pd.set_sale_statusSJ(productId);//uid传进来应该是一个 Integer的值,最终返回一个int类型的值
        if (row <= 0){//如果小于等于0，代表禁用用户的过程失败了
            rs = ResponseCode.defeatedRS(106,"商品上架失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }


    //商品列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        //设置默认值
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        //调用数据层
        List<Product> li = pd.selectAll(pageSize,pageNum);

        ResponseCode rs = ResponseCode.successRS(li);

        return rs;
    }




}
