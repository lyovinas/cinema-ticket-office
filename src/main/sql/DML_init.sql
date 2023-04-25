INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (1, 'admin', localtimestamp, null, null, false, null, null, 'Дэвид Финчер', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (2, 'admin', localtimestamp, null, null, false, null, null, 'Росс Грэйсон Белл', 'Продюсер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (3, 'admin', localtimestamp, null, null, false, null, null, 'Джим Улс', 'Сценарий');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (4, 'admin', localtimestamp, null, null, false, null, null, 'Квентин Тарантино', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (5, 'admin', localtimestamp, null, null, false, null, null, 'Роджер Эвери', 'Сценарий');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (6, 'admin', localtimestamp, null, null, false, null, null, 'Роберт Земекис', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (7, 'admin', localtimestamp, null, null, false, null, null, 'Фрэнк Дарабонт', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (8, 'admin', localtimestamp, null, null, false, null, null, 'Стивен Спилберг', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (9, 'admin', localtimestamp, null, null, false, null, null, 'Сергей Бондарчук', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (10, 'admin', localtimestamp, null, null, false, null, null, 'Марк ДиСалле', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (11, 'admin', localtimestamp, null, null, false, null, null, 'Гай Ричи', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (12, 'admin', localtimestamp, null, null, false, null, null, 'Кристофер Нолан', 'Режиссер');

INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
                                  updated_when, full_name, position)
VALUES (13, 'admin', localtimestamp, null, null, false, null, null, 'Клим Шипенко', 'Режиссер');

-- INSERT INTO public.film_creators (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by,
--                                   updated_when, full_name, position)
-- VALUES (14, 'admin', localtimestamp, null, null, false, null, null, 'Джеймс Кэмерон', 'Режиссер');






INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (1, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Сотрудник страховой компании страдает хронической бессонницей и отчаянно пытается вырваться из мучительно скучной жизни. Однажды в очередной командировке он встречает некоего Тайлера Дёрдена — харизматического торговца мылом с извращенной философией.',
        2, '1.jpg', 1999, 'Бойцовский клуб');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (2, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Двое бандитов Винсент Вега и Джулс Винфилд ведут философские беседы в перерывах между разборками и решением проблем с должниками криминального босса Марселласа Уоллеса.',
        3, '2.jpg', 1994, 'Криминальное чтиво');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (3, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни.',
        2, '3.jpg', 1994, 'Форрест Гамп');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (4, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки.',
        2, '4.jpg', 1994, 'Побег из Шоушенка');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (5, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.',
        2, '5.jpg', 1993, 'Список Шиндлера');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (6, 'admin', localtimestamp, null, null, false, null, null, 'СССР',
        'Июль 1942 года. На подступах к Сталинграду обескровленные, измотанные советские войска ведут тяжелые оборонительные бои, неся огромные потери... Фильм рассказывает о подвиге рядовых солдат, любви к родной земле, об истинной цене победы...',
        4, '6.jpg', 1975, 'Они сражались за Родину');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (7, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Братья Курт и Эрик Слоан отправляются в Таиланд, на родину кикбоксинга, чтобы доказать местным бойцам превосходство американской школы над тайской. Эрик, чемпион Америки по кикбоксингу, встречается в бою с Тонг По, чемпионом Таиланда.',
        5, '7.jpg', 1989, 'Кикбоксер');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (8, 'admin', localtimestamp, null, null, false, null, null, 'Великобритания',
        'Фрэнки Четыре Пальца должен был переправить краденый алмаз из Англии в США своему боссу Эви, но, сделав ставку на подпольный боксерский поединок, он попал в круговорот весьма нежелательных событий.',
        1, '8.jpg', 2000, 'Большой куш');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (9, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному кризису, коллектив исследователей и учёных отправляется сквозь червоточину в путешествие...',
        0, '9.jpg', 2014, 'Интерстеллар');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (10, 'admin', localtimestamp, null, null, false, null, null, 'Россия',
        'Торакальный хирург Женя за месяц должна подготовиться к космическому полету, чтобы отправиться на МКС и спасти заболевшего космонавта. Ей придётся преодолеть неуверенность и страхи, а также провести сложнейшую операцию в условиях невесомости.',
        2, '10.jpg', 2023, 'Вызов');

INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          country, description, genre, poster_file_name, release_year, title)
VALUES (11, 'admin', localtimestamp, null, null, false, null, null, 'США',
        'Подросток Марти с помощью машины времени, сооружённой его другом-профессором доком Брауном, попадает из 80-х в далекие 50-е. Там он встречается со своими будущими родителями, ещё подростками, и другом-профессором, совсем молодым.',
        0, '11.jpg', 1985, 'Назад в будущее');

-- INSERT INTO public.films (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
--                           country, description, genre, poster_file_name, release_year, title)
-- VALUES (12, 'admin', localtimestamp, null, null, false, null, null, 'США',
--         'История противостояния солдата Кайла Риза и киборга-терминатора, прибывших в 1984-й год из пост-апокалиптического будущего, где миром правят машины-убийцы, а человечество находится на грани вымирания.',
--         0, '12.jpg', 1984, 'Терминатор');





INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (1, 1);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (1, 2);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (1, 3);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (2, 4);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (2, 5);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (3, 6);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (4, 7);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (5, 8);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (6, 9);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (7, 10);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (8, 11);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (9, 12);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (10, 13);
INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (11, 6);
-- INSERT INTO public.films_film_creators (film_id, film_creator_id) VALUES (12, 14);






INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (1, 'admin', localtimestamp, null, null, false, null, null, 1, 1);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (2, 'admin', localtimestamp, null, null, false, null, null, 2, 1);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (3, 'admin', localtimestamp, null, null, false, null, null, 3, 1);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (4, 'admin', localtimestamp, null, null, false, null, null, 4, 1);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (5, 'admin', localtimestamp, null, null, false, null, null, 5, 1);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (6, 'admin', localtimestamp, null, null, false, null, null, 1, 2);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (7, 'admin', localtimestamp, null, null, false, null, null, 2, 2);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (8, 'admin', localtimestamp, null, null, false, null, null, 3, 2);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (9, 'admin', localtimestamp, null, null, false, null, null, 4, 2);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (10, 'admin', localtimestamp, null, null, false, null, null, 5, 2);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (11, 'admin', localtimestamp, null, null, false, null, null, 1, 3);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (12, 'admin', localtimestamp, null, null, false, null, null, 2, 3);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (13, 'admin', localtimestamp, null, null, false, null, null, 3, 3);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (14, 'admin', localtimestamp, null, null, false, null, null, 4, 3);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (15, 'admin', localtimestamp, null, null, false, null, null, 5, 3);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (16, 'admin', localtimestamp, null, null, false, null, null, 1, 4);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (17, 'admin', localtimestamp, null, null, false, null, null, 2, 4);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (18, 'admin', localtimestamp, null, null, false, null, null, 3, 4);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (19, 'admin', localtimestamp, null, null, false, null, null, 5, 4);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (20, 'admin', localtimestamp, null, null, false, null, null, 4, 4);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (21, 'admin', localtimestamp, null, null, false, null, null, 1, 5);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (22, 'admin', localtimestamp, null, null, false, null, null, 5, 5);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (23, 'admin', localtimestamp, null, null, false, null, null, 3, 5);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (24, 'admin', localtimestamp, null, null, false, null, null, 4, 5);

INSERT INTO public.seats (id, created_by, created_when, deleted_by, deleted_when, is_deleted, updated_by, updated_when,
                          place, row)
VALUES (25, 'admin', localtimestamp, null, null, false, null, null, 2, 5);

