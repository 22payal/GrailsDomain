package grails_domain_demo

class User implements Serializable{

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

    static hasMany = [topic:Topic,subscription:Subscription,readingItem:ReadingItem,resource:Resource]

    String getName()
    {
        println(name.toString())
    }

    static transients = ['pia']

    static constraints = {
        email(email: true,unique: true ,blank: false , nullable: false)
        password(size: 5..15, blank: false ,nullable: false)
        //firstName(blank: false ,nullable: false)
        //lastName(blank: false , nullable: false)
        userName(unique: true, blank:false)
        photo(nullable: true)
        admin(nullable:true)
        active(nullable: true)

    }

    static mapping = {
        id composite: ['firstName','lastName']
        table("mytable")
    }

}
