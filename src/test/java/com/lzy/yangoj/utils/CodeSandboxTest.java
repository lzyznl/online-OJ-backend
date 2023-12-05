package com.lzy.yangoj.utils;

import com.lzy.yangoj.judge.CodeSandbox;
import com.lzy.yangoj.judge.CodeSandboxs.CodeSandboxProxyFactory;
import com.lzy.yangoj.judge.CodeSandboxs.CodeSandboxsFactory;
import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CodeSandboxTest {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("1 2", "3,4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code("here is code")
                .codeLanguage("java")
                .inputList(stringList).build();
        CodeSandbox codeSandbox = CodeSandboxsFactory.newInstance("thirdparty");
        CodeSandboxProxyFactory codeSandboxProxyFactory = new CodeSandboxProxyFactory(codeSandbox);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxyFactory.executeCode(executeCodeRequest);

    }
}
