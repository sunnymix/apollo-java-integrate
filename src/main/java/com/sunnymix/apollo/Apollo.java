package com.sunnymix.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * Apollo辅助类
 * <p>
 * 使用方法：
 * （1）初始化：Apollo.init("Apollo服务地址", "Apollo应用ID")。
 * （2）获取配置属性：
 * - 获取APP命名空间配置的属性：Apollo.getAppConfig().getProperty("属性名称", "默认值")。
 * - 获取指定命名空间配置的属性：Apollo.getConfig("命名空间").getProperty("属性名称", "默认值")。
 *
 * @author Sunny
 * @since 2021/08/10
 */
public class Apollo {

    /**
     * KEY：Apollo服务地址
     */
    private static final String CONFIG_SERVICE_KEY = "apollo.configService";

    /**
     * KEY：Apollo应用ID
     */
    private static final String APP_ID_KEY = "app.id";

    /**
     * KEY：APP命名空间
     */
    private static final String APP_NAMESPACE_KEY = "application";

    /**
     * Apollo单例对象
     */
    private volatile static Apollo instance;

    /**
     * Apollo单例对象初始化
     *
     * @param configServiceHost Apollo服务地址
     * @param appId             Apollo应用ID
     */
    public static void init(String configServiceHost, String appId) {
        if (instance == null) {
            synchronized (Apollo.class) {
                if (instance == null) {
                    instance = new Apollo(configServiceHost, appId);
                }
            }
        }
        setupSystem();
    }

    private final String configServiceHost;
    private final String appId;

    /**
     * 构造Apollo对象（私有）
     *
     * @param configServiceHost Apollo服务地址
     * @param appId             Apollo应用ID
     */
    private Apollo(String configServiceHost, String appId) {
        this.configServiceHost = configServiceHost;
        this.appId = appId;
    }

    /**
     * 配置系统属性
     */
    private static void setupSystem() {
        System.setProperty(CONFIG_SERVICE_KEY, instance.configServiceHost);
        System.setProperty(APP_ID_KEY, instance.appId);
    }

    /**
     * 获取APP命名空间的配置
     *
     * @return 配置对象
     */
    public static Config getAppConfig() {
        return getConfig(APP_NAMESPACE_KEY);
    }

    /**
     * 获取指定命名空间的配置
     *
     * @return 配置对象
     */
    public static Config getConfig(String namespace) {
        return ConfigService.getConfig(namespace);
    }

}
