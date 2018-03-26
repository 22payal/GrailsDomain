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

    def "Email address of user should be in valid format"() {
        setup:

        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2 = new User(
                email: "payal.nigam",
                userName: "payal",
                password: password,
                firstName: "payal",
                lastName: "nigam",
                admin: false,
                active: true
        )

        user2.save()
        then:
        User.count() == 1
        user2.errors.getFieldErrorCount('email') == 1
        user2.errors.allErrors.size() == 1

    }

    def "Passowrd of a user should be minimum of 5 characters"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"payal.nigam@gmail.com",
                userName:"payal",
                password:"90s",
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.getFieldErrorCount('password') == 1
        user2.errors.allErrors.size() == 1
    }
    def "Password of a user should not be blank or null"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password:"",
                firstName: "payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:

        user2.errors.getFieldErrorCount('password') == 1
        user2.errors.hasErrors()==true

        when:
        User user3= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password:null,
                firstName:"Payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.getFieldErrorCount('password') == 1
        user2.errors.hasErrors()==true
    }

    def "First name of a user should not be blank or null"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password:"payal123",
                firstName: " ",
                lastName: "Nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:

        user2.errors.getFieldErrorCount('firstName') == 1
        user2.errors.hasErrors()==true

        when:
        User user3= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password:"payal123",
                firstName:null,
                lastName: "Nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.getFieldErrorCount('firstName') == 1
        user2.errors.hasErrors()==true
    }


    def "Last name of a user should not be blank or null"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user1 = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password:"payal123",
                firstName: "payal",
                lastName: " ",
                admin:false,
                active:true
        )

        user2.save()
        then:

        user2.errors.getFieldErrorCount('lastName') == 1
        user2.errors.hasErrors()==true

        when:
        User user3= new User(
                email:"payal.nigam@tothenew.com",
                userName:"payalNigam",
                password: "payal123",
                firstName:"Payal",
                lastName: null,
                admin:false,
                active:true
        )

        user2.save()
        then:
        user2.errors.getFieldErrorCount('lastName') == 1
        user2.errors.hasErrors()==true
    }


    def "User name of a user should not be blank or null"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        String userName = "payalNigam"
        User user1 = new User(
                email: email,
                userName: userName,
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )

        when:
        user1.save()
        then:
        user1.count() == 1

        when:
        User user2= new User(
                email:"payal.nigam@tothenew.com",
                userName:" ",
                password:"payal123",
                firstName: "payal",
                lastName: "nigam",
                admin:false,
                active:true
        )

        user2.save()
        then:

        user2.errors.getFieldErrorCount('userName') == 1
        user2.errors.hasErrors()==true

        when:
        User user3= new User(
                email:"payal.nigam@tothenew.com",
                userName:null,
                password: "payal123",
                firstName:"Payal",
                lastName: "nigam",
                admin:false,
                active:true
        )

        user3.save()
        then:
        user3.errors.getFieldErrorCount('userName') == 1
        user3.errors.hasErrors()==true

        when :
        User user4= new User(
                email:"payal.nigam@tothenew.com",
                userName: "payalNigam",
                password: "payal123",
                firstName:"Payal",
                lastName: "nigam",
                admin:false,
                active:true
        )

        user4.save()
        then:
        user4.errors.getFieldErrorCount('userName') == 1
        user4.errors.hasErrors()==true

    }




}
