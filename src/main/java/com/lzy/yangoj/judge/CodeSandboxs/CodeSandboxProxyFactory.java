package com.lzy.yangoj.judge.CodeSandboxs;

import com.lzy.yangoj.judge.CodeSandbox;
import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CodeSandboxProxyFactory implements CodeSandbox {

    private CodeSandbox codeSandbox;

    public CodeSandboxProxyFactory(CodeSandbox codeSandbox){
        this.codeSandbox = codeSandbox;
    }


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("开始判题");
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("判题结束");
        return executeCodeResponse;
    }
}
