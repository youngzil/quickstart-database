/**
 * 项目名称：quickstart-jooq 
 * 文件名：JooqGeneratorUtil.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.jooq;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import javax.xml.bind.JAXB;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;

/**
 * JooqGeneratorUtil
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月12日 上午11:18:34
 * @since 1.0
 */
public class JooqGeneratorUtil {
    public static void main(String[] args) throws Exception {
        generate("jooqConfig.xml");
    }

    private static void generate(String xml) throws Exception {
        URL url = JooqGeneratorUtil.class.getClassLoader().getResource(xml);
        String s = URLDecoder.decode(url.getFile(), "utf-8");
        Configuration configuration = JAXB.unmarshal(new File(s), Configuration.class);
        GenerationTool.generate(configuration);
    }
}
