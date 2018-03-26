package grails_domain_demo

import enumeration.Visibility

class Topic {

    String topicName
    Date dateCreated
    Date lastUpdated
    User createdBy

    static hasMany = [subscriptions:Subscription,resource:Resource]

 Visibility visibility

    static belongsTo = [createdBy:User]

    static constraints = {

        topicName(blank: false, nullable: false)
        createdBy(nullable: false)
        visibility(nullable: false)
    }
//
//    static mapping = {
//
//
//    }
}

