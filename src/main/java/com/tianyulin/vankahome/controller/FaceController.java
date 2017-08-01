package com.tianyulin.vankahome.controller;

import com.tianyulin.vankahome.util.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;

/**
 * Created by tianyulin on 2017/7/27.
 */
@Controller
@RequestMapping("/face")
public class FaceController {

    private static final String URL = "https://api-cn.faceplusplus.com/facepp/v3/detect";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        return "face";
    }

    @RequestMapping(value = "/sb_face")
    public String sbFace(@RequestParam("imageFile")MultipartFile file, @RequestParam("imgBase64")String imgBase64, Model model){
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "XdfdFGLO4NoXbayvndVzaJYQ0W2TQMJD");
        map.put("api_secret", "UTvRJPyIai-AumOvLqi4v8e1vgrrr2cE");
        map.put("return_attributes", "gender,age,smiling,glass,emotion,ethnicity");
        try{
            byte[] buff = file.getBytes();
            byteMap.put("image_file", buff);
            byte[] bacd = HttpClientUtil.postToFace(URL, map, byteMap);
            String str = new String(bacd);
            model.addAttribute("FaceImageUrl", imgBase64);
            model.addAttribute("FaceRequestVo", str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "faceDetail";
    }
}
