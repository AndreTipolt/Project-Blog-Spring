package tipolt.andre.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.workshopmongo.domain.Post;
import tipolt.andre.workshopmongo.repository.PostRepository;
import tipolt.andre.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	public Post findById(String id) {
		Post post = postRepository.findById(id).get();

		if (post == null) {
			throw new ObjectNotFoundException("Object Not Found");
		}
		return post;
	}
	
	public Post insert(Post obj) {
		return postRepository.save(obj);
	}
	
	public void deleteById(String id) {
		findById(id); // É pra se nao ter, ele ja retorna aquele erro
		postRepository.deleteById(id);
	}
	
}
