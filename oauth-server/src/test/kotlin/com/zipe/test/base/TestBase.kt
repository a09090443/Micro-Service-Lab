package com.zipe.test.base

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

//@SpringBootTest(
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//    classes = [Application::class]
//)
@SpringBootTest
@ExtendWith(SpringExtension::class)
class TestBase {
}
