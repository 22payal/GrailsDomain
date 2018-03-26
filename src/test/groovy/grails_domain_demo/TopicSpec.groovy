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
        Topic topic1 = new Topic()
        topic1.createdBy = user
        topic1.topicName = "topic"

        topic1.visibility = Visibility.PRIVATE

        user.addToTopic(topic1)
        user.validate()
        user.save(flush: true)

        Topic topic2 = new Topic()
        topic2.topicName = "topic"
        topic2.createdBy = user
        topic2.visibility = Visibility.PUBLIC

        user.addToTopic(topic2)
        user.validate()
        user.save(flush: true)

        then:
        user.errors.hasErrors() == true
    }

    def "Topic name should not be null or blank"(){

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

        when:
        Topic topic1 = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        user.addToTopic(topic1)
        user.save(flush:true)

        then:
        Topic.count==1

        when:
        Topic topic2 = new Topic(name: null,createdBy: user,visibility: Visibility.PUBLIC)
        user.addToTopic(topic2)
        topic2.validate()
        topic2.save()
        user.save(flush:true)

        then:
        user.errors.hasErrors() == true



    }

    def "Visiblity of topic should not be null"(){

        given:
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

        when:
        Topic topic1=new Topic(name:"topic",createdBy: user,visibility:null)
        user.addToTopic(topic1)
        topic1.validate()
        topic1.save()
        user.save()
        then:
        user.errors.hasErrors()==true

        when:
        Topic topic2=new Topic(name:"new topic",createdBy: user,visibility:Visibility.PUBLIC)
        user.addToTopic(topic2)
        topic2.validate()
        topic2.save()
        user.save()

        then:
        Topic.count==1

    }

    def "Created by should not be null"(){
        given:
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

        when:
        Topic topic1=new Topic (name:"topic",createdBy:user,visibility: Visibility.PUBLIC)
        topic1.validate()
        topic1.save()
        user.save()

        then:

        Topic.count==1

        when:
        Topic topic=new Topic (name:"topic",createdBy: null,visibility: Visibility.PUBLIC)
        topic.validate()
        topic.save()

        then:

        topic.errors.hasErrors()

    }

}