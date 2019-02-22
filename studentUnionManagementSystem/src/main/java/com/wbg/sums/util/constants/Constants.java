package com.wbg.sums.util.constants;

/**
 * @author: WBG
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2019/02/18 08:15
 */
public class Constants {

	public static final int SUCCESS_CODE = 200;//（成功） 服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
	public static final String SUCCESS_MSG = "请求成功";

	public static final int FAIL_CODE = 202;	//服务器已接受请求，但尚未处理。
	public static final String FAIL_MSG = "请求失败";

	public static final int VALIDATION_FAILED_CODE = 202;//主要用于判断验证  列如登录
	public static final String VALIDATION_FAILED_MSG = "验证失败";

	public static final int EXCEPTION_CODE = 400;
	public static final String EXCEPTION_MSG = "错误请求";

	public static final int ERROR_CODE = 500;
	public static final String ERROR_MSG = "请求方式有误,请检查 GET/POST";

	public static final int NOT_URL_CODE = 404;
	public static final String NOT_URL_MSG = "请求路径不存在";

	public static final int INSUFFICIENT_AUTHORITY_CODE = 502;
	public static final String INSUFFICIENT_AUTHORITY_MSG = "权限不足";

	public static final int LOGON_EXPIRATION_CODE = 20011;
	public static final String LOGON_EXPIRATION_MSG = "未登录或登陆已过期";


}
