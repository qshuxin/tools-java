package com.qinsx.service;

import java.io.IOException;
import java.util.List;

/**
 * Created by shuxinqin
 * DATE : 18-2-5
 * TIME : 下午3:30
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
public interface FileService {

    List<String> readFile(String path) throws IOException;

    void writeFile();
}
