package grails_domain_demo

class User {

    String firstName
    String lastName
    String userName
    String password
    String email
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated
    Byte photo
    List<Topic> topic
    String name

    static hasMany = [topic:Topic,subscription:Subscription,readingItem:ReadingItem,resource:Resource,resourceRating:ResourceRating]


    static transients = ['name']

    static constraints = {
        email(email: true,unique: true ,blank: false , nullable: false)
        password(size: 5..15, blank: false ,nullable: false)
        firstName(blank: false ,nullable: false)
        lastName(blank: false , nullable: false)
        userName(unique: true, blank:false , nullable: false)
        photo(nullable: true,sqlType:'longBlob')
        admin(nullable:true)
        active(nullable: true)

    }


}
