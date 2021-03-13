package com.example.demo.persistencia.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.example.demo.modelo.user.UserEntity;
import com.example.demo.modelo.user.UserRole;
import com.example.demo.persistencia.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseService<UserEntity, String, UserEntityRepository> {

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	public Optional<UserEntity> findByUserName(String username) {
		return this.repositorio.findByUsername(username);
	}

	// metodo que nos permitirá registrar un nuevo usuario en el sistema
	public UserEntity nuevoUsuario(UserEntity userEntity) {

		userEntity.setId(String.valueOf(Math.abs(new Random().nextInt())));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

		// en caso de no haber asignado un rol en la petición, añadimos uno por defecto
		Set<UserRole> defaultRoles = new HashSet<UserRole>();
		defaultRoles.add(UserRole.USER);

		if (userEntity.getRoles() == null) {

			userEntity.setRoles(defaultRoles);
		} else if (userEntity.getRoles().size() == 0) {

			userEntity.setRoles(defaultRoles);
		}
		return this.repositorio.save(userEntity);
	}
}
