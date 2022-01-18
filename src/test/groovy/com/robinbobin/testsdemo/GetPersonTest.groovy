package com.robinbobin.testsdemo

import com.robinbobin.testsdemo.http.request.AddPerson
import com.robinbobin.testsdemo.http.request.DeletePerson
import com.robinbobin.testsdemo.http.request.GetPerson
import com.robinbobin.testsdemo.model.Person
import com.robinbobin.testsdemo.utility.RandomDataUtility
import spock.lang.Shared
import spock.lang.Specification

class GetPersonTest extends Specification {

    @Shared Person expectedPerson

    def setupSpec() {
        given: "Random person"
            expectedPerson = RandomDataUtility.randomPerson
        and: "Random person is added"
            AddPerson.send(expectedPerson)
    }

    def "Get person"() {
        when: "Sending request"
            def response = GetPerson.send(expectedPerson.phone)
        then: "Check response code"
            response.statusOk
        and: "Check person data"
            expectedPerson == response.deserializeBody(Person.class)
    }

    def cleanupSpec() {
        cleanup: "Delete person"
            DeletePerson.send(expectedPerson.phone)
    }

}