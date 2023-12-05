package com.lzy.yangoj.judge;

import com.lzy.yangoj.judge.model.ExecuteCodeRequest;
import com.lzy.yangoj.judge.model.ExecuteCodeResponse;

/**
 * @author lzy
 */
public interface CodeSandbox {

    /**
     * 代码沙箱通用接口类
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
