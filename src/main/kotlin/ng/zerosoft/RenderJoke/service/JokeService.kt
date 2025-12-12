package ng.zerosoft.RenderJoke.service

import ng.zerosoft.RenderJoke.domain.Joke
import ng.zerosoft.RenderJoke.repository.JokeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


class JokeNotFoundException(id: Long) : RuntimeException("Joke with id=$id not found")

@Service
class JokeService(private val jokeRepository: JokeRepository) {

    @Transactional(readOnly = true)
    fun list(): List<Joke> = jokeRepository.findAll()

    @Transactional(readOnly = true)
    fun findById(id: Long): Joke = jokeRepository.findById(id).orElseThrow { JokeNotFoundException(id) }

    @Transactional
    fun create(setup: String, punchline: String): Joke {
        val joke = Joke(setup = setup, punchline = punchline)
        return jokeRepository.save(joke)
    }

    @Transactional
    fun update(id: Long, setup: String, punchline: String): Joke {
        val joke = jokeRepository.findById(id).orElseThrow { JokeNotFoundException(id) }
        joke.setup = setup
        joke.punchline = punchline
        return jokeRepository.save(joke)
    }

    @Transactional
    fun delete(id: Long) {
        if (!jokeRepository.existsById(id)) {
            throw JokeNotFoundException(id)
        }
        jokeRepository.deleteById(id)
    }
}
