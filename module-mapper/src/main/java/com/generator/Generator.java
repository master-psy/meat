package com.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis generator逆向工程 自定义启动器
 */
public class Generator {
    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<>();
            File configFile = new File("module-mapper/src/main/resources/generatorConfig-shanyou.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("code generator successful!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
