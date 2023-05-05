

CREATE TABLE USERS_2(
       ID number,
       FIRSTNAME varchar2(50),
       LASTNAME varchar2(50),
       CONTACT varchar2(50),
       EMAIL varchar2(50),
      IMAGE BLOB
   );
create sequence USERS_2_sequence start with 1 increment by 1;

create or replace trigger USERS_2trg
before insert on USERS_2
for each row
  begin
    select USERS_2_sequence.nextval into :new.id from sys.dual;
    
    end;
    
    select * from USERS_2;
    delete from USERS_2;
    commit;
    
    SELECT ID, FIRSTNAME, LASTNAME, EMAIL, CONTACT,IMAGE FROM USERS_2 WHERE EMAIL='admin';
    
    
    
    CREATE TABLE USERS_CONTENTS(
       ID number,
       USERNAME varchar2(50),
       TITLE varchar2(50),
       BODY varchar2(50),
       CREATED_ON date default sysdate
   );
   
   create sequence USERS_CONTENTS_sequence start with 1 increment by 1;

create or replace trigger USERS_CONTENTStrg
before insert on USERS_CONTENTS
for each row
  begin
    select USERS_CONTENTS_sequence.nextval into :new.id from sys.dual;
    
    end;
    
    
    select * from USERS_CONTENTS;