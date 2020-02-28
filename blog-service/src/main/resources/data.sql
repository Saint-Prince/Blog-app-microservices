DROP TABLE IF EXISTS comment;

DROP TABLE IF EXISTS post;

DROP TABLE IF EXISTS blog;

CREATE TABLE blog(
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject varchar(80) NOT NULL
);

CREATE TABLE post(
    id INT AUTO_INCREMENT PRIMARY KEY,
    writer varchar(50) NOT NULL,
    detail varchar(150) NOT NULL,
    blog_id INT,
    FOREIGN KEY (blog_id) REFERENCES blog(id)
);

CREATE TABLE comment(
    id INT AUTO_INCREMENT PRIMARY KEY,
    writer varchar(50) NOT NULL,
    detail varchar(150) NOT NULL,
    post_id INT,
    FOREIGN KEY (post_id) REFERENCES post(id)
);

-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////
INSERT INTO blog(subject) VALUES    ('Sample Blog 1'),
                                    ('Sample Blog 2'),
                                    ('Sample Blog 3');


INSERT INTO post(writer, detail, blog_id) VALUES    ('Sample writer-1','Sample post detail-1', 1),
                                                    ('Sample writer-2','Sample post detail-2', 1),
                                                    ('Sample writer-3','Sample post detail-3', 1),
                                                    ('Sample writer-1','Sample post detail-4', 2),
                                                    ('Sample writer-5','Sample post detail-5', 3);

INSERT INTO comment(writer, detail, post_id)  VALUES    ('Sample writer-1', 'Sample comment detail-1', 1),
                                                ('Sample writer-2', 'Sample comment detail-2', 1),
                                                ('Sample writer-1', 'Sample comment detail-3', 3),
                                                ('Sample writer-2', 'Sample comment detail-4', 3),
                                                ('Sample writer-2', 'Sample comment detail-5', 4),
                                                ('Sample writer-3', 'Sample comment detail-6', 1),
                                                ('Sample writer-1', 'Sample comment detail-7', 2),
                                                ('Sample writer-1', 'Sample comment detail-8', 2);

