Este proyecto implementa el juego Blackjack en Java. El juego incluye la lógica básica del Blackjack, con la capacidad de configurar el número de jugadores, sus nombres, y jugar varias rondas hasta determinar un ganador general.

## Estructura del Proyecto

El proyecto está organizado en paquetes para una mejor modularidad:

- **Model**: Contiene las clases relacionadas con la lógica del juego.
- **View**: Contiene clases relacionadas con la interfaz de usuario y la entrada/salida.
- **Controller**: Contiene la clase principal que gestiona el flujo del juego.

## Contenido del Proyecto

- **Card.java**: Representa una carta del juego Blackjack.
- **Deck.java**: Representa el mazo de cartas y las operaciones relacionadas.
- **Player.java**: Define la clase para los jugadores, incluyendo sus manos y puntuaciones.
- **IO.java**: Proporciona métodos para la entrada/salida con manejo de errores.
- **Menu.java**: Implementa un menú para configurar el juego y los nombres de los jugadores.
- **GameController.java**: Controla el flujo del juego, gestiona rondas y determina al ganador.

## Uso del Juego

1. Ejecuta el programa.
2. Configura el número de jugadores y sus nombres utilizando el menú.
3. Juega rondas hasta que se cumpla la condición de finalización.
4. Visualiza el estado del juego y al ganador al finalizar.

## Requisitos del Sistema

- Java 8 o superior.
