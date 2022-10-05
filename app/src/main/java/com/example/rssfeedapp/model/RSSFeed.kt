package com.example.rssfeedapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rssFeeds")
data class RSSFeed(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "imageURL") var imageURL: String
) : java.io.Serializable