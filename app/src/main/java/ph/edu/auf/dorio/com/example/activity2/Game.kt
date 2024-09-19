package ph.edu.auf.dorio.com.example.activity2

import kotlin.random.Random

class Game(private val hero: Character, private var enemy: Character, private val activity: MainActivity) {

    init {
        spawnNewEnemy() // Initialize with a new enemy whose stats depend on hero's level
    }

    private fun randomizeEnemyStats() {
        // Scale enemy stats based on the hero's level
        val levelMultiplier = hero.level * 1.2  // Scale enemy stats relative to hero level (adjustable)

        enemy.hp = (Random.nextInt(100, 300) * levelMultiplier).toInt()
        enemy.attack = (Random.nextInt(50, 250) * levelMultiplier).toInt()
        enemy.defense = (Random.nextInt(30, 150) * levelMultiplier).toInt()
        enemy.level = (Random.nextInt(1, 5) + levelMultiplier).toInt()
    }

    fun playerAction(action: String): String {
        val heroActionResult = when (action) {
            "attack" -> hero.attack(enemy)
            "heal" -> hero.heal()
            "defend" -> hero.defend()
            else -> return "Invalid action. Please choose 'attack', 'heal', or 'defend'."
        }
        val enemyActionResult = enemyAction()

        // Check if enemy is defeated
        if (!enemy.isAlive()) {
            hero.levelUp() // Level up only if the enemy is defeated
            spawnNewEnemy() // Spawn a new enemy based on hero's updated level
            return "Hero Wins! A new enemy appears!"
        }

        // Check if hero is defeated (Game Over)
        return if (!hero.isAlive()) {
            activity.disableButtons() // Disable buttons in the UI
            "Enemy Wins! Game Over! \n${hero.getStatus()}\n${enemy.getStatus()}"
        } else {
            "$heroActionResult\n$enemyActionResult\n\n${hero.getStatus()}\n\n${enemy.getStatus()}"
        }
    }

    private fun enemyAction(): String {
        return when ((1..3).random()) {
            1 -> enemy.attack(hero)
            2 -> enemy.heal()
            3 -> enemy.defend()
            else -> "Enemy takes no action"
        }
    }

    private fun spawnNewEnemy() {
        // Scale the new enemy's stats based on the hero's level
        randomizeEnemyStats()
    }
}



