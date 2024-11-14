-- テーブル削除
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS bucket CASCADE;
DROP TABLE IF EXISTS report CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS priority CASCADE;

-- テーブル生成

-- Categoryテーブル
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    category VARCHAR(255) NOT NULL
);

-- Priorityテーブル
CREATE TABLE priority (
    id SERIAL PRIMARY KEY,
    priority VARCHAR(255) NOT NULL
);

-- Usersテーブル
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255),
    age INTEGER,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50),
    role VARCHAR(50) NOT NULL
);

-- Bucketテーブル
CREATE TABLE bucket (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    category_id INTEGER,
    user_id INTEGER,
    budjet INTEGER,
    due_date DATE,
    priority_id INTEGER,
    url VARCHAR(255),
    memo TEXT,
    creation_date DATE,
    achievement BOOLEAN,
    permission BOOLEAN,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (priority_id) REFERENCES priority(id)
);

-- Reportテーブル
CREATE TABLE report (
    id SERIAL PRIMARY KEY,
    report TEXT NOT NULL,
    report_category_id INTEGER,
    report_bucket_id INTEGER ,
    report_user_id INTEGER,
    suspicion_user_id INTEGER,
    FOREIGN KEY (report_bucket_id) REFERENCES bucket(id),
    FOREIGN KEY (report_user_id) REFERENCES users(id),
    FOREIGN KEY (suspicion_user_id) REFERENCES users(id)
);


-- Insert into "category" table
INSERT INTO category (category) VALUES
('Technology'),
('Health'),
('Education'),
('Sports'),
('Music'),
('Travel'),
('Food'),
('Fashion'),
('Business'),
('Entertainment');

-- Insert into "priority" table
INSERT INTO priority (priority) VALUES
('Low'),
('Medium'),
('High'),
('Critical'),
('Must');

-- Insert into "users" table
INSERT INTO users (name, icon, age, email, password, gender, role) VALUES
('John Doe', 'icon_1.png', 28, 'john.doe@example.com', 'password123', 'Male', 'User'),
('Jane Smith', 'icon_2.png', 34, 'jane.smith@example.com', 'password456', 'Female', 'Admin'),
('Mike Johnson', 'icon_3.png', 45, 'mike.johnson@example.com', 'password789', 'Male', 'User'),
('Emily Davis', 'icon_4.png', 22, 'emily.davis@example.com', 'password321', 'Female', 'User'),
('David Brown', 'icon_5.png', 37, 'david.brown@example.com', 'password654', 'Male', 'Admin'),
('Sarah Wilson', 'icon_6.png', 29, 'sarah.wilson@example.com', 'password987', 'Female', 'User'),
('Chris Lee', 'icon_7.png', 31, 'chris.lee@example.com', 'password654', 'Male', 'User'),
('Ashley Harris', 'icon_8.png', 26, 'ashley.harris@example.com', 'password321', 'Female', 'User'),
('James Clark', 'icon_9.png', 40, 'james.clark@example.com', 'password111', 'Male', 'Admin'),
('Sophia Taylor', 'icon_10.png', 33, 'sophia.taylor@example.com', 'password222', 'Female', 'User');

-- Insert into "bucket" table
INSERT INTO bucket (title, image, category_id, user_id, budjet, due_date, priority_id, url, memo, creation_date, achievement, permission) VALUES
('ウェブサイトを作成する', 'website.png', 1, 1, 5000, '2024-12-31', 3, 'https://example.com', 'ポートフォリオウェブサイトを作成する', '2024-11-01', false, false),
('健康診断', 'health.png', 2, 2, 300, '2024-11-30', 2, 'https://health.com', '年次健康診断を受ける', '2024-11-01', false, false),
('Javaを学ぶ', 'java.png', 3, 3, 0, '2025-06-30', 2, 'https://java.com', 'Javaのコースを修了する', '2024-11-02', false, false),
('サッカー練習', 'football.png', 4, 4, 100, '2024-11-15', 1, 'https://football.com', '地域のサッカー試合のために練習する', '2024-11-03', true, false),
('音楽コンサート', 'concert.png', 5, 5, 200, '2024-12-15', 3, 'https://concert.com', '音楽コンサートに参加する', '2024-11-04', false, false),
('東京を訪れる', 'tokyo.png', 6, 6, 1000, '2025-03-31', 2, 'https://tokyo.com', '東京への旅行を計画する', '2024-11-05', false, false),
('夕食を作る', 'food.png', 7, 7, 50, '2024-11-10', 1, 'https://food.com', '特別な夕食を作る', '2024-11-06', false, false),
('新しい服を買う', 'clothes.png', 8, 8, 150, '2024-11-20', 1, 'https://fashion.com', '新しい服を買いに行く', '2024-11-07', true, false),
('ビジネスミーティング', 'meeting.png', 9, 9, 500, '2024-12-01', 4, 'https://business.com', 'ビジネスミーティングに参加する', '2024-11-08', false, false),
('映画の夜', 'movie.png', 10, 10, 30, '2024-11-10', 2, 'https://movie.com', '友達と映画を観る', '2024-11-09', true, false),
('旅行を計画する', 'travel.png', 6, 1, 10000, '2025-03-01', 2, 'https://travel.com', '友人と一緒に海外旅行を計画する', '2024-11-05', false, true),
('新しいギターを買う', 'guitar.png', 7, 2, 1500, '2024-12-20', 1, 'https://guitar.com', 'エレキギターを購入してバンド活動に参加する', '2024-11-06', false, true),
('本を10冊読む', 'books.png', 8, 3, 0, '2025-01-31', 3, 'https://bookstore.com', '年末までに本を10冊読むチャレンジ', '2024-11-07', false, true),
('フィットネスプランを立てる', 'fitness.png', 9, 4, 500, '2024-12-01', 2, 'https://fitness.com', '体力向上のためのフィットネスプランを作成する', '2024-11-08', false, true),
('オンラインコースを受講する', 'online_course.png', 10, 5, 1000, '2025-02-28', 1, 'https://onlinecourse.com', '技術スキルを向上させるためにオンラインコースを受講する', '2024-11-09', false, true);

-- Insert into "report" table
INSERT INTO report (report, report_category_id, report_bucket_id, report_user_id, suspicion_user_id) VALUES
('Report about the website project', 1, 1, 1, 2),
('Report about health checkup', 2, 2, 2, 3),
('Report about Java learning progress', 3, 3, 3, 4);