package com.example.tushu.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class result<T> {

    private Integer code;
    private String msg;
    private Object data;

    public result(){

    }
    public result(Integer Code,String Msg){
        this.code = Code;
        this.msg = Msg;
    }
    public result(Integer Code,String Msg,Object Data){
        this.code = Code;
        this.msg = Msg;
        this.data = Data;
    }
    public static result err(){
        result res = new result();
        res.code = -200;
        res.msg = "失败";
        return res;
    }
    public static result err(Object data){
        result res = new result<>();
        res.code = -200;
        res.msg = "失败";
        res.data = data;
        return res;
    }

    //固定返回值 公开 任何地方都可以调用
    public static <T> result<T> ok(){
        result<T> r = new result<>();
        r.code = 200;
        r.msg = "成功";
        return r;
    }
    public static <T> result<T> ok(Object data){
        result<T> r = new result<>();
        r.code = 200;
        r.msg = "成功";
        r.data = data;
        return r;
    }


}
