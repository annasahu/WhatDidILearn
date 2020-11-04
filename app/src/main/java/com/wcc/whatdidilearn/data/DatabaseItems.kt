package com.wcc.whatdidilearn.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wcc.whatdidilearn.entities.LearnedItem
import com.wcc.whatdidilearn.entities.Level
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [LearnedItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseItems: RoomDatabase() {

    abstract fun learnedItemDao(): LearnedItemDao

    companion object {
        // Singleton para evitar que multiplas instancias do banco de dados sejam abertas ao mesmo tempo
        @Volatile
        private var INSTANCE: DatabaseItems? = null

        fun getDatabase(context: Context): DatabaseItems {
            // se INSTANCE não é nulo, então retorna ela mesma,
            // se INSTANCE é nula, então cria uma instancia do banco
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseItems::class.java,
                    "learned_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun getAll(): List<LearnedItem> {
            val itemOne = LearnedItem(
                "Kotlin - Null safety",
                "O sistema de tipos de Kotlin visa eliminar o perigo de referências nulas do código",
                Level.HIGH
            )

            val itemTwo = LearnedItem(
                "Layout editor",
                "O Design Editor exibe o layout em vários dispositivos e versões do Android.É possível criar e editar um layout usando apenas componentes visuais.",
                Level.MEDIUM
            )

            val itemThree = LearnedItem(
                "Git",
                "É um sistema de controle de versão distribuído. Com ele é possível rastrear mudanças no código-fonte durante o desenvolvimento de software.",
                Level.LOW
            )

            val itemFour = LearnedItem(
                "GroupView",
                "É uma view especial que pode conter outras views (chamadas de filhos).É a classe base para layouts e contêineres de views.",
                Level.HIGH
            )

            val itemFive = LearnedItem(
                "ViewBinding",
                "View Binding é um recurso que facilita a programação de códigos que interagem com views.",
                Level.LOW
            )

            return listOf(itemOne, itemTwo, itemThree, itemFour, itemFive)

        }
    }

    private class DatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.learnedItemDao()) }
            }
        }
        suspend fun populateDatabase(dao: LearnedItemDao) {
            val itemOne = LearnedItem(
                "Kotlin - Null safety",
                "O sistema de tipos de Kotlin visa eliminar o perigo de referências nulas do código",
                Level.HIGH
            )

            val itemTwo = LearnedItem(
                "Layout editor",
                "O Design Editor exibe o layout em vários dispositivos e versões do Android.É possível criar e editar um layout usando apenas componentes visuais.",
                Level.MEDIUM
            )

            val itemThree = LearnedItem(
                "Git",
                "É um sistema de controle de versão distribuído. Com ele é possível rastrear mudanças no código-fonte durante o desenvolvimento de software.",
                Level.LOW
            )

            val itemFour = LearnedItem(
                "GroupView",
                "É uma view especial que pode conter outras views (chamadas de filhos).É a classe base para layouts e contêineres de views.",
                Level.HIGH
            )

            val itemFive = LearnedItem(
                "ViewBinding",
                "View Binding é um recurso que facilita a programação de códigos que interagem com views.",
                Level.LOW
            )
            dao.insert(itemOne)
            dao.insert(itemTwo)
            dao.insert(itemThree)
            dao.insert(itemFour)
            dao.insert(itemFive)
        }
    }
}