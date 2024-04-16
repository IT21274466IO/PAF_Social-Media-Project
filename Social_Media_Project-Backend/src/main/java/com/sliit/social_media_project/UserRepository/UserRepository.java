package com.sliit.social_media_project.UserRepository;

import com.sliit.social_media_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {
}
