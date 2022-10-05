package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
data class Image @JvmOverloads constructor(
    @field:Element(name = "url", required = false)
    @param:Element(name = "url", required = false) var url: String = "",

    ) : java.io.Serializable