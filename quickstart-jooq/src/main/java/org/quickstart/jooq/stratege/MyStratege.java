/**
 * 项目名称：quickstart-jooq 
 * 文件名：MyStratege.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.jooq.stratege;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.codegen.GeneratorStrategy.Mode;
import org.jooq.meta.CatalogDefinition;
import org.jooq.meta.Definition;
import org.jooq.meta.SchemaDefinition;
import org.jooq.tools.StringUtils;

/**
 * MyStratege 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年11月12日 上午9:39:33 
 * @since 1.0
 */
public class MyStratege extends DefaultGeneratorStrategy {


    public String getJavaClassName(Definition definition, Mode mode) {
        String name = getFixedJavaClassName(definition);
        return name != null ? name : this.getJavaClassName0(definition, mode);
    }

    private String getJavaClassName0(Definition definition, Mode mode) {
        StringBuilder result = new StringBuilder();
        result.append(StringUtils.toCamelCase(definition.getOutputName().replace(' ', '_').replace('-', '_').replace('.', '_')));
        if (mode == Mode.RECORD) {
            result.append("Record");
        } else if (mode == Mode.DAO) {
            result.append("Dao");
        } else if (mode == Mode.POJO) {
            result.append("Bo");
        } else if (mode == Mode.INTERFACE) {
            result.insert(0, "I");
        }

        return result.toString();
    }

    final String getFixedJavaClassName(Definition definition) {
        if (definition instanceof CatalogDefinition && ((CatalogDefinition) definition).isDefaultCatalog()) {
            return "DefaultCatalog";
        } else {
            return definition instanceof SchemaDefinition && ((SchemaDefinition) definition).isDefaultSchema() ? "DefaultSchema" : null;
        }
    }
}
