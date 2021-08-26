package me.user0481.itemrename.config;

public abstract class ConfigFactory {
    public static Config getConfig() {
        return new StaticConfig();
    }
}
