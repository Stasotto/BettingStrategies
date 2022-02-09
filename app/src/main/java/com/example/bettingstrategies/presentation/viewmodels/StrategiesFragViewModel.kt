package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bettingstrategies.presentation.models.Strategy
import com.example.bettingstrategies.presentation.toStrategy
import com.example.bettingstrategies.presentation.toStrategyDomain
import com.example.domain.usecase.DeleteStrategyFromDBUseCase
import com.example.domain.usecase.GetAllDataUseCase
import com.example.domain.usecase.GetAllStrategiesFromDBUseCase
import com.example.domain.usecase.SaveStrategyToDBUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StrategiesFragViewModel(
    private val getAllStrategiesFromDBUseCase: GetAllStrategiesFromDBUseCase,
    private val saveStrategyToDBUseCase: SaveStrategyToDBUseCase,
    private val deleteStrategyFromDBUseCase: DeleteStrategyFromDBUseCase,
    private val getAllDataUseCase: GetAllDataUseCase
) : ViewModel() {


    private val _strategiesForRecycler = MutableLiveData<List<Strategy>>()
    val strategiesForRecycler: LiveData<List<Strategy>> get() = _strategiesForRecycler

    private val _favoritesStrategies = MutableLiveData<List<Strategy>>()
    val favoritesStrategies: LiveData<List<Strategy>> get() = _favoritesStrategies


    init {
        loadStrategies()

    }

    fun updateOnClickBack() {
        loadStrategies()
    }

    private fun loadStrategies() {
        viewModelScope.launch {
            val strategyDefault = async {
                return@async getAllDataUseCase.execute().map { strategyDomain ->
                    strategyDomain.toStrategy()
                }
            }
            val strategyFromDB = async {
                return@async getAllStrategiesFromDBUseCase.execute().map { strategyDomain ->
                    strategyDomain.toStrategy()
                }
            }
            val defaultStrategies = strategyDefault.await()
            val savedStrategies = strategyFromDB.await()
            _favoritesStrategies.value = savedStrategies
            checkSavedStrategies(defaultStrategies, savedStrategies)
        }
    }

    fun loadStrategyFromDB() {
        viewModelScope.launch {
            delay(200)
            _favoritesStrategies.value =
                getAllStrategiesFromDBUseCase.execute().map { strategyDomain ->
                    strategyDomain.toStrategy()
                }
        }
    }

    private fun checkSavedStrategies(
        defaultStrategies: List<Strategy>?,
        databaseStrategies: List<Strategy>?
    ) {
        val defStrategyList = defaultStrategies?.toMutableList() ?: return
        defStrategyList.forEachIndexed { index, defStrategy ->
            databaseStrategies?.forEach { strategyDB ->
                if (defStrategy.name == strategyDB.name) {
                    defStrategyList[index] = strategyDB
                }
            }
        }
        _strategiesForRecycler.value = defStrategyList
    }

    fun saveStrategy(strategy: Strategy) {
        viewModelScope.launch {
            saveStrategyToDBUseCase.execute(strategy.toStrategyDomain())
        }
    }

    fun deleteStrategy(strategy: Strategy) {
        viewModelScope.launch {
            deleteStrategyFromDBUseCase.execute(strategy.toStrategyDomain())
        }
    }

    fun updateData(position: Int) {
        val item = _strategiesForRecycler.value?.get(position) ?: return
        val listData = _strategiesForRecycler.value?.toMutableList() ?: return
        listData[position] = item.copy(isSaved = !item.isSaved)
        _strategiesForRecycler.value = listData
    }
}