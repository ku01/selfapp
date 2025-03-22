package dev.ku01.selfapp.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import kotlin.reflect.KClass

object JacksonObjectMapper : ObjectMapper() {
    init {
        registerModule(KotlinModule.Builder().build())
        registerModule(JavaTimeModule())
    }
}

fun MockHttpServletRequestBuilder.content(data: Any): MockHttpServletRequestBuilder {
    return this.content(JacksonObjectMapper.writeValueAsBytes(data))
}

fun <T : Any> ResultActions.andReturn(clazz: KClass<T>): T {
    return JacksonObjectMapper.readValue(this.andReturn().response.contentAsByteArray, clazz.java)
}
