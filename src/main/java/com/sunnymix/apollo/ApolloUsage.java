package com.sunnymix.apollo;

public class ApolloUsage {

    public static void main(String[] args) {
        Apollo.init("http://apollo-config-dev.local", "test");
        System.out.println(Apollo.getAppConfig().getProperty("test", null));
        System.out.println(Apollo.getConfig("test1").getProperty("test1", null));
    }

}
