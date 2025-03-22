package dev.ku01.selfapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
abstract class BaseControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc
}
