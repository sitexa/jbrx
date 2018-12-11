package com.sitexa.service;

import io.jboot.codegen.model.JbootModelGenerator;
import io.jboot.codegen.service.JbootServiceGenerator;

/**
 * 自动生成的实体类，可维护性很差。
 */

public class CodeGenerator {
    public static void main(String args[]){
        //依赖model的包名
        String modelPackage = "com.sitexa.entity";
        //生成service 的包名
        String basePackage = "com.sitexa.service";

        JbootModelGenerator.run(modelPackage);
        JbootServiceGenerator.run(basePackage, modelPackage);

    }
}
