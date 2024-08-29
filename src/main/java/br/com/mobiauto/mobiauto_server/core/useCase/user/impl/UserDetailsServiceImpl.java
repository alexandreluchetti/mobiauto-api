package br.com.mobiauto.mobiauto_server.core.useCase.user.impl;

import br.com.mobiauto.mobiauto_server.core.entity.user.CustomUserDetails;
import br.com.mobiauto.mobiauto_server.core.useCase.user.UserRepository;
import br.com.mobiauto.mobiauto_server.dataprovider.user.entity.UserValidationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserValidationEntity user = userRepository.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new CustomUserDetails(user);
    }
}
