package grails_domain_demo

import enumeration.Visibility

class Topic {

    String topicName
    Date dateCreated
    Date lastUpdated
    User createdBy

    @Override
    String toString() {
        return super.toString()
    }

    Visibility visibility

    List<Subscription> subscription

    static hasMany = [subscription:Subscription,resource:Resource]

    static belongsTo = [createdBy:User]

    static constraints = {

        topicName(blank: false, nullable: false, unique:'createdBy')
        createdBy(nullable: false)
        visibility(nullable: false)
    }

}

