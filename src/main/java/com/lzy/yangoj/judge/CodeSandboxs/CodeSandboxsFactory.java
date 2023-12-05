package com.lzy.yangoj.judge.CodeSandboxs;

import com.lzy.yangoj.judge.CodeSandbox;

/**
 * 代码沙箱工厂类
 */
public class CodeSandboxsFactory {

    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdparty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
