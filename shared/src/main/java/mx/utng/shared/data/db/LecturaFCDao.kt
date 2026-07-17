package mx.utng.shared.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mx.utng.shared.data.models.LecturaFC

@Dao
interface LecturaFCDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(lectura: LecturaFC)

    @Query("""
        SELECT * FROM lecturas_fc
        ORDER BY timestamp DESC
        LIMIT 50""")
    fun obtenerUltimas(): Flow<List<LecturaFC>>

    @Query("SELECT COUNT(*) FROM lecturas_fc")
    suspend fun contarRegistros(): Int

    @Query("""
        DELETE FROM lecturas_fc
        WHERE timestamp < :limite""")
    suspend fun limpiarViejos(limite: Long)
}
