package tipolt.andre.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import tipolt.andre.workshopmongo.domain.Post;
import tipolt.andre.workshopmongo.domain.User;
import tipolt.andre.workshopmongo.repository.PostRepository;
import tipolt.andre.workshopmongo.repository.UserRepository;
import tipolt.andre.workshopmongo.userdto.AuthorDTO;
import tipolt.andre.workshopmongo.userdto.CommentDTO;


@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, Instant.now(), "Partiu viagem", "Viajar é muito bommm!!!", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.now(), "Partiu viagem", "Viajar é muito bommm!!!", new AuthorDTO(alex));
		
		
		CommentDTO comment1 = new CommentDTO("Boa Viagem mano", Instant.now(), new AuthorDTO(alex)) ;
		CommentDTO comment2 = new CommentDTO("Que lugar incrível !!!", Instant.now(), new AuthorDTO(maria)) ;
		
		post1.getComments().add(comment1);
		post2.getComments().add(comment2);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().add(post1);
		alex.getPosts().add(post2);
		userRepository.saveAll(Arrays.asList(maria, alex));
		
		
	}
	
	
	
}
