CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE images
(
  image_id UUID NOT NULL DEFAULT uuid_generate_v4(),
  path     VARCHAR(255),
  PRIMARY KEY (image_id)
);

CREATE TABLE security
(
  security_id INTEGER NOT NULL ,
  name        VARCHAR(255),
  description VARCHAR(255),
  PRIMARY KEY (security_id)
);

CREATE TABLE posts
(
  post_id     UUID NOT NULL DEFAULT uuid_generate_v4(),
  author_id   UUID NOT NULL ,
  date        TIMESTAMP ,
  title       VARCHAR(255),
  post        TEXT,
  security_id INTEGER NOT NULL ,
  deleted     BOOLEAN NOT NULL DEFAULT 'false',
  PRIMARY KEY (post_id),
  FOREIGN KEY (security_id) REFERENCES security (security_id)
);

CREATE TABLE comments
(
  comment_id UUID NOT NULL DEFAULT uuid_generate_v4(),
  post_id    UUID NOT NULL ,
  author_id  UUID NOT NULL ,
  date       TIMESTAMP ,
  comment    TEXT,
  deleted    BOOLEAN NOT NULL DEFAULT 'false',
  PRIMARY KEY (comment_id),
  FOREIGN KEY (post_id) REFERENCES posts (post_id)
);

CREATE TABLE posts_images
(
  post_id  UUID NOT NULL ,
  image_id UUID NOT NULL ,
  PRIMARY KEY (post_id, image_id),
  FOREIGN KEY (post_id) REFERENCES posts (post_id),
  FOREIGN KEY (image_id) REFERENCES images (image_id)
);

INSERT INTO security VALUES (1,'public','Everyone reads my post');
INSERT INTO security VALUES (2,'group','Only my friends read my post');
INSERT INTO security VALUES (3,'private','Ony for me this post');

ALTER TABLE posts ALTER COLUMN post_id SET DEFAULT uuid_generate_v4();
ALTER TABLE posts ALTER COLUMN date SET DEFAULT now();
ALTER TABLE posts ALTER COLUMN security_id SET DEFAULT '1';
ALTER TABLE images ALTER COLUMN image_id SET DEFAULT uuid_generate_v4();
ALTER TABLE comments ALTER COLUMN comment_id SET DEFAULT uuid_generate_v4();

INSERT INTO posts (author_id, date, title, post, security_id)
VALUES (uuid_generate_v4(), now(), 'My 34 post','Hello, friend! I like to see you!', 1);
INSERT INTO posts (post_id, author_id, date, title, post, security_id)
VALUES (uuid_generate_v4(), uuid_generate_v4(), now(), 'My first post','Hello, friend! I like to see you!', 1);
INSERT INTO posts (post_id, author_id, date, title, post, security_id)
VALUES (uuid_generate_v4(), uuid_generate_v4(), now(), 'My second post','Topic my second post.', 1);
INSERT INTO posts (post_id, author_id, date, title, post, security_id)
VALUES (uuid_generate_v4(), uuid_generate_v4(), now(), 'My third post','My second post. Topic my third post for my friends.', 2);
INSERT INTO posts (post_id, author_id, date, title, post, security_id)
VALUES (uuid_generate_v4(), uuid_generate_v4(), now(), 'My fourth post','Only for me! Topic my third post for my friends.', 3);


/*
ALTER TABLE post
  ADD CONSTRAINT fk_security
FOREIGN KEY (security)
REFERENCES security (id);
*/