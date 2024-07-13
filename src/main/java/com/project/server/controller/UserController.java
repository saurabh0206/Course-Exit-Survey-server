// package com.project.server.controller;

// import org.springframework.beans.factory.annotation.Value;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

// // @Autowired
// // private UserRepository userRepository;

// @Value("${jwt.secret}")
// private String jwtSecret;

// @Value("${jwt.expiration}")
// private Long jwtExpiration;

// // private Key getSigningKey() {
// // byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
// // return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
// // }

// // private String createToken(String userId) {
// // return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
// // .setExpiration(new Date(System.currentTimeMillis() +
// // jwtExpiration)).signWith(getSigningKey())
// // .compact();
// // }

// // @PostMapping("/login")
// // public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest)
// {
// // Optional<User> userOpt =
// userRepository.findByEmail(loginRequest.getEmail());

// // if (!userOpt.isPresent() || !BCrypt.checkpw(loginRequest.getPassword(),
// // userOpt.get().getPassword())) {
// // return ResponseEntity.status(400).body("Invalid email or password");
// // }

// // User user = userOpt.get();
// // //String token = createToken(user.getId());

// // return ResponseEntity.ok(new LoginResponse(user.getEmail(), token,
// // user.getRole()));
// // }

// // @PostMapping("/google-login")
// // public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginRequest
// // googleLoginRequest) {
// // Optional<User> userOpt =
// // userRepository.findByEmail(googleLoginRequest.getData().getEmail());

// // if (!userOpt.isPresent()) {
// // return ResponseEntity.status(401).body("User currently not enrolled in any
// // course");
// // }

// // User user = userOpt.get();
// // String token = createToken(user.getId());

// // return ResponseEntity.ok(new LoginResponse(user.getEmail(), token,
// // user.getRole()));
// // }
// }
