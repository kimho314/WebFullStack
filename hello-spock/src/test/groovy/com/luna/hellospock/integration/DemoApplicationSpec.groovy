package com.luna.hellospock.integration

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DemoApplicationSpec extends Specification {
    def "contextLoads"() {
        expect:
        true
    }
}
