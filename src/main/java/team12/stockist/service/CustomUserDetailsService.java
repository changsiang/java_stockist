package team12.stockist.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import team12.stockist.model.User;
import team12.stockist.repository.UserRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		}
		else
		{
		
			List<String> userRoles = userRepository.findRoleByUserName(username);
			return new CustomUserDetails(user,userRoles);
		}

	}

}
