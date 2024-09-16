package ph.edu.auf.dorio.com.example.activity2

class Character(val name: String, var hp: Int, var attack: Int, var defense: Int) {

    var level: Int = 1

    fun attack(target: Character): String {
        val damage = (this.attack - target.defense).coerceAtLeast(0)
        target.hp -= damage
        return "${this.name} attacks ${target.name} for $damage damage!"
    }

    fun heal(): String {
        val healingAmount = (10..20).random()
        this.hp += healingAmount
        return "${this.name} heals for $healingAmount HP!"
    }

    fun defend(): String = "${this.name} defends!"

    fun isAlive(): Boolean = hp > 0

    fun levelUp() {
        level++
        attack += 10
        defense += 5
        hp += 20
    }

    fun getStatus(): String = "$name - HP: $hp, Attack: $attack, Defense: $defense, Level: $level"
}