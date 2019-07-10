package com.qinsx.service.impl;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.qinsx.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by shuxinqin
 * DATE : 18-2-5
 * TIME : 下午3:33
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service.impl
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
public class FileServiceImpl implements FileService {

    @Override
    public List<String> readFile(String path) throws IOException {
        return Files.readLines(new File(Resources.getResource(path).getPath()), Charset.forName("UTF-8"));
    }

    @Override
    public void writeFile() {

    }
}
