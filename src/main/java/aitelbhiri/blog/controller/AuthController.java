package aitelbhiri.blog.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aitelbhiri.blog.model.Comment;
import aitelbhiri.blog.model.ERole;
import aitelbhiri.blog.model.Role;
import aitelbhiri.blog.model.Utilisateur;
import aitelbhiri.blog.model.Post;
import aitelbhiri.blog.request.LoginRequest;
import aitelbhiri.blog.request.SignupRequest;
import aitelbhiri.blog.response.JwtResponse;
import aitelbhiri.blog.response.MessageResponse;
import aitelbhiri.blog.repository.RoleRepository;
import aitelbhiri.blog.repository.UtilisateurRepository;
import aitelbhiri.blog.repository.CommentRepository;
import aitelbhiri.blog.repository.PostRepository;
import aitelbhiri.blog.security.JwtUtils;
import aitelbhiri.blog.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles));
    }

    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Utilisateur user;
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
                    .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(gestionnaireRole);
                        break;
                    case "author":
                        Role employeRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(employeRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
                                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                }
            });
        }

        if (signUpRequest.getUsername() != null && signUpRequest.getNom() != null && signUpRequest.getPrenom() != null) {
            // Fetch Post and Post entities by their IDs
            Comment comment = commentRepository.findById(signUpRequest.getComment().getId())
                    .orElseThrow(() -> new RuntimeException("Error: Comment not found."));

            Post post = postRepository.findById(signUpRequest.getPost().getId())
                    .orElseThrow(() -> new RuntimeException("Error: Post not found."));
            // Use the constructor with more arguments if matricule, datenaissance, and cin
            // are provided
            user = new Utilisateur(0, signUpRequest.getUsername(), signUpRequest.getNom(), signUpRequest.getPrenom(),
                    signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), comment, post);
        } else {
            // Use the constructor with fewer arguments
            user = new Utilisateur(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
