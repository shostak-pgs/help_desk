package com.help_desk_app.service;

import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.Urgency;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonService {

    public Map<String, Object> getFixedVales(){
        Map<String, Object> map = new HashMap<>() ;
        map.put("categories", Category.values());
        map.put("urgency", Urgency.values());
        return  map;
    }
}
