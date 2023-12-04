package com.JJEP.JJEP.user;

// custom exception for when a user is not found
public class UserNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public UserNotFoundException(String message) {
            super(message);
        }
}
