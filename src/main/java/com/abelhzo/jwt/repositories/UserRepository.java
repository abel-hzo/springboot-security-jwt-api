package com.abelhzo.jwt.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abelhzo.jwt.entities.User;

/**
 * @author: Abel HZO
 * @project: springboot-security-jwt-api
 * @file: UserRepository.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 05 Septiembre 2023, 14:08:14
 * @description: El presente archivo UserRepository.java fue creado por Abel HZO.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	@Query(value = "SELECT photo FROM users WHERE id_user = :idUser", nativeQuery = true)
	byte[] getPhoto(@Param("idUser") Long idUser);
	
	@Query(value = "SELECT id_user, username, email, birthday FROM users LIMIT :noToShow OFFSET (:noToShow * :page)", nativeQuery = true)
	List<Object[]> getAllUsers(@Param("noToShow") Integer noToShow,
							   @Param("page") Integer page);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET username = :username, "
								  + "email = :email, "
			                      + "password = :password, "
			                      + "birthday = :birthday "
			                      + "WHERE id_user = :idUser", nativeQuery = true)
	void updateUser(@Param("username") String username,
					@Param("email") String email,
					@Param("password") String password,
					@Param("birthday") LocalDate birthday,
					@Param("idUser") Long idUser);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users_roles "
				  + "SET id_rol = :idRol "
			      + "WHERE id_user = :idUser", nativeQuery = true)
	void updateUserRol(@Param("idRol") Integer idRol,
			           @Param("idUser") Long idUser);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET photo = :photo WHERE id_user = :idUser", nativeQuery = true)
	void updatePhoto(@Param("photo") byte[] photo,
					 @Param("idUser") Long idUser);
	
	
	@Query(value = "SELECT COUNT(username) / :noToShow FROM users", nativeQuery = true)
	int getNoPages(@Param("noToShow") Integer noToShow);	

}
