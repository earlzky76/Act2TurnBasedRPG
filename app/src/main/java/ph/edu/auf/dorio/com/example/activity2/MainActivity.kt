package ph.edu.auf.dorio.com.example.activity2


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private lateinit var attackButton: Button
    private lateinit var defendButton: Button
    private lateinit var healButton: Button

    private val hero = Character("Victor Magtanggol", 180, 200, 200)
    private val enemy = Character("John Cena", Random.nextInt(100, 300), Random.nextInt(50, 250), Random.nextInt(30, 150))
    private val game = Game(hero, enemy)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusTextView = findViewById(R.id.statusTextView)
        attackButton = findViewById(R.id.attackButton)
        defendButton = findViewById(R.id.defendButton)
        healButton = findViewById(R.id.healButton)

        attackButton.setOnClickListener { performAction("attack") }
        defendButton.setOnClickListener { performAction("defend") }
        healButton.setOnClickListener { performAction("heal") }

        updateStatus("Game started! ${hero.name} vs ${enemy.name}\n${hero.getStatus()}\n${enemy.name} - HP: ${enemy.hp}")
    }

    private fun performAction(action: String) {
        val result = game.playerAction(action)
        updateStatus(result)
    }

    private fun updateStatus(message: String) {
        statusTextView.text = message
    }
}