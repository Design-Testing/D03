Decisiones de planificacion:

Se decidió modelar UML conceptual y de dominio hasta nivel A para evaluar la complejidad del sistema y una vez completos, decidimos ir a por el nivel B.

-----------------------------------

Decisiones de implementacion:

Hemos decidido añadir mensajería con el objetivo de implementar la funcionalidad de notificar a los usuarios de diferentes acciones dentro del sistema, como una brecha de datos. Acciones exigidas por parte de la GDPR.

En cuanto a la creación de posiciones:

- La empresa puede asignar uno de sus problemas a una sola posición.
- La campaña puede desasociar un problema desde una posición siempre que la posición esté en modo borrador (estar disponible nuevamente para asignarse a cualquier otra posición) o eliminarla del sistema.
- Una vez que la posición se establece en el modo final, esos problemas se asignarán a esa posición de forma permanente y no se podrán eliminar del sistema. Sin embargo, si la compañía cancela la posición, entonces los problemas se pueden eliminar.
