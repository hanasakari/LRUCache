package test.web;

import core.LRUCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AmamiyaAsuka on 2017/7/26.
 * spring内缓存测试类
 */
@Controller
@RequestMapping(value = "/cache")
public class CacheController {
    //分配1000条缓存 并保存30分钟(1800000ms)
    LRUCache lruCache = new LRUCache(1000, 1800000);

    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String,Object> cacheSave(HttpServletRequest request) {
        Map<String,Object> rtn = new HashMap<String,Object>();
        String id = RequestUtil.getParameter(request,"id");
        String value = RequestUtil.getParameter(request,"value");
        try {
            lruCache.put(id, value);
            rtn.put("status",200);
            rtn.put("request","成功");
            rtn.put("request","插入id是 : "+ id);
        }catch (Exception e){
            rtn.put("status",500);
            rtn.put("request","失败");
            rtn.put("request","失败id是 : "+ id);
        }
       return rtn;

    }

    @RequestMapping(value = "/load")
    @ResponseBody
    public Map<String,Object> cacheOut(HttpServletRequest request) {
        Map<String,Object> rtn = new HashMap<String,Object>();
        String id = RequestUtil.getParameter(request,"id");
        try {
            rtn.put("status",200);
            rtn.put("request","成功");
            rtn.put("request","数据id是 : "+ id);
            rtn.put("request","数据value是 : "+ lruCache.get(id));
            System.out.println(lruCache.get(id));
        }catch (Exception e){
            rtn.put("status",500);
            rtn.put("request","失败");
            rtn.put("request","失败id是 : "+ id);
        }
        return rtn;
    }
}
