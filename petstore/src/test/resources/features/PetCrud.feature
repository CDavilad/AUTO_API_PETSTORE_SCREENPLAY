Feature: Gestion completa del ciclo de vida de una mascota en la PetStore

  Scenario: Registro consulta actualizacion y eliminacion de una mascota
    Given el usuario quiere gestionar una mascota en la tienda
    When registra una nueva mascota con nombre "Firulais" y estado "available"
    Then la mascota es registrada exitosamente
    When consulta la informacion de la mascota registrada
    Then obtiene los datos correctos de la mascota
    When actualiza el nombre de la mascota a "Firulais Jr" y el estado a "sold"
    Then la mascota es actualizada exitosamente
    When elimina la mascota de la tienda
    Then la mascota es eliminada exitosamente