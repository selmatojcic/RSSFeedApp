package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title", required = false) var title: String = "",

    @field:Element(name = "description")
    @param:Element(name = "description", required = false) var description: String = "",

    @field:Element(name = "link")
    @param:Element(name = "link", required = false) var link: String = "",

    ) : java.io.Serializable {

    @Override
    override fun toString(): String {
        return "\n\nItem: " +
                "title ='" + title + '\'' +
                ", description ='" + description + '\'' +
                ", link ='" + link + '\'' +
                '}' + "\n"
    }

    @JvmName("getTitle1")
    fun getTitle(): String {
        return title
    }

    @JvmName("getDescription1")
    fun getDescription(): String {
        return description
    }

    @JvmName("getLink1")
    fun getLink(): String {
        return link
    }
}