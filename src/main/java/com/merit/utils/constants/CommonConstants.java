package com.merit.utils.constants;


import java.util.Locale;

/**
 * Created by jack on 2017/1/26.
 */
public final class CommonConstants {
    public static final String I18N_RECORD_ON_KEY = "i18n.record.on";
    public static final String MSGTYPE_DEFAULT_VALUE = "UNDEFINE_TYPE";
    public static final String MSGTYPE_ERROR_MESSAGE = "ERROR_MESSAGAE";
    public static final String MSGTYPE_SEEDOPLATFORM_MESSAGE = "SEEDOPLATFORM_MESSAGAE";
    public static final String DEFAULT_PREFERRED_LOCALE = Locale.getDefault().getLanguage() +
            "_" + Locale.getDefault().getCountry();
    /*
     * 默认分页大小
     */
    public static int PAGERESULTINFO_PAGE_SIZE = 10;
    /*
     * 分页最大数量
     */
    public static int PAGERESULTINFO_PAGE_SIZE_MAX = 100;
    /*
	 * Loginfo使用bean对象属性新增、删除、修改时过滤属性的类型(策略)
	 */
    public static final String FILTER_TYPE_INCLUDE = "include";
    public static final String FILTER_TYPE_EXCLUDE = "exclude";
    /*
	 * 每张表中的createBy,updateBy如果当前没有登录者id，则系统自动加此字串作为标识
	 */
    public static final String NONE_USER_ID = "-1";
    /*
	 * 程序中所用到字符集
	 */
    public static final String CHARSET_ISO8859_1 = "ISO8859-1";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CHARSET_GB2312 = "CHARSET_GB2312";
    public static final String CHARSET_GBK = "CHARSET_GBK";
    public static final String CHARSET_GB18030 = "CHARSET_GB18030";
    /*
	 * 路径分割符
	 */
    public static final String PATH_SPLITTER = "/";

    /*
     * 在configs.properties中配置ExceptionUtil 抛出异常时是否需要一起用log记录到文件中。
	 */
    public static final String EXCEPTION_UTIL_SWITCH_LOG_OPEN = "exceptionutil.switchlog.open";

    /*
 * SeedoReverseStrategy生成Pojo类时如主键策略是Long且是用sequence则该工具类自动生成Sequence sql。该配置是配置生成sequence sql文件的路径
 */
    public static final String SEEDOREVERSESTRATEGY_SEQSQL_PATH="hibernate.tools.createSequence.sqlFilePath";
}
