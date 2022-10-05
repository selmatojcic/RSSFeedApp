package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:Element(name = "title", required = false)
    @param:Element(name = "title", required = false) var title: String = "",

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false) var description: String = "",

    @field:Element(name = "image", required = false)
    @param:Element(name = "image", required = false) var image: Image = Image(),

    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(
        name = "item", inline = true, required = false
    ) var items: List<Item> = emptyList()
) : java.io.Serializable {

    @Override
    override fun toString(): String {
        return "Feed: \n [Items: \n$items]"
    }
}

