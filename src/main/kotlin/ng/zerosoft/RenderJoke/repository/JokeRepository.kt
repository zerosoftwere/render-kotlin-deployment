package ng.zerosoft.RenderJoke.repository

import ng.zerosoft.RenderJoke.domain.Joke
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JokeRepository : JpaRepository<Joke, Long>
