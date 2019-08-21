package com.sszh.web.admin.cache.init.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sszh.cache.BaseCacheService;
import com.sszh.common.util.date.ExpireTime;
import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.feign.interfaces.SysMenuClient;
import com.sszh.web.admin.cache.AdminBaseCache;
import com.sszh.web.admin.cache.init.user.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 运营缓存-菜单m
 */
public class SysMenuCache extends AdminBaseCache {

    
    private static final Logger logger = LoggerFactory.getLogger(UserCache.class);

    @Resource
    private SysMenuClient sysMenuClient;

    /**================================== 菜单相关 ========================================*/
    public static final String SELF_KEY = PROJECT_KEY + "sysmenu:";
    public static final String SYSTEM_MENU_LIST_KEY = SELF_KEY + "list:";


    /**
     * 获取菜单信息
     */
    public List<SysMenuEntity> getSystemMenu() throws Exception {
        JSONArray jsonArr = basecache.getJson(SYSTEM_MENU_LIST_KEY, JSONArray.class);
        List<SysMenuEntity> list = new ArrayList<>();
        if (null != jsonArr) {
            //参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据
            list = JSONObject.parseArray(jsonArr.toJSONString(), SysMenuEntity.class);
            return list;
        }
        if (null == list || list.size() <= 0) {
            list = sysMenuClient.selectAll();
            //菜单必须是开启的
            Iterator<SysMenuEntity> it = list.iterator();
            while (it.hasNext()) {
                SysMenuEntity entity = it.next();
                if ((null == entity.getStatus() || null == entity.getSystemMark()) || (entity.getStatus() != 1 || entity.getSystemMark() != 1)) {
                    it.remove();
                }
            }
        }
        if (list.size() > 0) {
            //保存一天
            basecache.setJson(SYSTEM_MENU_LIST_KEY, JSONArray.toJSON(list), new ExpireTime(86400L));
        }
        return list;
    }


    /**
     * 构造器
     */
    private BaseCacheService basecache;
    public SysMenuCache(BaseCacheService basecache) {
        this.basecache = basecache;
    }
    /**
     * 缓存类初始化（可以用于将数据库数据缓存进redis）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("----------------------->初始化缓存控制器：运营-菜单模块");
        this.sysMenuClient = applicationContext.getBean(SysMenuClient.class);
    }

}