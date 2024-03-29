CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `TOPICS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `content` varchar(2000)
);

CREATE TABLE `SUBSCRIPTIONS` (
  `user_id` integer,
  `topic_id` integer
);

CREATE TABLE `POSTS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer,
  `topic_id` integer,
  `title` varchar(2000),
  `content` varchar(2000),
  `created_at` timestamp
);

CREATE TABLE `COMMENTS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer,
  `post_id` integer,
  `content` varchar(2000),
  `created_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`);
