package be.cuizon.hoopertracker

class Exercise {

    var userEmail:String
    var category:String
    var description:String
    var time:String

    constructor( userEmail:String,category:String, description:String, time:String ) {
        this.userEmail=userEmail
        this.category=category
        this.description=description
        this.time=time
    }
}