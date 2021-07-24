package com.concordia;

import com.concordia.common.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testToken() throws Exception {
        String username = "ray";
        String userId = "rayrayrayray";
        String token = JwtTokenUtil.createJWT(username, userId);

        Assertions.assertFalse(JwtTokenUtil.isExpired(token));
        Assertions.assertEquals(username, JwtTokenUtil.getUsername(token));
        Assertions.assertEquals(userId, JwtTokenUtil.getUserId(token));
    }

}
