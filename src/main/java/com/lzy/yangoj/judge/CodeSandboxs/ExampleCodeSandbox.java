package com.lzy.yangoj.judge.CodeSandboxs;

import com.lzy.yangoj.judge.CodeSandbox;
import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExampleCodeSandbox implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("这是示例代码沙箱");
        return null;
    }
}
