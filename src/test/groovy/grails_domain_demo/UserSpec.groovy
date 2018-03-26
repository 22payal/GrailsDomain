package grails_domain_demo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect: "fix me"
//        true == false
//    }

    def "Email address of user should be unique"() {

        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User(
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                photo:"123456789",
                admin:false,
                active:true,
                dateCreated:'Thu Mar 26 14:27:15 GST 2018' ,
                lastUpdated:'Thu Mar 26 14:27:15 GST 2018'
        )

        when:
        user.validate()

        then:
        user.save(flush: true , failOnError: true)
    }
}
