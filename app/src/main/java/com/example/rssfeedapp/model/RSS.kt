package com.example.rssfeedapp.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RSS @JvmOverloads constructor(
    @field:Element(name = "channel")
    @param:Element(name = "channel", required = false) var channel: Channel = Channel()
) : java.io.Serializable