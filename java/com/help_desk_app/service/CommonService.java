package com.help_desk_app.service;

import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.Urgency;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommonService {
    private  static final  String CATEGORIES = "categories";
    private  static final  String URGENCY = "urgency";

    public Map<String, Object> getFixedVales(){
        Map<String, Object> map = new HashMap<>() ;
        map.put(CATEGORIES, Category.values());
        map.put(URGENCY, Urgency.values());
        return  map;
    }
}
