ALTER TABLE t_user_role ADD COLUMN update_time timestamp on update current_timestamp;
ALTER TABLE t_user_role ADD COLUMN create_time timestamp default current_timestamp