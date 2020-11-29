DROP TABLE if EXISTS post;

CREATE TABLE post (
    post_id BIGSERIAL PRIMARY KEY,
    post_title VARCHAR (100) NOT NULL,
    post_content text NOT NULL,
    post_create_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    post_update_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE comment (
    comment_id BIGSERIAL PRIMARY KEY,
    post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
    comment_content TEXT,
    comment_create_at TIMESTAMP WITHOUT TIME ZONE,
    comment_update_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE tag(
    tag_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) PRIMARY KEY
);

CREATE TABLE post_tag(
    post_id BIGINT REFERENCES post(id) ON DELETE CASCADE,
    tag_id BIGINT REFERENCES tag(tag_id),
    UNIQUE (post_id,tag_name)
);

INSERT INTO post (post_title, post_content, post_create_at, post_update_at) VALUES ('Day 1', 'it is all good', current_date - INTERVAL '2 days', null);
INSERT INTO post (post_title, post_content, post_create_at, post_update_at) VALUES ('Day 2', 'it is all ok', current_date - INTERVAL '1 days', null);
INSERT INTO post (post_title, post_content, post_create_at, post_update_at) VALUES ('Day 3', 'it is all bad', current_date, null);

INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (2, 'Nice!', current_timestamp);
INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (1, 'Awesome!', current_timestamp);
INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (1, 'Excellent!', current_timestamp);
INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (1, 'Wonderful!', current_timestamp);
INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (3, 'Disgusting!', current_timestamp);
INSERT INTO comment (post_id, comment_content, comment_create_at) VALUES (3, 'Atrocious!', current_timestamp);

INSERT INTO tag (name) VALUES ('news');
INSERT INTO tag (name) VALUES ('life');
INSERT INTO tag (name) VALUES ('day');
INSERT INTO tag (name) VALUES ('mood');
INSERT INTO tag (name) VALUES ('ideas');
INSERT INTO tag (name) VALUES ('thoughts');

INSERT INTO post_tag (post_id, tag_id) VALUES (1, 1);
INSERT INTO post_tag (post_id, tag_id) VALUES (1, 2);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 3);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 2);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 1);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 5);
INSERT INTO post_tag (post_id, tag_id) VALUES (3, 3);
INSERT INTO post_tag (post_id, tag_id) VALUES (3, 6);

SELECT post_id, post_title, post_content, post_create_at, post_update_at FROM post;
SELECT post_id, post_title, post_content, post_create_at, post_update_at FROM post WHERE post_id = ?;

INSERT INTO post (post_title, post_content, post_create_at, post_update_at) VALUES (?, ?, ?, ?);

UPDATE post SET post_title = ?, post_content = ?, post_create_at = ?, post_update_at = ? WHERE post_id = ?;

DELETE FROM post WHERE post_id = ?;