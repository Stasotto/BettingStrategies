package com.example.data.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.R

@Entity(tableName = "strategy")
data class StrategyEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isSaved")
    val isSaved: Boolean = false,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @PrimaryKey
    var id: Int
) {
    companion object {

        fun getStrategies(context: Context): List<StrategyEntity> {
            val listOfNames: List<String> =
                (context.resources.getStringArray(
                    R.array.type_of_betting_strategies
                )).toList()
            val listDescriptions: List<String> =
                (context.resources.getStringArray(
                    R.array.description_of_the_betting_strategies
                )).toList()
            val imageUrls: List<String> =
                (context.resources.getStringArray(R.array.urls)).toList()
            return listOfNames.indices.map { index ->
                StrategyEntity(
                    name = listOfNames[index],
                    description = listDescriptions[index],
                    imageUrl = imageUrls[index],
                    id = index
                )
            }
        }
    }
}
