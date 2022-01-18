package com.robinbobin.testsdemo

import com.robinbobin.testsdemo.http.request.GetPerson
import com.robinbobin.testsdemo.utility.RandomDataUtility
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification
import spock.lang.Unroll


class GetPersonNegativeTest extends Specification {

    @Unroll
    def "Get person with [phoneType] phone should fail"() {
        when: "Getting person"
            def response = GetPerson.send(phone)
        then: "Check failure"
            response.status == expectedStatus

        where:
            phoneType        | phone                                  || expectedStatus
            "random numbers" | RandomDataUtility.randomPhone          || 404
            "random string"  | RandomStringUtils.randomAlphabetic(10) || 400
    }

}