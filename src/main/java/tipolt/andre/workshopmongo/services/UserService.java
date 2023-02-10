package tipolt.andre.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.workshopmongo.domain.User;
import tipolt.andre.workshopmongo.repository.UserRepository;
import tipolt.andre.workshopmongo.services.exception.ObjectNotFoundException;
import tipolt.andre.workshopmongo.userdto.UserDTO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	public User findById(String id) {
		User user = userRepository.findById(id).get();

		if (user == null) {
			throw new ObjectNotFoundException("Object Not Found");
		}
		return user;
	}
	
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(),objDTO.getName(), objDTO.getEmail());
	}
	
	public void deleteById(String id) {
		findById(id); // É pra se nao ter, ele ja retorna aquele erro
		userRepository.deleteById(id);
	}
	
	public User update(User newObj) {
		User obj = userRepository.findById(newObj.getId()).get();
		updateData(obj, newObj);
		return userRepository.save(obj);
		
	}
	private void updateData(User obj, User newObj) {
		
		obj.setName(newObj.getName());
		obj.setEmail(newObj.getEmail());
	}
}
