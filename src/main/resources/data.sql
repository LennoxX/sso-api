INSERT INTO public.applications (created_at, id, name, description, api_path, app_path, icon, selectable) VALUES ('2025-01-15 10:27:54.657575', 1, 'SSO', 'Aplicação para controle de usuários, single sign-on, gerenciamento de permissões, etc.', 'sso-api', 'sso-app', 'fa-solid fa-shield-halved', false);

INSERT INTO public.users (created_at, id, updated_at, username, email, password) VALUES ('2025-01-14 23:01:40.987433', 5, '2025-01-14 23:01:40.987433', 'Admin', 'admin@admin.com', '$2a$12$V/Sxcp5.Es5UYuHY4.d6E.DQOhu9Xv.dDff/NIAvTdhoGzrHArLYG');

INSERT INTO public.user_roles (user_id, role) VALUES (5, 'ADMIN');

INSERT INTO public.user_applications (application_id, user_id) VALUES (1, 5);
INSERT INTO public.user_applications (application_id, user_id) VALUES (2, 5);


