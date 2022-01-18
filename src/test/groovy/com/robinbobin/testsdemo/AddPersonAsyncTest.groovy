package com.robinbobin.testsdemo

import com.robinbobin.testsdemo.http.request.AddPersonAsync
import com.robinbobin.testsdemo.http.request.DeletePerson
import com.robinbobin.testsdemo.http.request.GetPerson
import com.robinbobin.testsdemo.http.request.GetPersonQueueStatus
import com.robinbobin.testsdemo.http.response.AddPersonAsyncResponse
import com.robinbobin.testsdemo.http.response.GetQueueStatusResponse
import com.robinbobin.testsdemo.model.Person
import com.robinbobin.testsdemo.model.QueueStatus
import com.robinbobin.testsdemo.utility.RandomDataUtility
import com.robinbobin.testsdemo.utility.Waiter
import spock.lang.Shared
import spock.lang.Specification


class AddPersonAsyncTest extends Specification {

    @Shared Person person

    def setupSpec() {
        given: "Random person"
            person = RandomDataUtility.randomPerson
    }

    def "Add person async"() {
        when: "Adding person async"
            def response = AddPersonAsync.send(person)
        then: "Request is successful"
            response.statusOk
        and: "Queue status is InProgress"
            def addAsyncResponse = response.deserializeBody(AddPersonAsyncResponse.class)
            def getStatusResponse = GetPersonQueueStatus.send(addAsyncResponse.queueId)
                    .deserializeBody(GetQueueStatusResponse.class)
            getStatusResponse.status == QueueStatus.IN_PROGRESS.name

        when: "Waiting for person to be added"
            def waitStatusLastResponse = Waiter.waitForQueueToProcess(addAsyncResponse.queueId)
        then: "Status is Success"
            waitStatusLastResponse.status == QueueStatus.SUCCESS.name

        when: "Getting person"
            def addedPerson = GetPerson.send(person.phone).deserializeBody(Person.class)
        then: "Person is added correctly"
            person == addedPerson
    }

    def cleanupSpec() {
        cleanup: "Delete person"
            DeletePerson.send(person.phone)
    }

}
