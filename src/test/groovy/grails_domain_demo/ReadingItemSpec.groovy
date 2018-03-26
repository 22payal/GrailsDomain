package grails_domain_demo

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ReadingItemSpec extends Specification implements DomainUnitTest<ReadingItem> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }

    def "isRead should not be null"(){
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
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        when:
        ReadingItem readingItem1=new ReadingItem(isRead: null,user:user,resource:resource)

        readingItem1.validate()
        readingItem1.save()
        then:
        readingItem1.errors.getFieldErrorCount('isRead')==1
        readingItem1.errors.hasErrors()==true


        when:
        ReadingItem readingItem2=new ReadingItem(isRead:0,user:user,resource:resource)

        readingItem2.validate()
        readingItem2.save()
        then:
        ReadingItem.count==1
    }
    def "user should not be null"() {
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
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)

        when:
        ReadingItem readingItem1 = new ReadingItem(isRead: true, user: null, resource: resource)

        readingItem1.validate()
        readingItem1.save()
        then:
        readingItem1.errors.getFieldErrorCount('user') == 1

        when:
        ReadingItem readingItem2 = new ReadingItem(isRead: true, user: null, resource: resource)

        readingItem2.validate()
        readingItem2.save()
        then:

        ReadingItem.count==1

    }




    def "resource should not be null"(){
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
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)

        when:
        ReadingItem readingItem1=new ReadingItem(isRead: true,user:user,resource:null)

        readingItem1.validate()
        readingItem1.save()
        then:
        readingItem1.errors.getFieldErrorCount('resource')==1
        readingItem1.hasErrors()==true

        when:
        ReadingItem readingItem2=new ReadingItem(isRead: true,user:user,resource:resource)

        readingItem2.validate()
        readingItem2.save()
        then:
        ReadingItem.count==1
    }

    def "Readingitem resource should be unique per user"(){
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
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        when:
        ReadingItem readingItem1=new ReadingItem(isRead: true,user:user,resource:resource)
        readingItem1.validate()
        readingItem1.save()

        then:
        ReadingItem.count==1


        when:

        ReadingItem readingItem2=new ReadingItem(isRead: true,user:user,resource:resource)
        resource.addToReadingItem(readingItem2)
        resource.save()
        user.addToReadingItem(readingItem2)
        user.save()
        readingItem2.validate()
        readingItem2.save()

        ReadingItem readingItem3=new ReadingItem(isRead: true,user:user,resource:resource)
        resource.addToReadingItem(readingItem3)
        resource.save()
        user.addToReadingItem(readingItem3)
        user.save()
        readingItem3.validate()
        readingItem3.save()

        then:
        readingItem3.errors.hasErrors()==true

    }
}

