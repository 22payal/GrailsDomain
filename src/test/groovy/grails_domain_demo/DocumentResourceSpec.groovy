package grails_domain_demo

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DocumentResourceSpec extends Specification implements DomainUnitTest<DocumentResource> {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }

    def "filePath should not be null or blank"(){
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
        Topic topic = new Topic(name:"Grails Domain",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        DocumentResource documentResource1=new DocumentResource(filePath: "prachi/file.txt", user:user,topic: topic,description: "aaaaaaa")
        user.addToResource(documentResource1)
        topic.addToResource(documentResource1)
        documentResource1.validate()
        user.save()

        then:
        User.count==1

        when:
        DocumentResource documentResource2=new DocumentResource(filePath: " ", user:user,topic: topic,description: "aaaaaaa")
        user.addToResource(documentResource2)
        topic.addToResource(documentResource2)
        documentResource2.validate()
        user.save()

        then:
        documentResource2.errors.hasErrors()==1

        when:
        DocumentResource documentResource3=new DocumentResource(filePath: null, user:user,topic: topic,description: "aaaaaaa")
        user.addToResource(documentResource3)
        topic.addToResource(documentResource3)
        documentResource3.validate()
        user.save()

        then:
        documentResource3.errors.hasErrors()==1

    }
}
