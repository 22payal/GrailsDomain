package grails_domain_demo

class ReadingItem {


    Resource resource
    User user
    Boolean isRead

    static belongsTo = [user:User,resource:Resource]

    static constraints = {
        isRead(nullable: false)
        user(nullable: false)
        resource(nullable:false)
    }

}
