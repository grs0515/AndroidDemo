package com.grs.demo.compiler.generators;


import com.grs.app2.templates.AppRegisterTemplate;
import com.grs.lib.annotations.AppRegisterGenerator;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.grs.app2",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
