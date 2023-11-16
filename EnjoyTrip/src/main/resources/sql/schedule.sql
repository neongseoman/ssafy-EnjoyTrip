create table `schedule`(
	`user_id` varchar(16) not null,
    `title` varchar(500) not null, 
    `time` varchar(8),
    `date` varchar(10), 
    `completed` boolean not null,
    primary key(`user_id`, `time`, `date`)
);