package com.lzy.yangoj.judge;

import com.lzy.yangoj.model.entity.uploadSolve;
import org.springframework.stereotype.Service;

/**
 * @author lzy
 * 判题实现类接口
 */
@Service
public interface judgeService {

    uploadSolve doJudge (Long questionSubmitId);
}
