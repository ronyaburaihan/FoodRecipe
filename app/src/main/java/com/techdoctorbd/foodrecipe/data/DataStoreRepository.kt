package com.techdoctorbd.foodrecipe.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.techdoctorbd.foodrecipe.data.models.MealAndDietType
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_DIET_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.DEFAULT_MEAL_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_BACK_ONLINE
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_DIET_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_DIET_TYPE_ID
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_MEAL_TYPE
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_MEAL_TYPE_ID
import com.techdoctorbd.foodrecipe.utils.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCE_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCE_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCE_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCE_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCE_BACK_ONLINE)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    private val dataStore = context.dataStore

    suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit {
            it[PreferenceKeys.selectedMealType] = mealType
            it[PreferenceKeys.selectedMealTypeId] = mealTypeId
            it[PreferenceKeys.selectedDietType] = dietType
            it[PreferenceKeys.selectedDietTypeId] = dietTypeId

        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val mealType = preference[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val mealTypeId = preference[PreferenceKeys.selectedMealTypeId] ?: 0
            val dietType = preference[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val dietTypeId = preference[PreferenceKeys.selectedDietTypeId] ?: 0

            MealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

    val readBackOnline: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val backOnline = preference[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}