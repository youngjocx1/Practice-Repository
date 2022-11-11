package com.cvpcorp.learn.springboot.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SpringFoxConfigTest {

    @InjectMocks
    SpringFoxConfig config;

    @Test
    void api() {
        Docket docket = config.api();
        assertNotNull(docket);
    }
}
