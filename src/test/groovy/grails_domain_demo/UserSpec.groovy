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
        User user1 = new User(
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
        user1.save()
        then:
        User.count() == 1


        when:
        User user2= new User(
                email: email,
                userName:"NewUser",
                password:password,
                firstName: "new",
                lastName: "user",
                admin: false,
                active:true
        )
        user2.save()
        then:
        user2.errors.allErrors.size() == 1
    }

    def "Email address of user should not be blank"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
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
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"",
                userName:"NewUser",
                password:password,
                firstName: "New",
                lastName: "User",
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.allErrors.size() == 1
    }

    def "Email address of user should not be null"() {
        setup:

        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:null,userName:"NewUser",
                password:password,
                firstName: "New",
                lastName: "User",
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.allErrors.size() == 1
    }
}
