package grails_domain_demo

import enumeration.Seriousness
import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SubscriptionSpec extends Specification implements DomainUnitTest<Subscription> {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }

    def "User should not be null"(){
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User(
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )
        Topic topic=new Topic(name:"grails",visibility:Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(
                seriousness:Seriousness.VerySerious,
                user:null,
                topics:null
        )
        subscription.validate()
        topic.addToSubscription(subscription)
        user.addToTopic(topic)
        user.addToSubscription(subscription)
        user.save(flush:true)

        then:
        subscription.errors.hasErrors()==true


    }

    def "Topic should not be null"(){
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User(
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
        )
        Topic topic=new Topic(name:"grails",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(
                seriousness:Seriousness.VerySerious,
                user:user,
                topic:null
        )
        subscription.validate()
        topic.addToSubscription(subscription)
        user.addToTopic(topic)
        user.addToSubscription(subscription)
        user.save(flush:true)

        then:
        subscription.errors.hasErrors()==true
    }

    def "User should not be able to subscribe to topic multiple times "(){
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User
                 (
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
                 )

        Topic topic=new Topic(name:"grails",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription1=new Subscription(seriousness:Seriousness.VerySerious, user:user,topics:topic)
        subscription1.validate()

        topic.addToSubscription(subscription1)

        user.addToTopic(topic)

        user.addToSubscription(subscription1)

        user.save(flush:true)

        Subscription subscription2=new Subscription(seriousness:Seriousness.Serious, user:user,topics:topic)
        subscription1.validate()

        topic.addToSubscription(subscription2)
        user.addToTopic(topic)
        user.addToSubscription(subscription2)
        user.save(flush:true)


        then:
        subscription1.errors.hasErrors()==true
    }

    def "Seriousness should not be null"(){

        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User
                 (
                email: email,
                userName:"payalNigam",
                password:password,
                firstName: "Payal",
                lastName: "Nigam",
                admin:false,
                active:true
                 )

        Topic topic=new Topic(name:"grails",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription1=new Subscription(seriousness:Seriousness.VerySerious, user:user,topics:topic)
        subscription1.validate()

        topic.addToSubscription(subscription1)
        user.addToTopic(topic)
        user.addToSubscription(subscription1)
        user.save(flush:true)

        then:
        Subscription.count==1

        when:
        Subscription subscription2=new Subscription(seriousness:null, user:user,topics:topic)
        subscription1.validate()
        topic.addToSubscription(subscription2)
        user.addToTopic(topic)
        user.addToSubscription(subscription2)
        user.save(flush:true)

        then:
        subscription1.errors.hasErrors()==true

    }



}
