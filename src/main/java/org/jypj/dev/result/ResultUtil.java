package org.jypj.dev.result;

/**
 * 结果集工具类
 *
 * @author yu_chen
 * @date 2017-12-1 18:09:45
 **/
public class ResultUtil {

    /**
     * 成功
     *
     * @param object 传递的数据对象
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setData(object);
        return result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result fail(int code, String msg) {
        return new Result(code, msg);
    }

    /**
     * 失败
     * code =fail code
     * msg 需要自定义
     *
     * @param msg
     * @return
     */
    public static Result fail(String msg) {
        return new Result(ResultEnum.FAIL.getCode(), msg);
    }

    /**
     * 提示接口调用失败的方法
     *
     * @param resultEnum 枚举对象
     * @return
     */
    public static Result fail(ResultEnum resultEnum) {

        return fail(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 不需要知道状态情况下调用此方法
     *
     * @return new Result(-1,"接口调用失败")
     */
    public static Result fail() {
        return fail(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage());
    }
}
