package ph.edu.auf.dorio.com.example.activity2

import kotlin.random.Random

class Game(private val hero: Character, private val enemy: Character) {

    init {
        // Randomize enemy stats for each game
        enemy.hp = Random.nextInt(100, 300)
        enemy.attack = Random.nextInt(50, 250)
        enemy.defense = Random.nextInt(30, 150)
    }

    fun playerAction(action: String): String {
        val heroActionResult = when (action) {
            "attack" -> hero.attack(enemy)
            "heal" -> hero.heal()
            "defend" -> hero.defend()
            else -> "Invalid action"
        }

        if (!enemy.isAlive()) {
            hero.levelUp() // Level up only if the enemy is defeated
            return "$heroActionResult\n${enemy.name} is defeated!\n${hero.name} wins!\n${hero.getStatus()}"
        }

        val enemyActionResult = enemyAction()
        return if (!hero.isAlive()) {
            "${heroActionResult}\n${enemyActionResult}\n${hero.name} is defeated!\n${enemy.name} wins!"
        } else {
            "$heroActionResult\n$enemyActionResult\n${hero.getStatus()}\n${enemy.name} - HP: ${enemy.hp}"
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
}