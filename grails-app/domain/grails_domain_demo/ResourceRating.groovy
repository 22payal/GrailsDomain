package grails_domain_demo

class ResourceRating {
    Resource resource
    User createdBy
    Integer score

    static belongsTo = [resource:Resource,createdBy:User]

    static constraints = {
        score(nullable: false , size:1..5)
        resource(nullable: false )
        createdBy(nullable: false , unique:'createdBy')
    }
}
