package com.lzy.yangoj.judge.CodeSandboxs;

import com.lzy.yangoj.judge.CodeSandbox;
import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;
import lombok.Builder;
import lombok.Data;

/**
 * @author lzy
 */
@Data
@Builder
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("这里是第三方代码沙箱");
        return null;
    }
}
