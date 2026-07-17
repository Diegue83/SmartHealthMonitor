package mx.utng.shared.data.models

object MockData {
    val historialFC = listOf(
        LecturaFC(1, 67, System.currentTimeMillis() - 10000, "11:00"),
        LecturaFC(2, 82, System.currentTimeMillis() - 20000, "10:30"),
        LecturaFC(3, 76, System.currentTimeMillis() - 30000, "10:00"),
        LecturaFC(4, 95, System.currentTimeMillis() - 40000, "09:30", false),
        LecturaFC(5, 71, System.currentTimeMillis() - 50000, "09:00"),
        LecturaFC(6, 80, System.currentTimeMillis() - 60000, "08:30"),
        LecturaFC(7, 74, System.currentTimeMillis() - 70000, "08:00")
    )
    var fcActual = 67
    var pasosActual = 4250
    var sp02Actual = 30
}
