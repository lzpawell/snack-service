package goods.controller;

import com.alibaba.fastjson.JSONArray;
import goods.entities.LabelEntity;
import goods.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/allLabels")
    public List<LabelEntity> getAllLabels(){
        return labelService.getAllLabels();
    }

    @PostMapping("/addLabels")
    public String addLabels(@RequestBody JSONArray labelArray){
        String[] labels = new String[labelArray.size()];
        for(int i = 0; i < labelArray.size(); i++){
            labels[i] = labelArray.getString(i);
            System.out.println(labelArray.getString(i));
        }

        Object result = labelService.addLabels(labels);
        if(result == null)
            return "ERROR";
        else
            return "OK";
    }

    @DeleteMapping("/removeLabels")
    public String removeLabels(@RequestBody JSONArray labelArray){
        String[] labels = new String[labelArray.size()];
        for(int i = 0; i < labelArray.size(); i++){
            labels[i] = labelArray.getString(i);
            System.out.println(labelArray.getString(i));
        }

        Object result = labelService.removeLabels(labels);
        if(result == null)
            return "ERROR";
        else
            return "OK";
    }
}
