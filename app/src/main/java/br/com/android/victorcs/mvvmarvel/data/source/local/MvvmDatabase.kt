package br.com.android.victorcs.mvvmarvel.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.android.victorcs.mvvmarvel.data.model.*
import br.com.android.victorcs.mvvmarvel.data.source.local.dao.*

@Database(
    entities = [
        Character::class,
        Comics::class,
        GenericList::class,
        GenericItem::class,
        Thumbnail::class,
        Url::class
    ], version = MvvmDatabase.DB_VERSION
)
abstract class MvvmDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "br.com.android.mvvmarvel.db"
        const val DB_VERSION = 1
    }

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getComicsDao(): ComicsDao
    abstract fun getGenericItemDao(): GenericItemDao
    abstract fun getGenericListDao(): GenericListDao
    abstract fun getThumbnailDao(): ThumbnailDao
    abstract fun getUrlDao(): UrlDao

}