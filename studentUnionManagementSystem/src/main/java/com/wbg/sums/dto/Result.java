package com.wbg.sums.dto;

public class Result {

//    1203错误编码
//       1224正确编码，有返回数据
//       1028正确编码，无返回数据
//       1222 正确编码 无错误  用于判断删除成功或者失败

    /**
     * 当修改添加删除 成功的时候，统一使用successMessage方法   编码为1028  否则就是没有成功
     *
     */


    //状态码
    int code;
    //数据
    Object data;
    //消息提示
    String message;

    public Result() {
    }

    public Result(String message) {
        this.code=1222;
        this.message = message;
    }

    //数量
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Result(int code, String message, Object data, int count) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.count = count;
    }
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * successMessage
     * 正常返回，携带消息
     * code:1028
     *
     * @param message 消息
     *                data:null
     *                count:0
     * @return
     */
    public static Result successMessage(String message) {
        return new Result(1028, message);
    }


    /**
     * success
     * 成功方法 带数据返回
     * code:1224
     *
     * @param data    数据
     * @param count     总数
     * @return
     */
    public static Result success(Object data, int count) {
        return new Result(1224, "success", data, count);
    }

    /**
     * success
     * 成功方法 带数据返回
     * code:1224
     * message: success
     *
     * @param data 数据
     *             count :0
     * @return
     */
    public static Result success(Object data) {
        return new Result(1224, "success", data, 0);
    }

    /**
     * error
     * code:203
     * data:null
     * count:0
     *
     * @param message 错误信息
     * @return
     */
    public static Result error(String message) {
        return new Result(1203, message);
    }

}
