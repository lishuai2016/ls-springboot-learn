package com.ls.demo.common;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 18:38
 */
public class Status {
    //======================================入参检测
    // 入参文件名有误
    public static final ApiStatus PARAMETER_FILE_NAME_ERROR = ApiStatus.newErrorApi(311, "入参有误", null);
    // 操作类型有误
    public static final ApiStatus PARAMETER_OPERATE_TYPE_ERROR = ApiStatus.newErrorApi(312, "操作类型有误", null);
    // 服务器内部错误
    public static final ApiStatus SERVER_INTERNAL_ERROR = ApiStatus.newErrorApi(500, "服务器内部错误", null);
    // 托底未捕获错误
    public static final ApiStatus SERVER_UNKNOWN_ERROR = ApiStatus.newErrorApi(501, "未捕获异常", null);

    //===================================操作过程
    // 文件不存在
    public static final ApiStatus FILE_NOT_EXIST_ERROR = ApiStatus.newErrorApi(321, "文件不存在", null);
    // 文件已存在
    public static final ApiStatus FILE_EXIST_ERROR = ApiStatus.newErrorApi(322, "文件已存在", null);

    // 移动失败
    public static final ApiStatus MOVE_ERROR = ApiStatus.newErrorApi(323, "移动失败", null);

    // 操作异常
    public static final ApiStatus OPERATE_EXCEPTION_ERROR = ApiStatus.newErrorApi(324, "操作异常", null);

    // 正常
    public static final ApiStatus OK = ApiStatus.newOkApi();

    //=========================================安全相关
    // 未找到
    public static final ApiStatus SECURITY_UN_GET = ApiStatus.newErrorApi(410, "没有找到secretKey/accessKey", null);

    // 无效
    public static final ApiStatus SECURITY_UN_VALID = ApiStatus.newErrorApi(411, "无效的secretKey/accessKey", null);

    // 无权限的
    public static final ApiStatus SECURITY_NO_AUTH = ApiStatus.newErrorApi(412, "无权限的secretKey/accessKey", null);

    // 签名过期
    public static final ApiStatus SECURITY_KEY_EXPIRE = ApiStatus.newErrorApi(413, "secretKey/accessKey过期", null);

    // 无效的IP
    public static final ApiStatus SECURITY_UN_VALID_IP = ApiStatus.newErrorApi(414, "此IP没有访问权限，请申请权限后在使用", null);

    // 文件下载次数太多
    public static final ApiStatus SECURITY_REQUEST_TOO_FREQUENCY= ApiStatus.newErrorApi(414, "文件下载次数太多，超出了限制", null);

    static {
    }
}
