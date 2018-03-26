package grails_domain_demo

class LinkResource extends Resource{
    String url

    static constraints = {
        url(unique: true,nullable:false,blank: false)
    }
}

