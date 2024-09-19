package ph.edu.auf.dorio.com.example.activity2


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var attackButton: Button
    private lateinit var defendButton: Button
    private lateinit var healButton: Button
    private lateinit var restartButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var game: Game

    private lateinit var hero: Character
    private lateinit var enemy: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attackButton = findViewById(R.id.attackButton)
        defendButton = findViewById(R.id.defendButton)
        healButton = findViewById(R.id.healButton)
        restartButton = findViewById(R.id.restartButton)
        resultTextView = findViewById(R.id.resultTextView)

        restartButton.visibility = View.GONE

        // Initialize characters and game
        initializeGame()

        // Set up button listeners
        attackButton.setOnClickListener {
            val result = game.playerAction("attack")
            resultTextView.text = result
        }

        defendButton.setOnClickListener {
            val result = game.playerAction("defend")
            resultTextView.text = result
        }

        healButton.setOnClickListener {
            val result = game.playerAction("heal")
            resultTextView.text = result
        }

        restartButton.setOnClickListener {
            initializeGame() // Restart the game
            resultTextView.text = "New Game Started! \n${hero.getStatus()}\n${enemy.getStatus()}"
            restartButton.visibility = View.GONE // Hide the restart button
        }
    }

    // Initialize the game state
    private fun initializeGame() {
        hero = Character("Victor Magtanggol", Random.nextInt(1000, 1500), Random.nextInt(100, 200), Random.nextInt(80, 150))
        enemy = Character("John Cena", Random.nextInt(1000, 2000), Random.nextInt(1000, 2000), Random.nextInt(90, 180))
        game = Game(hero, enemy, this)

        attackButton.visibility = View.VISIBLE
        defendButton.visibility = View.VISIBLE
        healButton.visibility = View.VISIBLE
        restartButton.visibility = View.GONE
    }

    // Method to hide buttons when the game is over
    fun disableButtons() {
        attackButton.visibility = View.GONE
        defendButton.visibility = View.GONE
        healButton.visibility = View.GONE
        restartButton.visibility = View.VISIBLE // Show the restart button
    }
}

