
CREATE TABLE t_task(
 client_id VARCHAR(30), 
 machine_id VARCHAR(30),
 task_type INT,
 money VARCHAR(30),
 time_len  VARCHAR(30),
 STATUS VARCHAR(30) ,
 flag INT DEFAULT 0,
 insert_date TIMESTAMP
)

CREATE TABLE t_card(
 id int(11) NOT NULL PRIMARY KEY  AUTO_INCREMENT,
 card_id VARCHAR(30), 
 
 reserve_money VARCHAR(30),
 
 balance_money VARCHAR(30),
 flag  VARCHAR(30),
 STATUS VARCHAR(30) ,
 
 insert_date TIMESTAMP
);

CREATE TABLE t_recharge(
 id int(11) NOT NULL PRIMARY KEY  AUTO_INCREMENT,
 client_id VARCHAR(30), 
 machine_id VARCHAR(30),
 task_type INT,
 card_id VARCHAR(30), 
 hex_card_id VARCHAR(30),
 reserve_money VARCHAR(30),
 
 recharge_money VARCHAR(30),
 balance_money VARCHAR(30),
 STATUS VARCHAR(30) ,
 flag INT DEFAULT 0,
 insert_date TIMESTAMP
)
