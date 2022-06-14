INSERT INTO role (name) SELECT 'admin' WHERE NOT EXISTS (SELECT * FROM role WHERE id = 1);
INSERT INTO role (name) SELECT 'user' WHERE NOT EXISTS (SELECT * FROM role WHERE id = 2);

INSERT INTO permission (name) SELECT 'admin_permission' WHERE NOT EXISTS (SELECT * FROM permission WHERE id = 1);
INSERT INTO permission (name) SELECT 'user_permission' WHERE NOT EXISTS (SELECT * FROM permission WHERE id = 2);

INSERT INTO "authorization" VALUES (1, 1) ON CONFLICT (role_id, permission_id) DO NOTHING;
INSERT INTO "authorization" VALUES (2, 2) ON CONFLICT (role_id, permission_id) DO NOTHING;

/* ADMIN PW = ADMIN, USER PW = USER */
INSERT INTO "user" (email, password, first_name, last_name, role_id)
SELECT 'admin@demo.com', '$2a$10$ODyE8eT17T9jksC4Ci4Ifu3fS22OW83ZZ79E/dQ8xalNeRLwueHaC', 'mister', 'admin', 1
WHERE NOT EXISTS (SELECT * FROM "user" WHERE id = 1);
INSERT INTO "user" (email, password, first_name, last_name, role_id)
SELECT 'user@demo.com', '$2a$10$02gNN9XHKvnXW5OPhKQoWuPMVk.EhL2LiERfpSmsyYywu7YwAaVUW', 'mister', 'user', 2
WHERE NOT EXISTS (SELECT * FROM "user" WHERE id = 2);
INSERT INTO "user" (email, password, first_name, last_name, role_id)
SELECT 'user555@demo.com', '$2a$10$02gNN9XHKvnXW5OPhKQoWuPMVk.EhL2LiERfpSmsyYywu7YwAaVUW', 'mister', 'user', 2
WHERE NOT EXISTS (SELECT * FROM "user" WHERE id = 4);
INSERT INTO "user" (email, password, first_name, last_name, role_id)
SELECT 'user666@demo.com', '$2a$10$02gNN9XHKvnXW5OPhKQoWuPMVk.EhL2LiERfpSmsyYywu7YwAaVUW', 'mister', 'user', 2
WHERE NOT EXISTS (SELECT * FROM "user" WHERE id = 5);
INSERT INTO "user" (email, password, first_name, last_name, role_id)
SELECT 'user777@demo.com', '$2a$10$02gNN9XHKvnXW5OPhKQoWuPMVk.EhL2LiERfpSmsyYywu7YwAaVUW', 'mister', 'user', 2
WHERE NOT EXISTS (SELECT * FROM "user" WHERE id = 6);