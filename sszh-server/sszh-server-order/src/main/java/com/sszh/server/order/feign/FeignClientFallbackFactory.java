package com.sszh.server.order.feign;

import com.sszh.server.order.bean.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 */
@Component
public class FeignClientFallbackFactory implements FallbackFactory<OrderFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public OrderFeignClient create(Throwable cause) {
        return new OrderFeignClient() {
            @Override
            public User findById(Long id) {
                FeignClientFallbackFactory.LOGGER.info("fallback; reason was:", cause);
                User user = new User();
                user.setId(-1L);
                user.setUsername("默认用户");
                user.setAge(0);
                user.setBalance((double) 0);
                return user;
            }
        };
    }
}