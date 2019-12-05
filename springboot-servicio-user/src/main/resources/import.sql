INSERT INTO `usuarios` (user_name, password, enabled, name, last_name, email) VALUES ('andres','$2a$10$LdOlKF2/VcTR83zz8ErLj.WI4ndoGaC3nc8JaosfUo7tkA.nRvuVi',1, 'Andres', 'Guzman','profesor@bolsadeideas.com');
INSERT INTO `usuarios` (user_name, password, enabled, name, last_name, email) VALUES ('admin','$2a$10$Rd0TsZrAE6iPxLuJhJuo8eRf35XrfjX0KlgN5ebMu65divFb9u8R2',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);
