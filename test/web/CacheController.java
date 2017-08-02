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
    //全局声明
    LRUCache lruCache = new LRUCache(1000, 1800000);

    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String,Object> cacheSave(HttpServletRequest request) {
        Map<String,Object> rtn = new HashMap<String,Object>();
        //获取传入的id和value
        String id = request.getParameter("id");
        String value = request.getParameter("value");
        try {
            //保存 id 和 value
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
        String id =  request.getParameter("id");
        try {
            rtn.put("status",200);
            rtn.put("request","成功");
            rtn.put("request","数据id是 : "+ id);
            //获取 参数直接写入保存时的id即可
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
