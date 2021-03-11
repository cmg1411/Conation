insert into region
values (1, current_timestamp, current_timestamp, 0, '서울특별시');
insert into region
values (2, current_timestamp, current_timestamp, 0, '경상도');

insert into city
values (1, current_timestamp, current_timestamp, '서울', 0, 1);
insert into city
values (2, current_timestamp, current_timestamp, '울산', 0, 2);
insert into city
values (3, current_timestamp, current_timestamp, '양산', 0, 2);

insert into advertisement
values (1, current_timestamp, current_timestamp, 'FOOD', '광고1', 1, 10, 10000, 'ACTIVE', 'URL1', 0);
insert into advertisement
values (2, current_timestamp, current_timestamp, 'PRODUCT', '광고2', 2, 10, 10000, 'ACTIVE', 'URL2', 0);
insert into advertisement
values (3, current_timestamp, current_timestamp, 'CULTURE', '광고3', 2, 10, 10000, 'ACTIVE', 'URL3', 0);
insert into advertisement
values (4, current_timestamp, current_timestamp, 'SERVICE', '광고4', 3, 10, 10000, 'ACTIVE', 'URL4', 0);
insert into advertisement
values (5, current_timestamp, current_timestamp, 'FOOD', '광고5', 3, 10, 10000, 'ACTIVE', 'URL5', 0);

insert into user
values (1, current_timestamp, current_timestamp, 0, '닉네임1', '비밀번호1', '010-1111-2222', 'ACTIVE', '유저 찐아이디1', 1);
insert into user
values (2, current_timestamp, current_timestamp, 0, '닉네임2', '비밀번호2', '010-2222-2222', 'ACTIVE', '유저 찐아이디1', 2);
insert into user
values (3, current_timestamp, current_timestamp, 0, '닉네임3', '비밀번호3', '010-3333-2222', 'ACTIVE', '유저 찐아이디1', 3);
insert into user
values (4, current_timestamp, current_timestamp, 0, '닉네임4', '비밀번호4', '010-4444-2222', 'ACTIVE', '유저 찐아이디1', 1);
insert into user
values (5, current_timestamp, current_timestamp, 0, '닉네임5', '비밀번호5', '010-5555-2222', 'ACTIVE', '유저 찐아이디1', 2);


