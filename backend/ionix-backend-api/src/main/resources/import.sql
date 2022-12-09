INSERT INTO users (name, username, email, phone) VALUES('Jorge Rodriguez', 'jrodriguez', 'jrodrigueznovoa@gmail.com', '+569821313185');
INSERT INTO users (name, username, email, phone) VALUES('Aldo Fuenzalida', 'aldoFuenzalida', 'aldoFuenzalida@gmail.com', '+569821313183');
INSERT INTO users (name, username, email, phone) VALUES('Carlos Ponce', 'carlosPonce23', 'carlosPonce23@gmail.com', '+569821313999');


INSERT INTO user_token(id_usuario, nombre, clave, estado) values (1, 'jrodrigueznovoa', '$2a$12$SQ9.iOC3HTtgIWwMbJilqOUtNXMo9x7GWOc98nzhVXD8nGzE6pIWe', 1);

INSERT INTO Rol (id_rol, nombre) VALUES (1, 'ADMIN');

INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 1);