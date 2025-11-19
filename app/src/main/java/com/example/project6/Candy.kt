package com.example.project6

class Candy {
    private var id : Int = 0
    private var name : String = ""
    private var price : Double = 0.0

    constructor( id : Int, name : String, price : Double ) {
        setId( id )
        setName( name )
        setPrice( price )
    }

    fun getName( ) : String {
        return name
    }

    fun getId( ) : Int {
        return id
    }


    fun getPrice( ) : Double {
        return price
    }

    fun setId( id : Int ) {
        this.id = id
    }

    fun setName( name: String ) {
        this.name = name
    }

    fun setPrice( price: Double ) {
        if( price >= 0 )
            this.price = price
    }
    override fun toString(): String {
        return "" + id + ": " + name + " $" + price
    }

    fun toStringNoId(): String {
        return name + " $" + price
    }
}