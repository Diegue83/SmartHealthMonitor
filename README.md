# SmartHealth Monitor
![Android CI](https://img.shields.io/badge/Android-API26+-green)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-MD3-blue)
 
Aplicación Android multiplataforma para monitoreo de salud personal.
Desarrollada como proyecto integrador en UTNG — 9° Cuatrimestre 2025.
 
## Stack tecnológico
| Tecnología | Uso |
|---|---|
| Kotlin + Jetpack Compose | UI declarativa con Material Design 3 |
| Wearable Data Layer API  | Comunicación reloj ↔ teléfono (BLE) |
| Health Services API     | Sensor FC real en background (Wear OS) |
| Room Database           | Historial persistente de lecturas FC |
| Jetpack Navigation      | NavHost entre 4 pantallas |
| GitHub + Conventional Commits | Control de versiones profesional |
 
## Pantallas
| Pantalla | Descripción |
|---|---|
| LoginScreen | Autenticación con validación y State |
| DashboardScreen | FC y Pasos en tiempo real del wearable |
| HistorialScreen | Lecturas persistidas en Room con Flow reactivo |
| AlertaScreen | AlertDialog MD3 + Snackbar de confirmación |

 
## Pantallas implementadas
- [x] LoginScreen — S4
- [x] DashboardScreen — S5
- [ ] Historial + wearable real — S6
- [ ] Android TV — S10-S12
 
## Autor
Juan Diego Juarez Cruz — UTNG — 4181180400d@gmail.com

# Vista previa de la aplicación
## LogIn
<img width="800" height="1800" alt="image" src="https://github.com/user-attachments/assets/0d3f1d6e-68a5-4989-87ed-e9c4b7b15334" />

## Dashboard
<img width="800" height="1800" alt="image" src="https://github.com/user-attachments/assets/9a8d4647-a69c-401c-bc4a-5da79f54101e" />



