package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:Element(name = "title")
    @param:Element(name = "title", required = false) var title: String = "",

    @field:Element(name = "description")
    @param:Element(name = "description", required = false) var description: String = ""
)

