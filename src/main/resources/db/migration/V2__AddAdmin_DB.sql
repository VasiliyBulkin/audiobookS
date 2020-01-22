INSERT INTO usr (id, active, password, username)
    VALUES (1, true, '1', '1');
INSERT INTO user_role (user_id, roles)
    VALUES (1, 'USER'), (1, 'ADMIN');