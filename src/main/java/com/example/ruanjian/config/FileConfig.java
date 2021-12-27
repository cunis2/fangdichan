package com.example.ruanjian.config;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * file 是保存的文件
 * unit1 一级目录，（项目名-客户名-客户单位-时间）
 * unit2 二级目录，（资料、模型文件、后期文件、渲染文件）
 * unit3 三级文件，（原始文件、最终文件、原始模型、最终模型......）
 */
public class FileConfig {    //保存文件返回url
    public static String saveFileReturnUrl(MultipartFile file,String unit1,String unit2,String unit3){
        String fileUrl="";
        String path="";
        if (!unit3.equals(""))
            path="D:\\房地产公司项目\\"+unit1+"\\"+unit2+"\\"+unit3+"\\"+file.getOriginalFilename();    //三级的单位可空
        else
            path="D:\\房地产公司项目\\"+unit1+"\\"+unit2+"\\"+file.getOriginalFilename();
        File desFile=new File(path);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            System.out.println("保存失败");
            e.printStackTrace();
        }
        fileUrl=path;

        return fileUrl;
    }
}
