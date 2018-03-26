package grails_domain_demo

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LinkResourceSpec extends Specification implements DomainUnitTest<LinkResource> {

    def setup() {
    }

    def cleanup() {
    }
//
//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }

    def "url field should contain valid url"() {
        setup:
        String email = "payal.nigam@tothenew.com"
        String password = 'payal123'
        User user = new User(
                email: email,
                userName: "payalNigam",
                password: password,
                firstName: "Payal",
                lastName: "Nigam",
                admin: false,
                active: true
        )
        Topic topic = new Topic(name: "Grails domain1", visibility: Visibility.PUBLIC, createdBy: user)

        when:
        LinkResource linkResource1 = new LinkResource(url: "www.google.com", user: user, topic: topic, description: "aaaaaaa")

        topic.addToResource(linkResource1)
        user.addToTopic(topic)
        linkResource1.validate()
        user.save()

        then:
        LinkResource.count == 1

        when:
        LinkResource linkResource2 = new LinkResource(url: "www", user: user, topic: topic, description: "aaaaaaa")

        topic.addToResource(linkResource2)
        user.addToTopic(topic)
        user.addToResource(linkResource2)
        linkResource2.validate()
        user.save()

        then:
        linkResource2.errors.hasErrors()==1

        when:
        LinkResource linkResource3 = new LinkResource(url: " ", user: user, topic: topic, description: "aaaaaaa")

        topic.addToResource(linkResource3)
        user.addToTopic(topic)
        user.addToResource(linkResource3)
        linkResource3.validate()
        user.save()

        then:
        linkResource3.errors.hasErrors()==1

        when:
        LinkResource linkResource4 = new LinkResource(url:null, user: user, topic: topic, description: "aaaaaaa")

        topic.addToResource(linkResource4)
        user.addToTopic(topic)
        user.addToResource(linkResource4)
        linkResource4.validate()
        user.save()

        then:
        linkResource4.errors.hasErrors()==1

    }
}