package com.grs.demo.compiler.generators;


import com.grs.app2.templates.WXEntryTemplate;
import com.grs.lib.annotations.EntryGenerator;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.grs.app2",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
