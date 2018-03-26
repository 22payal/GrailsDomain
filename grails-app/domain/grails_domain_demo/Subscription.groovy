package grails_domain_demo

import enumeration.Seriousness

class Subscription {
    Topic topic
    User user


    Date dateCreated
    static belongsTo = [topic:Topic,user:User]

    Seriousness seriousness

    static constraints = {
        user (nullable: false)
        topic(nullable: false)
        seriousness(nullable:false)

    }
}
