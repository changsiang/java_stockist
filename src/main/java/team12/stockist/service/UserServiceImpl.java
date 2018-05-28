package team12.stockist.service;

import java.util.ArrayList;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team12.stockist.model.User;
import team12.stockist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

	@Override
	@Transactional
	public ArrayList<User> findAllUser() {
		ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
		return users;

	}
	
	
	@Transactional
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	@Transactional
	public User findUserById(Integer userId) {
		return userRepository.findOne(userId);
	}

	@Override
	@Transactional
	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	

	public boolean userAlreadyExists(User user) {
		if (findByUserName(user.getUsername()) != null)
			return true;
		else
			return false;
	}
	
}
