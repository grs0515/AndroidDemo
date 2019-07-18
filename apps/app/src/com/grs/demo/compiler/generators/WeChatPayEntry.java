package com.grs.demo.compiler.generators;


import com.grs.app2.templates.WXPayEntryTemplate;
import com.grs.lib.annotations.PayEntryGenerator;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.grs.app2",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
