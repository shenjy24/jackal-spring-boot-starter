package com.shenjy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple WebApplication.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(WebApplicationTest.class);

    @Test
    public void testLog() {
        logger.debug("debug");
        logger.warn("玉石");
    }
}
