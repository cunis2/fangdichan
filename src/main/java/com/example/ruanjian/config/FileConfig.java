package com.example.ruanjian.config;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * file 是保存的文件
 * unit1 一级目录，（项目名-客户名-客户单位-时间）
 * unit2 二级目录，（资料、模型文件、后期文件、渲染文件）
 * unit3 三级文件，（原始文件、最终文件、原始模型、最终模型......）
 */
public class    FileConfig {    //保存文件返回url

    public static String saveFileReturnUrl(MultipartFile file,String staticPath,String unit1,String unit2,String unit3,HttpServletRequest request) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String hostAdress = address.getHostAddress();
        File desFile;
        String oldName = file.getOriginalFilename(); //文件原名
        if (!unit3.equals(""))
            desFile=new File(staticPath+unit1+"/"+unit2+"/"+unit3);
        else
            desFile=new File(staticPath+unit1+"/"+unit2);

        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            assert oldName != null;
            file.transferTo(new File(desFile,oldName));
        } catch (IOException e) {
            System.out.println("保存失败");
            e.printStackTrace();
        }

        String filePath="";
        if (!unit3.equals(""))
            filePath = request.getScheme() + "://" + hostAdress + ":" + request.getServerPort() + "/" + unit1 + "/"+ unit2 +"/"+ unit3 +"/"+ oldName;
        else
            filePath = request.getScheme() + "://" + hostAdress + ":" + request.getServerPort() + "/" + unit1 + "/"+ unit2 +"/"+ oldName;

        return filePath;
    }
}
