package com.nevile.system.utils;
/**
 * Created by 草帽boy on 2017/2/16.
 * 启动文件，只需要点击运行就行
 */
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
public class MybatisGeneratorStartUp {
    public static void main(String[] args) throws URISyntaxException {
        try {
            System.out.println("=======MyGenerator StartUp=========================");
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            //直接获取generatorConfig.xml的文件路径 根据具体情况查看
            File configFile = new File("E:\\workspaceIDEA\\framwork\\src\\main\\resources\\generatorConfig.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            for (int i = 0; i < warnings.size(); i++) {
                String s =  warnings.get(i);
                System.out.println(s);

            }
            System.out.println("=======MyGenerator End=========================");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
}
