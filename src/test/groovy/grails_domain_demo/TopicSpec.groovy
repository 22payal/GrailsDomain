package grails_domain_demo

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TopicSpec extends Specification implements DomainUnitTest<Topic> {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }

    def "Topic name should be unique per user"() {

        setup:
        String email = "payal.nigam@tothenew.com"
        User user = new User(
                email: email,
                userName: "payalNigam",
                password: "payal123",
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        Topic topic = new Topic(name: "topic", createdBy: user, Visibility: Visibility.PRIVATE)
        user.addToTopic(topic)
    }
}
