drop table if exists ers_reimbursement_status, ers_reimbursement_type, ers_user_roles, ers_users, ers_reimbursement;
create table if not exists ers_reimbursement_status (
	reimb_status_id SERIAL primary key,
	reimb_status VARCHAR(10) not null
);
create table if not exists ers_reimbursement_type (
	reimb_type_id SERIAL primary key,
	reimb_type VARCHAR(10) not null
);
create table if not exists ers_user_roles (
	ers_user_role_id SERIAL primary key,
	user_role VARCHAR(10) not null
);

create table if not exists ers_users (
	ers_users_id SERIAL primary key,
	ers_username VARCHAR(50) unique not null,
	ers_password VARCHAR(50) not null,
	user_first_name VARCHAR(100) not null,
	user_last_name VARCHAR(100) not null,
	user_email VARCHAR(150) not null,
	user_role_id VARCHAR(250) references ers_user_roles(ers_user_role_id) not null
);

create table if not exists ers_reimbursement (
	reimb_id SERIAL primary key,
	reimb_amount INTEGER not null,
	reimb_submitted TIMESTAMP not null,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250) not null,
	reimb_author INTEGER references ers_users(ers_users_id) not null,
	reimb_resolver INTEGER references ers_users(ers_users_id),
	reimb_status_id INTEGER references ers_reimbursement_status(reimb_status_id) not null,
	reimb_type_id INTEGER references ers_reimbursement_type(reimb_type_id) not null
);

truncate ers_reimbursement_status, ers_reimbursement_type, ers_user_roles, ers_users, ers_reimbursement;
insert into ers_user_roles (user_role) values 
('Employee'),
('Manager');
insert into ers_reimbursement_type (reimb_type) values 
('Business'),
('Deductible'),
('Medical'),
('Travel');
insert into ers_reimbursement_status (reimb_status) values 
('Pending'),
('Rejected'),
('Accepted');
insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id) values
('manager', 'manager', 'manager', 'manager', 'manager', 2),
('employee', 'employee', 'employee','employee','employee', 1);
insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values
(200, CURRENT_TIMESTAMP, null, 'Gas fordriving to meeting', 2, null, 1, 4);