package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class Image @JvmOverloads constructor(
    @field:Element(name = "url")
    @param:Element(name = "url", required = false) var url: String = "",

    @field:Element(name = "title")
    @param:Element(name = "title", required = false) var title: String = "",

    @field:Element(name = "link")
    @param:Element(name = "link", required = false) var link: String = "",
    ) : java.io.Serializable